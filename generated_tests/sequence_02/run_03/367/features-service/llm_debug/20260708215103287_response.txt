package ts01qwen3_7_plus;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DuplicatedObjectExceptionTest {

    @Test(timeout = 60000)
    public void testCreateDuplicateProduct() {
        String productName = "DuplicateProduct-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
        .when()
            .post("/products/{productName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
        .when()
            .post("/products/{productName}")
        .then()
            .statusCode(201);
    }
}