package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    private String getBaseUrl() {
        String envUrl = System.getenv("BASE_URL");
        return envUrl != null ? envUrl : "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testCorsHeaderOrigin() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/v1/all")
        .then()
            .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsHeaderMethods() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/v1/alpha/US")
        .then()
            .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsHeaderHeaders() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/v1/name/France")
        .then()
            .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsHeaderCacheControl() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/v2/all")
        .then()
            .header("Cache-Control", nullValue());
    }
}