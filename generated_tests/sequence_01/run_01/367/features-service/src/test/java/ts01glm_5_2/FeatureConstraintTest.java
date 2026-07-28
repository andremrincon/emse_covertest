package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSetIdTriggeredByRequiresConstraintRetrieval() {
        String productName = "test-product-requires-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-feat-" + UUID.randomUUID().toString().substring(0, 8);

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
                .contentType(ContentType.URLENC)
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
    public void testSetIdTriggeredByExcludesConstraintRetrieval() {
        String productName = "test-product-excludes-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-feat-" + UUID.randomUUID().toString().substring(0, 8);

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
                .contentType(ContentType.URLENC)
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
    public void testSetIdTriggeredByConstraintDeletion() {
        String productName = "test-product-delete-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-feat-" + UUID.randomUUID().toString().substring(0, 8);

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
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/" + productName + "/constraints/requires")
                .then()
                .statusCode(lessThan(300));

        String constraintId = "1";

        given()
                .when()
                .get("/products/" + productName)
                .then()
                .statusCode(200);

        given()
                .when()
                .delete("/products/" + productName + "/constraints/" + constraintId)
                .then()
                .statusCode(204);
    }
}