package ts01qwen3_7_plus;

import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintRequiresTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    private void createProduct(String productName) {
        given().baseUri(baseUrl).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
    }

    private void createFeature(String productName, String featureName) {
        given().baseUri(baseUrl).when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
    }

    private void createConfiguration(String productName, String configName) {
        given().baseUri(baseUrl).when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
    }

    private void addFeatureToConfiguration(String productName, String configName, String featureName) {
        given().baseUri(baseUrl).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));
    }

    private void createRequiresConstraint(String productName, String source, String required) {
        given().baseUri(baseUrl)
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", source)
                .formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint() {
        String productName = "Prod-" + UUID.randomUUID();
        String source = "Src-" + UUID.randomUUID();
        String required = "Req-" + UUID.randomUUID();

        createProduct(productName);
        createFeature(productName, source);
        createFeature(productName, required);

        given().baseUri(baseUrl)
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", source)
                .formParam("requiredFeature", required)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationSourceActiveRequiredInactive() {
        String productName = "Prod-" + UUID.randomUUID();
        String source = "Src-" + UUID.randomUUID();
        String required = "Req-" + UUID.randomUUID();
        String config = "Conf-" + UUID.randomUUID();

        createProduct(productName);
        createFeature(productName, source);
        createFeature(productName, required);
        createConfiguration(productName, config);
        addFeatureToConfiguration(productName, config, source);
        createRequiresConstraint(productName, source, required);

        given().baseUri(baseUrl)
                .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, config)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationBothActive() {
        String productName = "Prod-" + UUID.randomUUID();
        String source = "Src-" + UUID.randomUUID();
        String required = "Req-" + UUID.randomUUID();
        String config = "Conf-" + UUID.randomUUID();

        createProduct(productName);
        createFeature(productName, source);
        createFeature(productName, required);
        createConfiguration(productName, config);
        addFeatureToConfiguration(productName, config, source);
        addFeatureToConfiguration(productName, config, required);
        createRequiresConstraint(productName, source, required);

        given().baseUri(baseUrl)
                .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, config)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationSourceInactive() {
        String productName = "Prod-" + UUID.randomUUID();
        String source = "Src-" + UUID.randomUUID();
        String required = "Req-" + UUID.randomUUID();
        String config = "Conf-" + UUID.randomUUID();

        createProduct(productName);
        createFeature(productName, source);
        createFeature(productName, required);
        createConfiguration(productName, config);
        addFeatureToConfiguration(productName, config, required);
        createRequiresConstraint(productName, source, required);

        given().baseUri(baseUrl)
                .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, config)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetProductWithRequiresConstraint() {
        String productName = "Prod-" + UUID.randomUUID();
        String source = "Src-" + UUID.randomUUID();
        String required = "Req-" + UUID.randomUUID();

        createProduct(productName);
        createFeature(productName, source);
        createFeature(productName, required);
        createRequiresConstraint(productName, source, required);

        given().baseUri(baseUrl)
                .when()
                .get("/products/{productName}", productName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintMissingSource() {
        String productName = "Prod-" + UUID.randomUUID();
        String required = "Req-" + UUID.randomUUID();

        createProduct(productName);
        createFeature(productName, required);

        given().baseUri(baseUrl)
                .contentType(ContentType.URLENC)
                .formParam("requiredFeature", required)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintMissingRequired() {
        String productName = "Prod-" + UUID.randomUUID();
        String source = "Src-" + UUID.randomUUID();

        createProduct(productName);
        createFeature(productName, source);

        given().baseUri(baseUrl)
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", source)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintNonExistentProduct() {
        String productName = "Prod-" + UUID.randomUUID();
        String source = "Src-" + UUID.randomUUID();
        String required = "Req-" + UUID.randomUUID();

        given().baseUri(baseUrl)
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", source)
                .formParam("requiredFeature", required)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintNonExistentSource() {
        String productName = "Prod-" + UUID.randomUUID();
        String source = "Src-" + UUID.randomUUID();
        String required = "Req-" + UUID.randomUUID();

        createProduct(productName);
        createFeature(productName, required);

        given().baseUri(baseUrl)
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", source)
                .formParam("requiredFeature", required)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintNonExistentRequired() {
        String productName = "Prod-" + UUID.randomUUID();
        String source = "Src-" + UUID.randomUUID();
        String required = "Req-" + UUID.randomUUID();

        createProduct(productName);
        createFeature(productName, source);

        given().baseUri(baseUrl)
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", source)
                .formParam("requiredFeature", required)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(201);
    }
}