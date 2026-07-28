package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsDAOTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testInsertConstraintViaRequiresEndpoint() {
        String productName = "test-req-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-" + UUID.randomUUID().toString().substring(0, 8);

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
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
            .when()
                .post("/products/{productName}/constraints/requires", productName)
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testInsertConstraintViaExcludesEndpoint() {
        String productName = "test-exc-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-" + UUID.randomUUID().toString().substring(0, 8);

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
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
            .when()
                .post("/products/{productName}/constraints/excludes", productName)
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintsForProductWithConstraints() {
        String productName = "test-del-con-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "fs-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "fr-" + UUID.randomUUID().toString().substring(0, 8);

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
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
            .when()
                .post("/products/{productName}/constraints/requires", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .delete("/products/{productName}", productName)
            .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintsForProductWithoutConstraints() {
        String productName = "test-no-con-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .delete("/products/{productName}", productName)
            .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintsForProductWithMultipleConstraints() {
        String productName = "test-multi-con-" + UUID.randomUUID().toString().substring(0, 8);
        String featureA = "fa-" + UUID.randomUUID().toString().substring(0, 8);
        String featureB = "fb-" + UUID.randomUUID().toString().substring(0, 8);
        String featureC = "fc-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/features/{featureName}", productName, featureA)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/features/{featureName}", productName, featureB)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/features/{featureName}", productName, featureC)
            .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", featureA)
                .formParam("requiredFeature", featureB)
            .when()
                .post("/products/{productName}/constraints/requires", productName)
            .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", featureA)
                .formParam("excludedFeature", featureC)
            .when()
                .post("/products/{productName}/constraints/excludes", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .delete("/products/{productName}", productName)
            .then()
                .statusCode(204);
    }
}