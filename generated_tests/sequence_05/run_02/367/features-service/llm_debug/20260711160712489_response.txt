package ts01glm_5_2;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;
import java.util.StringJoiner;

import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductsServiceTest {

    private static String baseUrl;

    @BeforeClass
    public static void setup() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
    }

    private static String formatPath(String pathTemplate, String[] pathParams) throws UnsupportedEncodingException {
        String result = pathTemplate;
        if (pathParams != null) {
            for (String param : pathParams) {
                int start = result.indexOf('{');
                int end = result.indexOf('}', start);
                if (start >= 0 && end > start) {
                    String encoded = URLEncoder.encode(param, "UTF-8");
                    result = result.substring(0, start) + encoded + result.substring(end + 1);
                }
            }
        }
        return result;
    }

    private static String buildForm(Map<String, String> formParams) throws UnsupportedEncodingException {
        if (formParams == null || formParams.isEmpty()) {
            return "";
        }
        StringJoiner joiner = new StringJoiner("&");
        for (Map.Entry<String, String> e : formParams.entrySet()) {
            String k = URLEncoder.encode(e.getKey(), "UTF-8");
            String v = URLEncoder.encode(e.getValue(), "UTF-8");
            joiner.add(k + "=" + v);
        }
        return joiner.toString();
    }

    private static int doRequest(String method, String pathTemplate, String... pathParams) throws Exception {
        String path = formatPath(pathTemplate, pathParams);
        URL url = new URL(baseUrl + path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        if ("POST".equals(method) || "PUT".equals(method)) {
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            OutputStream os = conn.getOutputStream();
            os.write(new byte[0]);
            os.flush();
            os.close();
        }
        int code = conn.getResponseCode();
        conn.disconnect();
        return code;
    }

    private static int doRequestWithForm(String method, String pathTemplate, Map<String, String> formParams, String... pathParams) throws Exception {
        String path = formatPath(pathTemplate, pathParams);
        URL url = new URL(baseUrl + path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        String body = buildForm(formParams);
        OutputStream os = conn.getOutputStream();
        os.write(body.getBytes("UTF-8"));
        os.flush();
        os.close();
        int code = conn.getResponseCode();
        conn.disconnect();
        return code;
    }

    @Test(timeout = 60000)
    public void addFeatureToProductSuccess() throws Exception {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feat-" + UUID.randomUUID().toString().substring(0, 8);
        int c1 = doRequest("POST", "/products/{productName}", productName);
        assertTrue(c1 < 300);
        int c2 = doRequestWithForm("POST", "/products/{productName}/features/{featureName}", java.util.Collections.emptyMap(), productName, featureName);
        assertEquals(201, c2);
    }

    @Test(timeout = 60000)
    public void addFeatureToProductDuplicateThrowsException() throws Exception {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "dup-feat-" + UUID.randomUUID().toString().substring(0, 8);
        int c1 = doRequest("POST", "/products/{productName}", productName);
        assertTrue(c1 < 300);
        int c2 = doRequestWithForm("POST", "/products/{productName}/features/{featureName}", java.util.Collections.emptyMap(), productName, featureName);
        assertTrue(c2 < 300);
        int c3 = doRequestWithForm("POST", "/products/{productName}/features/{featureName}", java.util.Collections.emptyMap(), productName, featureName);
        assertEquals(500, c3);
    }

    @Test(timeout = 60000)
    public void addFeatureToProductWithDescription() throws Exception {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "desc-feat-" + UUID.randomUUID().toString().substring(0, 8);
        int c1 = doRequest("POST", "/products/{productName}", productName);
        assertTrue(c1 < 300);
        int c2 = doRequestWithForm("POST", "/products/{productName}/features/{featureName}", java.util.Collections.singletonMap("description", "A test feature description"), productName, featureName);
        assertEquals(201, c2);
    }

    @Test(timeout = 60000)
    public void deleteFeatureOfProductSuccess() throws Exception {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "del-feat-" + UUID.randomUUID().toString().substring(0, 8);
        int c1 = doRequest("POST", "/products/{productName}", productName);
        assertTrue(c1 < 300);
        int c2 = doRequestWithForm("POST", "/products/{productName}/features/{featureName}", java.util.Collections.emptyMap(), productName, featureName);
        assertTrue(c2 < 300);
        int c3 = doRequest("DELETE", "/products/{productName}/features/{featureName}", productName, featureName);
        assertEquals(204, c3);
    }

    @Test(timeout = 60000)
    public void deleteFeatureOfProductWithActiveConfiguration() throws Exception {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "cfg-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "cfg-" + UUID.randomUUID().toString().substring(0, 8);
        int c1 = doRequest("POST", "/products/{productName}", productName);
        assertTrue(c1 < 300);
        int c2 = doRequestWithForm("POST", "/products/{productName}/features/{featureName}", java.util.Collections.emptyMap(), productName, featureName);
        assertTrue(c2 < 300);
        int c3 = doRequest("POST", "/products/{productName}/configurations/{configurationName}", productName, configName);
        assertTrue(c3 < 300);
        int c4 = doRequest("POST", "/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName);
        assertTrue(c4 < 300);
        int c5 = doRequest("DELETE", "/products/{productName}/features/{featureName}", productName, featureName);
        assertEquals(204, c5);
    }

    @Test(timeout = 60000)
    public void addRequiresConstraintToProductSuccess() throws Exception {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-feat-" + UUID.randomUUID().toString().substring(0, 8);
        int c1 = doRequest("POST", "/products/{productName}", productName);
        assertTrue(c1 < 300);
        int c2 = doRequestWithForm("POST", "/products/{productName}/features/{featureName}", java.util.Collections.emptyMap(), productName, sourceFeature);
        assertTrue(c2 < 300);
        int c3 = doRequestWithForm("POST", "/products/{productName}/features/{featureName}", java.util.Collections.emptyMap(), productName, requiredFeature);
        assertTrue(c3 < 300);
        int c4 = doRequestWithForm("POST", "/products/{productName}/constraints/requires", new java.util.HashMap<String, String>() {{
            put("sourceFeature", sourceFeature);
            put("requiredFeature", requiredFeature);
        }}, productName);
        assertEquals(201, c4);
    }

    @Test(timeout = 60000)
    public void addRequiresConstraintToProductNonExistentProduct() throws Exception {
        String productName = "nonexistent-prod-" + UUID.randomUUID().toString().substring(0, 8);
        int c = doRequestWithForm("POST", "/products/{productName}/constraints/requires", new java.util.HashMap<String, String>() {{
            put("sourceFeature", "featureA");
            put("requiredFeature", "featureB");
        }}, productName);
        assertEquals(500, c);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraintToProductSuccess() throws Exception {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "excl-src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "excl-tgt-" + UUID.randomUUID().toString().substring(0, 8);
        int c1 = doRequest("POST", "/products/{productName}", productName);
        assertTrue(c1 < 300);
        int c2 = doRequestWithForm("POST", "/products/{productName}/features/{featureName}", java.util.Collections.emptyMap(), productName, sourceFeature);
        assertTrue(c2 < 300);
        int c3 = doRequestWithForm("POST", "/products/{productName}/features/{featureName}", java.util.Collections.emptyMap(), productName, excludedFeature);
        assertTrue(c3 < 300);
        int c4 = doRequestWithForm("POST", "/products/{productName}/constraints/excludes", new java.util.HashMap<String, String>() {{
            put("sourceFeature", sourceFeature);
            put("excludedFeature", excludedFeature);
        }}, productName);
        assertEquals(201, c4);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraintToProductNonExistentProduct() throws Exception {
        String productName = "nonexistent-prod-" + UUID.randomUUID().toString().substring(0, 8);
        int c = doRequestWithForm("POST", "/products/{productName}/constraints/excludes", new java.util.HashMap<String, String>() {{
            put("sourceFeature", "featureA");
            put("excludedFeature", "featureB");
        }}, productName);
        assertEquals(500, c);
    }

    @Test(timeout = 60000)
    public void addFeatureToProductNonExistentProduct() throws Exception {
        String productName = "nonexistent-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "orphan-feat-" + UUID.randomUUID().toString().substring(0, 8);
        int c = doRequestWithForm("POST", "/products/{productName}/features/{featureName}", java.util.Collections.emptyMap(), productName, featureName);
        assertEquals(500, c);
    }

    @Test(timeout = 60000)
    public void deleteFeatureOfProductNonExistentFeature() throws Exception {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "ghost-feat-" + UUID.randomUUID().toString().substring(0, 8);
        int c1 = doRequest("POST", "/products/{productName}", productName);
        assertTrue(c1 < 300);
        int c2 = doRequest("DELETE", "/products/{productName}/features/{featureName}", productName, featureName);
        assertEquals(500, c2);
    }

    @Test(timeout = 60000)
    public void addRequiresConstraintWithSameSourceAndRequired() throws Exception {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "self-feat-" + UUID.randomUUID().toString().substring(0, 8);
        int c1 = doRequest("POST", "/products/{productName}", productName);
        assertTrue(c1 < 300);
        int c2 = doRequestWithForm("POST", "/products/{productName}/features/{featureName}", java.util.Collections.emptyMap(), productName, featureName);
        assertTrue(c2 < 300);
        int c3 = doRequestWithForm("POST", "/products/{productName}/constraints/requires", new java.util.HashMap<String, String>() {{
            put("sourceFeature", featureName);
            put("requiredFeature", featureName);
        }}, productName);
        assertEquals(201, c3);
    }
}