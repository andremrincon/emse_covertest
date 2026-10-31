package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsDAOTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testInsertRequiresConstraint() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String feature1 = "Feature1-" + UUID.randomUUID().toString();
        String feature2 = "Feature2-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
            .when()
            .post("/products/{productName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", feature1)
            .formParam("description", "Test feature 1")
            .when()
            .post("/products/{productName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", feature2)
            .formParam("description", "Test feature 2")
            .when()
            .post("/products/{productName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
            .when()
            .post("/products/{productName}/constraints/requires")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testInsertExcludesConstraint() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String feature1 = "Feature1-" + UUID.randomUUID().toString();
        String feature2 = "Feature2-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
            .when()
            .post("/products/{productName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", feature1)
            .formParam("description", "Test feature 1")
            .when()
            .post("/products/{productName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", feature2)
            .formParam("description", "Test feature 2")
            .when()
            .post("/products/{productName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .formParam("sourceFeature", feature1)
            .formParam("excludedFeature", feature2)
            .when()
            .post("/products/{productName}/constraints/excludes")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintsForProduct() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String feature1 = "Feature1-" + UUID.randomUUID().toString();
        String feature2 = "Feature2-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
            .when()
            .post("/products/{productName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", feature1)
            .formParam("description", "Test feature 1")
            .when()
            .post("/products/{productName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", feature2)
            .formParam("description", "Test feature 2")
            .when()
            .post("/products/{productName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
            .when()
            .post("/products/{productName}/constraints/requires")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .when()
            .delete("/products/{productName}")
            .then()
            .statusCode(204);
    }
}