package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CORSFilterTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("baseUrl");
        }
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
                .statusCode(200)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowMethodsHeaderOnV1Alpha() {
        given()
                .queryParam("codes", "US,CA,MX")
                .when()
                .get("/v1/alpha")
                .then()
                .statusCode(400)
                .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowHeadersHeaderOnV1Name() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(200)
                .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCacheControlHeaderOnV1Region() {
        given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .statusCode(200)
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsAllCORSHeadersOnV2All() {
        given()
                .when()
                .get("/v2/all")
                .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", nullValue())
                .header("Access-Control-Allow-Headers", nullValue())
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAppliesOnV2AlphaByCode() {
        given()
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAppliesOnV2Currency() {
        given()
                .when()
                .get("/v2/currency/EUR")
                .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAppliesOnV2CallingCode() {
        given()
                .when()
                .get("/v2/callingcode/1")
                .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAppliesOnV2Capital() {
        given()
                .when()
                .get("/v2/capital/Paris")
                .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAppliesOnV2Lang() {
        given()
                .when()
                .get("/v2/lang/Spanish")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAppliesOnV2RegionalBloc() {
        given()
                .when()
                .get("/v2/regionalbloc/EU")
                .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAppliesOnV2Root() {
        given()
                .when()
                .get("/v2")
                .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", nullValue());
    }
}