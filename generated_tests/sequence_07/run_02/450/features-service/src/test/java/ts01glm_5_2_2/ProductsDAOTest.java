package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsDAOTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testInsertConstraintViaRequiresEndpoint_1() {
        String productName = "test-req-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "FeatureA").then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "FeatureB").then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", "FeatureA")
            .formParam("requiredFeature", "FeatureB")
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testInsertConstraintViaExcludesEndpoint_1() {
        String productName = "test-exc-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "Color-Black").then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "Logo-White-On-Black").then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", "Color-Black")
            .formParam("excludedFeature", "Logo-White-On-Black")
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintsForProductWithConstraints_1() {
        String productName = "test-del-constraints-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "SourceFeat").then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "ExcludedFeat").then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", "SourceFeat")
            .formParam("excludedFeature", "ExcludedFeat")
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/{productName}", productName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintsForProductWithoutConstraints_1() {
        String productName = "test-del-no-constraints-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/{productName}", productName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testInsertConstraintRequiresOnNonExistentProduct_1() {
        String productName = "non-existent-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .formParam("sourceFeature", "FeatureA")
            .formParam("requiredFeature", "FeatureB")
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testInsertConstraintExcludesOnNonExistentProduct_1() {
        String productName = "non-existent-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .formParam("sourceFeature", "FeatureA")
            .formParam("excludedFeature", "FeatureB")
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(500);
    }
}