package ts01glm_5_2;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

public class CORSFilterTest {

    private String baseUrl;

    @Before
    public void setUp() {
        String base = System.getProperty("baseUrl", System.getenv("baseUrl"));
        if (base != null && !base.isEmpty()) {
            baseUrl = base;
        } else {
            baseUrl = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void doGetRequestPassesThroughCORSFilter() throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("Origin", "http://example.com");
        headers.put("Content-Type", "application/json");
        Response resp = sendRequest("GET", "/products", headers, null);
        assertThat(resp.getStatusCode(), lessThan(300));
    }

    @Test(timeout = 60000)
    public void doOptionsRequestHandledByCORSFilter() throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("Origin", "http://example.com");
        Response resp = sendRequest("OPTIONS", "/products", headers, null);
        String v = resp.getHeader("Access-Control-Allow-Origin");
        assertThat(v, equalTo("*"));
    }

    @Test(timeout = 60000)
    public void doPostRequestPassesThroughCORSFilter() throws Exception {
        String productName = "CORS-Test-Product-" + UUID.randomUUID().toString().substring(0, 8);
        Map<String, String> headers = new HashMap<>();
        headers.put("Origin", "http://example.com");
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        Response resp = sendRequest("POST", "/products/" + encodePath(productName), headers, "");
        assertThat(resp.getStatusCode(), lessThan(300));
    }

    @Test(timeout = 60000)
    public void doDeleteRequestPassesThroughCORSFilter() throws Exception {
        String productName = "CORS-Delete-Test-" + UUID.randomUUID().toString().substring(0, 8);
        Map<String, String> headers = new HashMap<>();
        headers.put("Origin", "http://example.com");
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        Response createResp = sendRequest("POST", "/products/" + encodePath(productName), headers, "");
        assertThat(createResp.getStatusCode(), lessThan(300));

        Map<String, String> delHeaders = new HashMap<>();
        delHeaders.put("Origin", "http://example.com");
        Response delResp = sendRequest("DELETE", "/products/" + encodePath(productName), delHeaders, null);
        assertThat(delResp.getStatusCode(), equalTo(204));
    }

    @Test(timeout = 60000)
    public void corsFilterSetsAccessControlAllowMethodsHeader() throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("Origin", "http://example.com");
        Response resp = sendRequest("OPTIONS", "/products", headers, null);
        String v = resp.getHeader("Access-Control-Allow-Methods");
        assertThat(v, equalTo("POST, PUT, GET, OPTIONS, DELETE"));
    }

    @Test(timeout = 60000)
    public void corsFilterSetsAccessControlMaxAgeHeader() throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("Origin", "http://example.com");
        Response resp = sendRequest("OPTIONS", "/products", headers, null);
        String v = resp.getHeader("Access-Control-Max-Age");
        assertThat(v, equalTo("3600"));
    }

    private Response sendRequest(String method, String path, Map<String, String> headers, String body) throws Exception {
        URL url = new URL(concatUrl(path));
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setInstanceFollowRedirects(false);
        if (headers != null) {
            for (Map.Entry<String, String> e : headers.entrySet()) {
                conn.setRequestProperty(e.getKey(), e.getValue());
            }
        }
        if (body != null && ("POST".equals(method) || "PUT".equals(method))) {
            conn.setDoOutput(true);
            byte[] bytes = body.getBytes("UTF-8");
            conn.setRequestProperty("Content-Length", Integer.toString(bytes.length));
            OutputStream os = null;
            try {
                os = conn.getOutputStream();
                os.write(bytes);
                os.flush();
            } finally {
                if (os != null) try { os.close(); } catch (Exception ignored) {}
            }
        }
        int status = conn.getResponseCode();
        Map<String, List<String>> hdrs = conn.getHeaderFields();
        InputStream is = null;
        try {
            if (status >= 200 && status < 400) {
                try {
                    is = conn.getInputStream();
                    drain(is);
                } catch (Exception ignored) {
                }
            } else {
                try {
                    is = conn.getErrorStream();
                    if (is != null) drain(is);
                } catch (Exception ignored) {
                }
            }
        } finally {
            if (is != null) try { is.close(); } catch (Exception ignored) {}
            conn.disconnect();
        }
        return new Response(status, hdrs);
    }

    private String concatUrl(String path) {
        if (path == null) path = "";
        if (baseUrl.endsWith("/") && path.startsWith("/")) {
            return baseUrl + path.substring(1);
        } else if (!baseUrl.endsWith("/") && !path.startsWith("/")) {
            return baseUrl + "/" + path;
        } else {
            return baseUrl + path;
        }
    }

    private void drain(InputStream is) throws Exception {
        byte[] buf = new byte[1024];
        while (is.read(buf) >= 0) {}
    }

    private String encodePath(String s) {
        try {
            return java.net.URLEncoder.encode(s, "UTF-8").replace("+", "%20");
        } catch (Exception e) {
            return s;
        }
    }

    private static class Response {
        private final int statusCode;
        private final Map<String, List<String>> headers;

        Response(int statusCode, Map<String, List<String>> headers) {
            this.statusCode = statusCode;
            this.headers = headers;
        }

        int getStatusCode() {
            return statusCode;
        }

        String getHeader(String name) {
            if (name == null) return null;
            if (headers == null) return null;
            for (Map.Entry<String, List<String>> e : headers.entrySet()) {
                String key = e.getKey();
                if (key == null) continue;
                if (key.equalsIgnoreCase(name) && e.getValue() != null && !e.getValue().isEmpty()) {
                    return e.getValue().get(0);
                }
            }
            return null;
        }

        Map<String, List<String>> getHeaders() {
            return headers;
        }
    }
}