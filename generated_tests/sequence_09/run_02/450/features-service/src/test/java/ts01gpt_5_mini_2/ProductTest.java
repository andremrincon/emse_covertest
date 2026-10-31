package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ProductTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void addFeature_shouldReturn201() {
        String productName = "prod-add-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void deleteFeature_shouldReturn204() {
        String productName = "prod-del-" + UUID.randomUUID().toString();
        String featureName = "FeatureToRemove-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void getProduct_shouldContainName_afterAddingFeature() {
        String productName = "prod-get-" + UUID.randomUUID().toString();
        String featureName = "Feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}", productName).then().body("name", equalTo(productName));
    }

    @Test(timeout = 60000)
    public void getFeaturesForProduct_shouldReturn200_withFeaturesPresent() {
        String productName = "prod-features-" + UUID.randomUUID().toString();
        String featureA = "FeatA-" + UUID.randomUUID().toString();
        String featureB = "FeatB-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureB).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/features", productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void addRequiresConstraint_shouldReturn201() {
        String productName = "prod-req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", "Source-" + UUID.randomUUID().toString())
               .formParam("requiredFeature", "Required-" + UUID.randomUUID().toString())
               .when().post("/products/{productName}/constraints/requires", productName)
               .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraint_shouldReturn201() {
        String productName = "prod-excl-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", "Src-" + UUID.randomUUID().toString())
               .formParam("excludedFeature", "Excl-" + UUID.randomUUID().toString())
               .when().post("/products/{productName}/constraints/excludes", productName)
               .then().statusCode(201);
    }
}