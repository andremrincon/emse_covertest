package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct() {
        String productName = "Product-" + System.nanoTime();

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/Feature1")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddMultipleFeaturesToProduct() {
        String productName = "Product-" + System.nanoTime();

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/Feature1")
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/Feature2")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromProduct() {
        String productName = "Product-" + System.nanoTime();

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/Feature1")
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .delete("/products/" + productName + "/features/Feature1")
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint() {
        String productName = "Product-" + System.nanoTime();

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/Feature1")
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/Feature2")
            .then()
            .statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", "Feature1")
            .formParam("requiredFeature", "Feature2")
            .when()
            .post("/products/" + productName + "/constraints/requires")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint() {
        String productName = "Product-" + System.nanoTime();

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/Feature1")
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/Feature2")
            .then()
            .statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", "Feature1")
            .formParam("excludedFeature", "Feature2")
            .when()
            .post("/products/" + productName + "/constraints/excludes")
            .then()
            .statusCode(201);
    }
}