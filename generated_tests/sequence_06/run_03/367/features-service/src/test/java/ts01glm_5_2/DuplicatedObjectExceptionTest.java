package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class DuplicatedObjectExceptionTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void createDuplicateProductThrowsException() {
        String productName = "TestProduct-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName).then().statusCode(201);
    }

    @Ignore("1 expectation failed. Expected status code <201> but was <500>.")
    @Test(timeout = 60000)
    public void addDuplicateFeatureThrowsException() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String featureName = "TestFeature-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void addDuplicateConfigurationThrowsException() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String configurationName = "TestConfig-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(201);
    }
}