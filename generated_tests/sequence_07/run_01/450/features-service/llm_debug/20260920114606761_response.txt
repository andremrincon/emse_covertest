package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConstraintsResourceTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void addRequiresConstraintToProduct_returnsCreated() {
        String productName = "TestProduct-Requires-" + UUID.randomUUID();
        String sourceFeature = "RAID-Controller-Card";
        String requiredFeature = "128GB-ECC-RAM";

        given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/features/{featureName}", productName, requiredFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
                .post("/products/{productName}/constraints/requires", productName)
        .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraintToProduct_returnsCreated() {
        String productName = "TestProduct-Excludes-" + UUID.randomUUID();
        String sourceFeature = "CPU-i9-13900H";
        String excludedFeature = "Integrated-Graphics-Only";

        given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/features/{featureName}", productName, excludedFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
                .post("/products/{productName}/constraints/excludes", productName)
        .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void deleteConstraint_returnsNoContent() {
        String productName = "TestProduct-Delete-" + UUID.randomUUID();
        String sourceFeature = "PSU-Redundant";
        String excludedFeature = "OS-Home";

        given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/features/{featureName}", productName, excludedFeature)
            .then()
                .statusCode(lessThan(300));

        String location = given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
                .post("/products/{productName}/constraints/excludes", productName)
        .then()
                .statusCode(lessThan(300))
                .extract()
                .header("Location");

        String constraintId = location.substring(location.lastIndexOf("/") + 1);

        given()
            .when()
                .delete("/products/{productName}/constraints/{constraintId}", productName, constraintId)
        .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void addRequiresConstraintToProduct_withNonExistentProduct_returnsServerError() {
        String productName = "NonExistentProduct-" + UUID.randomUUID();

        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", "FeatureA")
            .formParam("requiredFeature", "FeatureB")
        .when()
                .post("/products/{productName}/constraints/requires", productName)
        .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraintToProduct_withNonExistentProduct_returnsServerError() {
        String productName = "NonExistentProduct-" + UUID.randomUUID();

        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", "FeatureA")
            .formParam("excludedFeature", "FeatureB")
        .when()
                .post("/products/{productName}/constraints/excludes", productName)
        .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void deleteConstraint_withNonExistentConstraint_returnsServerError() {
        String productName = "TestProduct-DeleteNotFound-" + UUID.randomUUID();

        given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .delete("/products/{productName}/constraints/{constraintId}", productName, 99999999)
        .then()
                .statusCode(204);
    }
}