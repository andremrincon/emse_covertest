package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class FeatureTest {

    @BeforeClass
    public static void setUp() {
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
    public void createFeature_setsNameAndProduct() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void updateFeature_invokesSetName() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .formParam("description", "Updated description for feature")
            .contentType(ContentType.URLENC)
            .put("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getFeatures_invokesGetProduct() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .get("/products/{productName}/features", productName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getProduct_invokesFeatureEquals() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .get("/products/{productName}", productName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_invokesEquals() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                productName, configurationName, featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromConfiguration_invokesEquals() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                productName, configurationName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                productName, configurationName, featureName)
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void getConfigurationFeatures_invokesEqualsAndGetProduct() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                productName, configurationName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .get("/products/{productName}/configurations/{configurationName}/features",
                productName, configurationName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void addRequiresConstraint_invokesFeatureEquals() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
            .then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/features/{featureName}", productName, requiredFeature)
            .then().statusCode(lessThan(300));

        given().when()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .contentType(ContentType.URLENC)
            .post("/products/{productName}/constraints/requires", productName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraint_invokesFeatureEquals() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
            .then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/features/{featureName}", productName, excludedFeature)
            .then().statusCode(lessThan(300));

        given().when()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .contentType(ContentType.URLENC)
            .post("/products/{productName}/constraints/excludes", productName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void deleteFeature_invokesSetProductAndEquals() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature-" + UUID.randomUUID().toString().substring(0, 8);

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
    public void getConfigurations_invokesFeatureEquals() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                productName, configurationName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .get("/products/{productName}/configurations", productName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationDetail_invokesFeatureEqualsAndGetProduct() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                productName, configurationName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then()
            .statusCode(200);
    }
}