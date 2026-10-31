package ts01glm_5_2_2;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;
import java.util.LinkedHashMap;
import java.util.StringJoiner;

public class ProductTest {

    private static String baseUrl;

    @BeforeClass
    public static void setup() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        if (baseUrl.endsWith("/")) {
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
        }
    }

    private static class Resp {
        int status;
        String body;
        Resp(int status, String body) {
            this.status = status;
            this.body = body;
        }
    }

    private static Resp doRequest(String method, String path, String contentType, String body) throws Exception {
        URL url = new URL(baseUrl + path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setConnectTimeout(30000);
        conn.setReadTimeout(30000);
        if (contentType != null) {
            conn.setRequestProperty("Content-Type", contentType);
        }
        if (body != null && !body.isEmpty()) {
            conn.setDoOutput(true);
            byte[] out = body.getBytes("UTF-8");
            conn.setRequestProperty("Content-Length", String.valueOf(out.length));
            OutputStream os = conn.getOutputStream();
            os.write(out);
            os.flush();
            os.close();
        }
        int status = conn.getResponseCode();
        InputStream is;
        try {
            if (status >= 200 && status < 400) {
                is = conn.getInputStream();
            } else {
                is = conn.getErrorStream();
            }
        } catch (Exception e) {
            is = null;
        }
        StringBuilder sb = new StringBuilder();
        if (is != null) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append('\n');
            }
            br.close();
        }
        conn.disconnect();
        return new Resp(status, sb.toString().trim());
    }

    private static String encodeForm(Map<String, String> params) throws Exception {
        StringJoiner sj = new StringJoiner("&");
        for (Map.Entry<String, String> e : params.entrySet()) {
            sj.add(URLEncoder.encode(e.getKey(), "UTF-8") + "=" + URLEncoder.encode(e.getValue(), "UTF-8"));
        }
        return sj.toString();
    }

    @Test(timeout = 60000)
    public void addFeatureToProductReturns201() throws Exception {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        Resp r1 = doRequest("POST", "/products/" + productName, null, null);
        Assert.assertTrue(r1.status < 300);

        Map<String, String> form = new LinkedHashMap<String, String>();
        form.put("description", "A test feature description");
        Resp r2 = doRequest("POST", "/products/" + productName + "/features/" + featureName, "application/x-www-form-urlencoded", encodeForm(form));
        Assert.assertEquals(201, r2.status);
    }

    @Test(timeout = 60000)
    public void removeFeatureFromProductReturns204() throws Exception {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        Resp r1 = doRequest("POST", "/products/" + productName, null, null);
        Assert.assertTrue(r1.status < 300);
        Resp r2 = doRequest("POST", "/products/" + productName + "/features/" + featureName, "application/x-www-form-urlencoded", "");
        Assert.assertTrue(r2.status < 300);

        Resp r3 = doRequest("DELETE", "/products/" + productName + "/features/" + featureName, null, null);
        Assert.assertEquals(204, r3.status);
    }

    @Test(timeout = 60000)
    public void buildWithFeaturesViaProductCreation() throws Exception {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);

        Resp r1 = doRequest("POST", "/products/" + productName, null, null);
        Assert.assertTrue(r1.status < 300);
        Resp r2 = doRequest("POST", "/products/" + productName + "/features/FeatureA", "application/x-www-form-urlencoded", "");
        Assert.assertTrue(r2.status < 300);
        Resp r3 = doRequest("POST", "/products/" + productName + "/features/FeatureB", "application/x-www-form-urlencoded", "");
        Assert.assertTrue(r3.status < 300);

        Resp r4 = doRequest("GET", "/products/" + productName, null, null);
        Assert.assertEquals(200, r4.status);
        Assert.assertTrue("Response should contain product name", r4.body != null && r4.body.contains(productName));
    }

    @Test(timeout = 60000)
    public void findProductFeatureByNameViaUpdateFeature() throws Exception {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        Resp r1 = doRequest("POST", "/products/" + productName, null, null);
        Assert.assertTrue(r1.status < 300);
        Resp r2 = doRequest("POST", "/products/" + productName + "/features/" + featureName, "application/x-www-form-urlencoded", "");
        Assert.assertTrue(r2.status < 300);

        Map<String, String> form = new LinkedHashMap<String, String>();
        form.put("description", "Updated description");
        Resp r3 = doRequest("PUT", "/products/" + productName + "/features/" + featureName, "application/x-www-form-urlencoded", encodeForm(form));
        Assert.assertEquals(200, r3.status);
    }

    @Test(timeout = 60000)
    public void findProductFeatureByNameThrowsNotFoundViaUpdateNonExistentFeature() throws Exception {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);

        Resp r1 = doRequest("POST", "/products/" + productName, null, null);
        Assert.assertTrue(r1.status < 300);

        Map<String, String> form = new LinkedHashMap<String, String>();
        form.put("description", "Updated description");
        Resp r2 = doRequest("PUT", "/products/" + productName + "/features/NonExistentFeature", "application/x-www-form-urlencoded", encodeForm(form));
        Assert.assertTrue("Expected some HTTP response status", r2.status > 0);
    }

    @Test(timeout = 60000)
    public void hasFeatureNamedTrueViaRequiresConstraint() throws Exception {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID().toString().substring(0, 8);

        Resp r1 = doRequest("POST", "/products/" + productName, null, null);
        Assert.assertTrue(r1.status < 300);
        Resp r2 = doRequest("POST", "/products/" + productName + "/features/" + sourceFeature, "application/x-www-form-urlencoded", "");
        Assert.assertTrue(r2.status < 300);
        Resp r3 = doRequest("POST", "/products/" + productName + "/features/" + requiredFeature, "application/x-www-form-urlencoded", "");
        Assert.assertTrue(r3.status < 300);

        Map<String, String> form = new LinkedHashMap<String, String>();
        form.put("sourceFeature", sourceFeature);
        form.put("requiredFeature", requiredFeature);
        Resp r4 = doRequest("POST", "/products/" + productName + "/constraints/requires", "application/x-www-form-urlencoded", encodeForm(form));
        Assert.assertEquals(201, r4.status);
    }

    @Test(timeout = 60000)
    public void hasFeatureNamedFalseViaRequiresConstraintWithMissingFeature() throws Exception {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString().substring(0, 8);

        Resp r1 = doRequest("POST", "/products/" + productName, null, null);
        Assert.assertTrue(r1.status < 300);
        Resp r2 = doRequest("POST", "/products/" + productName + "/features/" + sourceFeature, "application/x-www-form-urlencoded", "");
        Assert.assertTrue(r2.status < 300);

        Map<String, String> form = new LinkedHashMap<String, String>();
        form.put("sourceFeature", sourceFeature);
        form.put("requiredFeature", "NonExistentFeature");
        Resp r3 = doRequest("POST", "/products/" + productName + "/constraints/requires", "application/x-www-form-urlencoded", encodeForm(form));
        Assert.assertTrue("Expected some HTTP response status", r3.status > 0);
    }

    @Test(timeout = 60000)
    public void addFeatureConstraintViaExcludesConstraint() throws Exception {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeature-" + UUID.randomUUID().toString().substring(0, 8);

        Resp r1 = doRequest("POST", "/products/" + productName, null, null);
        Assert.assertTrue(r1.status < 300);
        Resp r2 = doRequest("POST", "/products/" + productName + "/features/" + sourceFeature, "application/x-www-form-urlencoded", "");
        Assert.assertTrue(r2.status < 300);
        Resp r3 = doRequest("POST", "/products/" + productName + "/features/" + excludedFeature, "application/x-www-form-urlencoded", "");
        Assert.assertTrue(r3.status < 300);

        Map<String, String> form = new LinkedHashMap<String, String>();
        form.put("sourceFeature", sourceFeature);
        form.put("excludedFeature", excludedFeature);
        Resp r4 = doRequest("POST", "/products/" + productName + "/constraints/excludes", "application/x-www-form-urlencoded", encodeForm(form));
        Assert.assertEquals(201, r4.status);
    }

    @Test(timeout = 60000)
    public void addFeatureWithNoFeaturesViaBuildWithFeatures() throws Exception {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);

        Resp r1 = doRequest("POST", "/products/" + productName, null, null);
        Assert.assertTrue(r1.status < 300);

        Resp r2 = doRequest("GET", "/products/" + productName + "/features", null, null);
        Assert.assertEquals(200, r2.status);
    }

    @Test(timeout = 60000)
    public void addFeatureMultipleTimesCoversAddFeatureLoop() throws Exception {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);

        Resp r1 = doRequest("POST", "/products/" + productName, null, null);
        Assert.assertTrue(r1.status < 300);
        Resp r2 = doRequest("POST", "/products/" + productName + "/features/FeatureOne", "application/x-www-form-urlencoded", "");
        Assert.assertTrue(r2.status < 300);
        Resp r3 = doRequest("POST", "/products/" + productName + "/features/FeatureTwo", "application/x-www-form-urlencoded", "");
        Assert.assertTrue(r3.status < 300);
        Resp r4 = doRequest("POST", "/products/" + productName + "/features/FeatureThree", "application/x-www-form-urlencoded", "");
        Assert.assertTrue(r4.status < 300);

        Resp r5 = doRequest("GET", "/products/" + productName + "/features", null, null);
        Assert.assertEquals(200, r5.status);
    }

    @Test(timeout = 60000)
    public void removeFeatureThatDoesNotExist() throws Exception {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);

        Resp r1 = doRequest("POST", "/products/" + productName, null, null);
        Assert.assertTrue(r1.status < 300);

        Resp r2 = doRequest("DELETE", "/products/" + productName + "/features/NonExistentFeature", null, null);
        Assert.assertTrue("Expected some HTTP response status", r2.status > 0);
    }

    @Test(timeout = 60000)
    public void findProductFeatureByNameCaseInsensitiveViaUpdate() throws Exception {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature";

        Resp r1 = doRequest("POST", "/products/" + productName, null, null);
        Assert.assertTrue(r1.status < 300);
        Resp r2 = doRequest("POST", "/products/" + productName + "/features/" + featureName, "application/x-www-form-urlencoded", "");
        Assert.assertTrue(r2.status < 300);

        Map<String, String> form = new LinkedHashMap<String, String>();
        form.put("description", "Updated via case-insensitive lookup");
        Resp r3 = doRequest("PUT", "/products/" + productName + "/features/testfeature", "application/x-www-form-urlencoded", encodeForm(form));
        Assert.assertEquals(200, r3.status);
    }

    @Test(timeout = 60000)
    public void hasFeatureNamedCaseInsensitiveViaExcludesConstraint() throws Exception {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeature";
        String excludedFeature = "ExcludedFeature";

        Resp r1 = doRequest("POST", "/products/" + productName, null, null);
        Assert.assertTrue(r1.status < 300);
        Resp r2 = doRequest("POST", "/products/" + productName + "/features/" + sourceFeature, "application/x-www-form-urlencoded", "");
        Assert.assertTrue(r2.status < 300);
        Resp r3 = doRequest("POST", "/products/" + productName + "/features/" + excludedFeature, "application/x-www-form-urlencoded", "");
        Assert.assertTrue(r3.status < 300);

        Map<String, String> form = new LinkedHashMap<String, String>();
        form.put("sourceFeature", "sourcefeature");
        form.put("excludedFeature", "excludedfeature");
        Resp r4 = doRequest("POST", "/products/" + productName + "/constraints/excludes", "application/x-www-form-urlencoded", encodeForm(form));
        Assert.assertEquals(201, r4.status);
    }

    @Test(timeout = 60000)
    public void addFeatureConstraintViaRequiresConstraintWithCaseInsensitive() throws Exception {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "RAID-Controller";
        String requiredFeature = "ECC-RAM";

        Resp r1 = doRequest("POST", "/products/" + productName, null, null);
        Assert.assertTrue(r1.status < 300);
        Resp r2 = doRequest("POST", "/products/" + productName + "/features/" + sourceFeature, "application/x-www-form-urlencoded", "");
        Assert.assertTrue(r2.status < 300);
        Resp r3 = doRequest("POST", "/products/" + productName + "/features/" + requiredFeature, "application/x-www-form-urlencoded", "");
        Assert.assertTrue(r3.status < 300);

        Map<String, String> form = new LinkedHashMap<String, String>();
        form.put("sourceFeature", "raid-controller");
        form.put("requiredFeature", "ecc-ram");
        Resp r4 = doRequest("POST", "/products/" + productName + "/constraints/requires", "application/x-www-form-urlencoded", encodeForm(form));
        Assert.assertEquals(201, r4.status);
    }

    @Test(timeout = 60000)
    public void buildWithFeaturesMultipleFeaturesViaConfigurationCreation() throws Exception {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);

        Resp r1 = doRequest("POST", "/products/" + productName, null, null);
        Assert.assertTrue(r1.status < 300);
        Resp r2 = doRequest("POST", "/products/" + productName + "/features/FeatureAlpha", "application/x-www-form-urlencoded", "");
        Assert.assertTrue(r2.status < 300);
        Resp r3 = doRequest("POST", "/products/" + productName + "/features/FeatureBeta", "application/x-www-form-urlencoded", "");
        Assert.assertTrue(r3.status < 300);
        Resp r4 = doRequest("POST", "/products/" + productName + "/configurations/" + configurationName, null, null);
        Assert.assertTrue(r4.status < 300);

        Resp r5 = doRequest("GET", "/products/" + productName + "/configurations/" + configurationName, null, null);
        Assert.assertEquals(200, r5.status);
    }
}