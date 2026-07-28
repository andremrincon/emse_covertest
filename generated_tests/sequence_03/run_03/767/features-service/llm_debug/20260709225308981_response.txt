package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCreateFeatureSetsNameAndProduct() {
        String productName = "Prod-Name-1";
        String featureName = "Feat-Name-1";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureDescription() {
        String productName = "Prod-Name-2";
        String featureName = "Feat-Name-2";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Updated")
            .when()
            .put("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProduct() {
        String productName = "Prod-Name-3";
        String featureName = "Feat-Name-3";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/" + productName + "/features")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration() {
        String productName = "Prod-Name-4";
        String featureName = "Feat-Name-4";
        String configName = "Conf-Name-4";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddSameFeatureToConfigurationTwice() {
        String productName = "Prod-Name-5";
        String featureName = "Feat-Name-5";
        String configName = "Conf-Name-5";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint() {
        String productName = "Prod-Name-6";
        String feature1 = "Feat-Name-6A";
        String feature2 = "Feat-Name-6B";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
            .when()
            .post("/products/" + productName + "/constraints/requires")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint() {
        String productName = "Prod-Name-7";
        String feature1 = "Feat-Name-7A";
        String feature2 = "Feat-Name-7B";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", feature1)
            .formParam("excludedFeature", feature2)
            .when()
            .post("/products/" + productName + "/constraints/excludes")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteFeature() {
        String productName = "Prod-Name-8";
        String featureName = "Feat-Name-8";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .delete("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsForProduct() {
        String productName = "Prod-Name-9";
        String configName = "Conf-Name-9";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/" + productName + "/configurations")
            .then()
            .statusCode(200);
    }
}