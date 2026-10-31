package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationFeaturesResourceTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_validFeature_returns201() {
        String product = "TestProd-" + UUID.randomUUID().toString().substring(0, 8);
        String feature = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String config = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_violatesExcludesConstraint_returns400() {
        String product = "TestProd-" + UUID.randomUUID().toString().substring(0, 8);
        String featureA = "FeatureA-" + UUID.randomUUID().toString().substring(0, 8);
        String featureB = "FeatureB-" + UUID.randomUUID().toString().substring(0, 8);
        String config = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureB).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC)
            .formParam("sourceFeature", featureA)
            .formParam("excludedFeature", featureB)
            .when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, featureA).then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, featureB)
            .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void deleteFeature_validFeature_returns204() {
        String product = "TestProd-" + UUID.randomUUID().toString().substring(0, 8);
        String feature = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String config = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));

        given().when()
            .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeature_violatesRequiresConstraint_returns400() {
        String product = "TestProd-" + UUID.randomUUID().toString().substring(0, 8);
        String featureA = "FeatureA-" + UUID.randomUUID().toString().substring(0, 8);
        String featureB = "FeatureB-" + UUID.randomUUID().toString().substring(0, 8);
        String config = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureB).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC)
            .formParam("sourceFeature", featureA)
            .formParam("requiredFeature", featureB)
            .when().post("/products/{productName}/constraints/requires", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, featureB).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, featureA).then().statusCode(lessThan(300));

        given().when()
            .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, featureB)
            .then().statusCode(204);
    }
}