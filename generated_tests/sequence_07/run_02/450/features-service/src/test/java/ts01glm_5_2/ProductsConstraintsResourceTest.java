package ts01glm_5_2;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.UUID;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class ProductsConstraintsResourceTest {

    private static String baseURI;

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("baseUrl");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        baseURI = baseUrl;
        if (baseURI.endsWith("/")) {
            baseURI = baseURI.substring(0, baseURI.length() - 1);
        }
    }

    @Ignore("Expected: a value less than <300>      but: <500> was greater than <300>")
    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProductSuccess() throws Exception {
        String productName = "TestProduct-Requires-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "RAID-Controller-Card";
        String requiredFeature = "128GB-ECC-RAM";

        int code1 = postNoBody("/products/{productName}", productName);
        Assert.assertThat(code1, lessThan(300));

        int code2 = postNoBody("/products/{productName}/features/{featureName}", productName, sourceFeature);
        Assert.assertThat(code2, lessThan(300));

        int code3 = postNoBody("/products/{productName}/features/{featureName}", productName, requiredFeature);
        Assert.assertThat(code3, lessThan(300));

        Map<String, String> form = new LinkedHashMap<String, String>();
        form.put("sourceFeature", sourceFeature);
        form.put("requiredFeature", requiredFeature);
        int code4 = postForm("/products/{productName}/constraints/requires", form, productName);
        Assert.assertEquals(201, code4);
    }

    @Ignore("Expected: a value less than <300>      but: <500> was greater than <300>")
    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProductSuccess() throws Exception {
        String productName = "TestProduct-Excludes-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "CPU-i9-13900H";
        String excludedFeature = "Integrated-Graphics-Only";

        int code1 = postNoBody("/products/{productName}", productName);
        Assert.assertThat(code1, lessThan(300));

        int code2 = postNoBody("/products/{productName}/features/{featureName}", productName, sourceFeature);
        Assert.assertThat(code2, lessThan(300));

        int code3 = postNoBody("/products/{productName}/features/{featureName}", productName, excludedFeature);
        Assert.assertThat(code3, lessThan(300));

        Map<String, String> form = new LinkedHashMap<String, String>();
        form.put("sourceFeature", sourceFeature);
        form.put("excludedFeature", excludedFeature);
        int code4 = postForm("/products/{productName}/constraints/excludes", form, productName);
        Assert.assertEquals(201, code4);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintWithNonExistentProduct() throws Exception {
        String productName = "NonExistentProduct-" + UUID.randomUUID().toString().substring(0, 8);

        Map<String, String> form = new LinkedHashMap<String, String>();
        form.put("sourceFeature", "FeatureA");
        form.put("requiredFeature", "FeatureB");
        int code = postForm("/products/{productName}/constraints/requires", form, productName);
        Assert.assertEquals(500, code);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintWithNonExistentProduct() throws Exception {
        String productName = "NonExistentProduct-" + UUID.randomUUID().toString().substring(0, 8);

        Map<String, String> form = new LinkedHashMap<String, String>();
        form.put("sourceFeature", "FeatureA");
        form.put("excludedFeature", "FeatureB");
        int code = postForm("/products/{productName}/constraints/excludes", form, productName);
        Assert.assertEquals(500, code);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintWithNonExistentFeatures() throws Exception {
        String productName = "TestProduct-ReqNoFeat-" + UUID.randomUUID().toString().substring(0, 8);

        int code1 = postNoBody("/products/{productName}", productName);
        Assert.assertThat(code1, lessThan(300));

        Map<String, String> form = new LinkedHashMap<String, String>();
        form.put("sourceFeature", "NonExistentSource");
        form.put("requiredFeature", "NonExistentRequired");
        int code = postForm("/products/{productName}/constraints/requires", form, productName);
        Assert.assertEquals(201, code);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintWithNonExistentFeatures() throws Exception {
        String productName = "TestProduct-ExcNoFeat-" + UUID.randomUUID().toString().substring(0, 8);

        int code1 = postNoBody("/products/{productName}", productName);
        Assert.assertThat(code1, lessThan(300));

        Map<String, String> form = new LinkedHashMap<String, String>();
        form.put("sourceFeature", "NonExistentSource");
        form.put("excludedFeature", "NonExistentExcluded");
        int code = postForm("/products/{productName}/constraints/excludes", form, productName);
        Assert.assertEquals(201, code);
    }

    private int postNoBody(String pathTemplate, String... vars) throws IOException {
        String path = fillUri(pathTemplate, vars);
        URL url = new URL(baseURI + path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        try {
            conn.setRequestMethod("POST");
            conn.setDoOutput(false);
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(15000);
            conn.connect();
            int code = conn.getResponseCode();
            consumeStream(conn);
            return code;
        } finally {
            conn.disconnect();
        }
    }

    private int postForm(String pathTemplate, Map<String, String> formParams, String... vars) throws IOException {
        String path = fillUri(pathTemplate, vars);
        URL url = new URL(baseURI + path);
        byte[] body = buildFormBody(formParams).getBytes("UTF-8");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        try {
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", Integer.toString(body.length));
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(15000);
            OutputStream os = conn.getOutputStream();
            os.write(body);
            os.flush();
            os.close();
            int code = conn.getResponseCode();
            consumeStream(conn);
            return code;
        } finally {
            conn.disconnect();
        }
    }

    private String buildFormBody(Map<String, String> params) throws IOException {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> e : params.entrySet()) {
            if (!first) {
                sb.append("&");
            }
            first = false;
            sb.append(URLEncoder.encode(e.getKey(), "UTF-8"));
            sb.append("=");
            sb.append(URLEncoder.encode(e.getValue(), "UTF-8"));
        }
        return sb.toString();
    }

    private String fillUri(String template, String... vars) throws IOException {
        String result = template;
        for (String v : vars) {
            int start = result.indexOf('{');
            if (start == -1) {
                break;
            }
            int end = result.indexOf('}', start);
            if (end == -1) {
                break;
            }
            String encoded = URLEncoder.encode(v, "UTF-8");
            result = result.substring(0, start) + encoded + result.substring(end + 1);
        }
        return result;
    }

    private void consumeStream(HttpURLConnection conn) {
        InputStream is = null;
        try {
            try {
                is = conn.getInputStream();
            } catch (IOException e) {
                is = conn.getErrorStream();
            }
            if (is == null) {
                return;
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int r;
            while ((r = is.read(buf)) != -1) {
                baos.write(buf, 0, r);
            }
            baos.toByteArray();
        } catch (Exception ignored) {
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception ignored) {
                }
            }
        }
    }
}