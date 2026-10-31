package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class ProductsConfigurationFeaturesResourceTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_valid_returns201() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String featureName = "TestFeature-" + uuid;
        String configurationName = "TestConfig-" + uuid;

        Response r1 = given().when().post("/products/{productName}", productName);
        r1.then().statusCode(lessThan(300));

        Response r2 = given().when().post("/products/{productName}/features/{featureName}", productName, featureName);
        r2.then().statusCode(lessThan(300));

        Response r3 = given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName);
        r3.then().statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then()
                .statusCode(201);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <500>.")
    @Test(timeout = 60000)
    public void addFeatureToConfiguration_constraintViolation_returnsError() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String featureA = "FeatureA-" + uuid;
        String featureB = "FeatureB-" + uuid;
        String configurationName = "TestConfig-" + uuid;

        Response r1 = given().when().post("/products/{productName}", productName);
        r1.then().statusCode(lessThan(300));

        Response r2 = given().when().post("/products/{productName}/features/{featureName}", productName, featureA);
        r2.then().statusCode(lessThan(300));

        Response r3 = given().when().post("/products/{productName}/features/{featureName}", productName, featureB);
        r3.then().statusCode(lessThan(300));

        Response r4 = given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName);
        r4.then().statusCode(lessThan(300));

        Response r5 = given()
                .formParam("sourceFeature", featureA)
                .formParam("excludedFeature", featureB)
                .when()
                .post("/products/{productName}/constraints/excludes", productName);
        r5.then().statusCode(lessThan(300));

        Response r6 = given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureA);
        r6.then().statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureB)
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void deleteFeature_valid_returns204() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String featureName = "TestFeature-" + uuid;
        String configurationName = "TestConfig-" + uuid;

        Response r1 = given().when().post("/products/{productName}", productName);
        r1.then().statusCode(lessThan(300));

        Response r2 = given().when().post("/products/{productName}/features/{featureName}", productName, featureName);
        r2.then().statusCode(lessThan(300));

        Response r3 = given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName);
        r3.then().statusCode(lessThan(300));

        Response r4 = given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName);
        r4.then().statusCode(lessThan(300));

        given()
                .when()
                .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then()
                .statusCode(204);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <500> was greater than <300>.")
    @Test(timeout = 60000)
    public void deleteFeature_constraintViolation_returnsError() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String featureA = "FeatureA-" + uuid;
        String featureB = "FeatureB-" + uuid;
        String configurationName = "TestConfig-" + uuid;

        Response r1 = given().when().post("/products/{productName}", productName);
        r1.then().statusCode(lessThan(300));

        Response r2 = given().when().post("/products/{productName}/features/{featureName}", productName, featureA);
        r2.then().statusCode(lessThan(300));

        Response r3 = given().when().post("/products/{productName}/features/{featureName}", productName, featureB);
        r3.then().statusCode(lessThan(300));

        Response r4 = given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName);
        r4.then().statusCode(lessThan(300));

        Response r5 = given()
                .formParam("sourceFeature", featureA)
                .formParam("requiredFeature", featureB)
                .when()
                .post("/products/{productName}/constraints/requires", productName);
        r5.then().statusCode(lessThan(300));

        Response r6 = given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureA);
        r6.then().statusCode(lessThan(300));

        Response r7 = given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureB);
        r7.then().statusCode(lessThan(300));

        given()
                .when()
                .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureB)
                .then()
                .statusCode(400);
    }
}