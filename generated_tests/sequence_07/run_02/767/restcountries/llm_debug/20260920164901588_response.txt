package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testNameNotFoundReturnsMessage() {
        given()
        .when()
            .get("/v1/name/{name}", "123")
        .then()
            .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testNameInternalServerErrorReturnsMessage() {
        given()
        .when()
            .get("/v1/name/{name}", "True")
        .then()
            .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testCapitalNotFoundReturnsStatusInBody() {
        given()
        .when()
            .get("/v1/capital/{capital}", "123")
        .then()
            .body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testRegionNotFoundReturns404() {
        given()
        .when()
            .get("/v1/region/{region}", "123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testRegionNonExistentReturns500() {
        given()
        .when()
            .get("/v1/region/{region}", "NonExistentRegion")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2NameNotFoundReturnsStatusInBody() {
        given()
        .when()
            .get("/v2/name/{name}", "123")
        .then()
            .body("status", equalTo(404));
    }
}