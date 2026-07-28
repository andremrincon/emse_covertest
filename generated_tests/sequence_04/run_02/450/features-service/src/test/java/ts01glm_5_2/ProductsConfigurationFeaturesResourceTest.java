package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationFeaturesResourceTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationValid() {
        String productName = "prod" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given().when()
            .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationExcludesConstraintViolation() {
        String productName = "prod" + UUID.randomUUID().toString().substring(0, 8);
        String feature1 = "featA" + UUID.randomUUID().toString().substring(0, 8);
        String feature2 = "featB" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given().contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", feature1)
            .formParam("excludedFeature", feature2)
            .when().post("/products/" + productName + "/constraints/excludes")
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + feature1)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + feature2)
            .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfigurationValid() {
        String productName = "prod" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when()
            .delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfigurationRequiresConstraintViolation() {
        String productName = "prod" + UUID.randomUUID().toString().substring(0, 8);
        String feature1 = "featA" + UUID.randomUUID().toString().substring(0, 8);
        String feature2 = "featB" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given().contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", feature2)
            .formParam("requiredFeature", feature1)
            .when().post("/products/" + productName + "/constraints/requires")
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + feature1)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + feature2)
            .then().statusCode(lessThan(300));

        given().when()
            .delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + feature1)
            .then()
            .statusCode(204);
    }
}