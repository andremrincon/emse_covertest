package ts01qwen3_7_plus;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintRequiresTest {

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct() {
        String productName = "Enterprise-Server-X1-" + UUID.randomUUID().toString();

        given()
            .when()
            .post("http://localhost:8080/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", "RAID-Controller-Card")
            .formParam("requiredFeature", "128GB-ECC-RAM")
            .when()
            .post("http://localhost:8080/products/" + productName + "/constraints/requires")
            .then()
            .statusCode(201);
    }
}