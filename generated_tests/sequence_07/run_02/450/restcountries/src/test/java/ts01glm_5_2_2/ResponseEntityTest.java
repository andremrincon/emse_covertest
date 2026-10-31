package ts01glm_5_2_2;

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
    public void testGetNameNotFoundReturnsStatusAndMessage() {
        given()
                .when()
                .get("/v1/name/{name}", "123")
                .then()
                .statusCode(404)
                .body("status", equalTo(404))
                .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testGetCapitalNotFoundReturnsStatusAndMessage() {
        given()
                .when()
                .get("/v1/capital/{capital}", "123")
                .then()
                .statusCode(404)
                .body("status", equalTo(404))
                .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testGetRegionNotFoundReturnsStatusAndMessage() {
        given()
                .when()
                .get("/v1/region/{region}", "123")
                .then()
                .statusCode(404)
                .body("status", equalTo(404))
                .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testGetSubregionNotFoundReturnsStatusAndMessage() {
        given()
                .when()
                .get("/v1/subregion/{subregion}", "123")
                .then()
                .statusCode(404)
                .body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testGetLangNotFoundReturnsStatusAndMessage() {
        given()
                .when()
                .get("/v1/lang/{lang}", "123")
                .then()
                .statusCode(404)
                .body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testGetCallingcodeNotFoundReturnsStatusAndMessage() {
        given()
                .when()
                .get("/v1/callingcode/{callingcode}", "abc")
                .then()
                .statusCode(404)
                .body("status", equalTo(404));
    }
}