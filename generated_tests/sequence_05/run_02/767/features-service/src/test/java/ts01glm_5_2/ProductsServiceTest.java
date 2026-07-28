package ts01glm_5_2;

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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class ProductsServiceTest {

    private static String baseUrl = "http://localhost:8080";

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getProperty("baseUrl", baseUrl);
    }

    private int doPost(String path) throws Exception {
        return doPostWithForm(path, null);
    }

    private int doPostWithForm(String path, Map<String, String> formParams) throws Exception {
        URL url = new URL(baseUrl + path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        String content = "";
        if (formParams != null && !formParams.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            boolean first = true;
            for (Map.Entry<String, String> e : formParams.entrySet()) {
                if (!first) sb.append("&");
                sb.append(URLEncoder.encode(e.getKey(), "UTF-8"));
                sb.append("=");
                sb.append(URLEncoder.encode(e.getValue(), "UTF-8"));
                first = false;
            }
            content = sb.toString();
        }
        byte[] out = content.getBytes("UTF-8");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        conn.setRequestProperty("Content-Length", String.valueOf(out.length));
        if (out.length > 0) {
            OutputStream os = conn.getOutputStream();
            os.write(out);
            os.flush();
            os.close();
        } else {
            conn.connect();
        }
        int code = conn.getResponseCode();
        InputStream is = null;
        try {
            if (code >= 200 && code < 400) {
                is = conn.getInputStream();
            } else {
                is = conn.getErrorStream();
            }
            if (is != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                while (br.readLine() != null) { }
                br.close();
            }
        } catch (Exception ignore) {
        } finally {
            if (is != null) try { is.close(); } catch (Exception ignore) {}
            conn.disconnect();
        }
        return code;
    }

    private int doDelete(String path) throws Exception {
        URL url = new URL(baseUrl + path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("DELETE");
        conn.setDoOutput(false);
        int code = conn.getResponseCode();
        InputStream is = null;
        try {
            if (code >= 200 && code < 400) {
                is = conn.getInputStream();
            } else {
                is = conn.getErrorStream();
            }
            if (is != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                while (br.readLine() != null) { }
                br.close();
            }
        } catch (Exception ignore) {
        } finally {
            if (is != null) try { is.close(); } catch (Exception ignore) {}
            conn.disconnect();
        }
        return code;
    }

    @Test(timeout = 60000)
    public void addFeatureToProduct_success() throws Exception {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        int code1 = doPost(String.format("/products/%s", productName));
        Assert.assertTrue(code1 < 300);

        Map<String, String> form = new LinkedHashMap<>();
        form.put("description", "Test feature description");
        int code2 = doPostWithForm(String.format("/products/%s/features/%s", productName, featureName), form);
        Assert.assertEquals(201, code2);
    }

    @Test(timeout = 60000)
    public void addFeatureToProduct_duplicateThrowsException() throws Exception {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        int c1 = doPost(String.format("/products/%s", productName));
        Assert.assertTrue(c1 < 300);

        Map<String, String> f1 = new LinkedHashMap<>();
        f1.put("description", "First description");
        int c2 = doPostWithForm(String.format("/products/%s/features/%s", productName, featureName), f1);
        Assert.assertTrue(c2 < 300);

        Map<String, String> f2 = new LinkedHashMap<>();
        f2.put("description", "Second description");
        int c3 = doPostWithForm(String.format("/products/%s/features/%s", productName, featureName), f2);
        Assert.assertEquals(500, c3);
    }

    @Test(timeout = 60000)
    public void deleteFeatureOfProduct_withActiveConfiguration() throws Exception {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "conf-" + UUID.randomUUID().toString().substring(0, 8);

        int c1 = doPost(String.format("/products/%s", productName));
        Assert.assertTrue(c1 < 300);

        Map<String, String> f = new LinkedHashMap<>();
        f.put("description", "Test feature");
        int c2 = doPostWithForm(String.format("/products/%s/features/%s", productName, featureName), f);
        Assert.assertTrue(c2 < 300);

        int c3 = doPost(String.format("/products/%s/configurations/%s", productName, configName));
        Assert.assertTrue(c3 < 300);

        int c4 = doPost(String.format("/products/%s/configurations/%s/features/%s", productName, configName, featureName));
        Assert.assertTrue(c4 < 300);

        int c5 = doDelete(String.format("/products/%s/features/%s", productName, featureName));
        Assert.assertEquals(204, c5);
    }

    @Test(timeout = 60000)
    public void deleteFeatureOfProduct_noConfiguration() throws Exception {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        int c1 = doPost(String.format("/products/%s", productName));
        Assert.assertTrue(c1 < 300);

        Map<String, String> f = new LinkedHashMap<>();
        f.put("description", "Test feature");
        int c2 = doPostWithForm(String.format("/products/%s/features/%s", productName, featureName), f);
        Assert.assertTrue(c2 < 300);

        int c3 = doDelete(String.format("/products/%s/features/%s", productName, featureName));
        Assert.assertEquals(204, c3);
    }

    @Test(timeout = 60000)
    public void addRequiresConstraintToProduct_success() throws Exception {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-" + UUID.randomUUID().toString().substring(0, 8);

        int c1 = doPost(String.format("/products/%s", productName));
        Assert.assertTrue(c1 < 300);

        int c2 = doPost(String.format("/products/%s/features/%s", productName, sourceFeature));
        Assert.assertTrue(c2 < 300);

        int c3 = doPost(String.format("/products/%s/features/%s", productName, requiredFeature));
        Assert.assertTrue(c3 < 300);

        Map<String, String> form = new LinkedHashMap<>();
        form.put("sourceFeature", sourceFeature);
        form.put("requiredFeature", requiredFeature);
        int c4 = doPostWithForm(String.format("/products/%s/constraints/requires", productName), form);
        Assert.assertEquals(201, c4);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraintToProduct_success() throws Exception {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-" + UUID.randomUUID().toString().substring(0, 8);

        int c1 = doPost(String.format("/products/%s", productName));
        Assert.assertTrue(c1 < 300);

        int c2 = doPost(String.format("/products/%s/features/%s", productName, sourceFeature));
        Assert.assertTrue(c2 < 300);

        int c3 = doPost(String.format("/products/%s/features/%s", productName, excludedFeature));
        Assert.assertTrue(c3 < 300);

        Map<String, String> form = new LinkedHashMap<>();
        form.put("sourceFeature", sourceFeature);
        form.put("excludedFeature", excludedFeature);
        int c4 = doPostWithForm(String.format("/products/%s/constraints/excludes", productName), form);
        Assert.assertEquals(201, c4);
    }

    @Test(timeout = 60000)
    public void addRequiresConstraintToProduct_nonExistentProduct() throws Exception {
        String productName = "nonexistent-" + UUID.randomUUID().toString().substring(0, 8);

        Map<String, String> form = new LinkedHashMap<>();
        form.put("sourceFeature", "featureA");
        form.put("requiredFeature", "featureB");
        int c = doPostWithForm(String.format("/products/%s/constraints/requires", productName), form);
        Assert.assertEquals(500, c);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraintToProduct_nonExistentProduct() throws Exception {
        String productName = "nonexistent-" + UUID.randomUUID().toString().substring(0, 8);

        Map<String, String> form = new LinkedHashMap<>();
        form.put("sourceFeature", "featureA");
        form.put("excludedFeature", "featureB");
        int c = doPostWithForm(String.format("/products/%s/constraints/excludes", productName), form);
        Assert.assertEquals(500, c);
    }

    @Test(timeout = 60000)
    public void addFeatureToProduct_nonExistentProduct() throws Exception {
        String productName = "nonexistent-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        Map<String, String> form = new LinkedHashMap<>();
        form.put("description", "Test description");
        int c = doPostWithForm(String.format("/products/%s/features/%s", productName, featureName), form);
        Assert.assertEquals(500, c);
    }

    @Test(timeout = 60000)
    public void deleteFeatureOfProduct_nonExistentFeature() throws Exception {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "nonexistent-feat-" + UUID.randomUUID().toString().substring(0, 8);

        int c1 = doPost(String.format("/products/%s", productName));
        Assert.assertTrue(c1 < 300);

        int c2 = doDelete(String.format("/products/%s/features/%s", productName, featureName));
        Assert.assertEquals(500, c2);
    }

    @Test(timeout = 60000)
    public void addRequiresConstraintToProduct_withExistingFeatures() throws Exception {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-" + UUID.randomUUID().toString().substring(0, 8);

        int c1 = doPost(String.format("/products/%s", productName));
        Assert.assertTrue(c1 < 300);

        int c2 = doPost(String.format("/products/%s/features/%s", productName, sourceFeature));
        Assert.assertTrue(c2 < 300);

        int c3 = doPost(String.format("/products/%s/features/%s", productName, requiredFeature));
        Assert.assertTrue(c3 < 300);

        Map<String, String> form = new LinkedHashMap<>();
        form.put("sourceFeature", sourceFeature);
        form.put("requiredFeature", requiredFeature);
        int c4 = doPostWithForm(String.format("/products/%s/constraints/requires", productName), form);
        Assert.assertTrue(c4 < 300);

        int c5 = doPostWithForm(String.format("/products/%s/constraints/requires", productName), form);
        Assert.assertEquals(201, c5);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraintToProduct_withExistingFeatures() throws Exception {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-" + UUID.randomUUID().toString().substring(0, 8);

        int c1 = doPost(String.format("/products/%s", productName));
        Assert.assertTrue(c1 < 300);

        int c2 = doPost(String.format("/products/%s/features/%s", productName, sourceFeature));
        Assert.assertTrue(c2 < 300);

        int c3 = doPost(String.format("/products/%s/features/%s", productName, excludedFeature));
        Assert.assertTrue(c3 < 300);

        Map<String, String> form = new LinkedHashMap<>();
        form.put("sourceFeature", sourceFeature);
        form.put("excludedFeature", excludedFeature);
        int c4 = doPostWithForm(String.format("/products/%s/constraints/excludes", productName), form);
        Assert.assertTrue(c4 < 300);

        int c5 = doPostWithForm(String.format("/products/%s/constraints/excludes", productName), form);
        Assert.assertEquals(201, c5);
    }
}