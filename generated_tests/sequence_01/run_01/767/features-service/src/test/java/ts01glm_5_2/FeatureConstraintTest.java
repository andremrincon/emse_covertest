package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testRequiresConstraintCreationAndRetrievalCoversSetId() {
        String productName = "TestProduct-Requires-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RequiredFeat-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/" + productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/" + productName + "/features/" + sourceFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/" + productName + "/features/" + requiredFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/" + productName + "/constraints/requires")
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/" + productName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExcludesConstraintCreationAndRetrievalCoversSetId() {
        String productName = "TestProduct-Excludes-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "ExclSource-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExclTarget-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/" + productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/" + productName + "/features/" + sourceFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/" + productName + "/features/" + excludedFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when()
                .post("/products/" + productName + "/constraints/excludes")
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/" + productName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testConstraintDeletionCoversSetId() {
        String productName = "TestProduct-Delete-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "DelSource-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "DelRequired-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/" + productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/" + productName + "/features/" + sourceFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/" + productName + "/features/" + requiredFeature)
                .then()
                .statusCode(lessThan(300));

        String constraintLocation = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/" + productName + "/constraints/requires")
                .then()
                .statusCode(lessThan(300))
                .extract()
                .header("Location");

        given()
                .when()
                .get("/products/" + productName)
                .then()
                .statusCode(lessThan(300));

        String constraintId = "1";

        given()
                .when()
                .delete("/products/" + productName + "/constraints/" + constraintId)
                .then()
                .statusCode(204);
    }
}