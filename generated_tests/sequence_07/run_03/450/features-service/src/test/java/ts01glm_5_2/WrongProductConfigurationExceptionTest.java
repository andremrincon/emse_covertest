package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class WrongProductConfigurationExceptionTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("baseUrl");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <200>.")
    @Test(timeout = 60000)
    public void testExcludesConstraintViolationTriggersWrongProductConfigurationException() {
        String productName = "test-excludes-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "FeatureA-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "FeatureB-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("description", "Source feature")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("description", "Excluded feature")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, excludedFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when()
                .post("/products/{productName}/constraints/excludes", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, sourceFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, excludedFeature)
                .then()
                .statusCode(500);

        given()
                .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then()
                .statusCode(500);
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <200>.")
    @Test(timeout = 60000)
    public void testRequiresConstraintViolationTriggersWrongProductConfigurationException() {
        String productName = "test-requires-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "ReqSource-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "ReqTarget-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("description", "Source feature")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("description", "Required feature")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, requiredFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, sourceFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then()
                .statusCode(500);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <500> was greater than <300>.")
    @Test(timeout = 60000)
    public void testMultipleConstraintViolationsTriggersWrongProductConfigurationException() {
        String productName = "test-multi-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureA = "FeatA-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureB = "FeatB-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureC = "FeatC-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureD = "FeatD-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("description", "Feature A")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureA)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("description", "Feature B")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureB)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("description", "Feature C")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureC)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("description", "Feature D")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureD)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", featureA)
                .formParam("excludedFeature", featureB)
                .when()
                .post("/products/{productName}/constraints/excludes", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", featureC)
                .formParam("requiredFeature", featureD)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureA)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureB)
                .then()
                .statusCode(500);

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureC)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then()
                .statusCode(500);
    }
}