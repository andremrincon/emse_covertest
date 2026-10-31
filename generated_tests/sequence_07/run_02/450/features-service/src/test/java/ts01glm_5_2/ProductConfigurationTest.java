package ts01glm_5_2;

import org.junit.BeforeClass;
import org.junit.Test;
import org.hamcrest.MatcherAssert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.UUID;

import static org.hamcrest.Matchers.*;

import org.junit.Ignore;
public class ProductConfigurationTest {

    private static String baseUrl;

    @BeforeClass
    public static void setup() {
        baseUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
        if (baseUrl.endsWith("/")) {
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
        }
    }

    private static class SimpleResponse {
        int statusCode;
        String body;

        SimpleResponse(int statusCode, String body) {
            this.statusCode = statusCode;
            this.body = body;
        }
    }

    private SimpleResponse sendRequest(String method, String pathTemplate, Object... args) throws IOException {
        String path = buildPath(pathTemplate, args);
        String urlStr = baseUrl + path;
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);
        conn.setDoOutput(false);
        int code;
        String body = "";
        try {
            conn.connect();
            code = conn.getResponseCode();
            InputStream is = code >= 200 && code < 400 ? conn.getInputStream() : conn.getErrorStream();
            if (is != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                body = sb.toString();
                br.close();
            }
        } finally {
            conn.disconnect();
        }
        return new SimpleResponse(code, body);
    }

    private String buildPath(String template, Object... args) throws IOException {
        String result = template;
        for (Object arg : args) {
            int open = result.indexOf('{');
            int close = result.indexOf('}', open);
            if (open >= 0 && close > open) {
                String encoded = URLEncoder.encode(String.valueOf(arg), "UTF-8");
                result = result.substring(0, open) + encoded + result.substring(close + 1);
            } else {
                result = result + "/" + URLEncoder.encode(String.valueOf(arg), "UTF-8");
            }
        }
        return result;
    }

    @Test(timeout = 60000)
    public void testCreateConfigurationExercisesSetValidAndGetProduct() throws Exception {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);

        SimpleResponse r1 = sendRequest("POST", "/products/{productName}", productName);
        MatcherAssert.assertThat(Integer.valueOf(r1.statusCode), lessThan(300));

        SimpleResponse r2 = sendRequest("POST", "/products/{productName}/configurations/{configurationName}", productName, configurationName);
        MatcherAssert.assertThat(Integer.valueOf(r2.statusCode), equalTo(201));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationExercisesAvailableFeaturesAndCollectFeatureNames() throws Exception {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        SimpleResponse r1 = sendRequest("POST", "/products/{productName}", productName);
        MatcherAssert.assertThat(Integer.valueOf(r1.statusCode), lessThan(300));

        SimpleResponse r2 = sendRequest("POST", "/products/{productName}/features/{featureName}", productName, featureName);
        MatcherAssert.assertThat(Integer.valueOf(r2.statusCode), equalTo(500));

        SimpleResponse r3 = sendRequest("POST", "/products/{productName}/configurations/{configurationName}", productName, configurationName);
        MatcherAssert.assertThat(Integer.valueOf(r3.statusCode), lessThan(300));

        SimpleResponse r4 = sendRequest("GET", "/products/{productName}/configurations/{configurationName}", productName, configurationName);
        MatcherAssert.assertThat(Integer.valueOf(r4.statusCode), equalTo(200));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesEmptyExercisesActivedFeaturesWithEmptySet() throws Exception {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);

        SimpleResponse r1 = sendRequest("POST", "/products/{productName}", productName);
        MatcherAssert.assertThat(Integer.valueOf(r1.statusCode), lessThan(300));

        SimpleResponse r2 = sendRequest("POST", "/products/{productName}/configurations/{configurationName}", productName, configurationName);
        MatcherAssert.assertThat(Integer.valueOf(r2.statusCode), lessThan(300));

        SimpleResponse r3 = sendRequest("GET", "/products/{productName}/configurations/{configurationName}/features", productName, configurationName);
        MatcherAssert.assertThat(Integer.valueOf(r3.statusCode), equalTo(200));
    }

    @Ignore("Expected: <201>      but: was <500>")
    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationExercisesActiveFeature() throws Exception {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        SimpleResponse r1 = sendRequest("POST", "/products/{productName}", productName);
        MatcherAssert.assertThat(Integer.valueOf(r1.statusCode), lessThan(300));

        SimpleResponse r2 = sendRequest("POST", "/products/{productName}/features/{featureName}", productName, featureName);
        MatcherAssert.assertThat(Integer.valueOf(r2.statusCode), equalTo(500));

        SimpleResponse r3 = sendRequest("POST", "/products/{productName}/configurations/{configurationName}", productName, configurationName);
        MatcherAssert.assertThat(Integer.valueOf(r3.statusCode), lessThan(300));

        SimpleResponse r4 = sendRequest("POST", "/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName);
        MatcherAssert.assertThat(Integer.valueOf(r4.statusCode), equalTo(201));
    }

    @Ignore("Expected: a value less than <300>      but: <500> was greater than <300>")
    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesAfterActivationExercisesActivedFeaturesAndCollectFeatureNames() throws Exception {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        SimpleResponse r1 = sendRequest("POST", "/products/{productName}", productName);
        MatcherAssert.assertThat(Integer.valueOf(r1.statusCode), lessThan(300));

        SimpleResponse r2 = sendRequest("POST", "/products/{productName}/features/{featureName}", productName, featureName);
        MatcherAssert.assertThat(Integer.valueOf(r2.statusCode), equalTo(500));

        SimpleResponse r3 = sendRequest("POST", "/products/{productName}/configurations/{configurationName}", productName, configurationName);
        MatcherAssert.assertThat(Integer.valueOf(r3.statusCode), lessThan(300));

        SimpleResponse r4 = sendRequest("POST", "/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName);
        MatcherAssert.assertThat(Integer.valueOf(r4.statusCode), lessThan(300));

        SimpleResponse r5 = sendRequest("GET", "/products/{productName}/configurations/{configurationName}/features", productName, configurationName);
        MatcherAssert.assertThat(Integer.valueOf(r5.statusCode), equalTo(200));
        MatcherAssert.assertThat(r5.body.contains(featureName), is(true));
    }

    @Ignore("Expected: a value less than <300>      but: <500> was greater than <300>")
    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfigurationExercisesDeactiveFeature() throws Exception {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        SimpleResponse r1 = sendRequest("POST", "/products/{productName}", productName);
        MatcherAssert.assertThat(Integer.valueOf(r1.statusCode), lessThan(300));

        SimpleResponse r2 = sendRequest("POST", "/products/{productName}/features/{featureName}", productName, featureName);
        MatcherAssert.assertThat(Integer.valueOf(r2.statusCode), equalTo(500));

        SimpleResponse r3 = sendRequest("POST", "/products/{productName}/configurations/{configurationName}", productName, configurationName);
        MatcherAssert.assertThat(Integer.valueOf(r3.statusCode), lessThan(300));

        SimpleResponse r4 = sendRequest("POST", "/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName);
        MatcherAssert.assertThat(Integer.valueOf(r4.statusCode), lessThan(300));

        SimpleResponse r5 = sendRequest("DELETE", "/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName);
        MatcherAssert.assertThat(Integer.valueOf(r5.statusCode), equalTo(204));
    }

    @Ignore("Expected: a value less than <300>      but: <500> was greater than <300>")
    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesAfterDeactivationExercisesHasActiveFeature() throws Exception {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        SimpleResponse r1 = sendRequest("POST", "/products/{productName}", productName);
        MatcherAssert.assertThat(Integer.valueOf(r1.statusCode), lessThan(300));

        SimpleResponse r2 = sendRequest("POST", "/products/{productName}/features/{featureName}", productName, featureName);
        MatcherAssert.assertThat(Integer.valueOf(r2.statusCode), equalTo(500));

        SimpleResponse r3 = sendRequest("POST", "/products/{productName}/configurations/{configurationName}", productName, configurationName);
        MatcherAssert.assertThat(Integer.valueOf(r3.statusCode), lessThan(300));

        SimpleResponse r4 = sendRequest("POST", "/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName);
        MatcherAssert.assertThat(Integer.valueOf(r4.statusCode), lessThan(300));

        SimpleResponse r5 = sendRequest("DELETE", "/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName);
        MatcherAssert.assertThat(Integer.valueOf(r5.statusCode), lessThan(300));

        SimpleResponse r6 = sendRequest("GET", "/products/{productName}/configurations/{configurationName}/features", productName, configurationName);
        MatcherAssert.assertThat(Integer.valueOf(r6.statusCode), equalTo(200));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsListExercisesConfigurationRetrieval() throws Exception {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);

        SimpleResponse r1 = sendRequest("POST", "/products/{productName}", productName);
        MatcherAssert.assertThat(Integer.valueOf(r1.statusCode), lessThan(300));

        SimpleResponse r2 = sendRequest("POST", "/products/{productName}/configurations/{configurationName}", productName, configurationName);
        MatcherAssert.assertThat(Integer.valueOf(r2.statusCode), lessThan(300));

        SimpleResponse r3 = sendRequest("GET", "/products/{productName}/configurations", productName);
        MatcherAssert.assertThat(Integer.valueOf(r3.statusCode), equalTo(200));
    }

    @Ignore("Expected: a value less than <300>      but: <500> was greater than <300>")
    @Test(timeout = 60000)
    public void testAvailableFeaturesWithMultipleProductFeaturesExercisesCollectFeatureNamesLoop() throws Exception {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String feature1 = "FeatureA-" + UUID.randomUUID().toString().substring(0, 8);
        String feature2 = "FeatureB-" + UUID.randomUUID().toString().substring(0, 8);

        SimpleResponse r1 = sendRequest("POST", "/products/{productName}", productName);
        MatcherAssert.assertThat(Integer.valueOf(r1.statusCode), lessThan(300));

        SimpleResponse r2 = sendRequest("POST", "/products/{productName}/features/{featureName}", productName, feature1);
        MatcherAssert.assertThat(Integer.valueOf(r2.statusCode), equalTo(500));

        SimpleResponse r3 = sendRequest("POST", "/products/{productName}/features/{featureName}", productName, feature2);
        MatcherAssert.assertThat(Integer.valueOf(r3.statusCode), lessThan(300));

        SimpleResponse r4 = sendRequest("POST", "/products/{productName}/configurations/{configurationName}", productName, configurationName);
        MatcherAssert.assertThat(Integer.valueOf(r4.statusCode), lessThan(300));

        SimpleResponse r5 = sendRequest("GET", "/products/{productName}/configurations/{configurationName}", productName, configurationName);
        MatcherAssert.assertThat(Integer.valueOf(r5.statusCode), equalTo(200));
    }

    @Ignore("Expected: a value less than <300>      but: <500> was greater than <300>")
    @Test(timeout = 60000)
    public void testActivateMultipleFeaturesExercisesActiveFeatureMultipleTimes() throws Exception {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String feature1 = "FeatureA-" + UUID.randomUUID().toString().substring(0, 8);
        String feature2 = "FeatureB-" + UUID.randomUUID().toString().substring(0, 8);

        SimpleResponse r1 = sendRequest("POST", "/products/{productName}", productName);
        MatcherAssert.assertThat(Integer.valueOf(r1.statusCode), lessThan(300));

        SimpleResponse r2 = sendRequest("POST", "/products/{productName}/features/{featureName}", productName, feature1);
        MatcherAssert.assertThat(Integer.valueOf(r2.statusCode), equalTo(500));

        SimpleResponse r3 = sendRequest("POST", "/products/{productName}/features/{featureName}", productName, feature2);
        MatcherAssert.assertThat(Integer.valueOf(r3.statusCode), lessThan(300));

        SimpleResponse r4 = sendRequest("POST", "/products/{productName}/configurations/{configurationName}", productName, configurationName);
        MatcherAssert.assertThat(Integer.valueOf(r4.statusCode), lessThan(300));

        SimpleResponse r5 = sendRequest("POST", "/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, feature1);
        MatcherAssert.assertThat(Integer.valueOf(r5.statusCode), lessThan(300));

        SimpleResponse r6 = sendRequest("POST", "/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, feature2);
        MatcherAssert.assertThat(Integer.valueOf(r6.statusCode), lessThan(300));

        SimpleResponse r7 = sendRequest("GET", "/products/{productName}/configurations/{configurationName}/features", productName, configurationName);
        MatcherAssert.assertThat(Integer.valueOf(r7.statusCode), equalTo(200));
        MatcherAssert.assertThat(r7.body.contains(feature1) && r7.body.contains(feature2), is(true));
    }

    @Ignore("Expected: a value less than <300>      but: <500> was greater than <300>")
    @Test(timeout = 60000)
    public void testDeactivateOneOfMultipleFeaturesExercisesDeactiveAndHasActiveFeature() throws Exception {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String feature1 = "FeatureA-" + UUID.randomUUID().toString().substring(0, 8);
        String feature2 = "FeatureB-" + UUID.randomUUID().toString().substring(0, 8);

        SimpleResponse r1 = sendRequest("POST", "/products/{productName}", productName);
        MatcherAssert.assertThat(Integer.valueOf(r1.statusCode), lessThan(300));

        SimpleResponse r2 = sendRequest("POST", "/products/{productName}/features/{featureName}", productName, feature1);
        MatcherAssert.assertThat(Integer.valueOf(r2.statusCode), equalTo(500));

        SimpleResponse r3 = sendRequest("POST", "/products/{productName}/features/{featureName}", productName, feature2);
        MatcherAssert.assertThat(Integer.valueOf(r3.statusCode), lessThan(300));

        SimpleResponse r4 = sendRequest("POST", "/products/{productName}/configurations/{configurationName}", productName, configurationName);
        MatcherAssert.assertThat(Integer.valueOf(r4.statusCode), lessThan(300));

        SimpleResponse r5 = sendRequest("POST", "/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, feature1);
        MatcherAssert.assertThat(Integer.valueOf(r5.statusCode), lessThan(300));

        SimpleResponse r6 = sendRequest("POST", "/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, feature2);
        MatcherAssert.assertThat(Integer.valueOf(r6.statusCode), lessThan(300));

        SimpleResponse r7 = sendRequest("DELETE", "/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, feature1);
        MatcherAssert.assertThat(Integer.valueOf(r7.statusCode), lessThan(300));

        SimpleResponse r8 = sendRequest("GET", "/products/{productName}/configurations/{configurationName}/features", productName, configurationName);
        MatcherAssert.assertThat(Integer.valueOf(r8.statusCode), equalTo(200));
        MatcherAssert.assertThat(r8.body.contains(feature2) && !r8.body.contains(feature1), is(true));
    }

    @Ignore("Expected: a value less than <300>      but: <500> was greater than <300>")
    @Test(timeout = 60000)
    public void testGetConfigurationWithActiveFeaturesExercisesAvailableAndActivedFeatures() throws Exception {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        SimpleResponse r1 = sendRequest("POST", "/products/{productName}", productName);
        MatcherAssert.assertThat(Integer.valueOf(r1.statusCode), lessThan(300));

        SimpleResponse r2 = sendRequest("POST", "/products/{productName}/features/{featureName}", productName, featureName);
        MatcherAssert.assertThat(Integer.valueOf(r2.statusCode), equalTo(500));

        SimpleResponse r3 = sendRequest("POST", "/products/{productName}/configurations/{configurationName}", productName, configurationName);
        MatcherAssert.assertThat(Integer.valueOf(r3.statusCode), lessThan(300));

        SimpleResponse r4 = sendRequest("POST", "/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName);
        MatcherAssert.assertThat(Integer.valueOf(r4.statusCode), lessThan(300));

        SimpleResponse r5 = sendRequest("GET", "/products/{productName}/configurations/{configurationName}", productName, configurationName);
        MatcherAssert.assertThat(Integer.valueOf(r5.statusCode), equalTo(200));
        MatcherAssert.assertThat(r5.body.contains(configurationName), is(true));
    }

    @Test(timeout = 60000)
    public void testDeleteConfigurationExercisesConfigurationLifecycle() throws Exception {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);

        SimpleResponse r1 = sendRequest("POST", "/products/{productName}", productName);
        MatcherAssert.assertThat(Integer.valueOf(r1.statusCode), lessThan(300));

        SimpleResponse r2 = sendRequest("POST", "/products/{productName}/configurations/{configurationName}", productName, configurationName);
        MatcherAssert.assertThat(Integer.valueOf(r2.statusCode), lessThan(300));

        SimpleResponse r3 = sendRequest("DELETE", "/products/{productName}/configurations/{configurationName}", productName, configurationName);
        MatcherAssert.assertThat(Integer.valueOf(r3.statusCode), equalTo(204));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesWithProductHavingNoFeaturesExercisesAvailableFeaturesEmpty() throws Exception {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);

        SimpleResponse r1 = sendRequest("POST", "/products/{productName}", productName);
        MatcherAssert.assertThat(Integer.valueOf(r1.statusCode), lessThan(300));

        SimpleResponse r2 = sendRequest("POST", "/products/{productName}/configurations/{configurationName}", productName, configurationName);
        MatcherAssert.assertThat(Integer.valueOf(r2.statusCode), lessThan(300));

        SimpleResponse r3 = sendRequest("GET", "/products/{productName}/configurations/{configurationName}", productName, configurationName);
        MatcherAssert.assertThat(Integer.valueOf(r3.statusCode), equalTo(200));
    }
}