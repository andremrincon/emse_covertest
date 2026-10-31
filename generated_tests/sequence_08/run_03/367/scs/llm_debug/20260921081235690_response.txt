package ts01qwen3_7_plus_2;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class NotyPevarTest {

    private String getBaseUrl() {
        String url = System.getenv("BASE_URL");
        if (url == null || url.isEmpty()) {
            url = System.getProperty("baseUrl", "http://localhost:8080");
        }
        return url;
    }

    @Test(timeout = 60000)
    public void testNotyPevar_I0True_I3True() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("i", 28)
            .pathParam("s", "a")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevar_I1True_I2True() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("i", 7)
            .pathParam("s", "z")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevar_AllFalse() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("i", 0)
            .pathParam("s", "a")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200);
    }
}