package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

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
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void testNotyPevarBranchI0_xPlusYEquals56() {
        given()
            .when()
                .get("/api/notypevar/28/test")
            .then()
                .statusCode(200)
                .body(notNullValue());
    }

    @Test(timeout = 60000)
    public void testNotyPevarBranchI1_helloConcatEqualsHello7() {
        given()
            .when()
                .get("/api/notypevar/7/zzz")
            .then()
                .statusCode(200)
                .body(notNullValue());
    }

    @Test(timeout = 60000)
    public void testNotyPevarBranchesI2I3_stringCompareAndYGreaterThan5() {
        given()
            .when()
                .get("/api/notypevar/10/zzz")
            .then()
                .statusCode(200)
                .body(notNullValue());
    }
}