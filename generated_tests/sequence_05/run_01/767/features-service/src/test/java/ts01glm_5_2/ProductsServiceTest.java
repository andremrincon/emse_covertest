package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.util.UUID;

public class ProductsServiceTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductSuccess() {
        String productName = "prod_" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat_" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .formParam("description", "Test feature description")
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(equalTo(201));
    }

    @Test(timeout = 60000)
    public void testAddDuplicateFeatureReturnsError() {
        String productName = "prod_" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat_" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .formParam("description", "First feature")
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .formParam("description", "Duplicate feature")
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(equalTo(500));
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureRemovesFromActiveConfiguration() {
        String productName = "prod_" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat_" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config_" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .formParam("description", "Feature to delete")
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configurationName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .delete("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(equalTo(204));
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureWithoutConfiguration() {
        String productName = "prod_" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat_" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .formParam("description", "Feature to delete")
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .delete("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(equalTo(204));
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct() {
        String productName = "prod_" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "srcfeat" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "reqfeat" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .formParam("description", "Source feature")
            .when()
            .post("/products/" + productName + "/features/" + sourceFeature)
            .then()
            .statusCode(lessThan(300));

        given()
            .formParam("description", "Required feature")
            .when()
            .post("/products/" + productName + "/features/" + requiredFeature)
            .then()
            .statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .when()
            .post("/products/" + productName + "/constraints/requires")
            .then()
            .statusCode(equalTo(201));
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct() {
        String productName = "prod_" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "srcfeat" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "excfeat" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .formParam("description", "Source feature")
            .when()
            .post("/products/" + productName + "/features/" + sourceFeature)
            .then()
            .statusCode(lessThan(300));

        given()
            .formParam("description", "Excluded feature")
            .when()
            .post("/products/" + productName + "/features/" + excludedFeature)
            .then()
            .statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when()
            .post("/products/" + productName + "/constraints/excludes")
            .then()
            .statusCode(equalTo(201));
    }
}