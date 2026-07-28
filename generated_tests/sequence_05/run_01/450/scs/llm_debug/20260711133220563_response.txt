package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class NotyPevarTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testNotyPevarBranch1() {
        given()
                .when()
                .get(baseUrl + "/api/notypevar/28/world")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevarBranch2() {
        given()
                .when()
                .get(baseUrl + "/api/notypevar/7/a")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevarBranch3() {
        given()
                .when()
                .get(baseUrl + "/api/notypevar/2/world")
                .then()
                .statusCode(200);
    }
}