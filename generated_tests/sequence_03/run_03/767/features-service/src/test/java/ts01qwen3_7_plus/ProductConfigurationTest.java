package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductConfigurationTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesReturnsProductFeatures() {
        String productName = "Product-AvailableFeatures-" + System.currentTimeMillis();
        String featureName = "Feature-Available-" + System.currentTimeMillis();
        String configName = "Config-Available-" + System.currentTimeMillis();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/" + productName + "/configurations/" + configName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesWithEmptyProductFeatures() {
        String productName = "Product-EmptyFeatures-" + System.currentTimeMillis();
        String configName = "Config-Empty-" + System.currentTimeMillis();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/" + productName + "/configurations/" + configName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesWithMultipleProductFeatures() {
        String productName = "Product-MultiFeatures-" + System.currentTimeMillis();
        String featureName1 = "Feature-Multi1-" + System.currentTimeMillis();
        String featureName2 = "Feature-Multi2-" + System.currentTimeMillis();
        String featureName3 = "Feature-Multi3-" + System.currentTimeMillis();
        String configName = "Config-Multi-" + System.currentTimeMillis();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName2).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName3).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/" + productName + "/configurations/" + configName)
            .then()
            .statusCode(200);
    }
}