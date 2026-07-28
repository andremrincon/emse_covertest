package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import java.util.UUID;

public class FeatureConstraintTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint() {
        String productName = "test-req-" + UUID.randomUUID();
        String sourceFeature = "src-req-" + UUID.randomUUID();
        String requiredFeature = "need-req-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint() {
        String productName = "test-exc-" + UUID.randomUUID();
        String sourceFeature = "src-exc-" + UUID.randomUUID();
        String excludedFeature = "excl-feat-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductWithRequiresConstraint() {
        String productName = "test-get-req-" + UUID.randomUUID();
        String sourceFeature = "src-get-req-" + UUID.randomUUID();
        String requiredFeature = "need-get-req-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/{productName}", productName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetProductWithExcludesConstraint() {
        String productName = "test-get-exc-" + UUID.randomUUID();
        String sourceFeature = "src-get-exc-" + UUID.randomUUID();
        String excludedFeature = "excl-get-exc-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/{productName}", productName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteRequiresConstraint() {
        String productName = "test-del-req-" + UUID.randomUUID();
        String sourceFeature = "src-del-req-" + UUID.randomUUID();
        String requiredFeature = "need-del-req-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));

        Number constraintId = given()
            .when()
            .get("/products/{productName}", productName)
        .then()
            .statusCode(lessThan(300))
            .extract()
            .path("constraints[0].id");

        given()
            .when()
            .delete("/products/{productName}/constraints/{constraintId}", productName, constraintId)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteExcludesConstraint() {
        String productName = "test-del-exc-" + UUID.randomUUID();
        String sourceFeature = "src-del-exc-" + UUID.randomUUID();
        String excludedFeature = "excl-del-exc-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));

        Number constraintId = given()
            .when()
            .get("/products/{productName}", productName)
        .then()
            .statusCode(lessThan(300))
            .extract()
            .path("constraints[0].id");

        given()
            .when()
            .delete("/products/{productName}/constraints/{constraintId}", productName, constraintId)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintWithNonExistentFeatures() {
        String productName = "test-req-err-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", "non-existent-src-" + UUID.randomUUID())
            .formParam("requiredFeature", "non-existent-need-" + UUID.randomUUID())
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintWithNonExistentFeatures() {
        String productName = "test-exc-err-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", "non-existent-src-" + UUID.randomUUID())
            .formParam("excludedFeature", "non-existent-excl-" + UUID.randomUUID())
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductWithBothConstraintTypes() {
        String productName = "test-both-" + UUID.randomUUID();
        String sourceFeature = "src-both-" + UUID.randomUUID();
        String requiredFeature = "need-both-" + UUID.randomUUID();
        String excludedFeature = "excl-both-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/{productName}", productName)
        .then()
            .statusCode(200);
    }
}