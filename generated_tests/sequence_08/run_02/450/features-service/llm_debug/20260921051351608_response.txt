package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint() {
        String productName = "TestProduct-Requires-" + System.currentTimeMillis();
        String sourceFeature = "FeatureA";
        String requiredFeature = "FeatureB";

        given()
            .pathParam("productName", productName)
        .when()
            .post("/products/{productName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", sourceFeature)
            .formParam("description", "Source feature")
        .when()
            .post("/products/{productName}/features/{featureName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", requiredFeature)
            .formParam("description", "Required feature")
        .when()
            .post("/products/{productName}/features/{featureName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/{productName}/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint() {
        String productName = "TestProduct-Excludes-" + System.currentTimeMillis();
        String sourceFeature = "FeatureC";
        String excludedFeature = "FeatureD";

        given()
            .pathParam("productName", productName)
        .when()
            .post("/products/{productName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", sourceFeature)
            .formParam("description", "Source feature")
        .when()
            .post("/products/{productName}/features/{featureName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", excludedFeature)
            .formParam("description", "Excluded feature")
        .when()
            .post("/products/{productName}/features/{featureName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraint() {
        String productName = "TestProduct-Delete-" + System.currentTimeMillis();
        String sourceFeature = "FeatureE";
        String excludedFeature = "FeatureF";

        given()
            .pathParam("productName", productName)
        .when()
            .post("/products/{productName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", sourceFeature)
            .formParam("description", "Source feature")
        .when()
            .post("/products/{productName}/features/{featureName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", excludedFeature)
            .formParam("description", "Excluded feature")
        .when()
            .post("/products/{productName}/features/{featureName}")
        .then()
            .statusCode(lessThan(300));

        String location = given()
            .pathParam("productName", productName)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes")
        .then()
            .statusCode(lessThan(300))
            .extract()
            .header("Location");

        String constraintId = "";
        if (location != null) {
            int idx = location.lastIndexOf('/');
            if (idx >= 0 && idx < location.length() - 1) {
                constraintId = location.substring(idx + 1);
            } else {
                constraintId = location;
            }
        }

        given()
            .pathParam("productName", productName)
            .pathParam("constraintId", constraintId)
        .when()
            .delete("/products/{productName}/constraints/{constraintId}")
        .then()
            .statusCode(204);
    }
}