package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testDoFilterWithGetRequest() {
        String productName = "ProductGet-" + System.currentTimeMillis();

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
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDoFilterWithOptionsRequest() {
        String productName = "ProductOpt-" + System.currentTimeMillis();

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
            .statusCode(200);
    }
}