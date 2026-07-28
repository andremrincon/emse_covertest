package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsDAOTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testInsertConstraintViaRequiresEndpoint() {
        String productName = "test-prod-req-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testInsertConstraintViaExcludesEndpoint() {
        String productName = "test-prod-exc-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintsForProductViaProductDeletion() {
        String productName = "test-prod-del-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/" + productName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintsForProductWithMultipleConstraints() {
        String productName = "test-prod-multi-" + UUID.randomUUID().toString().substring(0, 8);
        String feat1 = "feat1-" + UUID.randomUUID().toString().substring(0, 8);
        String feat2 = "feat2-" + UUID.randomUUID().toString().substring(0, 8);
        String feat3 = "feat3-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feat1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feat2).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feat3).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", feat1)
            .formParam("requiredFeature", feat2)
        .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", feat1)
            .formParam("excludedFeature", feat3)
        .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/" + productName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintsForProductWithNoConstraints() {
        String productName = "test-prod-noconst-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/" + productName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testInsertConstraintAndDeleteProductWithBothConstraintTypes() {
        String productName = "test-prod-both-" + UUID.randomUUID().toString().substring(0, 8);
        String featA = "featA-" + UUID.randomUUID().toString().substring(0, 8);
        String featB = "featB-" + UUID.randomUUID().toString().substring(0, 8);
        String featC = "featC-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featA).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featB).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featC).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", featA)
            .formParam("requiredFeature", featB)
        .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", featB)
            .formParam("excludedFeature", featC)
        .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/" + productName)
        .then()
            .statusCode(204);
    }
}