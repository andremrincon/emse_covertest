package ts01qwen3_7_plus;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductTest {

    @Test(timeout = 60000)
    public void testAddFeatureToProduct() {
        String productName = "Product-AddFeature-" + UUID.randomUUID().toString();
        String featureName = "Feature-1-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
        .when()
            .post("/products/{productName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName)
        .when()
            .post("/products/{productName}/features/{featureName}")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromProduct() {
        String productName = "Product-RemoveFeature-" + UUID.randomUUID().toString();
        String featureName = "Feature-2-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
        .when()
            .post("/products/{productName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName)
        .when()
            .post("/products/{productName}/features/{featureName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName)
        .when()
            .delete("/products/{productName}/features/{featureName}")
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct() {
        String productName = "Product-Requires-" + UUID.randomUUID().toString();
        String feature1 = "Feature-Req1-" + UUID.randomUUID().toString();
        String feature2 = "Feature-Req2-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
        .when()
            .post("/products/{productName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", feature1)
        .when()
            .post("/products/{productName}/features/{featureName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", feature2)
        .when()
            .post("/products/{productName}/features/{featureName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
        .when()
            .post("/products/{productName}/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct() {
        String productName = "Product-Excludes-" + UUID.randomUUID().toString();
        String feature1 = "Feature-Excl1-" + UUID.randomUUID().toString();
        String feature2 = "Feature-Excl2-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
        .when()
            .post("/products/{productName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", feature1)
        .when()
            .post("/products/{productName}/features/{featureName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", feature2)
        .when()
            .post("/products/{productName}/features/{featureName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .formParam("sourceFeature", feature1)
            .formParam("excludedFeature", feature2)
        .when()
            .post("/products/{productName}/constraints/excludes")
        .then()
            .statusCode(201);
    }
}