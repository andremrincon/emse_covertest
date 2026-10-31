package ts01glm_5_2_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;

public class ProductsServiceTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void addFeatureToProductSuccess() {
        String productName = "prod-" + UUID.randomUUID();
        String featureName = "feat-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
            .formParam("description", "Test feature description")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureToProductDuplicate() {
        String productName = "prod-" + UUID.randomUUID();
        String featureName = "feat-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().formParam("description", "First description").when()
            .post("/products/" + productName + "/features/" + featureName)
            .then().statusCode(lessThan(300));

        given()
            .formParam("description", "Second description")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void addFeatureToProductWithoutDescription() {
        String productName = "prod-" + UUID.randomUUID();
        String featureName = "feat-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        when()
            .post("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void deleteFeatureWithActiveConfiguration() {
        String productName = "prod-" + UUID.randomUUID();
        String featureName = "feat-" + UUID.randomUUID();
        String configName = "config-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().formParam("description", "Test feature").when()
            .post("/products/" + productName + "/features/" + featureName)
            .then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName)
            .then().statusCode(lessThan(300));
        given().when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then().statusCode(lessThan(300));

        when()
            .delete("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeatureWithoutActiveConfiguration() {
        String productName = "prod-" + UUID.randomUUID();
        String featureName = "feat-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().formParam("description", "Test feature").when()
            .post("/products/" + productName + "/features/" + featureName)
            .then().statusCode(lessThan(300));

        when()
            .delete("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void addRequiresConstraintSuccess() {
        String productName = "prod-" + UUID.randomUUID();
        String sourceFeature = "src-feat-" + UUID.randomUUID();
        String requiredFeature = "req-feat-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraintSuccess() {
        String productName = "prod-" + UUID.randomUUID();
        String sourceFeature = "src-feat-" + UUID.randomUUID();
        String excludedFeature = "exc-feat-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addRequiresConstraintWithExistingFeatures() {
        String productName = "prod-" + UUID.randomUUID();
        String sourceFeature = "src-feat-" + UUID.randomUUID();
        String requiredFeature = "req-feat-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraintWithExistingFeatures() {
        String productName = "prod-" + UUID.randomUUID();
        String sourceFeature = "src-feat-" + UUID.randomUUID();
        String excludedFeature = "exc-feat-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void deleteFeatureOfNonExistentProduct() {
        String productName = "nonexistent-prod-" + UUID.randomUUID();
        String featureName = "feat-" + UUID.randomUUID();

        when()
            .delete("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(500);
    }
}