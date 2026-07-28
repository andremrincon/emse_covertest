package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.*;

public class ProductsDAOTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testInsertConstraintViaRequiresEndpoint() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "test-req-" + uuid;
        String sourceFeature = "src-feat-" + uuid;
        String requiredFeature = "req-feat-" + uuid;

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
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "test-excl-" + uuid;
        String sourceFeature = "src-feat-" + uuid;
        String excludedFeature = "excl-feat-" + uuid;

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
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "test-del-constraints-" + uuid;
        String sourceFeature = "src-feat-" + uuid;
        String requiredFeature = "req-feat-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));

        when()
            .delete("/products/" + productName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testInsertConstraintRequiresOnNonExistentProduct() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "non-existent-" + uuid;

        given()
            .formParam("sourceFeature", "feature-a")
            .formParam("requiredFeature", "feature-b")
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testInsertConstraintExcludesOnNonExistentProduct() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "non-existent-excl-" + uuid;

        given()
            .formParam("sourceFeature", "feature-a")
            .formParam("excludedFeature", "feature-b")
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintsForProductWithMultipleConstraints() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "test-multi-constraints-" + uuid;
        String feat1 = "feat1-" + uuid;
        String feat2 = "feat2-" + uuid;
        String feat3 = "feat3-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feat1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feat2).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feat3).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", feat1).formParam("requiredFeature", feat2)
            .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().formParam("sourceFeature", feat1).formParam("excludedFeature", feat3)
            .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));

        when()
            .delete("/products/" + productName)
        .then()
            .statusCode(204);
    }
}