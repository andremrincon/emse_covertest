package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    @Before
    public void setup() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    private void createProductAndFeatures(String productName, String feature1, String feature2) {
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("description", "desc1").when().post("/products/{productName}/features/{featureName}", productName, feature1).then().statusCode(lessThan(300));
        given().formParam("description", "desc2").when().post("/products/{productName}/features/{featureName}", productName, feature2).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint() {
        String productName = "Prod-Req-" + UUID.randomUUID().toString();
        String feature1 = "Feat1-" + UUID.randomUUID().toString();
        String feature2 = "Feat2-" + UUID.randomUUID().toString();
        createProductAndFeatures(productName, feature1, feature2);

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
        String productName = "Prod-Exc-" + UUID.randomUUID().toString();
        String feature1 = "Feat1-" + UUID.randomUUID().toString();
        String feature2 = "Feat2-" + UUID.randomUUID().toString();
        createProductAndFeatures(productName, feature1, feature2);

        given()
            .formParam("sourceFeature", feature1)
            .formParam("excludedFeature", feature2)
            .when()
            .post("/products/{productName}/constraints/excludes", productName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRetrieveProductWithRequiresConstraint() {
        String productName = "Prod-GetReq-" + UUID.randomUUID().toString();
        String feature1 = "Feat1-" + UUID.randomUUID().toString();
        String feature2 = "Feat2-" + UUID.randomUUID().toString();
        createProductAndFeatures(productName, feature1, feature2);

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

    @Test(timeout = 60000)
    public void testRetrieveProductWithExcludesConstraint() {
        String productName = "Prod-GetExc-" + UUID.randomUUID().toString();
        String feature1 = "Feat1-" + UUID.randomUUID().toString();
        String feature2 = "Feat2-" + UUID.randomUUID().toString();
        createProductAndFeatures(productName, feature1, feature2);

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

    @Test(timeout = 60000)
    public void testDeleteRequiresConstraint() {
        String productName = "Prod-DelReq-" + UUID.randomUUID().toString();
        String feature1 = "Feat1-" + UUID.randomUUID().toString();
        String feature2 = "Feat2-" + UUID.randomUUID().toString();
        createProductAndFeatures(productName, feature1, feature2);

        given()
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
            .when()
            .post("/products/{productName}/constraints/requires", productName)
            .then()
            .statusCode(lessThan(300));

        Number constraintIdNum = given()
            .when()
            .get("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300))
            .extract()
            .path("constraints[0].id");
        long constraintId = constraintIdNum.longValue();

        given()
            .when()
            .delete("/products/{productName}/constraints/{constraintId}", productName, constraintId)
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteExcludesConstraint() {
        String productName = "Prod-DelExc-" + UUID.randomUUID().toString();
        String feature1 = "Feat1-" + UUID.randomUUID().toString();
        String feature2 = "Feat2-" + UUID.randomUUID().toString();
        createProductAndFeatures(productName, feature1, feature2);

        given()
            .formParam("sourceFeature", feature1)
            .formParam("excludedFeature", feature2)
            .when()
            .post("/products/{productName}/constraints/excludes", productName)
            .then()
            .statusCode(lessThan(300));

        Number constraintIdNum = given()
            .when()
            .get("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300))
            .extract()
            .path("constraints[0].id");
        long constraintId = constraintIdNum.longValue();

        given()
            .when()
            .delete("/products/{productName}/constraints/{constraintId}", productName, constraintId)
            .then()
            .statusCode(204);
    }
}