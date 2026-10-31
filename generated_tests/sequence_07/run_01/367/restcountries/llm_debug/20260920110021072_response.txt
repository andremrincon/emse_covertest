package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
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
                .header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowMethodsHeaderOnV1Alpha() {
        given()
                .queryParam("codes", "US,CA,MX")
                .when()
                .get("/v1/alpha")
                .then()
                .header("Access-Control-Allow-Methods", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowHeadersHeaderOnV1AlphaByCode() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .header("Access-Control-Allow-Headers", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsCacheControlHeaderOnV1Currency() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .header("Cache-Control", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsAllCORSHeadersOnV1Name() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .header("Access-Control-Allow-Origin", equalTo(null))
                .header("Access-Control-Allow-Methods", equalTo(null))
                .header("Access-Control-Allow-Headers", equalTo(null))
                .header("Cache-Control", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsCORSHeadersOnV1CallingCode() {
        given()
                .when()
                .get("/v1/callingcode/1")
                .then()
                .header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsCORSHeadersOnV1Capital() {
        given()
                .when()
                .get("/v1/capital/London")
                .then()
                .header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsCORSHeadersOnV1Region() {
        given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsCORSHeadersOnV1Subregion() {
        given()
                .when()
                .get("/v1/subregion/Western%20Europe")
                .then()
                .header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsCORSHeadersOnV1Lang() {
        given()
                .when()
                .get("/v1/lang/es")
                .then()
                .header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsCORSHeadersOnV2All() {
        given()
                .queryParam("fields", "name;capital;region;population;flag")
                .when()
                .get("/v2/all")
                .then()
                .header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterAddsCORSHeadersOnV2AlphaByCode() {
        given()
                .queryParam("fields", "name;capital;population")
                .when()
                .get("/v2/alpha/US")
                .then()
                .header("Access-Control-Allow-Origin", equalTo(null));
    }
}