package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getMessageViaNameNotFound() {
        given()
                .when()
                .get("/v1/name/{name}", "123")
                .then()
                .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void getStatusViaCapitalNotFound() {
        given()
                .when()
                .get("/v1/capital/{capital}", "123")
                .then()
                .body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void getMessageViaRegionNotFound() {
        given()
                .when()
                .get("/v1/region/{region}", "123")
                .then()
                .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void getStatusViaCallingcodeNotFound() {
        given()
                .when()
                .get("/v1/callingcode/{callingcode}", "abc")
                .then()
                .body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void getMessageViaSubregionNotFound() {
        given()
                .when()
                .get("/v1/subregion/{subregion}", "123")
                .then()
                .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void getStatusViaLangNotFound() {
        given()
                .when()
                .get("/v1/lang/{lang}", "123")
                .then()
                .body("status", equalTo(404));
    }
}