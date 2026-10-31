package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null) {
            baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    private void createProduct(String productId) {
        given().when().post("/products/{productName}", productId).then().statusCode(lessThan(300));
    }

    private void addFeature(String productId, String featureName) {
        given().when().post("/products/{productName}/features/{featureName}", productId, featureName).then().statusCode(lessThan(300));
    }

    private void addRequiresConstraint(String productId, String source, String required) {
        given()
            .formParam("sourceFeature", source)
            .formParam("requiredFeature", required)
        .when()
            .post("/products/{productName}/constraints/requires", productId)
        .then()
            .statusCode(lessThan(300));
    }

    private void createConfiguration(String productId, String configName) {
        given().when().post("/products/{productName}/configurations/{configurationName}", productId, configName).then().statusCode(lessThan(300));
    }

    private void addFeatureToConfiguration(String productId, String configName, String featureName) {
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productId, configName, featureName).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint() {
        String productId = "prod-" + UUID.randomUUID().toString();
        String f1 = "f1-" + UUID.randomUUID().toString();
        String f2 = "f2-" + UUID.randomUUID().toString();

        createProduct(productId);
        addFeature(productId, f1);
        addFeature(productId, f2);

        given()
            .formParam("sourceFeature", f1)
            .formParam("requiredFeature", f2)
        .when()
            .post("/products/{productName}/constraints/requires", productId)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateConstraintSourceActiveRequiredInactive() {
        String productId = "prod-" + UUID.randomUUID().toString();
        String f1 = "f1-" + UUID.randomUUID().toString();
        String f2 = "f2-" + UUID.randomUUID().toString();
        String configName = "config-" + UUID.randomUUID().toString();

        createProduct(productId);
        addFeature(productId, f1);
        addFeature(productId, f2);
        addRequiresConstraint(productId, f1, f2);
        createConfiguration(productId, configName);
        addFeatureToConfiguration(productId, configName, f1);

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}/features", productId, configName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConstraintSourceActiveRequiredActive() {
        String productId = "prod-" + UUID.randomUUID().toString();
        String f1 = "f1-" + UUID.randomUUID().toString();
        String f2 = "f2-" + UUID.randomUUID().toString();
        String configName = "config-" + UUID.randomUUID().toString();

        createProduct(productId);
        addFeature(productId, f1);
        addFeature(productId, f2);
        addRequiresConstraint(productId, f1, f2);
        createConfiguration(productId, configName);
        addFeatureToConfiguration(productId, configName, f2);
        addFeatureToConfiguration(productId, configName, f1);

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}/features", productId, configName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConstraintSourceInactive() {
        String productId = "prod-" + UUID.randomUUID().toString();
        String f1 = "f1-" + UUID.randomUUID().toString();
        String f2 = "f2-" + UUID.randomUUID().toString();
        String configName = "config-" + UUID.randomUUID().toString();

        createProduct(productId);
        addFeature(productId, f1);
        addFeature(productId, f2);
        addRequiresConstraint(productId, f1, f2);
        createConfiguration(productId, configName);

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}/features", productId, configName)
        .then()
            .statusCode(200);
    }
}