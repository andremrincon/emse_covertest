package ts01qwen3_7_plus;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @Test(timeout = 60000)
    public void testGetProductsCoversDoFilterTrueBranch() {
        given()
            .when()
                .get("/products")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOptionsProductsCoversDoFilterFalseBranch() {
        given()
            .when()
                .options("/products")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetProductByNameCoversFilterWithValidProduct() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();

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