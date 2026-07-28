package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null) {
            baseUrl = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    private String createProduct() {
        String product = "Prod-" + UUID.randomUUID().toString();
        given().when().post("/products/" + product).then().statusCode(lessThan(300));
        return product;
    }

    private void addFeature(String product, String feature) {
        given().when().post("/products/" + product + "/features/" + feature).then().statusCode(lessThan(300));
    }

    private void createExcludesConstraint(String product, String source, String excluded) {
        given()
            .formParam("sourceFeature", source)
            .formParam("excludedFeature", excluded)
        .when()
            .post("/products/" + product + "/constraints/excludes")
        .then()
            .statusCode(lessThan(300));
    }

    private void createConfiguration(String product, String config) {
        given().when().post("/products/" + product + "/configurations/" + config).then().statusCode(lessThan(300));
    }

    private void addFeatureToConfig(String product, String config, String feature) {
        given().when().post("/products/" + product + "/configurations/" + config + "/features/" + feature).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationBothFeaturesActive() {
        String product = createProduct();
        String sourceFeat = "Source-" + UUID.randomUUID().toString();
        String excludedFeat = "Excluded-" + UUID.randomUUID().toString();
        String config = "Config-" + UUID.randomUUID().toString();

        addFeature(product, sourceFeat);
        addFeature(product, excludedFeat);
        createExcludesConstraint(product, sourceFeat, excludedFeat);
        createConfiguration(product, config);
        addFeatureToConfig(product, config, sourceFeat);

        given()
        .when()
            .post("/products/" + product + "/configurations/" + config + "/features/" + excludedFeat)
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationSourceNotActive() {
        String product = createProduct();
        String sourceFeat = "Source-" + UUID.randomUUID().toString();
        String excludedFeat = "Excluded-" + UUID.randomUUID().toString();
        String otherFeat = "Other-" + UUID.randomUUID().toString();
        String config = "Config-" + UUID.randomUUID().toString();

        addFeature(product, sourceFeat);
        addFeature(product, excludedFeat);
        addFeature(product, otherFeat);
        createExcludesConstraint(product, sourceFeat, excludedFeat);
        createConfiguration(product, config);
        addFeatureToConfig(product, config, excludedFeat);

        given()
        .when()
            .post("/products/" + product + "/configurations/" + config + "/features/" + otherFeat)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationExcludedNotActive() {
        String product = createProduct();
        String sourceFeat = "Source-" + UUID.randomUUID().toString();
        String excludedFeat = "Excluded-" + UUID.randomUUID().toString();
        String otherFeat = "Other-" + UUID.randomUUID().toString();
        String config = "Config-" + UUID.randomUUID().toString();

        addFeature(product, sourceFeat);
        addFeature(product, excludedFeat);
        addFeature(product, otherFeat);
        createExcludesConstraint(product, sourceFeat, excludedFeat);
        createConfiguration(product, config);
        addFeatureToConfig(product, config, sourceFeat);

        given()
        .when()
            .post("/products/" + product + "/configurations/" + config + "/features/" + otherFeat)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGettersAndSettersCoverage() {
        String product = createProduct();
        String sourceFeat = "Source-" + UUID.randomUUID().toString();
        String excludedFeat = "Excluded-" + UUID.randomUUID().toString();

        addFeature(product, sourceFeat);
        addFeature(product, excludedFeat);

        given()
            .formParam("sourceFeature", sourceFeat)
            .formParam("excludedFeature", excludedFeat)
        .when()
            .post("/products/" + product + "/constraints/excludes")
        .then()
            .statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + product)
        .then()
            .statusCode(200);
    }
}