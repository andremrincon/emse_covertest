package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URL;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class CORSFilterTest {
    @BeforeClass
    public static void setup() throws Exception {
        String raw = System.getenv("BASE_URL");
        if (raw == null || raw.isEmpty()) {
            raw = System.getProperty("base.url", "http://localhost:8080/rest");
        }
        URL url = new URL(raw);
        StringBuilder base = new StringBuilder();
        base.append(url.getProtocol()).append("://").append(url.getHost());
        if (url.getPort() != -1 && url.getPort() != url.getDefaultPort()) {
            base.append(":").append(url.getPort());
        }
        RestAssured.baseURI = base.toString();
        String path = url.getPath();
        if (path == null || path.isEmpty()) {
            RestAssured.basePath = "";
        } else {
            RestAssured.basePath = path;
        }
    }

    @Test(timeout = 60000)
    public void testAllowOriginHeaderOnV1All() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/all");
        act.then().header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testAllowMethodsHeaderOnV1Alpha() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        act.then().header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testAllowHeadersPresentOnV1Currency() {
        given().when().get("/v1/currency/USD").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/USD");
        act.then().header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCacheControlHeaderOnV2All() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/all");
        act.then().header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testOptionsRequestIncludesCorsHeaders() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response act = given().when().options("/v1/alpha/US");
        act.then().header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testPostV1ReturnsMethodNotAllowed405() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().post("/v1");
        act.then().statusCode(405);
    }

    @Test(timeout = 60000)
    public void testAlphaInvalidReturns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/123");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testAlphaNotFoundReturns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/XYZ");
        act.then().statusCode(404);
    }
}