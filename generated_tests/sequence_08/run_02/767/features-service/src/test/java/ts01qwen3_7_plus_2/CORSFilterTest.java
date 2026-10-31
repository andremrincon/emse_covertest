package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @Before
    public void setup() {
        RestAssured.baseURI = System.getProperty("test.base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testCORSFilterWithNonOptionsRequest() {
        String productName = "TestProduct-" + System.currentTimeMillis();
        given()
            .pathParam("productName", productName)
        .when()
            .post("/products/{productName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
        .when()
            .get("/products/{productName}")
        .then()
            .header("Access-Control-Allow-Origin", equalTo("*"));
    }

    @Test(timeout = 60000)
    public void testCORSFilterWithOptionsRequest() {
        String productName = "TestProduct-" + System.currentTimeMillis();
        given()
            .pathParam("productName", productName)
        .when()
            .post("/products/{productName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
        .when()
            .options("/products/{productName}")
        .then()
            .header("Access-Control-Allow-Methods", equalTo("POST, PUT, GET, OPTIONS, DELETE"));
    }

    @Test(timeout = 60000)
    public void testCORSFilterHeadersOnPostRequest() {
        String productName = "TestProduct-" + System.currentTimeMillis();
        given()
            .pathParam("productName", productName)
        .when()
            .post("/products/{productName}")
        .then()
            .header("Access-Control-Max-Age", equalTo("3600"));
    }
}