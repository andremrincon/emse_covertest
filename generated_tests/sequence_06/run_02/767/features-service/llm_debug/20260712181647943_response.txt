package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DuplicatedObjectExceptionTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
        RestAssured.config = RestAssured.config().httpClient(HttpClientConfig.httpClientConfig().setParam("http.connection.timeout", 30000).setParam("http.socket.timeout", 30000));
    }

    @Test(timeout = 60000)
    public void createDuplicateProductTriggersDuplicatedObjectException() {
        String productName = "dup-product-" + java.util.UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}", productName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void addDuplicateFeatureToProductTriggersDuplicatedObjectException() {
        String productName = "dup-prod-feat-" + java.util.UUID.randomUUID().toString();
        String featureName = "dup-feature-" + java.util.UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void createDuplicateConfigurationTriggersDuplicatedObjectException() {
        String productName = "dup-prod-config-" + java.util.UUID.randomUUID().toString();
        String configurationName = "dup-config-" + java.util.UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(201);
    }
}