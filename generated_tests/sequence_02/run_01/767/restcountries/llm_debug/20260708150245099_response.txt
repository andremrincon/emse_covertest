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
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetNameNotFoundReturnsStatusCode() {
        given()
                .when()
                .get("/v1/name/123")
                .then()
                .statusCode(equalTo(404));
    }

    @Test(timeout = 60000)
    public void testGetNameServerErrorReturnsStatusCode() {
        given()
                .when()
                .get("/v1/name/True")
                .then()
                .statusCode(equalTo(404));
    }

    @Test(timeout = 60000)
    public void testGetCapitalNotFoundReturnsMessage() {
        given()
                .when()
                .get("/v1/capital/123")
                .then()
                .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testGetRegionServerErrorReturnsMessage() {
        given()
                .when()
                .get("/v1/region/True")
                .then()
                .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testV2GetCapitalNotFoundReturnsStatus() {
        given()
                .when()
                .get("/v2/capital/12345")
                .then()
                .body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testV2GetRegionServerErrorReturnsStatus() {
        given()
                .when()
                .get("/v2/region/True")
                .then()
                .body("status", equalTo(404));
    }
}