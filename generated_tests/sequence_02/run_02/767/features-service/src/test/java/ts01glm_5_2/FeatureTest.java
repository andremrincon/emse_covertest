package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class FeatureTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("baseUrl");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void createFeature_setsNameAndProduct() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void getFeatures_invokesGetProductAndEquals() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));
        given().when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));

        given().when()
                .get("/products/{productName}/features", productName)
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void updateFeature_invokesSetName() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));
        given().when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));

        given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "Updated description")
                .when()
                .put("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_invokesEquals() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));
        given().when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));
        given().when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                        productName, configurationName, featureName)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void getConfigurationFeatures_invokesEquals() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));
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
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void addDuplicateFeature_invokesEqualsSameNameBranch() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));
        given().when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(anyOf(equalTo(201), equalTo(400), equalTo(500)));
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromConfiguration_invokesEquals() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));
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
                .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeature_invokesEquals() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));
        given().when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));

        given().when()
                .delete("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void getProductByName_returnsFeaturesInvokingEquals() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));
        given().when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));

        given().when()
                .get("/products/{productName}", productName)
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationsForProduct_invokesEquals() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));
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
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationWithName_invokesEquals() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));
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
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void addMultipleFeaturesToConfiguration_invokesEqualsMultipleComparisons() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName1 = "FeatureA-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName2 = "FeatureB-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));
        given().when()
                .post("/products/{productName}/features/{featureName}", productName, featureName1)
                .then().statusCode(lessThan(300));
        given().when()
                .post("/products/{productName}/features/{featureName}", productName, featureName2)
                .then().statusCode(lessThan(300));
        given().when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(lessThan(300));
        given().when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                        productName, configurationName, featureName1)
                .then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                        productName, configurationName, featureName2)
                .then().statusCode(201);
    }
}