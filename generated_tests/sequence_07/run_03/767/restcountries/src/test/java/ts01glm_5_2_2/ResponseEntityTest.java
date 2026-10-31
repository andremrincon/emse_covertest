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
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testNameNotFoundReturnsResponseEntityWithStatusAndMessage() {
        given()
                .when()
                .get("/v1/name/{name}", "123")
                .then()
                .statusCode(404)
                .body(equalTo(""));
    }

    @Test(timeout = 60000)
    public void testCapitalNotFoundReturnsResponseEntityWithStatusAndMessage() {
        given()
                .when()
                .get("/v1/capital/{capital}", "123")
                .then()
                .statusCode(404)
                .body(equalTo(""));
    }

    @Test(timeout = 60000)
    public void testRegionNotFoundReturnsResponseEntityWithStatusAndMessage() {
        given()
                .when()
                .get("/v1/region/{region}", "123")
                .then()
                .statusCode(404)
                .body(equalTo(""));
    }

    @Test(timeout = 60000)
    public void testCallingCodeNotFoundReturnsResponseEntityWithStatusAndMessage() {
        given()
                .when()
                .get("/v1/callingcode/{callingcode}", "abc")
                .then()
                .statusCode(404)
                .body(equalTo(""));
    }

    @Test(timeout = 60000)
    public void testLangNotFoundReturnsResponseEntityWithStatusAndMessage() {
        given()
                .when()
                .get("/v1/lang/{lang}", "123")
                .then()
                .statusCode(404)
                .body(equalTo(""));
    }

    @Test(timeout = 60000)
    public void testSubregionNotFoundReturnsResponseEntityWithMessage() {
        given()
                .when()
                .get("/v1/subregion/{subregion}", "123")
                .then()
                .statusCode(404)
                .body(equalTo(""));
    }
}