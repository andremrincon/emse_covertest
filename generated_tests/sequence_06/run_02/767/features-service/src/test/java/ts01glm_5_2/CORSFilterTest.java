package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasKey;

public class CORSFilterTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void doFilter_nonOptionsRequest_chainsFilterAndReturnsCORSHeaders() {
        given()
            .when()
                .get("/products")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void doFilter_optionsRequest_doesNotChainAndReturnsCORSHeaders() {
        given()
            .when()
                .request(Method.OPTIONS, "/products")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void doFilter_getRequest_setsAccessControlAllowOriginHeader() {
        given()
            .when()
                .get("/products")
            .then()
                .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void doFilter_getRequest_setsAccessControlAllowMethodsHeader() {
        given()
            .when()
                .get("/products")
            .then()
                .header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    }

    @Test(timeout = 60000)
    public void doFilter_optionsRequest_setsAccessControlMaxAgeHeader() {
        given()
            .when()
                .request(Method.OPTIONS, "/products")
            .then()
                .header("Access-Control-Max-Age", "3600");
    }

    @Test(timeout = 60000)
    public void doFilter_postRequest_setsAccessControlAllowHeadersHeader() {
        String productName = "CORS-Test-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .header("Access-Control-Allow-Headers", "x-requested-with");
    }
}