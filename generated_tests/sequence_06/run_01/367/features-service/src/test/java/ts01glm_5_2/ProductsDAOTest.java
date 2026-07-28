package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsDAOTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testInsertConstraintViaRequiresEndpoint() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String feature1 = "feat-" + UUID.randomUUID().toString().substring(0, 8);
        String feature2 = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testInsertConstraintViaExcludesEndpoint() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String feature1 = "feat-" + UUID.randomUUID().toString().substring(0, 8);
        String feature2 = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", feature1)
            .formParam("excludedFeature", feature2)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintsForProductWithSingleConstraint() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String feature1 = "feat-" + UUID.randomUUID().toString().substring(0, 8);
        String feature2 = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintsForProductWithMultipleConstraints() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String feature1 = "feat-" + UUID.randomUUID().toString().substring(0, 8);
        String feature2 = "feat-" + UUID.randomUUID().toString().substring(0, 8);
        String feature3 = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature3).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", feature2)
            .formParam("excludedFeature", feature3)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintsForProductWithNoConstraints() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testInsertConstraintOnNonExistentProduct() {
        String productName = "nonexistent-" + UUID.randomUUID().toString().substring(0, 8);
        String feature1 = "feat-" + UUID.randomUUID().toString().substring(0, 8);
        String feature2 = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(500);
    }
}