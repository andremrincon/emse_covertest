package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;

public class FeatureTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("base.url");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    private String uuid() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    @Test(timeout = 60000)
    public void addFeatureToProductCoversSetNameAndSetProduct() {
        String productName = "Prod-" + uuid();
        String featureName = "Feat-" + uuid();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void updateFeatureCoversSetName() {
        String productName = "Prod-" + uuid();
        String featureName = "Feat-" + uuid();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().formParam("description", "Updated description")
            .when().put("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getFeaturesCoversGetProduct() {
        String productName = "Prod-" + uuid();
        String featureName = "Feat-" + uuid();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/features", productName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getProductCoversGetProductAndEquals() {
        String productName = "Prod-" + uuid();
        String featureName = "Feat-" + uuid();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}", productName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void addFeatureToConfigurationCoversEquals() {
        String productName = "Prod-" + uuid();
        String featureName = "Feat-" + uuid();
        String configName = "Config-" + uuid();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void addSameFeatureToConfigurationTwiceCoversEqualsSameNameAndProduct() {
        String productName = "Prod-" + uuid();
        String featureName = "Feat-" + uuid();
        String configName = "Config-" + uuid();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void addDifferentFeaturesToConfigurationCoversEqualsDifferentName() {
        String productName = "Prod-" + uuid();
        String featureName1 = "FeatA-" + uuid();
        String featureName2 = "FeatB-" + uuid();
        String configName = "Config-" + uuid();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName2).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName2)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void getConfigurationFeaturesCoversEquals() {
        String productName = "Prod-" + uuid();
        String featureName = "Feat-" + uuid();
        String configName = "Config-" + uuid();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromConfigurationCoversEquals() {
        String productName = "Prod-" + uuid();
        String featureName = "Feat-" + uuid();
        String configName = "Config-" + uuid();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromProductCoversEquals() {
        String productName = "Prod-" + uuid();
        String featureName = "Feat-" + uuid();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void getConfigurationsForProductCoversEquals() {
        String productName = "Prod-" + uuid();
        String featureName = "Feat-" + uuid();
        String configName = "Config-" + uuid();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations", productName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationWithNameCoversEquals() {
        String productName = "Prod-" + uuid();
        String featureName = "Feat-" + uuid();
        String configName = "Config-" + uuid();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then().statusCode(200);
    }
}