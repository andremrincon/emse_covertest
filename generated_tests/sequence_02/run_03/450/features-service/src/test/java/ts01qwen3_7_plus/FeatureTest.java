package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCreateFeature() {
        String productName = "Prod-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feat-" + UUID.randomUUID();
        given()
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetFeatures() {
        String productName = "Prod-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/Feat1-" + UUID.randomUUID()).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/Feat2-" + UUID.randomUUID()).then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/" + productName + "/features")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration() {
        String productName = "Prod-" + UUID.randomUUID();
        String configName = "Conf-" + UUID.randomUUID();
        String featureName = "Feat-" + UUID.randomUUID();
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
    public void testGetConfigurationFeatures() {
        String productName = "Prod-" + UUID.randomUUID();
        String configName = "Conf-" + UUID.randomUUID();
        String featureName = "Feat-" + UUID.randomUUID();
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
    public void testUpdateFeature() {
        String productName = "Prod-" + UUID.randomUUID();
        String featureName = "Feat-" + UUID.randomUUID();
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
    public void testDeleteFeature() {
        String productName = "Prod-" + UUID.randomUUID();
        String featureName = "Feat-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .delete("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(204);
    }
}