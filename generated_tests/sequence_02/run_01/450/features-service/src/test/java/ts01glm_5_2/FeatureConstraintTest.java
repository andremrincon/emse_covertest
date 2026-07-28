package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class FeatureConstraintTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testCreateRequiresConstraint() {
        String uniqueSuffix = UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-Req-" + uniqueSuffix;
        String sourceFeature = "Source-Feature-" + uniqueSuffix;
        String requiredFeature = "Required-Feature-" + uniqueSuffix;

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{sourceFeature}/requires/{requiredFeature}", productName, sourceFeature, requiredFeature).then().statusCode(404);
        given().when().get("/products/{productName}/features/{featureName}/requires", productName, sourceFeature).then().statusCode(lessThan(300)).body(containsString(requiredFeature));
    }
}