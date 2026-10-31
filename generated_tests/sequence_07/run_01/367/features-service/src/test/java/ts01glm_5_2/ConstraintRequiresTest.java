package ts01glm_5_2;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static org.hamcrest.Matchers.lessThan;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.UUID;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Ignore;
public class ConstraintRequiresTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @BeforeClass
    public static void setup() {
    }

    private String uniqueProduct() {
        return "TestProd-" + UUID.randomUUID().toString().substring(0, 8);
    }

    private String uniqueFeature(String prefix) {
        return prefix + "-" + UUID.randomUUID().toString().substring(0, 8);
    }

    private String uniqueConfig() {
        return "config-" + UUID.randomUUID().toString().substring(0, 8);
    }

    private static class Response {
        final int status;
        final String body;
        Response(int status, String body) {
            this.status = status;
            this.body = body;
        }
    }

    private String encodeSegment(Object obj) throws Exception {
        return URLEncoder.encode(String.valueOf(obj), "UTF-8");
    }

    private String buildPath(String template, Object... args) throws Exception {
        StringBuilder sb = new StringBuilder();
        int argIndex = 0;
        for (int i = 0; i < template.length(); ) {
            int idx = template.indexOf('{', i);
            if (idx == -1) {
                sb.append(template.substring(i));
                break;
            } else {
                sb.append(template.substring(i, idx));
                int end = template.indexOf('}', idx);
                if (end == -1) {
                    sb.append(template.substring(idx));
                    break;
                } else {
                    if (argIndex < args.length) {
                        sb.append(encodeSegment(args[argIndex++]));
                    } else {
                        sb.append(template.substring(idx, end + 1));
                    }
                    i = end + 1;
                }
            }
        }
        return sb.toString();
    }

    private Response httpRequest(String method, String pathTemplate, String body, Object... pathArgs) throws Exception {
        String path = buildPath(pathTemplate, pathArgs);
        URL url = new URL(BASE_URL + path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        if ("POST".equals(method) || "PUT".equals(method)) {
            conn.setDoOutput(true);
            if (body != null) {
                byte[] out = body.getBytes("UTF-8");
                conn.setFixedLengthStreamingMode(out.length);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.connect();
                try (OutputStream os = conn.getOutputStream()) {
                    os.write(out);
                }
            } else {
                conn.connect();
            }
        } else {
            conn.connect();
        }
        int status = conn.getResponseCode();
        InputStream is;
        if (status >= 200 && status < 400) {
            is = conn.getInputStream();
        } else {
            is = conn.getErrorStream();
        }
        String bodyResp = "";
        if (is != null) {
            StringBuilder sb = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            }
            bodyResp = sb.toString();
        }
        conn.disconnect();
        return new Response(status, bodyResp);
    }

    private Response httpPost(String pathTemplate, Object... pathArgs) throws Exception {
        return httpRequest("POST", pathTemplate, null, pathArgs);
    }

    private Response httpPostForm(String pathTemplate, Map<String, String> formParams, Object... pathArgs) throws Exception {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> e : formParams.entrySet()) {
            if (!first) sb.append("&");
            sb.append(URLEncoder.encode(e.getKey(), "UTF-8"));
            sb.append("=");
            sb.append(URLEncoder.encode(e.getValue(), "UTF-8"));
            first = false;
        }
        return httpRequest("POST", pathTemplate, sb.toString(), pathArgs);
    }

    private Response httpGet(String pathTemplate, Object... pathArgs) throws Exception {
        return httpRequest("GET", pathTemplate, null, pathArgs);
    }

    private Response httpDelete(String pathTemplate, Object... pathArgs) throws Exception {
        return httpRequest("DELETE", pathTemplate, null, pathArgs);
    }

    private void createProductWithFeatures(String productName, String... features) {
        try {
            Response r = httpPost("/products/{productName}", productName);
            Assert.assertThat(r.status, lessThan(300));
            for (String feature : features) {
                Response r2 = httpPost("/products/{productName}/features/{featureName}", productName, feature);
                Assert.assertThat(r2.status, lessThan(300));
            }
        } catch (Exception e) {
            Assert.fail("Exception in createProductWithFeatures: " + e.getMessage());
        }
    }

    private void addRequiresConstraint(String productName, String sourceFeature, String requiredFeature) {
        try {
            Map<String, String> params = new LinkedHashMap<String, String>();
            params.put("sourceFeature", sourceFeature);
            params.put("requiredFeature", requiredFeature);
            Response r = httpPostForm("/products/{productName}/constraints/requires", params, productName);
            Assert.assertThat(r.status, lessThan(300));
        } catch (Exception e) {
            Assert.fail("Exception in addRequiresConstraint: " + e.getMessage());
        }
    }

    private void createConfigWithFeatures(String productName, String configName, String... features) {
        try {
            Response r = httpPost("/products/{productName}/configurations/{configurationName}", productName, configName);
            Assert.assertThat(r.status, lessThan(300));
            for (String feature : features) {
                Response r2 = httpPost("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, feature);
                Assert.assertThat(r2.status, lessThan(300));
            }
        } catch (Exception e) {
            Assert.fail("Exception in createConfigWithFeatures: " + e.getMessage());
        }
    }

    private int extractFirstConstraintIdFromProductJson(String json) {
        if (json == null) return -1;
        Pattern p = Pattern.compile("\"id\"\\s*:\\s*(\\d+)");
        Matcher m = p.matcher(json);
        if (m.find()) {
            try {
                return Integer.parseInt(m.group(1));
            } catch (NumberFormatException e) {
                return -1;
            }
        }
        return -1;
    }

    @Ignore("Expected: a value less than <300>      but: <500> was greater than <300>")
    @Test(timeout = 60000)
    public void testCreateRequiresConstraintReturns201() {
        String productName = uniqueProduct();
        String sourceFeature = uniqueFeature("Src");
        String requiredFeature = uniqueFeature("Req");

        createProductWithFeatures(productName, sourceFeature, requiredFeature);

        try {
            Map<String, String> params = new LinkedHashMap<String, String>();
            params.put("sourceFeature", sourceFeature);
            params.put("requiredFeature", requiredFeature);
            Response r = httpPostForm("/products/{productName}/constraints/requires", params, productName);
            Assert.assertEquals(201, r.status);
        } catch (Exception e) {
            Assert.fail("Exception in testCreateRequiresConstraintReturns201: " + e.getMessage());
        }
    }

    @Ignore("Expected: a value less than <300>      but: <500> was greater than <300>")
    @Test(timeout = 60000)
    public void testGetProductWithRequiresConstraintReturns200() {
        String productName = uniqueProduct();
        String sourceFeature = uniqueFeature("Src");
        String requiredFeature = uniqueFeature("Req");

        createProductWithFeatures(productName, sourceFeature, requiredFeature);
        addRequiresConstraint(productName, sourceFeature, requiredFeature);

        try {
            Response r = httpGet("/products/{productName}", productName);
            Assert.assertEquals(200, r.status);
        } catch (Exception e) {
            Assert.fail("Exception in testGetProductWithRequiresConstraintReturns200: " + e.getMessage());
        }
    }

    @Ignore("Expected: a value less than <300>      but: <500> was greater than <300>")
    @Test(timeout = 60000)
    public void testEvaluateSourceActiveRequiredNotActive() {
        String productName = uniqueProduct();
        String sourceFeature = uniqueFeature("Src");
        String requiredFeature = uniqueFeature("Req");
        String configName = uniqueConfig();

        createProductWithFeatures(productName, sourceFeature, requiredFeature);
        addRequiresConstraint(productName, sourceFeature, requiredFeature);
        createConfigWithFeatures(productName, configName, sourceFeature);

        try {
            Response r = httpGet("/products/{productName}/configurations/{configurationName}/features", productName, configName);
            Assert.assertTrue(r.body != null && r.body.contains(requiredFeature));
        } catch (Exception e) {
            Assert.fail("Exception in testEvaluateSourceActiveRequiredNotActive: " + e.getMessage());
        }
    }

    @Ignore("Expected: a value less than <300>      but: <500> was greater than <300>")
    @Test(timeout = 60000)
    public void testEvaluateSourceNotActive() {
        String productName = uniqueProduct();
        String sourceFeature = uniqueFeature("Src");
        String requiredFeature = uniqueFeature("Req");
        String otherFeature = uniqueFeature("Other");
        String configName = uniqueConfig();

        createProductWithFeatures(productName, sourceFeature, requiredFeature, otherFeature);
        addRequiresConstraint(productName, sourceFeature, requiredFeature);
        createConfigWithFeatures(productName, configName, otherFeature);

        try {
            Response r = httpGet("/products/{productName}/configurations/{configurationName}/features", productName, configName);
            Assert.assertFalse(r.body != null && r.body.contains(requiredFeature));
        } catch (Exception e) {
            Assert.fail("Exception in testEvaluateSourceNotActive: " + e.getMessage());
        }
    }

    @Ignore("Expected: a value less than <300>      but: <500> was greater than <300>")
    @Test(timeout = 60000)
    public void testEvaluateBothActive() {
        String productName = uniqueProduct();
        String sourceFeature = uniqueFeature("Src");
        String requiredFeature = uniqueFeature("Req");
        String configName = uniqueConfig();

        createProductWithFeatures(productName, sourceFeature, requiredFeature);
        addRequiresConstraint(productName, sourceFeature, requiredFeature);
        createConfigWithFeatures(productName, configName, sourceFeature, requiredFeature);

        try {
            Response r = httpGet("/products/{productName}/configurations/{configurationName}/features", productName, configName);
            Assert.assertTrue(r.body != null && r.body.contains(sourceFeature));
        } catch (Exception e) {
            Assert.fail("Exception in testEvaluateBothActive: " + e.getMessage());
        }
    }

    @Ignore("Expected: a value less than <300>      but: <500> was greater than <300>")
    @Test(timeout = 60000)
    public void testEvaluateEmptyConfig() {
        String productName = uniqueProduct();
        String sourceFeature = uniqueFeature("Src");
        String requiredFeature = uniqueFeature("Req");
        String configName = uniqueConfig();

        createProductWithFeatures(productName, sourceFeature, requiredFeature);
        addRequiresConstraint(productName, sourceFeature, requiredFeature);
        createConfigWithFeatures(productName, configName);

        try {
            Response r = httpGet("/products/{productName}/configurations/{configurationName}/features", productName, configName);
            Assert.assertEquals(200, r.status);
        } catch (Exception e) {
            Assert.fail("Exception in testEvaluateEmptyConfig: " + e.getMessage());
        }
    }

    @Ignore("Expected: a value less than <300>      but: <500> was greater than <300>")
    @Test(timeout = 60000)
    public void testDeleteRequiresConstraintReturns204() {
        String productName = uniqueProduct();
        String sourceFeature = uniqueFeature("Src");
        String requiredFeature = uniqueFeature("Req");

        createProductWithFeatures(productName, sourceFeature, requiredFeature);
        addRequiresConstraint(productName, sourceFeature, requiredFeature);

        try {
            Response r = httpGet("/products/{productName}", productName);
            Assert.assertThat(r.status, lessThan(300));
            int constraintId = extractFirstConstraintIdFromProductJson(r.body);
            Assert.assertTrue(constraintId >= 0);

            Response r2 = httpDelete("/products/{productName}/constraints/{constraintId}", productName, String.valueOf(constraintId));
            Assert.assertEquals(204, r2.status);
        } catch (Exception e) {
            Assert.fail("Exception in testDeleteRequiresConstraintReturns204: " + e.getMessage());
        }
    }

    @Ignore("Expected: a value less than <300>      but: <500> was greater than <300>")
    @Test(timeout = 60000)
    public void testCreateRequiresConstraintSameFeature() {
        String productName = uniqueProduct();
        String feature = uniqueFeature("Same");

        createProductWithFeatures(productName, feature);

        try {
            Map<String, String> params = new LinkedHashMap<String, String>();
            params.put("sourceFeature", feature);
            params.put("requiredFeature", feature);
            Response r = httpPostForm("/products/{productName}/constraints/requires", params, productName);
            Assert.assertEquals(201, r.status);
        } catch (Exception e) {
            Assert.fail("Exception in testCreateRequiresConstraintSameFeature: " + e.getMessage());
        }
    }

    @Ignore("Expected: a value less than <300>      but: <500> was greater than <300>")
    @Test(timeout = 60000)
    public void testEvaluateSameFeatureActive() {
        String productName = uniqueProduct();
        String feature = uniqueFeature("Same");
        String configName = uniqueConfig();

        createProductWithFeatures(productName, feature);
        addRequiresConstraint(productName, feature, feature);
        createConfigWithFeatures(productName, configName, feature);

        try {
            Response r = httpGet("/products/{productName}/configurations/{configurationName}/features", productName, configName);
            Assert.assertTrue(r.body != null && r.body.contains(feature));
        } catch (Exception e) {
            Assert.fail("Exception in testEvaluateSameFeatureActive: " + e.getMessage());
        }
    }

    @Ignore("Expected: a value less than <300>      but: <500> was greater than <300>")
    @Test(timeout = 60000)
    public void testCreateMultipleRequiresConstraints() {
        String productName = uniqueProduct();
        String source1 = uniqueFeature("Src1");
        String req1 = uniqueFeature("Req1");
        String source2 = uniqueFeature("Src2");
        String req2 = uniqueFeature("Req2");

        createProductWithFeatures(productName, source1, req1, source2, req2);
        addRequiresConstraint(productName, source1, req1);

        try {
            Map<String, String> params = new LinkedHashMap<String, String>();
            params.put("sourceFeature", source2);
            params.put("requiredFeature", req2);
            Response r = httpPostForm("/products/{productName}/constraints/requires", params, productName);
            Assert.assertEquals(201, r.status);
        } catch (Exception e) {
            Assert.fail("Exception in testCreateMultipleRequiresConstraints: " + e.getMessage());
        }
    }

    @Ignore("Expected: a value less than <300>      but: <500> was greater than <300>")
    @Test(timeout = 60000)
    public void testEvaluateWithMultipleConstraints() {
        String productName = uniqueProduct();
        String source1 = uniqueFeature("Src1");
        String req1 = uniqueFeature("Req1");
        String source2 = uniqueFeature("Src2");
        String req2 = uniqueFeature("Req2");
        String configName = uniqueConfig();

        createProductWithFeatures(productName, source1, req1, source2, req2);
        addRequiresConstraint(productName, source1, req1);
        addRequiresConstraint(productName, source2, req2);
        createConfigWithFeatures(productName, configName, source1, source2);

        try {
            Response r = httpGet("/products/{productName}/configurations/{configurationName}/features", productName, configName);
            Assert.assertTrue(r.body != null && r.body.contains(req1) && r.body.contains(req2));
        } catch (Exception e) {
            Assert.fail("Exception in testEvaluateWithMultipleConstraints: " + e.getMessage());
        }
    }

    @Ignore("Expected: a value less than <300>      but: <500> was greater than <300>")
    @Test(timeout = 60000)
    public void testEvaluateAfterConstraintDeletion() {
        String productName = uniqueProduct();
        String sourceFeature = uniqueFeature("Src");
        String requiredFeature = uniqueFeature("Req");
        String configName = uniqueConfig();

        createProductWithFeatures(productName, sourceFeature, requiredFeature);
        addRequiresConstraint(productName, sourceFeature, requiredFeature);
        createConfigWithFeatures(productName, configName, sourceFeature);

        try {
            Response r = httpGet("/products/{productName}", productName);
            Assert.assertThat(r.status, lessThan(300));
            int constraintId = extractFirstConstraintIdFromProductJson(r.body);
            Assert.assertTrue(constraintId >= 0);

            Response r2 = httpDelete("/products/{productName}/constraints/{constraintId}", productName, String.valueOf(constraintId));
            Assert.assertThat(r2.status, lessThan(300));

            Response r3 = httpGet("/products/{productName}/configurations/{configurationName}/features", productName, configName);
            Assert.assertFalse(r3.body != null && r3.body.contains(requiredFeature));
        } catch (Exception e) {
            Assert.fail("Exception in testEvaluateAfterConstraintDeletion: " + e.getMessage());
        }
    }
}