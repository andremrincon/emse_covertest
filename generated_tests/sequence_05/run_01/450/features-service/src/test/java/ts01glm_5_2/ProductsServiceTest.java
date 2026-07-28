package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductsServiceTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void addFeatureToProduct_withDescription_succeeds() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
            .formParam("description", "A test feature description")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureToProduct_withoutDescription_succeeds() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureToProduct_duplicateFeature_returnsError() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(greaterThanOrEqualTo(400));
    }

    @Test(timeout = 60000)
    public void deleteFeatureOfProduct_featureActiveInConfiguration_succeeds() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "conf-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/features/" + featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeatureOfProduct_featureNotInAnyConfiguration_succeeds() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/features/" + featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeatureOfProduct_featureActiveInMultipleConfigurations_succeeds() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);
        String config1 = "conf1-" + UUID.randomUUID().toString().substring(0, 8);
        String config2 = "conf2-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + config1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + config2).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + config1 + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + config2 + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/features/" + featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void addRequiresConstraintToProduct_validFeatures_succeeds() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-" + UUID.randomUUID().toString().substring(0, 8);

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
    public void addExcludesConstraintToProduct_validFeatures_succeeds() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-" + UUID.randomUUID().toString().substring(0, 8);

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
    public void addRequiresConstraintToProduct_nonExistentProduct_returnsError() {
        String productName = "nonexistent-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .formParam("sourceFeature", "featureA")
            .formParam("requiredFeature", "featureB")
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(greaterThanOrEqualTo(400));
    }

    @Test(timeout = 60000)
    public void addExcludesConstraintToProduct_nonExistentProduct_returnsError() {
        String productName = "nonexistent-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .formParam("sourceFeature", "featureA")
            .formParam("excludedFeature", "featureB")
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(greaterThanOrEqualTo(400));
    }
}