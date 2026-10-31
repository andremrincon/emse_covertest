package ts01qwen3_7_plus_3;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class NotyPevarTest {

    @Test(timeout = 60000)
    public void testNotyPevarAllFalse() {
        given()
            .pathParam("i", 0)
            .pathParam("s", "a")
        .when()
            .get("http://localhost:8080/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevarI2True() {
        given()
            .pathParam("i", 0)
            .pathParam("s", "z")
        .when()
            .get("http://localhost:8080/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevarI3True() {
        given()
            .pathParam("i", 6)
            .pathParam("s", "a")
        .when()
            .get("http://localhost:8080/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200);
    }
}