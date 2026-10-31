package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;

public class ProductsServiceTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_DuplicateFeature() {
        String productName = "Product-Dup-Feature-" + System.currentTimeMillis();
        String featureName = "duplicate-feature";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().formParam("description", "Initial description").when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().formParam("description", "Duplicate description").when().post("/products/" + productName + "/features/" + featureName).then().statusCode(is(500));
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProduct_Success() {
        String productName = "Product-Del-Feature-" + System.currentTimeMillis();
        String featureName = "feature-to-delete";
        String configName = "config-to-delete";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().formParam("description", "Description").when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/features/" + featureName).then().statusCode(is(204));
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct_Success() {
        String productName = "Product-Req-Constraint-" + System.currentTimeMillis();
        String sourceFeature = "source-feature";
        String requiredFeature = "required-feature";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().formParam("description", "Source feature description").when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().formParam("description", "Required feature description").when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(is(201));
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct_Success() {
        String productName = "Product-Excl-Constraint-" + System.currentTimeMillis();
        String sourceFeature = "source-feature";
        String excludedFeature = "excluded-feature";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().formParam("description", "Source feature description").when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().formParam("description", "Excluded feature description").when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(is(201));
    }
}