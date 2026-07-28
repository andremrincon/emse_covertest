package ts01glm_5_2;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;

import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ProductsConstraintsResourceTest {

    private static String baseUri;

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            baseUri = baseUrl;
        } else {
            baseUri = "http://localhost:8080";
        }
    }

    private String buildPath(String template, String... args) {
        String result = template;
        for (String arg : args) {
            int start = result.indexOf('{');
            int end = result.indexOf('}', start);
            if (start >= 0 && end > start) {
                String placeholder = result.substring(start, end + 1);
                result = result.replace(placeholder, urlEncode(arg));
            }
        }
        if (!result.startsWith("/")) {
            result = "/" + result;
        }
        return baseUri + result;
    }

    private String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8").replace("+", "%20");
        } catch (Exception e) {
            return s;
        }
    }

    private int doPost(String pathTemplate, String[] pathArgs, Map<String, String> formParams, String contentType) throws Exception {
        String fullUrl = buildPath(pathTemplate, pathArgs);
        URL url = new URL(fullUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setConnectTimeout(60000);
        conn.setReadTimeout(60000);
        if (formParams != null && !formParams.isEmpty()) {
            conn.setDoOutput(true);
            if (contentType != null && !contentType.isEmpty()) {
                conn.setRequestProperty("Content-Type", contentType);
            } else {
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            }
            StringBuilder sb = new StringBuilder();
            boolean first = true;
            for (Map.Entry<String, String> e : formParams.entrySet()) {
                if (!first) {
                    sb.append("&");
                }
                first = false;
                sb.append(URLEncoder.encode(e.getKey(), "UTF-8"));
                sb.append("=");
                sb.append(URLEncoder.encode(e.getValue(), "UTF-8"));
            }
            byte[] out = sb.toString().getBytes(StandardCharsets.UTF_8);
            conn.setRequestProperty("Content-Length", String.valueOf(out.length));
            OutputStream os = conn.getOutputStream();
            os.write(out);
            os.flush();
            os.close();
        }
        int code = conn.getResponseCode();
        InputStream is = null;
        try {
            if (code >= 400) {
                is = conn.getErrorStream();
            } else {
                is = conn.getInputStream();
            }
            if (is != null) {
                byte[] buffer = new byte[1024];
                while (is.read(buffer) != -1) {
                }
                is.close();
            }
        } catch (Exception ignored) {
        } finally {
            conn.disconnect();
        }
        return code;
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct() throws Exception {
        String productName = "TestProduct-Requires-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID().toString().substring(0, 8);

        int rc;

        rc = doPost("/products/{productName}", new String[]{productName}, null, null);
        assertThat(rc, lessThan(300));

        rc = doPost("/products/{productName}/features/{featureName}", new String[]{productName, sourceFeature}, null, null);
        assertEquals(500, rc);

        rc = doPost("/products/{productName}/features/{featureName}", new String[]{productName, requiredFeature}, null, null);
        assertEquals(500, rc);

        rc = doPost("/products/{productName}/constraints/requires", new String[]{productName},
                java.util.Collections.unmodifiableMap(new java.util.LinkedHashMap<String, String>() {{
                    put("sourceFeature", sourceFeature);
                    put("requiredFeature", requiredFeature);
                }}),
                "application/x-www-form-urlencoded");
        assertEquals(201, rc);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct() throws Exception {
        String productName = "TestProduct-Excludes-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeature-" + UUID.randomUUID().toString().substring(0, 8);

        int rc;

        rc = doPost("/products/{productName}", new String[]{productName}, null, null);
        assertThat(rc, lessThan(300));

        rc = doPost("/products/{productName}/features/{featureName}", new String[]{productName, sourceFeature}, null, null);
        assertEquals(500, rc);

        rc = doPost("/products/{productName}/features/{featureName}", new String[]{productName, excludedFeature}, null, null);
        assertEquals(500, rc);

        rc = doPost("/products/{productName}/constraints/excludes", new String[]{productName},
                java.util.Collections.unmodifiableMap(new java.util.LinkedHashMap<String, String>() {{
                    put("sourceFeature", sourceFeature);
                    put("excludedFeature", excludedFeature);
                }}),
                "application/x-www-form-urlencoded");
        assertEquals(201, rc);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintWithNonExistentProduct() throws Exception {
        String productName = "NonExistentProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID().toString().substring(0, 8);

        int rc = doPost("/products/{productName}/constraints/requires", new String[]{productName},
                java.util.Collections.unmodifiableMap(new java.util.LinkedHashMap<String, String>() {{
                    put("sourceFeature", sourceFeature);
                    put("requiredFeature", requiredFeature);
                }}),
                "application/x-www-form-urlencoded");
        assertEquals(500, rc);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintWithNonExistentProduct() throws Exception {
        String productName = "NonExistentProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeature-" + UUID.randomUUID().toString().substring(0, 8);

        int rc = doPost("/products/{productName}/constraints/excludes", new String[]{productName},
                java.util.Collections.unmodifiableMap(new java.util.LinkedHashMap<String, String>() {{
                    put("sourceFeature", sourceFeature);
                    put("excludedFeature", excludedFeature);
                }}),
                "application/x-www-form-urlencoded");
        assertEquals(500, rc);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintWithNonExistentFeature() throws Exception {
        String productName = "TestProduct-ReqNoFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "NonExistentSource-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "NonExistentRequired-" + UUID.randomUUID().toString().substring(0, 8);

        int rc;

        rc = doPost("/products/{productName}", new String[]{productName}, null, null);
        assertThat(rc, lessThan(300));

        rc = doPost("/products/{productName}/constraints/requires", new String[]{productName},
                java.util.Collections.unmodifiableMap(new java.util.LinkedHashMap<String, String>() {{
                    put("sourceFeature", sourceFeature);
                    put("requiredFeature", requiredFeature);
                }}),
                "application/x-www-form-urlencoded");
        assertEquals(201, rc);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintWithNonExistentFeature() throws Exception {
        String productName = "TestProduct-ExcNoFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "NonExistentSource-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "NonExistentExcluded-" + UUID.randomUUID().toString().substring(0, 8);

        int rc;

        rc = doPost("/products/{productName}", new String[]{productName}, null, null);
        assertThat(rc, lessThan(300));

        rc = doPost("/products/{productName}/constraints/excludes", new String[]{productName},
                java.util.Collections.unmodifiableMap(new java.util.LinkedHashMap<String, String>() {{
                    put("sourceFeature", sourceFeature);
                    put("excludedFeature", excludedFeature);
                }}),
                "application/x-www-form-urlencoded");
        assertEquals(201, rc);
    }
}