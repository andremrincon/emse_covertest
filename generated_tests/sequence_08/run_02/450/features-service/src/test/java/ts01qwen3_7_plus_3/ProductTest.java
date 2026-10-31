package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct() {
        String productName = "TestProduct-AddFeature-" + System.currentTimeMillis();
        String featureName = "TestFeature-Add";

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + featureName)
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromProduct() {
        String productName = "TestProduct-RemoveFeature-" + System.currentTimeMillis();
        String featureName = "TestFeature-Remove";

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
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
    public void testAddRequiresConstraintToProduct() {
        String productName = "TestProduct-Requires-" + System.currentTimeMillis();
        String featureA = "FeatureA-Req";
        String featureB = "FeatureB-Req";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureA).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureB).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", featureA)
            .formParam("requiredFeature", featureB)
            .when()
                .post("/products/" + productName + "/constraints/requires")
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct() {
        String productName = "TestProduct-Excludes-" + System.currentTimeMillis();
        String featureC = "FeatureC-Excl";
        String featureD = "FeatureD-Excl";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureC).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureD).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", featureC)
            .formParam("excludedFeature", featureD)
            .when()
                .post("/products/" + productName + "/constraints/excludes")
            .then()
                .statusCode(201);
    }
}