package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.*;

public class ProductConfigurationTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost");
        String port = System.getProperty("port", "8080");
        RestAssured.baseURI = baseUrl;
        RestAssured.port = Integer.parseInt(port);
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_returnsCreated_whenProductHasFeature() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("description", "Test feature description")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                        productName, configurationName, featureName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void getConfigurationActivedFeatures_returnsFeatureNames_whenFeatureActivated() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("description", "Test feature description")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                        productName, configurationName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}/configurations/{configurationName}/features",
                        productName, configurationName)
                .then()
                .statusCode(200)
                .body(equalTo("[\"" + featureName + "\"]"));
    }

    @Test(timeout = 60000)
    public void getConfigurationDetails_returnsConfiguration_whenConfigurationExists() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("description", "Test feature description")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                        productName, configurationName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}/configurations/{configurationName}",
                        productName, configurationName)
                .then()
                .statusCode(200)
                .body("name", equalTo(configurationName));
    }
}