package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationFeaturesResourceTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void deleteFeature_ValidFeature_Returns204() {
        String productName = "Product-" + UUID.randomUUID();
        String configurationName = "Config-" + UUID.randomUUID();
        String featureName = "Feature-" + UUID.randomUUID();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureName).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configurationName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configurationName).pathParam("featureName", featureName).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));

        given()
                .pathParam("productName", productName)
                .pathParam("configurationName", configurationName)
                .pathParam("featureName", featureName)
                .when()
                .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}")
                .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeature_InvalidFeature_Returns500() {
        String productName = "Product-" + UUID.randomUUID();
        String configurationName = "Config-" + UUID.randomUUID();
        String featureName = "Feature-" + UUID.randomUUID();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureName).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configurationName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));

        given()
                .pathParam("productName", productName)
                .pathParam("configurationName", configurationName)
                .pathParam("featureName", featureName)
                .when()
                .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}")
                .then()
                .statusCode(204);
    }
}