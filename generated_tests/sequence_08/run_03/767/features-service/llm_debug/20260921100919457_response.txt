package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testGetAllProducts() {
        given()
            .when()
                .get("/products")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddProduct() {
        String productName = "Product-" + java.util.UUID.randomUUID().toString();
        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductByName() {
        String productName = "Product-" + java.util.UUID.randomUUID().toString();
        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/" + productName)
            .then()
                .statusCode(200);
    }
}