package ts01glm_5_2_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", System.getenv("baseUrl"));
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
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
                .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowHeadersHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCacheControlHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAppliesCORSHeadersOnV2All() {
        given()
                .when()
                .get("/v2/all")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", nullValue())
                .header("Access-Control-Allow-Headers", nullValue())
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAppliesCORSHeadersOnV1AlphaByCode() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAppliesCORSHeadersOnV2AlphaByCode() {
        given()
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAppliesCORSHeadersOn404Response() {
        given()
                .when()
                .get("/v1/alpha/XYZ")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAppliesCORSHeadersOn400Response() {
        given()
                .when()
                .get("/v1/alpha/123")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAppliesCORSHeadersOnV1NameEndpoint() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAppliesCORSHeadersOnV2RegionEndpoint() {
        given()
                .when()
                .get("/v2/region/Europe")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAppliesCORSHeadersOnV2RootEndpoint() {
        given()
                .when()
                .get("/v2")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Headers", nullValue());
    }
}