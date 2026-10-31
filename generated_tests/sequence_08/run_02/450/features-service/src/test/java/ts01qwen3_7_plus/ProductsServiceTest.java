package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsServiceTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_Success() {
        String productName = "Prod-AddFeat-" + UUID.randomUUID();
        String featureName = "Feat-Add-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
            .formParam("description", "Test description for new feature")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_Duplicate() {
        String productName = "Prod-DupFeat-" + UUID.randomUUID();
        String featureName = "Feat-Dup-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().formParam("description", "Initial description").when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .formParam("description", "Duplicate description")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProduct_NoConfigurations() {
        String productName = "Prod-DelNoConf-" + UUID.randomUUID();
        String featureName = "Feat-DelNoConf-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().formParam("description", "Feature without config").when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProduct_WithConfigurations() {
        String productName = "Prod-DelConf-" + UUID.randomUUID();
        String featureName = "Feat-DelConf-" + UUID.randomUUID();
        String configName = "Conf-DelConf-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().formParam("description", "Feature with config").when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct() {
        String productName = "Prod-ReqConst-" + UUID.randomUUID();
        String sourceFeature = "SrcFeat-" + UUID.randomUUID();
        String requiredFeature = "ReqFeat-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().formParam("description", "Source feature").when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().formParam("description", "Required feature").when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct() {
        String productName = "Prod-ExclConst-" + UUID.randomUUID();
        String sourceFeature = "SrcFeat-" + UUID.randomUUID();
        String excludedFeature = "ExclFeat-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().formParam("description", "Source feature").when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().formParam("description", "Excluded feature").when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(201);
    }
}