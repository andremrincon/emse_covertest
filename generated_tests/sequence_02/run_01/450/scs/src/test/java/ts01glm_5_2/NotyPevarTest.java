package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

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
    public void testNotyPevarConstructorWithValidInputBranchOne() {
        given()
            .when()
                .get("/api/notypevar/28/zzz")
            .then()
                .statusCode(lessThan(300));

        Response response = given()
            .when()
                .get("/api/notypevar/28/zzz");

        assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testNotyPevarConstructorWithValidInputBranchTwo() {
        given()
            .when()
                .get("/api/notypevar/7/world")
            .then()
                .statusCode(lessThan(300));

        Response response = given()
            .when()
                .get("/api/notypevar/7/world");

        assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testNotyPevarConstructorWithInvalidInputReturns400() {
        Response response = given()
            .when()
                .get("/api/notypevar/abc/valid-string");

        assertEquals(400, response.getStatusCode());
    }
}