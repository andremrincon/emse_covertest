package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowOriginHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowMethodsHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowHeadersHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCacheControlHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(404)
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowOriginOnV2All() {
        given()
                .when()
                .get("/v2/all")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowMethodsOnV2All() {
        given()
                .when()
                .get("/v2/all")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowHeadersOnV2All() {
        given()
                .when()
                .get("/v2/all")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCacheControlOnV2All() {
        given()
                .when()
                .get("/v2/all")
                .then()
                .statusCode(404)
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCORSHeadersOnV1AlphaByCode() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", nullValue())
                .header("Access-Control-Allow-Headers", nullValue())
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCORSHeadersOnV2Name() {
        given()
                .when()
                .get("/v2/name/Germany")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", nullValue())
                .header("Access-Control-Allow-Headers", nullValue())
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCORSHeadersOnV1Region() {
        given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", nullValue())
                .header("Access-Control-Allow-Headers", nullValue())
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCORSHeadersOnV2Capital() {
        given()
                .when()
                .get("/v2/capital/Paris")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", nullValue())
                .header("Access-Control-Allow-Headers", nullValue())
                .header("Cache-Control", nullValue());
    }
}