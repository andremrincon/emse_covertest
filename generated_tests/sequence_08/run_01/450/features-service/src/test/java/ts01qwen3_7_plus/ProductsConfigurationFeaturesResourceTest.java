package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationFeaturesResourceTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationSuccess() {
        String productName = "prod-" + UUID.randomUUID();
        String configName = "config-" + UUID.randomUUID();
        String featureName = "feat-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationFailure() {
        String productName = "prod-" + UUID.randomUUID();
        String configName = "config-" + UUID.randomUUID();
        String featureName = "feat-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureSuccess() {
        String productName = "prod-" + UUID.randomUUID();
        String configName = "config-" + UUID.randomUUID();
        String featureName = "feat-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when()
                .delete("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFailure() {
        String productName = "prod-" + UUID.randomUUID();
        String configName = "config-" + UUID.randomUUID();
        String featureName = "feat-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
            .when()
                .delete("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
                .statusCode(500);
    }
}