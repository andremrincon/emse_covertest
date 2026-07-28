package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCreateFeatureCoversSetNameAndSetProduct() {
        String productName = "Product-Set-Name-Test";
        String featureName = "Feature-Set-Name-Test";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
            .when().post("/products/" + productName + "/features/" + featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureCoversSetName() {
        String productName = "Product-Update-Name-Test";
        String featureName = "Feature-Update-Name-Test";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .formParam("description", "Updated description")
            .when().put("/products/" + productName + "/features/" + featureName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationCoversGetProductAndEquals() {
        String productName = "Product-Config-Test";
        String featureName = "Feature-Config-Test";
        String configName = "Config-Test";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
            .when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesCoversGetProduct() {
        String productName = "Product-Get-Test";
        String featureName = "Feature-Get-Test";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when().get("/products/" + productName + "/features")
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCreateMultipleFeaturesCoversEqualsNameBranch() {
        String productName = "Product-Multi-Feature-Test";
        String featureName1 = "Feature-Multi-1";
        String featureName2 = "Feature-Multi-2";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName1).then().statusCode(lessThan(300));

        given()
            .when().post("/products/" + productName + "/features/" + featureName2)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateFeatureInDifferentProductCoversEqualsProductBranch() {
        String productName1 = "Product-Diff-1";
        String productName2 = "Product-Diff-2";
        String featureName = "Feature-Diff";

        given().when().post("/products/" + productName1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName2).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName1 + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when().post("/products/" + productName2 + "/features/" + featureName)
            .then().statusCode(201);
    }
}