package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class NotyPevarTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSubjectBranchI0True() {
        given()
            .when()
                .get("/api/notypevar/28/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectBranchI1TrueI2True() {
        given()
            .when()
                .get("/api/notypevar/7/z")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectBranchI3FalseI2False() {
        given()
            .when()
                .get("/api/notypevar/0/hello")
            .then()
                .statusCode(200);
    }
}