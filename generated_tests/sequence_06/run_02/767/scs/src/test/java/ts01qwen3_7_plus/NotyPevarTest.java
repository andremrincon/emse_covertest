package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class NotyPevarTest {

    @Test(timeout = 60000)
    public void testNotyPevarBranchI0True() {
        given()
            .when()
                .get("/api/notypevar/28/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevarBranchI1True() {
        given()
            .when()
                .get("/api/notypevar/7/z")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevarBranchAllFalse() {
        given()
            .when()
                .get("/api/notypevar/0/a")
            .then()
                .statusCode(200);
    }
}