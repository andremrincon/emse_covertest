package ts01qwen3_7_plus_2;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class NotyPevarTest {

    @Test(timeout = 60000)
    public void testNotyPevarBranchI0AndI3() {
        given()
            .pathParam("i", 28)
            .pathParam("s", "a")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevarBranchI1() {
        given()
            .pathParam("i", 7)
            .pathParam("s", "a")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevarBranchI2() {
        given()
            .pathParam("i", 2)
            .pathParam("s", "z")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200);
    }
}