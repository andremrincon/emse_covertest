package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProductsConfigurationFeaturesResourceTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("base.url", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_valid_returns201() {
        String uuid = java.util.UUID.randomUUID().toString();
        String productName = "TestProduct-" + uuid;
        String configurationName = "config-" + uuid;
        String featureName = "feature-" + uuid;

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_excludesConstraint_returns500() {
        String uuid = java.util.UUID.randomUUID().toString();
        String productName = "TestProduct-" + uuid;
        String configurationName = "config-" + uuid;
        String featureA = "featureA-" + uuid;
        String featureB = "featureB-" + uuid;

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureB).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", featureA)
            .formParam("excludedFeature", featureB)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
            .then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureA)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureB)
            .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void deleteFeature_valid_returns204() {
        String uuid = java.util.UUID.randomUUID().toString();
        String productName = "TestProduct-" + uuid;
        String configurationName = "config-" + uuid;
        String featureName = "feature-" + uuid;

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeature_requiresConstraintViolation_returns500() {
        String uuid = java.util.UUID.randomUUID().toString();
        String productName = "TestProduct-" + uuid;
        String configurationName = "config-" + uuid;
        String featureA = "featureA-" + uuid;
        String featureB = "featureB-" + uuid;

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureB).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", featureA)
            .formParam("requiredFeature", featureB)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
            .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureA)
            .then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureB)
            .then().statusCode(lessThan(300));

        given().when()
            .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureB)
            .then()
            .statusCode(500);
    }
}