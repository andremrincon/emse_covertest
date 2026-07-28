package ts01qwen3_7_plus;

import org.junit.Before;
import org.junit.Test;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class NotyPevarTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testNotyPevarBranchI0True() {
        given()
            .when()
                .get("/api/notypevar/28/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevarBranchI1I2True() {
        given()
            .when()
                .get("/api/notypevar/7/world")
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