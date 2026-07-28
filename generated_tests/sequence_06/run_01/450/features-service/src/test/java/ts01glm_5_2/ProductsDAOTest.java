package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class ProductsDAOTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void insertConstraintViaRequiresEndpoint() {
        String productName = "test-prod-requires-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RequiredFeat-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
    public void insertConstraintViaExcludesEndpoint() {
        String productName = "test-prod-excludes-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "ExclSource-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExclTarget-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
    public void deleteConstraintsForProductWhenProductHasConstraints() {
        String productName = "test-prod-del-constraints-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "DelSource-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "DelRequired-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
                .statusCode(lessThan(300));

        given()
                .when()
                .delete("/products/{productName}", productName)
                .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteConstraintsForProductWhenProductHasNoConstraints() {
        String productName = "test-prod-no-constraints-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
    public void deleteConstraintsForProductWithMultipleConstraints() {
        String productName = "test-prod-multi-constraints-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String feat1 = "Feat1-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String feat2 = "Feat2-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String feat3 = "Feat3-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, feat1)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, feat2)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, feat3)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", feat1)
                .formParam("requiredFeature", feat2)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", feat1)
                .formParam("excludedFeature", feat3)
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
    public void insertConstraintAndDeleteConstraintById() {
        String productName = "test-prod-constraint-del-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "CSource-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "CTarget-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .delete("/products/{productName}/constraints/{constraintId}", productName, 1)
                .then()
                .statusCode(equalTo(204));
    }
}