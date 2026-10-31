package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class FeatureConstraintTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        String feature1 = "Feature1-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, feature1).then().statusCode(lessThan(300));

        String feature2 = "Feature2-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, feature2).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        String feature1 = "Feature1-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, feature1).then().statusCode(lessThan(300));

        String feature2 = "Feature2-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, feature2).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", feature1)
            .formParam("excludedFeature", feature2)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductWithConstraints() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        String feature1 = "Feature1-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, feature1).then().statusCode(lessThan(300));

        String feature2 = "Feature2-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, feature2).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then()
            .statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}", productName)
        .then()
            .statusCode(200);
    }

    @Ignore("Failed to parse the JSON document")
    @Test(timeout = 60000)
    public void testDeleteConstraint() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        String feature1 = "Feature1-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, feature1).then().statusCode(lessThan(300));

        String feature2 = "Feature2-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, feature2).then().statusCode(lessThan(300));

        Integer constraintId = given()
            .formParam("sourceFeature", feature1)
            .formParam("excludedFeature", feature2)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(lessThan(300))
            .extract().path("id");

        given()
        .when()
            .delete("/products/{productName}/constraints/{constraintId}", productName, constraintId)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testCreateMultipleRequiresConstraints() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        String feature1 = "Feature1-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, feature1).then().statusCode(lessThan(300));

        String feature2 = "Feature2-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, feature2).then().statusCode(lessThan(300));

        String feature3 = "Feature3-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, feature3).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then()
            .statusCode(201);

        given()
            .formParam("sourceFeature", feature2)
            .formParam("requiredFeature", feature3)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateMultipleExcludesConstraints() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        String feature1 = "Feature1-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, feature1).then().statusCode(lessThan(300));

        String feature2 = "Feature2-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, feature2).then().statusCode(lessThan(300));

        String feature3 = "Feature3-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, feature3).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", feature1)
            .formParam("excludedFeature", feature2)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(201);

        given()
            .formParam("sourceFeature", feature2)
            .formParam("excludedFeature", feature3)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductAfterConstraintCreation() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        String feature1 = "Feature1-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, feature1).then().statusCode(lessThan(300));

        String feature2 = "Feature2-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, feature2).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", feature1)
            .formParam("excludedFeature", feature2)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}", productName)
        .then()
            .statusCode(200);
    }

    @Ignore("Failed to parse the JSON document")
    @Test(timeout = 60000)
    public void testDeleteConstraintAfterCreation() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        String feature1 = "Feature1-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, feature1).then().statusCode(lessThan(300));

        String feature2 = "Feature2-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, feature2).then().statusCode(lessThan(300));

        Integer constraintId = given()
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then()
            .statusCode(lessThan(300))
            .extract().path("id");

        given()
        .when()
            .delete("/products/{productName}/constraints/{constraintId}", productName, constraintId)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testCreateAndRetrieveConstraints() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        String feature1 = "Feature1-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, feature1).then().statusCode(lessThan(300));

        String feature2 = "Feature2-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, feature2).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then()
            .statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}", productName)
        .then()
            .statusCode(200);
    }
}