package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    private String createProduct() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        return productName;
    }

    private void addFeature(String productName, String featureName) {
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
    }

    private void addRequiresConstraint(String productName, String sourceFeature, String requiredFeature) {
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then().statusCode(lessThan(300));
    }

    private void createConfiguration(String productName, String configName) {
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
    }

    private void addFeatureToConfiguration(String productName, String configName, String featureName) {
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintCallsSetters() {
        String productName = createProduct();
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-" + UUID.randomUUID().toString().substring(0, 8);
        addFeature(productName, sourceFeature);
        addFeature(productName, requiredFeature);

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateSourceActiveRequiredNotActive() {
        String productName = createProduct();
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "cfg-" + UUID.randomUUID().toString().substring(0, 8);
        addFeature(productName, sourceFeature);
        addFeature(productName, requiredFeature);
        addRequiresConstraint(productName, sourceFeature, requiredFeature);
        createConfiguration(productName, configName);
        addFeatureToConfiguration(productName, configName, sourceFeature);

        given()
        .when()
            .get("/products/" + productName + "/configurations/" + configName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateBothFeaturesActive() {
        String productName = createProduct();
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "cfg-" + UUID.randomUUID().toString().substring(0, 8);
        addFeature(productName, sourceFeature);
        addFeature(productName, requiredFeature);
        addRequiresConstraint(productName, sourceFeature, requiredFeature);
        createConfiguration(productName, configName);
        addFeatureToConfiguration(productName, configName, requiredFeature);
        addFeatureToConfiguration(productName, configName, sourceFeature);

        given()
        .when()
            .get("/products/" + productName + "/configurations/" + configName + "/features")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateSourceNotActiveRequiredActive() {
        String productName = createProduct();
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "cfg-" + UUID.randomUUID().toString().substring(0, 8);
        addFeature(productName, sourceFeature);
        addFeature(productName, requiredFeature);
        addRequiresConstraint(productName, sourceFeature, requiredFeature);
        createConfiguration(productName, configName);
        addFeatureToConfiguration(productName, configName, requiredFeature);

        given()
        .when()
            .get("/products/" + productName + "/configurations/" + configName + "/features")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateNoFeaturesActive() {
        String productName = createProduct();
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "cfg-" + UUID.randomUUID().toString().substring(0, 8);
        addFeature(productName, sourceFeature);
        addFeature(productName, requiredFeature);
        addRequiresConstraint(productName, sourceFeature, requiredFeature);
        createConfiguration(productName, configName);

        given()
        .when()
            .get("/products/" + productName + "/configurations/" + configName + "/features")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintWithDistinctNames() {
        String productName = createProduct();
        String sourceFeature = "feat-a-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "feat-b-" + UUID.randomUUID().toString().substring(0, 8);
        addFeature(productName, sourceFeature);
        addFeature(productName, requiredFeature);

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }
}