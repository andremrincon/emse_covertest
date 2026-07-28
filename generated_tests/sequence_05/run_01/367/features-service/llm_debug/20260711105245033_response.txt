package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    @BeforeClass
    public static void setUp() {
        String host = System.getProperty("test.host", "localhost");
        String port = System.getProperty("test.port", "8080");
        RestAssured.baseURI = "http://" + host + ":" + port;
    }

    private String unique(String prefix) {
        return prefix + "-" + UUID.randomUUID().toString().substring(0, 8);
    }

    @Test(timeout = 60000)
    public void testSetNameViaCreateFeature() {
        String productName = unique("prod");
        String featureName = unique("feat");

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
            .formParam("description", "Test description")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testSetNameViaUpdateFeature() {
        String productName = unique("prod");
        String featureName = unique("feat");

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .formParam("description", "Updated description")
        .when()
            .put("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSetProductViaCreateFeature() {
        String productName = unique("prod");
        String featureName = unique("feat");

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductViaGetFeatures() {
        String productName = unique("prod");
        String featureName = unique("feat");

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName + "/features")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetProductViaGetProductByName() {
        String productName = unique("prod");
        String featureName = unique("feat");

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEqualsWhenAddingFeatureToConfiguration() {
        String productName = unique("prod");
        String configName = unique("config");
        String featureName = unique("feat");

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
    public void testEqualsNameNotEqualWhenAddingSecondFeature() {
        String productName = unique("prod");
        String configName = unique("config");
        String featureName1 = unique("feat1");
        String featureName2 = unique("feat2");

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName2).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName1).then().statusCode(lessThan(300));

        given()
        .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName2)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEqualsProductNotEqualSameFeatureNameDifferentProducts() {
        String productName1 = unique("prod1");
        String productName2 = unique("prod2");
        String featureName = unique("shared-feat");

        given().when().post("/products/" + productName1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName2).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName1 + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName2 + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName1 + "/features")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEqualsWhenGettingConfigurationFeatures() {
        String productName = unique("prod");
        String configName = unique("config");
        String featureName = unique("feat");

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName + "/configurations/" + configName + "/features")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEqualsWhenDeletingFeatureFromConfiguration() {
        String productName = unique("prod");
        String configName = unique("config");
        String featureName = unique("feat");

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testEqualsWhenAddingRequiresConstraint() {
        String productName = unique("prod");
        String sourceFeature = unique("src");
        String requiredFeature = unique("req");

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEqualsWhenAddingExcludesConstraint() {
        String productName = unique("prod");
        String sourceFeature = unique("src");
        String excludedFeature = unique("exc");

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(201);
    }
}