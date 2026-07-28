package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class ProductTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void addFeatureToProductReturns201() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void removeFeatureFromProductReturns204() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));

        given().when()
                .delete("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void findProductFeatureByNameFoundWhenAddingRequiresConstraint() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));

        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void findProductFeatureByNameNotFoundReturns500() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String nonExistingFeature = "NonExistingFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", nonExistingFeature)
                .formParam("requiredFeature", "SomeOtherFeature")
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void hasFeatureNamedTrueWhenAddingFeatureToConfiguration() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void hasFeatureNamedFalseWhenFeatureDoesNotExistReturns500() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String nonExistingFeature = "NonExistingFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, nonExistingFeature)
                .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void addFeatureConstraintExcludesReturns201() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));

        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when()
                .post("/products/{productName}/constraints/excludes", productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void buildWithFeaturesReturnsProductWithFeaturesOnGet() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given().when()
                .get("/products/{productName}", productName)
                .then()
                .statusCode(200)
                .body("features", hasItem(hasEntry("name", featureName)));
    }

    @Test(timeout = 60000)
    public void getProductFeaturesReturns200() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given().when()
                .get("/products/{productName}/features", productName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void removeFeatureFromConfigurationReturns204() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        given().when()
                .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void findProductFeatureByNameCaseInsensitiveWhenAddingConstraint() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "CamelCaseFeature";
        String upperCaseName = "CAMELCASEFEATURE";

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", upperCaseName)
                .formParam("requiredFeature", featureName)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void deleteNonExistingFeatureReturns500() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String nonExistingFeature = "NonExistingFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().when()
                .delete("/products/{productName}/features/{featureName}", productName, nonExistingFeature)
                .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void getAllProductsReturns200() {
        given().when()
                .get("/products")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void deleteProductReturns204() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().when()
                .delete("/products/{productName}", productName)
                .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void getConfigurationsForProductReturns200() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when()
                .get("/products/{productName}/configurations", productName)
                .then()
                .statusCode(200);
    }
}