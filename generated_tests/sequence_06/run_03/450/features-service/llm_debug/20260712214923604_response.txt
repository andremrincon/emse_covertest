package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testGetProducts() {
        given()
                .when()
                .get("/products")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOptionsRequest() {
        given()
                .when()
                .options("/products")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteProduct() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();

        given()
                .when()
                .post("/products/" + productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .delete("/products/" + productName)
                .then()
                .statusCode(204);
    }
}