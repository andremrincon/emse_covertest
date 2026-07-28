package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsServiceTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    private String uniqueProduct() {
        return "prod-" + UUID.randomUUID().toString().substring(0, 8);
    }

    private String uniqueFeature() {
        return "feat-" + UUID.randomUUID().toString().substring(0, 8);
    }

    private String uniqueConfig() {
        return "cfg-" + UUID.randomUUID().toString().substring(0, 8);
    }

    @Test(timeout = 60000)
    public void addFeatureToProduct_success() {
        String productName = uniqueProduct();
        String featureName = uniqueFeature();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
            .formParam("description", "Test description")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureToProduct_duplicateFeature() {
        String productName = uniqueProduct();
        String featureName = uniqueFeature();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void addFeatureToProduct_withoutDescription() {
        String productName = uniqueProduct();
        String featureName = uniqueFeature();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void deleteFeatureOfProduct_withActiveConfiguration() {
        String productName = uniqueProduct();
        String featureName = uniqueFeature();
        String configName = uniqueConfig();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeatureOfProduct_noActiveConfiguration() {
        String productName = uniqueProduct();
        String featureName = uniqueFeature();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void addRequiresConstraintToProduct_success() {
        String productName = uniqueProduct();
        String sourceFeature = uniqueFeature();
        String requiredFeature = uniqueFeature();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraintToProduct_success() {
        String productName = uniqueProduct();
        String sourceFeature = uniqueFeature();
        String excludedFeature = uniqueFeature();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(201);
    }
}