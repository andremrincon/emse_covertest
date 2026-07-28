package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class NotyPevarTest {

    private static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testNotyPevar_i0True_i2True_i3True() {
        given()
            .pathParam("i", 28)
            .pathParam("s", "world")
        .when()
            .get(BASE_URL + "/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i1True_i3True() {
        given()
            .pathParam("i", 7)
            .pathParam("s", "abc")
        .when()
            .get(BASE_URL + "/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevar_allFalse() {
        given()
            .pathParam("i", 3)
            .pathParam("s", "abc")
        .when()
            .get(BASE_URL + "/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200);
    }
}