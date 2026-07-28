package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

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
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", equalTo((String) null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowMethodsHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Methods", equalTo((String) null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowHeadersHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Headers", equalTo((String) null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsCacheControlHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(lessThan(300))
                .header("Cache-Control", equalTo((String) null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowOriginHeaderOnV2All() {
        given()
                .when()
                .get("/v2/all")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", equalTo((String) null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowMethodsHeaderOnV2All() {
        given()
                .when()
                .get("/v2/all")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Methods", equalTo((String) null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowHeadersHeaderOnV2All() {
        given()
                .when()
                .get("/v2/all")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Headers", equalTo((String) null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsCacheControlHeaderOnV2All() {
        given()
                .when()
                .get("/v2/all")
                .then()
                .statusCode(lessThan(300))
                .header("Cache-Control", equalTo((String) null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsAllCORSHeadersOnAlphaEndpoint() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", equalTo((String) null))
                .header("Access-Control-Allow-Methods", equalTo((String) null))
                .header("Access-Control-Allow-Headers", equalTo((String) null))
                .header("Cache-Control", equalTo((String) null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsAllCORSHeadersOnNameEndpoint() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", equalTo((String) null))
                .header("Access-Control-Allow-Methods", equalTo((String) null))
                .header("Access-Control-Allow-Headers", equalTo((String) null))
                .header("Cache-Control", equalTo((String) null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsAllCORSHeadersOnRegionEndpoint() {
        given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", equalTo((String) null))
                .header("Access-Control-Allow-Methods", equalTo((String) null))
                .header("Access-Control-Allow-Headers", equalTo((String) null))
                .header("Cache-Control", equalTo((String) null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsAllCORSHeadersOnCapitalEndpoint() {
        given()
                .when()
                .get("/v1/capital/London")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", equalTo((String) null))
                .header("Access-Control-Allow-Methods", equalTo((String) null))
                .header("Access-Control-Allow-Headers", equalTo((String) null))
                .header("Cache-Control", equalTo((String) null));
    }
}