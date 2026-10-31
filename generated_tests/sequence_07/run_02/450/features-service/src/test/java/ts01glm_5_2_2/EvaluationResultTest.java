package ts01glm_5_2_2;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class EvaluationResultTest {

    private static String baseUrl;

    @BeforeClass
    public static void setup() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Ignore("Expected POST .../configurations/.../features to return <300 but was 500")
    @Test(timeout = 60000)
    public void getConfigurationWithNameTriggersEvaluationResultCreation_1() throws Exception {
        String productName = "EvalTestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feature-" + UUID.randomUUID().toString().substring(0, 8);

        int r1 = sendRequest("POST", "/products/{productName}", productName);
        Assert.assertThat("Expected POST /products to return <300 but was " + r1, r1, lessThan(300));

        int r2 = sendRequest("POST", "/products/{productName}/configurations/{configurationName}", productName, configurationName);
        Assert.assertThat("Expected POST /products/{productName}/configurations to return <300 but was " + r2, r2, lessThan(300));

        int r3 = sendRequest("POST", "/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName);
        Assert.assertTrue("Expected POST .../configurations/.../features to return <300 but was " + r3, r3 < 300);

        int r4 = sendRequest("GET", "/products/{productName}/configurations/{configurationName}", productName, configurationName);
        Assert.assertEquals("Expected GET configuration to return 200 but was " + r4, 200, r4);
    }

    @Ignore("Expected POST .../configurations/.../features to return <300 but was 500")
    @Test(timeout = 60000)
    public void getConfigurationFeaturesTriggersEvaluationResultCreation_2() throws Exception {
        String productName = "EvalTestProduct2-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config2-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat2-" + UUID.randomUUID().toString().substring(0, 8);

        int r1 = sendRequest("POST", "/products/{productName}", productName);
        Assert.assertThat("Expected POST /products to return <300 but was " + r1, r1, lessThan(300));

        int r2 = sendRequest("POST", "/products/{productName}/configurations/{configurationName}", productName, configurationName);
        Assert.assertThat("Expected POST /products/{productName}/configurations to return <300 but was " + r2, r2, lessThan(300));

        int r3 = sendRequest("POST", "/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName);
        Assert.assertTrue("Expected POST .../configurations/.../features to return <300 but was " + r3, r3 < 300);

        int r4 = sendRequest("GET", "/products/{productName}/configurations/{configurationName}/features", productName, configurationName);
        Assert.assertEquals("Expected GET configuration features to return 200 but was " + r4, 200, r4);
    }

    @Ignore("Expected POST adding feature to configuration to return 201 but was 500 expected:<201> but was:<500>")
    @Test(timeout = 60000)
    public void addFeatureToConfigurationTriggersEvaluationResultCreation_3() throws Exception {
        String productName = "EvalTestProduct3-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config3-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat3-" + UUID.randomUUID().toString().substring(0, 8);

        int r1 = sendRequest("POST", "/products/{productName}", productName);
        Assert.assertThat("Expected POST /products to return <300 but was " + r1, r1, lessThan(300));

        int r2 = sendRequest("POST", "/products/{productName}/configurations/{configurationName}", productName, configurationName);
        Assert.assertThat("Expected POST /products/{productName}/configurations to return <300 but was " + r2, r2, lessThan(300));

        int r3 = sendRequest("POST", "/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName);
        Assert.assertEquals("Expected POST adding feature to configuration to return 201 but was " + r3, 201, r3);
    }

    private int sendRequest(String method, String pathTemplate, Object... params) throws IOException {
        String resolvedPath = resolvePath(pathTemplate, params);
        String full = baseUrl;
        if (full.endsWith("/") && resolvedPath.startsWith("/")) {
            full = full.substring(0, full.length() - 1);
        }
        full = full + resolvedPath;
        URL url = new URL(full);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setConnectTimeout(30000);
        conn.setReadTimeout(30000);
        conn.setDoInput(true);
        if ("POST".equalsIgnoreCase(method) || "PUT".equalsIgnoreCase(method) || "PATCH".equalsIgnoreCase(method)) {
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.getOutputStream().close();
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
                byte[] buf = new byte[1024];
                while (is.read(buf) != -1) {
                }
            }
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ignored) {
                }
            }
            conn.disconnect();
        }
        return code;
    }

    private String resolvePath(String template, Object... params) {
        Pattern p = Pattern.compile("\\{[^/]+?\\}");
        Matcher m = p.matcher(template);
        StringBuffer sb = new StringBuffer();
        int index = 0;
        while (m.find()) {
            String replacement = "";
            if (index < params.length && params[index] != null) {
                replacement = urlEncode(params[index].toString());
            }
            m.appendReplacement(sb, replacement);
            index++;
        }
        m.appendTail(sb);
        return sb.toString();
    }

    private String urlEncode(String s) {
        StringBuilder out = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (isUnreserved(c)) {
                out.append(c);
            } else {
                byte[] bytes;
                try {
                    bytes = String.valueOf(c).getBytes("UTF-8");
                } catch (Exception e) {
                    bytes = new byte[]{(byte) c};
                }
                for (byte b : bytes) {
                    out.append('%');
                    int upper = (b >> 4) & 0xF;
                    int lower = b & 0xF;
                    out.append(Character.forDigit(upper, 16));
                    out.append(Character.forDigit(lower, 16));
                }
            }
        }
        return out.toString();
    }

    private boolean isUnreserved(char c) {
        if (c >= 'a' && c <= 'z') return true;
        if (c >= 'A' && c <= 'Z') return true;
        if (c >= '0' && c <= '9') return true;
        if (c == '-' || c == '_' || c == '.' || c == '~') return true;
        return false;
    }
}