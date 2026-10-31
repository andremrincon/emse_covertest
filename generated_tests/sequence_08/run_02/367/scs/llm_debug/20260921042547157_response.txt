package ts01qwen3_7_plus_2;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class NotyPevarTest {

    @Test(timeout = 60000)
    public void testNotyPevar_I28_SWorld() {
        given()
            .pathParam("i", 28)
            .pathParam("s", "world")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevar_I7_SA() {
        given()
            .pathParam("i", 7)
            .pathParam("s", "a")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevar_I0_SA() {
        given()
            .pathParam("i", 0)
            .pathParam("s", "a")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200);
    }
}