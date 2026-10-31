package ts01glm_5_2;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.UUID;

import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    private String baseUri;

    @Before
    public void setUp() {
        baseUri = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testAddFeatureCoversWithNameAndSetters() throws Exception {
        String productName = "Product-" + UUID.randomUUID();
        String featureName = "Feature-" + UUID.randomUUID();

        int status;

        status = post("/products/" + encode(productName));
        Assert.assertThat(status, lessThan(300));

        status = postForm("/products/" + encode(productName) + "/features/" + encode(featureName), "Test description");
        Assert.assertEquals(201, status);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesCoversGettersAndHydration() throws Exception {
        String productName = "Product-" + UUID.randomUUID();
        String featureName = "Feature-" + UUID.randomUUID();

        int status;

        status = post("/products/" + encode(productName));
        Assert.assertThat(status, lessThan(300));

        status = postForm("/products/" + encode(productName) + "/features/" + encode(featureName), "Test description");
        Assert.assertThat(status, lessThan(300));

        status = get("/products/" + encode(productName) + "/features");
        Assert.assertEquals(200, status);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureCoversSetDescription() throws Exception {
        String productName = "Product-" + UUID.randomUUID();
        String featureName = "Feature-" + UUID.randomUUID();

        int status;

        status = post("/products/" + encode(productName));
        Assert.assertThat(status, lessThan(300));

        status = postForm("/products/" + encode(productName) + "/features/" + encode(featureName), "Initial description");
        Assert.assertThat(status, lessThan(300));

        status = putForm("/products/" + encode(productName) + "/features/" + encode(featureName), "Updated description");
        Assert.assertEquals(200, status);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationCoversEquals() throws Exception {
        String productName = "Product-" + UUID.randomUUID();
        String configurationName = "Config-" + UUID.randomUUID();
        String featureName = "Feature-" + UUID.randomUUID();

        int status;

        status = post("/products/" + encode(productName));
        Assert.assertThat(status, lessThan(300));

        status = postForm("/products/" + encode(productName) + "/features/" + encode(featureName), "Test description");
        Assert.assertThat(status, lessThan(300));

        status = post("/products/" + encode(productName) + "/configurations/" + encode(configurationName));
        Assert.assertThat(status, lessThan(300));

        status = post("/products/" + encode(productName) + "/configurations/" + encode(configurationName) + "/features/" + encode(featureName));
        Assert.assertEquals(201, status);
    }

    @Test(timeout = 60000)
    public void testAddSameFeatureToConfigurationCoversEqualsTrue() throws Exception {
        String productName = "Product-" + UUID.randomUUID();
        String configurationName = "Config-" + UUID.randomUUID();
        String featureName = "Feature-" + UUID.randomUUID();

        int status;

        status = post("/products/" + encode(productName));
        Assert.assertThat(status, lessThan(300));

        status = postForm("/products/" + encode(productName) + "/features/" + encode(featureName), "Test description");
        Assert.assertThat(status, lessThan(300));

        status = post("/products/" + encode(productName) + "/configurations/" + encode(configurationName));
        Assert.assertThat(status, lessThan(300));

        status = post("/products/" + encode(productName) + "/configurations/" + encode(configurationName) + "/features/" + encode(featureName));
        Assert.assertThat(status, lessThan(300));

        status = post("/products/" + encode(productName) + "/configurations/" + encode(configurationName) + "/features/" + encode(featureName));
        Assert.assertEquals(500, status);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesCoversGetProduct() throws Exception {
        String productName = "Product-" + UUID.randomUUID();
        String configurationName = "Config-" + UUID.randomUUID();
        String featureName = "Feature-" + UUID.randomUUID();

        int status;

        status = post("/products/" + encode(productName));
        Assert.assertThat(status, lessThan(300));

        status = postForm("/products/" + encode(productName) + "/features/" + encode(featureName), "Test description");
        Assert.assertThat(status, lessThan(300));

        status = post("/products/" + encode(productName) + "/configurations/" + encode(configurationName));
        Assert.assertThat(status, lessThan(300));

        status = post("/products/" + encode(productName) + "/configurations/" + encode(configurationName) + "/features/" + encode(featureName));
        Assert.assertThat(status, lessThan(300));

        status = get("/products/" + encode(productName) + "/configurations/" + encode(configurationName) + "/features");
        Assert.assertEquals(200, status);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureCoversDeletion() throws Exception {
        String productName = "Product-" + UUID.randomUUID();
        String featureName = "Feature-" + UUID.randomUUID();

        int status;

        status = post("/products/" + encode(productName));
        Assert.assertThat(status, lessThan(300));

        status = postForm("/products/" + encode(productName) + "/features/" + encode(featureName), "Test description");
        Assert.assertThat(status, lessThan(300));

        status = delete("/products/" + encode(productName) + "/features/" + encode(featureName));
        Assert.assertEquals(204, status);
    }

    private int post(String path) throws Exception {
        return send("POST", path, null, null);
    }

    private int postForm(String path, String description) throws Exception {
        String body = "description=" + encode(description);
        return send("POST", path, body, "application/x-www-form-urlencoded");
    }

    private int putForm(String path, String description) throws Exception {
        String body = "description=" + encode(description);
        return send("PUT", path, body, "application/x-www-form-urlencoded");
    }

    private int delete(String path) throws Exception {
        return send("DELETE", path, null, null);
    }

    private int get(String path) throws Exception {
        return send("GET", path, null, null);
    }

    private int send(String method, String path, String body, String contentType) throws Exception {
        URL url = new URL(baseUri + path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        if (contentType != null) {
            conn.setRequestProperty("Content-Type", contentType);
        }
        if (body != null) {
            conn.setDoOutput(true);
            byte[] out = body.getBytes("UTF-8");
            conn.setFixedLengthStreamingMode(out.length);
            OutputStream os = conn.getOutputStream();
            os.write(out);
            os.close();
        }
        int code = conn.getResponseCode();
        conn.disconnect();
        return code;
    }

    private String encode(String s) throws Exception {
        return URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20");
    }
}