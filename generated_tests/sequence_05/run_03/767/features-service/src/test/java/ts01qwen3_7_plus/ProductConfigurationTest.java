package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductConfigurationTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesWithNoProductFeatures() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String configurationName = "TestConfig-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        Response response = given().when().get("/products/{productName}/configurations/{configurationName}", productName, configurationName);

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesWithProductFeatures() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String configurationName = "TestConfig-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        Response response = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName);

        response.then().statusCode(201);
    }
}