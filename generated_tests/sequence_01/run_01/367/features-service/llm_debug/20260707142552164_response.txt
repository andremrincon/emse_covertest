package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationFeaturesResourceTest {

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_Success() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
        String productName = "Prod-" + UUID.randomUUID();
        String configName = "Conf-" + UUID.randomUUID();
        String featureName = "Feat-" + UUID.randomUUID();

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
    public void addFeatureToConfiguration_Failure() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
        String productName = "Prod-" + UUID.randomUUID();
        String configName = "Conf-" + UUID.randomUUID();
        String featureName = "feature-name-that-is-intentionally-made-extremely-long-to-exceed-any-reasonable-database-column-width-or-url-path-segment-limit-and-potentially-cause-an-unhandled-exception-or-a-buffer-overflow-somewhere-deep-in-the-application-stack-resulting-in-a-generic-five-hundred-internal-server-error-response-instead-of-a-more-graceful-four-hundred-bad-request-error";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
        .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void deleteFeature_Success() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
        String productName = "Prod-" + UUID.randomUUID();
        String configName = "Conf-" + UUID.randomUUID();
        String featureName = "Feat-" + UUID.randomUUID();

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
    public void deleteFeature_Failure() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
        String productName = "Prod-" + UUID.randomUUID();
        String configName = "Conf-" + UUID.randomUUID();
        String featureName = "feature-name-that-is-intentionally-made-very-long-to-exceed-potential-database-column-size-limits-or-other-internal-buffer-restrictions-leading-to-an-unhandled-server-side-exception-and-a-500-internal-server-error-response";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
        .then()
            .statusCode(500);
    }
}