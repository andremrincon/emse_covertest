package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
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
    public void testDeleteProduct() {
        String productName = "Product-" + java.util.UUID.randomUUID().toString();

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