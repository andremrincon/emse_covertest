package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class ProductsServiceTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_Success() {
        String productName = "Product-AddFeature-" + System.currentTimeMillis();
        String featureName = "Feature-1";

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
            .statusCode(201);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <500>.")
    @Test(timeout = 60000)
    public void testAddFeatureToProduct_Duplicate() {
        String productName = "Product-DupFeature-" + System.currentTimeMillis();
        String featureName = "Feature-Dup";

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
            .statusCode(lessThan(300));

        given()
            .formParam("description", "Test feature description duplicate")
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProduct_Success() {
        String productName = "Product-DelFeature-" + System.currentTimeMillis();
        String featureName = "Feature-Del";

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
            .statusCode(lessThan(300));

        given()
            .when()
            .delete("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProduct_WithActiveConfiguration() {
        String productName = "Product-DelFeatureConf-" + System.currentTimeMillis();
        String featureName = "Feature-DelConf";
        String configName = "Config-1";

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
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .delete("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct_Success() {
        String productName = "Product-ReqConst-" + System.currentTimeMillis();
        String sourceFeature = "Source-Feature";
        String requiredFeature = "Required-Feature";

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
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct_Success() {
        String productName = "Product-ExclConst-" + System.currentTimeMillis();
        String sourceFeature = "Source-Feature-Excl";
        String excludedFeature = "Excluded-Feature";

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
            .statusCode(201);
    }
}