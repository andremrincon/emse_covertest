package ts01qwen3_7_plus;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductTest {

    private String getBaseUrl() {
        String baseUrl = System.getenv("BASE_URL");
        return baseUrl != null ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testAddFeature() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given()
            .baseUri(getBaseUrl())
            .pathParam("productName", productName)
        .when()
            .post("/products/{productName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .baseUri(getBaseUrl())
            .pathParam("productName", productName)
            .pathParam("featureName", featureName)
        .when()
            .post("/products/{productName}/features/{featureName}")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeature() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given()
            .baseUri(getBaseUrl())
            .pathParam("productName", productName)
        .when()
            .post("/products/{productName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .baseUri(getBaseUrl())
            .pathParam("productName", productName)
            .pathParam("featureName", featureName)
        .when()
            .post("/products/{productName}/features/{featureName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .baseUri(getBaseUrl())
            .pathParam("productName", productName)
            .pathParam("featureName", featureName)
        .when()
            .delete("/products/{productName}/features/{featureName}")
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String feature1 = "Feature1-" + UUID.randomUUID().toString();
        String feature2 = "Feature2-" + UUID.randomUUID().toString();

        given()
            .baseUri(getBaseUrl())
            .pathParam("productName", productName)
        .when()
            .post("/products/{productName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .baseUri(getBaseUrl())
            .pathParam("productName", productName)
            .pathParam("featureName", feature1)
        .when()
            .post("/products/{productName}/features/{featureName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .baseUri(getBaseUrl())
            .pathParam("productName", productName)
            .pathParam("featureName", feature2)
        .when()
            .post("/products/{productName}/features/{featureName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .baseUri(getBaseUrl())
            .pathParam("productName", productName)
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
        .when()
            .post("/products/{productName}/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String feature1 = "Feature1-" + UUID.randomUUID().toString();
        String feature2 = "Feature2-" + UUID.randomUUID().toString();

        given()
            .baseUri(getBaseUrl())
            .pathParam("productName", productName)
        .when()
            .post("/products/{productName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .baseUri(getBaseUrl())
            .pathParam("productName", productName)
            .pathParam("featureName", feature1)
        .when()
            .post("/products/{productName}/features/{featureName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .baseUri(getBaseUrl())
            .pathParam("productName", productName)
            .pathParam("featureName", feature2)
        .when()
            .post("/products/{productName}/features/{featureName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .baseUri(getBaseUrl())
            .pathParam("productName", productName)
            .formParam("sourceFeature", feature1)
            .formParam("excludedFeature", feature2)
        .when()
            .post("/products/{productName}/constraints/excludes")
        .then()
            .statusCode(201);
    }
}