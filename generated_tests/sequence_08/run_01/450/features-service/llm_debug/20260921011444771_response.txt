package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductConfigurationTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesViaPostFeatureToConfiguration() {
        String productName = "Product-" + java.util.UUID.randomUUID().toString();
        String featureName = "Feature-" + java.util.UUID.randomUUID().toString();
        String configurationName = "Config-" + java.util.UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().formParam("description", "Test feature description").when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesViaGetConfiguration() {
        String productName = "Product-" + java.util.UUID.randomUUID().toString();
        String featureName = "Feature-" + java.util.UUID.randomUUID().toString();
        String configurationName = "Config-" + java.util.UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().formParam("description", "Test feature description").when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/" + productName + "/configurations/" + configurationName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesViaGetConfigurationFeatures() {
        String productName = "Product-" + java.util.UUID.randomUUID().toString();
        String featureName = "Feature-" + java.util.UUID.randomUUID().toString();
        String configurationName = "Config-" + java.util.UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().formParam("description", "Test feature description").when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/" + productName + "/configurations/" + configurationName + "/features")
            .then()
            .statusCode(200);
    }
}