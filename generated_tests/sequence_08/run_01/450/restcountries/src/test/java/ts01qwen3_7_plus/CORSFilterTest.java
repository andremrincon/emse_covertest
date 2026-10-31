package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetV1All() {
        given()
            .when()
            .get("/v1/all")
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .when()
            .get("/v1/all");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetV1Alpha() {
        given()
            .when()
            .get("/v1/alpha/US")
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .when()
            .get("/v1/alpha/US");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetV1Currency() {
        given()
            .when()
            .get("/v1/currency/USD")
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .when()
            .get("/v1/currency/USD");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetV1Name() {
        given()
            .when()
            .get("/v1/name/France")
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .when()
            .get("/v1/name/France");

        response.then().statusCode(200);
    }
}