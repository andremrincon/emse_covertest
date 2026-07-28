package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        if (System.getenv("BASE_URL") != null) {
            baseUrl = System.getenv("BASE_URL");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("Ambiguous method overloading for method io.restassured.internal.ValidatableResponseImpl#header. C...")
    @Test(timeout = 60000)
    public void testDoFilterAddsAccessControlAllowOriginHeader() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", (String) null);
    }

    @Ignore("Ambiguous method overloading for method io.restassured.internal.ValidatableResponseImpl#header. C...")
    @Test(timeout = 60000)
    public void testDoFilterAddsAccessControlAllowMethodsHeader() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .header("Access-Control-Allow-Methods", (String) null);
    }

    @Ignore("Ambiguous method overloading for method io.restassured.internal.ValidatableResponseImpl#header. C...")
    @Test(timeout = 60000)
    public void testDoFilterAddsAccessControlAllowHeadersHeader() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(200)
                .header("Access-Control-Allow-Headers", (String) null);
    }

    @Ignore("Ambiguous method overloading for method io.restassured.internal.ValidatableResponseImpl#header. C...")
    @Test(timeout = 60000)
    public void testDoFilterAddsCacheControlHeader() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(200)
                .header("Cache-Control", (String) null);
    }

    @Ignore("Ambiguous method overloading for method io.restassured.internal.ValidatableResponseImpl#header. C...")
    @Test(timeout = 60000)
    public void testDoFilterAppliedToV2AllEndpoint() {
        given()
                .when()
                .get("/v2/all")
                .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", (String) null);
    }

    @Ignore("Ambiguous method overloading for method io.restassured.internal.ValidatableResponseImpl#header. C...")
    @Test(timeout = 60000)
    public void testDoFilterAppliedToV2AlphaEndpoint() {
        given()
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", (String) null);
    }

    @Ignore("Ambiguous method overloading for method io.restassured.internal.ValidatableResponseImpl#header. C...")
    @Test(timeout = 60000)
    public void testDoFilterAppliedToV2CapitalEndpoint() {
        given()
                .when()
                .get("/v2/capital/Paris")
                .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", (String) null);
    }

    @Ignore("Ambiguous method overloading for method io.restassured.internal.ValidatableResponseImpl#header. C...")
    @Test(timeout = 60000)
    public void testDoFilterAppliedToV2RegionEndpoint() {
        given()
                .when()
                .get("/v2/region/Europe")
                .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", (String) null);
    }

    @Ignore("Ambiguous method overloading for method io.restassured.internal.ValidatableResponseImpl#header. C...")
    @Test(timeout = 60000)
    public void testDoFilterAppliedToV2SubregionEndpoint() {
        given()
                .when()
                .get("/v2/subregion/Western%20Europe")
                .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", (String) null);
    }

    @Ignore("Ambiguous method overloading for method io.restassured.internal.ValidatableResponseImpl#header. C...")
    @Test(timeout = 60000)
    public void testDoFilterAppliedToV2LangEndpoint() {
        given()
                .when()
                .get("/v2/lang/Spanish")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", (String) null);
    }

    @Ignore("Ambiguous method overloading for method io.restassured.internal.ValidatableResponseImpl#header. C...")
    @Test(timeout = 60000)
    public void testDoFilterAppliedToV2DemonymEndpoint() {
        given()
                .when()
                .get("/v2/demonym/American")
                .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", (String) null);
    }

    @Ignore("Ambiguous method overloading for method io.restassured.internal.ValidatableResponseImpl#header. C...")
    @Test(timeout = 60000)
    public void testDoFilterAppliedToV2RegionalblocEndpoint() {
        given()
                .when()
                .get("/v2/regionalbloc/EU")
                .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", (String) null);
    }
}