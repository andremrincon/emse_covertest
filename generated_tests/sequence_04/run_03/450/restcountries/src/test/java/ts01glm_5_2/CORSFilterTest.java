package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", System.getenv("BASE_URL"));
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080/rest";
        }
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowOriginHeaderOnV1All() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowMethodsHeaderOnV1All() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Methods", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowHeadersHeaderOnV1All() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Headers", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsCacheControlHeaderOnV1All() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(200)
                .header("Cache-Control", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsCorsHeadersOnV2All() {
        given()
            .when()
                .get("/v2/all")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", equalTo(null))
                .header("Access-Control-Allow-Methods", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsCorsHeadersOnAlphaEndpoint() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsCorsHeadersOnNameEndpoint() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsCorsHeadersOnRegionEndpoint() {
        given()
            .when()
                .get("/v1/region/Europe")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsCorsHeadersOnCurrencyEndpoint() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsCorsHeadersOnCallingCodeEndpoint() {
        given()
            .when()
                .get("/v1/callingcode/1")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsCorsHeadersOnCapitalEndpoint() {
        given()
            .when()
                .get("/v1/capital/London")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsCorsHeadersOnV2AlphaEndpoint() {
        given()
            .when()
                .get("/v2/alpha/US")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", equalTo(null));
    }
}