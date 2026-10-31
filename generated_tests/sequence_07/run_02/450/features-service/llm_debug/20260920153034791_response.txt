package ts01glm_5_2_2;

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
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    private String createProduct() {
        String productName = "TestProd-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        return productName;
    }

    private void addFeature(String productName, String featureName) {
        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void addFeatureToProduct_success() {
        String productName = createProduct();
        String featureName = "Feature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureToProduct_withDescription() {
        String productName = createProduct();
        String featureName = "Feature-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Test description for feature")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureToProduct_duplicateThrowsException() {
        String productName = createProduct();
        String featureName = "Feature-" + UUID.randomUUID().toString().substring(0, 8);

        addFeature(productName, featureName);

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void addFeatureToProduct_nonExistentProduct() {
        String productName = "NonExistentProd-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void deleteFeatureOfProduct_withActiveConfiguration() {
        String productName = createProduct();
        String featureName = "Feature-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + UUID.randomUUID().toString().substring(0, 8);

        addFeature(productName, featureName);

        given().when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .delete("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeatureOfProduct_withoutConfiguration() {
        String productName = createProduct();
        String featureName = "Feature-" + UUID.randomUUID().toString().substring(0, 8);

        addFeature(productName, featureName);

        given().when()
            .delete("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeatureOfProduct_nonExistentFeature() {
        String productName = createProduct();
        String featureName = "NonExistent-" + UUID.randomUUID().toString().substring(0, 8);

        given().when()
            .delete("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void addRequiresConstraintToProduct_success() {
        String productName = createProduct();
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RequiredFeat-" + UUID.randomUUID().toString().substring(0, 8);

        addFeature(productName, sourceFeature);
        addFeature(productName, requiredFeature);

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void addRequiresConstraintToProduct_nonExistentProduct() {
        String productName = "NonExistentProd-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", "FeatureA")
            .formParam("requiredFeature", "FeatureB")
        .when()
            .post("/products/{productName}/constraints/requires", productName)
            .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraintToProduct_success() {
        String productName = createProduct();
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeat-" + UUID.randomUUID().toString().substring(0, 8);

        addFeature(productName, sourceFeature);
        addFeature(productName, excludedFeature);

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraintToProduct_nonExistentProduct() {
        String productName = "NonExistentProd-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", "FeatureA")
            .formParam("excludedFeature", "FeatureB")
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
            .then().statusCode(500);
    }
}