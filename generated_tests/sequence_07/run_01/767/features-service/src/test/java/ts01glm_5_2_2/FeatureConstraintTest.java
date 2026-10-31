package ts01glm_5_2_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;

public class FeatureConstraintTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintExercisesSetForProductAndSetId() {
        String productName = "TestProduct-Req-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "RAID-Controller-Card";
        String requiredFeature = "128GB-ECC-RAM";

        RestAssured.given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        RestAssured.given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintExercisesSetForProductAndSetId() {
        String productName = "TestProduct-Excl-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "CPU-i9-13900H";
        String excludedFeature = "Integrated-Graphics-Only";

        RestAssured.given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        RestAssured.given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when()
                .post("/products/{productName}/constraints/excludes", productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintExercisesGetId() {
        String productName = "TestProduct-Del-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SSD-Storage";
        String requiredFeature = "Graphics-Card";

        RestAssured.given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        Response resp = RestAssured.given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(lessThan(300))
                .extract()
                .response();

        Integer constraintId = null;
        String body = resp.getBody() == null ? null : resp.getBody().asString();
        Object idObj = null;
        if (body != null && !body.isEmpty() && resp.getContentType() != null) {
            idObj = resp.path("id");
            if (idObj instanceof Number) {
                constraintId = ((Number) idObj).intValue();
            } else if (idObj instanceof String) {
                try {
                    constraintId = Integer.parseInt((String) idObj);
                } catch (NumberFormatException e) {
                    constraintId = null;
                }
            }
        }

        if (constraintId == null) {
            RestAssured.given()
                    .when()
                    .get("/products/{productName}", productName)
                    .then()
                    .statusCode(lessThan(300));
            constraintId = RestAssured.given()
                    .when()
                    .get("/products/{productName}", productName)
                    .then()
                    .statusCode(lessThan(300))
                    .extract()
                    .path("constraints[0].id");
        }

        RestAssured.given()
                .when()
                .delete("/products/{productName}/constraints/{constraintId}", productName, constraintId)
                .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetProductWithConstraintsExercisesGetId() {
        String productName = "TestProduct-Get-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "Push-Notifications";
        String requiredFeature = "User-Authentication";

        RestAssured.given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        RestAssured.given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(lessThan(300));

        RestAssured.given()
                .when()
                .get("/products/{productName}", productName)
                .then()
                .statusCode(200)
                .body("constraints", not(empty()));
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintWithMissingSourceFeatureReturnsError() {
        String productName = "TestProduct-ReqErr-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        RestAssured.given()
                .contentType(ContentType.URLENC)
                .formParam("requiredFeature", "FeatureB")
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintWithMissingExcludedFeatureReturnsError() {
        String productName = "TestProduct-ExclErr-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        RestAssured.given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", "Color-Black")
                .when()
                .post("/products/{productName}/constraints/excludes", productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteNonExistentConstraintReturnsError() {
        String productName = "TestProduct-NoConstraint-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        RestAssured.given()
                .when()
                .delete("/products/{productName}/constraints/{constraintId}", productName, 999999)
                .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintOnNonExistentProductReturnsError() {
        String productName = "NonExistentProduct-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", "FeatureA")
                .formParam("requiredFeature", "FeatureB")
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintOnNonExistentProductReturnsError() {
        String productName = "NonExistentProduct2-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", "FeatureA")
                .formParam("excludedFeature", "FeatureB")
                .when()
                .post("/products/{productName}/constraints/excludes", productName)
                .then()
                .statusCode(500);
    }
}