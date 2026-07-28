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
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testV1NameNotFoundReturnsResponseEntityStatusAndMessage() {
        given()
            .when()
            .get("/v1/name/123")
            .then()
            .body("status", equalTo(404))
            .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testV1CapitalNotFoundReturnsResponseEntityStatusAndMessage() {
        given()
            .when()
            .get("/v1/capital/123")
            .then()
            .body("status", equalTo(404))
            .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testV1RegionNotFoundReturnsResponseEntityStatusAndMessage() {
        given()
            .when()
            .get("/v1/region/123")
            .then()
            .body("status", equalTo(404))
            .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testV2NameNotFoundReturnsResponseEntityStatusAndMessage() {
        given()
            .when()
            .get("/v2/name/123")
            .then()
            .body("status", equalTo(404))
            .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testV2CapitalNotFoundReturnsResponseEntityStatusAndMessage() {
        given()
            .when()
            .get("/v2/capital/12345")
            .then()
            .body("status", equalTo(404))
            .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testV2RegionalblocNotFoundReturnsResponseEntityStatusAndMessage() {
        given()
            .when()
            .get("/v2/regionalbloc/123")
            .then()
            .body("status", equalTo(404))
            .body("message", equalTo("Not Found"));
    }
}