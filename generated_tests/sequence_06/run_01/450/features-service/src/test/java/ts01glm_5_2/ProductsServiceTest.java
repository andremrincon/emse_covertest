package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsServiceTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void addFeatureToProduct_success() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Test feature description")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureToProduct_duplicateFeature_returnsError() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "First feature")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Duplicate feature")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void addFeatureToProduct_withoutDescription() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void deleteFeatureOfProduct_featureActiveInConfiguration_removesFromConfig() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "conf-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeatureOfProduct_featureNotInAnyConfiguration() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeatureOfProduct_nonExistentFeature_returnsError() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/" + productName + "/features/non-existent-feature")
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void addRequiresConstraintToProduct_success() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addRequiresConstraintToProduct_nonExistentProduct_returnsError() {
        String productName = "non-existent-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", "featureA")
            .formParam("requiredFeature", "featureB")
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraintToProduct_success() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraintToProduct_nonExistentProduct_returnsError() {
        String productName = "non-existent-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", "featureA")
            .formParam("excludedFeature", "featureB")
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void deleteFeatureOfProduct_multipleConfigurationsWithFeature() {
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

        given()
        .when()
            .delete("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(204);
    }
}