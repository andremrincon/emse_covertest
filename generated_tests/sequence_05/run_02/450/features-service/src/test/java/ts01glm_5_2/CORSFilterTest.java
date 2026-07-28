package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CORSFilterTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testGetRequestPassesThroughFilterChain_1() {
        given()
            .when()
            .get("/products")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOptionsRequestHandledByFilter_2() {
        given()
            .when()
            .request(Method.OPTIONS, "/products")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCorsAllowOriginHeaderOnGet_3() {
        given()
            .when()
            .get("/products")
            .then()
            .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testCorsAllowMethodsHeaderOnOptions_4() {
        given()
            .when()
            .request(Method.OPTIONS, "/products")
            .then()
            .header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    }

    @Test(timeout = 60000)
    public void testCorsAllowHeadersHeaderOnGet_5() {
        given()
            .when()
            .get("/products")
            .then()
            .header("Access-Control-Allow-Headers", "x-requested-with");
    }

    @Test(timeout = 60000)
    public void testCorsMaxAgeHeaderOnOptions_6() {
        given()
            .when()
            .request(Method.OPTIONS, "/products")
            .then()
            .header("Access-Control-Max-Age", "3600");
    }
}