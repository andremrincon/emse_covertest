package ts01qwen3_7_plus_2;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint() {
        String productName = "Laptop-Pro-15-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", "CPU-i9-13900H")
            .formParam("excludedFeature", "Integrated-Graphics-Only")
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductWithExcludesConstraint() {
        String productName = "Custom-T-Shirt-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", "Color-Black")
            .formParam("excludedFeature", "Logo-White-On-Black")
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintWithDifferentFeatures() {
        String productName = "Workstation-Z8-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", "PSU-Redundant")
            .formParam("excludedFeature", "OS-Home")
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(201);
    }
}