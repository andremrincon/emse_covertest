package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void doFilterSetsAccessControlAllowOriginHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterSetsAccessControlAllowMethodsHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Access-Control-Allow-Methods", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterSetsAccessControlAllowHeadersHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Access-Control-Allow-Headers", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterSetsCacheControlHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Cache-Control", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterSetsCorsHeadersOnV2All() {
        given()
                .when()
                .get("/v2/all")
                .then()
                .header("Access-Control-Allow-Origin", equalTo(null))
                .header("Access-Control-Allow-Methods", equalTo(null))
                .header("Access-Control-Allow-Headers", equalTo(null))
                .header("Cache-Control", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterSetsCorsHeadersOnAlphaCodeEndpoint() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterSetsCorsHeadersOn404Response() {
        given()
                .when()
                .get("/v1/alpha/XYZ")
                .then()
                .header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterSetsCorsHeadersOn400Response() {
        given()
                .when()
                .get("/v1/alpha/123")
                .then()
                .header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterSetsCorsHeadersOnNameEndpoint() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .header("Access-Control-Allow-Origin", equalTo(null))
                .header("Cache-Control", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterSetsCorsHeadersOnRegionEndpoint() {
        given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .header("Access-Control-Allow-Origin", equalTo(null))
                .header("Access-Control-Allow-Methods", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterSetsCorsHeadersOnV2CapitalEndpoint() {
        given()
                .when()
                .get("/v2/capital/Paris")
                .then()
                .header("Access-Control-Allow-Origin", equalTo(null))
                .header("Access-Control-Allow-Headers", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilterSetsCorsHeadersOnV2AlphaCodesEndpoint() {
        given()
                .queryParam("codes", "US,CA,MX")
                .when()
                .get("/v2/alpha")
                .then()
                .header("Access-Control-Allow-Origin", equalTo(null))
                .header("Cache-Control", equalTo(null));
    }
}