package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ProductsDAOTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testInsertConstraintViaRequiresEndpoint() {
        String productName = "test-req-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureA = "featA-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureB = "featB-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
                .formParam("sourceFeature", featureA)
                .formParam("requiredFeature", featureB)
            .when()
                .post("/products/{productName}/constraints/requires", productName)
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testInsertConstraintViaExcludesEndpoint() {
        String productName = "test-exc-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureA = "featC-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureB = "featD-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
                .formParam("sourceFeature", featureA)
                .formParam("excludedFeature", featureB)
            .when()
                .post("/products/{productName}/constraints/excludes", productName)
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintsForProductWithConstraints() {
        String productName = "test-del-constr-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureA = "srcFeat-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureB = "reqFeat-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
                .formParam("sourceFeature", featureA)
                .formParam("requiredFeature", featureB)
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
    public void testDeleteConstraintsForProductWithNoConstraints() {
        String productName = "test-no-constr-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
        String productName = "test-multi-constr-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureA = "fA-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureB = "fB-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureC = "fC-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
                .formParam("sourceFeature", featureA)
                .formParam("requiredFeature", featureB)
            .when()
                .post("/products/{productName}/constraints/requires", productName)
            .then()
                .statusCode(lessThan(300));

        given()
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

    @Test(timeout = 60000)
    public void testInsertConstraintAndDeleteConstraintById() {
        String productName = "test-constr-id-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureA = "sFeat-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureB = "eFeat-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
                .formParam("sourceFeature", featureA)
                .formParam("excludedFeature", featureB)
            .when()
                .post("/products/{productName}/constraints/excludes", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .delete("/products/{productName}", productName)
            .then()
                .statusCode(204);
    }
}