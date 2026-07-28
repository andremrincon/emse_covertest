package ts01glm_5_2;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.UUID;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.StringJoiner;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class ConstraintExcludesTest {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Ignore("For input string: \"8080products\"")
    @Test(timeout = 60000)
    public void testCreateExcludesConstraintReturns201() throws Exception {
        String productName = "Prod-" + UUID.randomUUID();
        String sourceFeature = "Src-" + UUID.randomUUID();
        String excludedFeature = "Exc-" + UUID.randomUUID();

        Response r1 = post("/products/{productName}", null, productName);
        Assert.assertThat(r1.statusCode, lessThan(300));

        Response r2 = post("/products/{productName}/features/{featureName}", null, productName, sourceFeature);
        Assert.assertThat(r2.statusCode, lessThan(300));

        Response r3 = post("/products/{productName}/features/{featureName}", null, productName, excludedFeature);
        Assert.assertThat(r3.statusCode, lessThan(300));

        Map<String,String> form = new LinkedHashMap<String,String>();
        form.put("sourceFeature", sourceFeature);
        form.put("excludedFeature", excludedFeature);

        Response r4 = postForm("/products/{productName}/constraints/excludes", form, productName);
        Assert.assertEquals(201, r4.statusCode);
    }

    @Ignore("For input string: \"8080products\"")
    @Test(timeout = 60000)
    public void testGetProductReturnsExcludesConstraintType() throws Exception {
        String productName = "Prod-" + UUID.randomUUID();
        String sourceFeature = "Src-" + UUID.randomUUID();
        String excludedFeature = "Exc-" + UUID.randomUUID();

        Response p1 = post("/products/{productName}", null, productName);
        Assert.assertThat(p1.statusCode, lessThan(300));
        Response p2 = post("/products/{productName}/features/{featureName}", null, productName, sourceFeature);
        Assert.assertThat(p2.statusCode, lessThan(300));
        Response p3 = post("/products/{productName}/features/{featureName}", null, productName, excludedFeature);
        Assert.assertThat(p3.statusCode, lessThan(300));

        Map<String,String> form = new LinkedHashMap<String,String>();
        form.put("sourceFeature", sourceFeature);
        form.put("excludedFeature", excludedFeature);

        Response created = postForm("/products/{productName}/constraints/excludes", form, productName);
        Assert.assertThat(created.statusCode, lessThan(300));

        Response getProduct = get("/products/{productName}", productName);
        String body = getProduct.body != null ? getProduct.body : "";
        boolean hasExcludes = body.contains("\"type\":\"excludes\"") || body.contains("\"type\": \"excludes\"") || body.contains("\"excludes\"");
        Assert.assertTrue(hasExcludes);
    }

    @Ignore("For input string: \"8080products\"")
    @Test(timeout = 60000)
    public void testEvaluateConfigurationBothFeaturesActiveIsInvalid() throws Exception {
        String productName = "Prod-" + UUID.randomUUID();
        String configName = "Cfg-" + UUID.randomUUID();
        String sourceFeature = "Src-" + UUID.randomUUID();
        String excludedFeature = "Exc-" + UUID.randomUUID();

        Response p1 = post("/products/{productName}", null, productName);
        Assert.assertThat(p1.statusCode, lessThan(300));
        Response p2 = post("/products/{productName}/features/{featureName}", null, productName, sourceFeature);
        Assert.assertThat(p2.statusCode, lessThan(300));
        Response p3 = post("/products/{productName}/features/{featureName}", null, productName, excludedFeature);
        Assert.assertThat(p3.statusCode, lessThan(300));

        Map<String,String> form = new LinkedHashMap<String,String>();
        form.put("sourceFeature", sourceFeature);
        form.put("excludedFeature", excludedFeature);

        Response c = postForm("/products/{productName}/constraints/excludes", form, productName);
        Assert.assertThat(c.statusCode, lessThan(300));
        Response c2 = post("/products/{productName}/configurations/{configurationName}", null, productName, configName);
        Assert.assertThat(c2.statusCode, lessThan(300));
        Response c3 = post("/products/{productName}/configurations/{configurationName}/features/{featureName}", null, productName, configName, sourceFeature);
        Assert.assertThat(c3.statusCode, lessThan(300));
        Response c4 = post("/products/{productName}/configurations/{configurationName}/features/{featureName}", null, productName, configName, excludedFeature);
        Assert.assertThat(c4.statusCode, lessThan(300));

        Response eval = get("/products/{productName}/configurations/{configurationName}", productName, configName);
        String body = eval.body != null ? eval.body : "";
        boolean validIsFalse = body.contains("\"valid\":false") || body.contains("\"valid\": false");
        Assert.assertTrue(validIsFalse);
    }

    @Ignore("For input string: \"8080products\"")
    @Test(timeout = 60000)
    public void testEvaluateConfigurationOnlySourceActiveIsValid() throws Exception {
        String productName = "Prod-" + UUID.randomUUID();
        String configName = "Cfg-" + UUID.randomUUID();
        String sourceFeature = "Src-" + UUID.randomUUID();
        String excludedFeature = "Exc-" + UUID.randomUUID();

        Response p1 = post("/products/{productName}", null, productName);
        Assert.assertThat(p1.statusCode, lessThan(300));
        Response p2 = post("/products/{productName}/features/{featureName}", null, productName, sourceFeature);
        Assert.assertThat(p2.statusCode, lessThan(300));
        Response p3 = post("/products/{productName}/features/{featureName}", null, productName, excludedFeature);
        Assert.assertThat(p3.statusCode, lessThan(300));

        Map<String,String> form = new LinkedHashMap<String,String>();
        form.put("sourceFeature", sourceFeature);
        form.put("excludedFeature", excludedFeature);

        Response c = postForm("/products/{productName}/constraints/excludes", form, productName);
        Assert.assertThat(c.statusCode, lessThan(300));
        Response c2 = post("/products/{productName}/configurations/{configurationName}", null, productName, configName);
        Assert.assertThat(c2.statusCode, lessThan(300));
        Response c3 = post("/products/{productName}/configurations/{configurationName}/features/{featureName}", null, productName, configName, sourceFeature);
        Assert.assertThat(c3.statusCode, lessThan(300));

        Response eval = get("/products/{productName}/configurations/{configurationName}", productName, configName);
        String body = eval.body != null ? eval.body : "";
        boolean validIsTrue = body.contains("\"valid\":true") || body.contains("\"valid\": true");
        Assert.assertTrue(validIsTrue);
    }

    @Ignore("For input string: \"8080products\"")
    @Test(timeout = 60000)
    public void testEvaluateConfigurationOnlyExcludedActiveIsValid() throws Exception {
        String productName = "Prod-" + UUID.randomUUID();
        String configName = "Cfg-" + UUID.randomUUID();
        String sourceFeature = "Src-" + UUID.randomUUID();
        String excludedFeature = "Exc-" + UUID.randomUUID();

        Response p1 = post("/products/{productName}", null, productName);
        Assert.assertThat(p1.statusCode, lessThan(300));
        Response p2 = post("/products/{productName}/features/{featureName}", null, productName, sourceFeature);
        Assert.assertThat(p2.statusCode, lessThan(300));
        Response p3 = post("/products/{productName}/features/{featureName}", null, productName, excludedFeature);
        Assert.assertThat(p3.statusCode, lessThan(300));

        Map<String,String> form = new LinkedHashMap<String,String>();
        form.put("sourceFeature", sourceFeature);
        form.put("excludedFeature", excludedFeature);

        Response c = postForm("/products/{productName}/constraints/excludes", form, productName);
        Assert.assertThat(c.statusCode, lessThan(300));
        Response c2 = post("/products/{productName}/configurations/{configurationName}", null, productName, configName);
        Assert.assertThat(c2.statusCode, lessThan(300));
        Response c3 = post("/products/{productName}/configurations/{configurationName}/features/{featureName}", null, productName, configName, excludedFeature);
        Assert.assertThat(c3.statusCode, lessThan(300));

        Response eval = get("/products/{productName}/configurations/{configurationName}", productName, configName);
        String body = eval.body != null ? eval.body : "";
        boolean validIsTrue = body.contains("\"valid\":true") || body.contains("\"valid\": true");
        Assert.assertTrue(validIsTrue);
    }

    @Ignore("For input string: \"8080products\"")
    @Test(timeout = 60000)
    public void testEvaluateConfigurationNeitherFeatureActiveIsValid() throws Exception {
        String productName = "Prod-" + UUID.randomUUID();
        String configName = "Cfg-" + UUID.randomUUID();
        String sourceFeature = "Src-" + UUID.randomUUID();
        String excludedFeature = "Exc-" + UUID.randomUUID();

        Response p1 = post("/products/{productName}", null, productName);
        Assert.assertThat(p1.statusCode, lessThan(300));
        Response p2 = post("/products/{productName}/features/{featureName}", null, productName, sourceFeature);
        Assert.assertThat(p2.statusCode, lessThan(300));
        Response p3 = post("/products/{productName}/features/{featureName}", null, productName, excludedFeature);
        Assert.assertThat(p3.statusCode, lessThan(300));

        Map<String,String> form = new LinkedHashMap<String,String>();
        form.put("sourceFeature", sourceFeature);
        form.put("excludedFeature", excludedFeature);

        Response c = postForm("/products/{productName}/constraints/excludes", form, productName);
        Assert.assertThat(c.statusCode, lessThan(300));
        Response c2 = post("/products/{productName}/configurations/{configurationName}", null, productName, configName);
        Assert.assertThat(c2.statusCode, lessThan(300));

        Response eval = get("/products/{productName}/configurations/{configurationName}", productName, configName);
        String body = eval.body != null ? eval.body : "";
        boolean validIsTrue = body.contains("\"valid\":true") || body.contains("\"valid\": true");
        Assert.assertTrue(validIsTrue);
    }

    @Ignore("For input string: \"8080products\"")
    @Test(timeout = 60000)
    public void testDeleteExcludesConstraintReturns204() throws Exception {
        String productName = "Prod-" + UUID.randomUUID();
        String sourceFeature = "Src-" + UUID.randomUUID();
        String excludedFeature = "Exc-" + UUID.randomUUID();

        Response p1 = post("/products/{productName}", null, productName);
        Assert.assertThat(p1.statusCode, lessThan(300));
        Response p2 = post("/products/{productName}/features/{featureName}", null, productName, sourceFeature);
        Assert.assertThat(p2.statusCode, lessThan(300));
        Response p3 = post("/products/{productName}/features/{featureName}", null, productName, excludedFeature);
        Assert.assertThat(p3.statusCode, lessThan(300));

        Map<String,String> form = new LinkedHashMap<String,String>();
        form.put("sourceFeature", sourceFeature);
        form.put("excludedFeature", excludedFeature);

        Response created = postForm("/products/{productName}/constraints/excludes", form, productName);
        Assert.assertThat(created.statusCode, lessThan(300));
        String id = extractId(created.body);
        Assert.assertNotNull(id);

        Response del = delete("/products/{productName}/constraints/{constraintId}", productName, id);
        Assert.assertEquals(204, del.statusCode);
    }

    private static String extractId(String body) {
        if (body == null) return null;
        int idx = body.indexOf("\"id\"");
        if (idx < 0) return null;
        int colon = body.indexOf(':', idx);
        if (colon < 0) return null;
        int start = colon + 1;
        while (start < body.length() && Character.isWhitespace(body.charAt(start))) start++;
        if (start >= body.length()) return null;
        char c = body.charAt(start);
        boolean quoted = c == '"' || c == '\'';
        int end;
        if (quoted) {
            start++;
            end = body.indexOf(c, start);
            if (end < 0) end = body.length();
            return body.substring(start, end);
        } else {
            end = start;
            while (end < body.length() && (Character.isDigit(body.charAt(end)) || body.charAt(end) == '-')) end++;
            return body.substring(start, end);
        }
    }

    private static Response get(String pathTemplate, Object... args) throws Exception {
        String path = replacePlaceholders(pathTemplate, args);
        return sendRequest("GET", path, null, null);
    }

    private static Response post(String pathTemplate, String body, Object... args) throws Exception {
        String path = replacePlaceholders(pathTemplate, args);
        return sendRequest("POST", path, body, "text/plain; charset=UTF-8");
    }

    private static Response postForm(String pathTemplate, Map<String,String> formParams, Object... args) throws Exception {
        String path = replacePlaceholders(pathTemplate, args);
        StringJoiner sj = new StringJoiner("&");
        for (Map.Entry<String,String> e : formParams.entrySet()) {
            sj.add(encode(e.getKey()) + "=" + encode(e.getValue()));
        }
        String body = sj.toString();
        return sendRequest("POST", path, body, "application/x-www-form-urlencoded; charset=UTF-8");
    }

    private static Response delete(String pathTemplate, Object... args) throws Exception {
        String path = replacePlaceholders(pathTemplate, args);
        return sendRequest("DELETE", path, null, null);
    }

    private static String replacePlaceholders(String template, Object... args) {
        String result = template;
        for (Object a : args) {
            int start = result.indexOf('{');
            int end = result.indexOf('}', start);
            if (start >= 0 && end > start) {
                String before = result.substring(0, start);
                String after = result.substring(end + 1);
                String rep = a == null ? "null" : urlEncodePathSegment(a.toString());
                result = before + rep + after;
            } else {
                break;
            }
        }
        return result;
    }

    private static String urlEncodePathSegment(String s) {
        try {
            return java.net.URLEncoder.encode(s, "UTF-8").replace("+", "%20");
        } catch (Exception e) {
            return s;
        }
    }

    private static String encode(String s) {
        try {
            return java.net.URLEncoder.encode(s, "UTF-8");
        } catch (Exception e) {
            return s;
        }
    }

    private static Response sendRequest(String method, String path, String body, String contentType) throws Exception {
        String full = baseUrl;
        if (full.endsWith("/") && path.startsWith("/")) path = path.substring(1);
        if (!full.endsWith("/") && !path.startsWith("/")) full += "/";
        full += path.startsWith("/") ? path.substring(1) : path;
        URL url = new URL(full);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setConnectTimeout(30000);
        conn.setReadTimeout(30000);
        if (contentType != null) conn.setRequestProperty("Content-Type", contentType);
        if (body != null && (method.equals("POST") || method.equals("PUT"))) {
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
            is = (status >= 200 && status <= 399) ? conn.getInputStream() : conn.getErrorStream();
        } catch (Exception e) {
            is = conn.getErrorStream();
        }
        String responseBody = null;
        if (is != null) {
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            responseBody = sb.toString();
        }
        conn.disconnect();
        return new Response(status, responseBody);
    }

    private static class Response {
        final int statusCode;
        final String body;
        Response(int statusCode, String body) {
            this.statusCode = statusCode;
            this.body = body;
        }
    }
}