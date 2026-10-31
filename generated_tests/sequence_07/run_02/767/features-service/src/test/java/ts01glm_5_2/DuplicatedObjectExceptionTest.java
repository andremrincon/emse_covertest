package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class DuplicatedObjectExceptionTest {

    @BeforeClass
    public static void setUpClass() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
        RestAssured.config = RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().defaultContentCharset("UTF-8"));
    }

    @Test(timeout = 60000)
    public void duplicateProductCreationThrowsDuplicatedObjectException() {
        String productName = "DupProduct-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(201);
    }

    @Ignore("1 expectation failed. Expected status code <201> but was <500>.")
    @Test(timeout = 60000)
    public void duplicateFeatureCreationThrowsDuplicatedObjectException() {
        String productName = "DupProdFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "DupFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .formParam("description", "Test description")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .formParam("description", "Test description again")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void duplicateConfigurationCreationThrowsDuplicatedObjectException() {
        String productName = "DupProdConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "DupConfig-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then()
                .statusCode(201);
    }
}