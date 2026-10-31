package ts01qwen3_7_plus_3;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class NotyPevarTest {

    @Test(timeout = 60000)
    public void testNotyPevarBranch1() {
        given()
            .pathParam("i", 28)
            .pathParam("s", "a")
        .when()
            .get("http://localhost:8080/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevarBranch2() {
        given()
            .pathParam("i", 7)
            .pathParam("s", "z")
        .when()
            .get("http://localhost:8080/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevarBranch3() {
        given()
            .pathParam("i", 0)
            .pathParam("s", "a")
        .when()
            .get("http://localhost:8080/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200);
    }
}