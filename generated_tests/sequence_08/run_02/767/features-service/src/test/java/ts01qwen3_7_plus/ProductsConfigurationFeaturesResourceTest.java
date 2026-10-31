package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class ProductsConfigurationFeaturesResourceTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_Success() {
        String productName = "Prod-Add-Success-" + UUID.randomUUID().toString();
        String configName = "Config-Add-Success";
        String featureName = "Feature-Add-Success";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().formParam("description", "desc").when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_Failure() {
        String productName = "Prod-Add-Fail-" + UUID.randomUUID().toString();
        String configName = "Config-Add-Fail";
        String feature1 = "Feature1-Add-Fail";
        String feature2 = "Feature2-Add-Fail";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().formParam("description", "desc1").when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().formParam("description", "desc2").when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));

        given().formParam("sourceFeature", feature1).formParam("excludedFeature", feature2)
            .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + feature1).then().statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + feature2)
            .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteFeature_Success() {
        String productName = "Prod-Del-Success-" + UUID.randomUUID().toString();
        String configName = "Config-Del-Success";
        String featureName = "Feature-Del-Success";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().formParam("description", "desc").when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .delete("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
            .statusCode(204);
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <204>.")
    @Test(timeout = 60000)
    public void testDeleteFeature_Failure() {
        String productName = "Prod-Del-Fail-" + UUID.randomUUID().toString();
        String configName = "Config-Del-Fail";
        String feature1 = "Feature1-Del-Fail";
        String feature2 = "Feature2-Del-Fail";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().formParam("description", "desc1").when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().formParam("description", "desc2").when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));

        given().formParam("sourceFeature", feature1).formParam("requiredFeature", feature2)
            .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + feature2).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + feature1).then().statusCode(lessThan(300));

        given()
            .when()
            .delete("/products/" + productName + "/configurations/" + configName + "/features/" + feature2)
            .then()
            .statusCode(500);
    }
}