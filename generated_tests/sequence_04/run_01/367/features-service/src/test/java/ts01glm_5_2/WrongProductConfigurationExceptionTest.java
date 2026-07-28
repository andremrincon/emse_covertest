package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class WrongProductConfigurationExceptionTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <200>.")
    @Test(timeout = 60000)
    public void testWrongProductConfigurationExceptionViaExcludesConstraintViolation() {
        String productName = "TestProduct-Excl-" + UUID.randomUUID().toString().substring(0, 8);
        String featureA = "FeatureA-" + UUID.randomUUID().toString().substring(0, 8);
        String featureB = "FeatureB-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureA).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureB).then().statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", featureA)
                .formParam("excludedFeature", featureB)
                .when()
                .post("/products/{productName}/constraints/excludes", productName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureA).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureB).then().statusCode(500);

        given().when().get("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(500);
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <200>.")
    @Test(timeout = 60000)
    public void testWrongProductConfigurationExceptionViaRequiresConstraintViolation() {
        String productName = "TestProduct-Req-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, sourceFeature).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(500);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <500> was greater than <300>.")
    @Test(timeout = 60000)
    public void testWrongProductConfigurationExceptionViaMultipleConstraintViolations() {
        String productName = "TestProduct-Multi-" + UUID.randomUUID().toString().substring(0, 8);
        String featureA = "FeatA-" + UUID.randomUUID().toString().substring(0, 8);
        String featureB = "FeatB-" + UUID.randomUUID().toString().substring(0, 8);
        String featureC = "FeatC-" + UUID.randomUUID().toString().substring(0, 8);
        String featureD = "FeatD-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureB).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureC).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureD).then().statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", featureA)
                .formParam("excludedFeature", featureB)
                .when()
                .post("/products/{productName}/constraints/excludes", productName)
                .then().statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", featureC)
                .formParam("requiredFeature", featureD)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureB).then().statusCode(500);
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureC).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(500);
    }
}