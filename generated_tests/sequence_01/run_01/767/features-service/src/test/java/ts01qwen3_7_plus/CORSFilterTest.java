package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetProducts_CoverDoFilterNonOptions() {
        given()
            .when()
                .get("/products")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOptionsProducts_CoverDoFilterOptions() {
        given()
            .when()
                .options("/products")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPostProduct_CoverDoFilterNonOptions() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testPutFeature_CoverDoFilterNonOptions() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Test feature description")
            .when()
                .post("/products/" + productName + "/features/" + featureName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Updated feature description")
            .when()
                .put("/products/" + productName + "/features/" + featureName)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteProduct_CoverDoFilterNonOptions() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .delete("/products/" + productName)
            .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetFeatures_CoverDoFilterNonOptions() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Test feature description")
            .when()
                .post("/products/" + productName + "/features/" + featureName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/" + productName + "/features")
            .then()
                .statusCode(200);
    }
}