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
        String baseUrl = System.getProperty("test.base.url");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_Success() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String configName = "Conf-" + UUID.randomUUID().toString();
        String featureName = "Feat-" + UUID.randomUUID().toString();

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
    public void testAddFeatureToConfiguration_Failure() {
        String productName = "InvalidProd-" + UUID.randomUUID().toString();
        String configName = "InvalidConf-" + UUID.randomUUID().toString();
        String featureName = "InvalidFeat-" + UUID.randomUUID().toString();

        given()
                .when()
                .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
                .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteFeature_Success() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String configName = "Conf-" + UUID.randomUUID().toString();
        String featureName = "Feat-" + UUID.randomUUID().toString();

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
    public void testDeleteFeature_Failure() {
        String productName = "InvalidProd-" + UUID.randomUUID().toString();
        String configName = "InvalidConf-" + UUID.randomUUID().toString();
        String featureName = "InvalidFeat-" + UUID.randomUUID().toString();

        given()
                .when()
                .delete("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
                .then()
                .statusCode(500);
    }
}