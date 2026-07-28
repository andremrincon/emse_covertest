package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {

    private static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testNotyPevar_branchI0True_I2True_I3True() {
        given()
            .when()
                .get(BASE_URL + "/api/notypevar/28/z")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_branchI1True_I3True() {
        given()
            .when()
                .get(BASE_URL + "/api/notypevar/7/a")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_allBranchesFalse() {
        given()
            .when()
                .get(BASE_URL + "/api/notypevar/3/a")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }
}