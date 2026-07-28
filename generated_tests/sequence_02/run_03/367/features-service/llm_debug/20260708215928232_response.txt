package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @BeforeClass
    public static void init() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test(timeout = 60000)
    public void testNonOptionsRequestPassesThroughFilterAndReturns200() {
        String product = "test-prod-" + UUID.randomUUID().toString();
        given().contentType(ContentType.URLENC).when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/features", product);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOptionsRequestReturnsCORSAllowOriginHeader() {
        String product = "test-prod-" + UUID.randomUUID().toString();
        given().contentType(ContentType.URLENC).when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response resp = given().header("Origin", "http://example.com").header("Access-Control-Request-Method", "POST").when().options("/products/{productName}/features", product);
        resp.then().header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testGetRequestIncludesCORSAllowMethodsHeader() {
        String product = "test-prod-" + UUID.randomUUID().toString();
        given().contentType(ContentType.URLENC).when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/features", product);
        resp.then().header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    }
}