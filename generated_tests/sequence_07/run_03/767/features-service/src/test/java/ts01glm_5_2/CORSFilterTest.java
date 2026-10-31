package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

public class CORSFilterTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void getRequestPassesThroughCORSFilter() {
        RestAssured.given()
        .when()
            .get("/products")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void optionsRequestHandledByCORSFilter() {
        RestAssured.given()
        .when()
            .options("/products")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void corsAllowOriginHeaderSetOnGetResponse() {
        RestAssured.given()
        .when()
            .get("/products")
        .then()
            .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void corsAllowMethodsHeaderSetOnOptionsResponse() {
        RestAssured.given()
        .when()
            .options("/products")
        .then()
            .header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    }

    @Test(timeout = 60000)
    public void corsAllowHeadersHeaderSetOnResponse() {
        RestAssured.given()
        .when()
            .get("/products")
        .then()
            .header("Access-Control-Allow-Headers", "x-requested-with");
    }

    @Test(timeout = 60000)
    public void corsMaxAgeHeaderSetOnOptionsResponse() {
        RestAssured.given()
        .when()
            .options("/products")
        .then()
            .header("Access-Control-Max-Age", "3600");
    }
}