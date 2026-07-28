package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class NotyPevarTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testNotyPevarValidBranchOne() {
        given()
            .when()
                .get("/api/notypevar/28/hello")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevarValidBranchTwoThree() {
        given()
            .when()
                .get("/api/notypevar/7/world")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevarInvalidInteger() {
        given()
            .when()
                .get("/api/notypevar/abc/valid-string")
            .then()
                .statusCode(400);
    }
}