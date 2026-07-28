package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @Before
    public void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
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
        given()
            .when()
                .post("/products/TestProductToDelete")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .delete("/products/TestProductToDelete")
            .then()
                .statusCode(204);
    }
}