package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationsServiceTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void addFeatureFromConfiguration_successPath_featureAddedToConfiguration() {
        String productName = "test-product-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureFromConfiguration_duplicateFeature_throwsDuplicatedObjectException() {
        String productName = "test-product-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void addFeatureFromConfiguration_featureNotInProduct_returnsServerError() {
        String productName = "test-product-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "nonexistent-feature-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then()
            .statusCode(500);
    }
}