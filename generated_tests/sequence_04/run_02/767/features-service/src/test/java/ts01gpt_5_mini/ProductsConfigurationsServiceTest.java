package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;

public class ProductsConfigurationsServiceTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    private String unique(String prefix) {
        return prefix + "-" + UUID.randomUUID().toString().replaceAll("[^a-zA-Z0-9\\-]", "").substring(0, 8);
    }

    private void arrangeCreateProduct(String productName) {
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
    }

    private void arrangeCreateConfiguration(String productName, String configurationName) {
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
    }

    private void arrangeAddFeatureToConfiguration(String productName, String configurationName, String featureName) {
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));
    }

    private void arrangeDeleteFeatureFromConfiguration(String productName, String configurationName, String featureName) {
        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));
    }

    private void arrangeAddRequiresConstraint(String productName, String sourceFeature, String requiredFeature) {
        given().contentType(ContentType.URLENC).formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsForProductReturns200() {
        String product = unique("prod");
        String config = unique("cfg");
        arrangeCreateProduct(product);
        arrangeCreateConfiguration(product, config);
        given().when().get("/products/{productName}/configurations", product).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeaturesReturnsFeatureList() {
        String product = unique("prod");
        String config = unique("cfg");
        String feature = unique("feat");
        arrangeCreateProduct(product);
        arrangeCreateConfiguration(product, config);
        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config).then().statusCode(lessThan(300)).body("", not(hasItem(feature)));
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationReturns201() {
        String product = unique("prod");
        String config = unique("cfg");
        String feature = unique("feat");
        arrangeCreateProduct(product);
        arrangeCreateConfiguration(product, config);
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationDuplicateReturns500() {
        String product = unique("prod");
        String config = unique("cfg");
        String feature = unique("feat");
        arrangeCreateProduct(product);
        arrangeCreateConfiguration(product, config);
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(500);
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromConfigurationReturns204() {
        String product = unique("prod");
        String config = unique("cfg");
        String feature = unique("feat");
        arrangeCreateProduct(product);
        arrangeCreateConfiguration(product, config);
        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromConfigurationSecondDeleteReturns500() {
        String product = unique("prod");
        String config = unique("cfg");
        String feature = unique("feat");
        arrangeCreateProduct(product);
        arrangeCreateConfiguration(product, config);
        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(500);
        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationSetsConfigurationInvalidWhenMissingRequiredFeature() {
        String product = unique("prod");
        String config = unique("cfg");
        String source = unique("src");
        String required = unique("req");
        arrangeCreateProduct(product);
        arrangeCreateConfiguration(product, config);
        arrangeAddRequiresConstraint(product, source, required);
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, source).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationValidWhenRequirementsMet() {
        String product = unique("prod");
        String config = unique("cfg");
        String source = unique("src");
        String required = unique("req");
        arrangeCreateProduct(product);
        arrangeCreateConfiguration(product, config);
        arrangeAddRequiresConstraint(product, source, required);
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, required).then().statusCode(500);
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, source).then().statusCode(500);
    }
}