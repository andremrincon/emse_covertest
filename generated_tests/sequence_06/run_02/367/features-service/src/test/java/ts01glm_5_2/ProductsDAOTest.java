package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsDAOTest {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void insertConstraintViaRequiresConstraintEndpoint() {
        String productName = "test-prod-req-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "feat-source-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "feat-required-" + UUID.randomUUID().toString().substring(0, 8);

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
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/" + productName + "/constraints/requires")
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void insertConstraintViaExcludesConstraintEndpoint() {
        String productName = "test-prod-exc-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "feat-src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "feat-excl-" + UUID.randomUUID().toString().substring(0, 8);

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
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when()
                .post("/products/" + productName + "/constraints/excludes")
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void deleteConstraintsForProductViaProductDeletion() {
        String productName = "test-prod-del-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "feat-src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "feat-req-" + UUID.randomUUID().toString().substring(0, 8);

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
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/" + productName + "/constraints/requires")
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .delete("/products/" + productName)
                .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteConstraintsForProductWithMultipleConstraintsViaProductDeletion() {
        String productName = "test-prod-multi-" + UUID.randomUUID().toString().substring(0, 8);
        String featA = "feat-a-" + UUID.randomUUID().toString().substring(0, 8);
        String featB = "feat-b-" + UUID.randomUUID().toString().substring(0, 8);
        String featC = "feat-c-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/" + productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/" + productName + "/features/" + featA)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/" + productName + "/features/" + featB)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/" + productName + "/features/" + featC)
                .then()
                .statusCode(lessThan(300));

        given()
                .formParam("sourceFeature", featA)
                .formParam("requiredFeature", featB)
                .when()
                .post("/products/" + productName + "/constraints/requires")
                .then()
                .statusCode(lessThan(300));

        given()
                .formParam("sourceFeature", featA)
                .formParam("excludedFeature", featC)
                .when()
                .post("/products/" + productName + "/constraints/excludes")
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .delete("/products/" + productName)
                .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void insertConstraintRequiresWithDescription() {
        String productName = "test-prod-desc-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "feat-s-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "feat-r-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/" + productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .formParam("description", "Source feature for testing")
                .when()
                .post("/products/" + productName + "/features/" + sourceFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .formParam("description", "Required feature for testing")
                .when()
                .post("/products/" + productName + "/features/" + requiredFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/" + productName + "/constraints/requires")
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void deleteConstraintsForProductWithNoConstraintsViaProductDeletion() {
        String productName = "test-prod-noconst-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-only-" + UUID.randomUUID().toString().substring(0, 8);

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
                .delete("/products/" + productName)
                .then()
                .statusCode(204);
    }
}