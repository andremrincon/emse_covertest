package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;

public class NotyPevarTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("baseUrl");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testNotyPevarBranchI0TrueAndI2FalseAndI3True_1() {
        RestAssured
            .given()
            .when()
                .get("/api/notypevar/28/a")
            .then()
                .statusCode(200)
                .body(Matchers.equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevarBranchI1TrueAndI2True_2() {
        RestAssured
            .given()
            .when()
                .get("/api/notypevar/7/zzzz")
            .then()
                .statusCode(200)
                .body(Matchers.equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevarAllBranchesFalse_3() {
        RestAssured
            .given()
            .when()
                .get("/api/notypevar/5/a")
            .then()
                .statusCode(200)
                .body(Matchers.equalTo("0"));
    }
}