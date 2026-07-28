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
    public void testInsertConstraintRequires() {
        String productName = "test-prod-req-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testInsertConstraintExcludes() {
        String productName = "test-prod-exc-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintsForProductWithRequiresConstraint() {
        String productName = "test-prod-del-req-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then().statusCode(lessThan(300));

        when()
            .delete("/products/{productName}", productName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintsForProductWithExcludesConstraint() {
        String productName = "test-prod-del-exc-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then().statusCode(lessThan(300));

        when()
            .delete("/products/{productName}", productName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintsForProductWithMultipleConstraints() {
        String productName = "test-prod-del-multi-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureA = "feat-a-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureB = "feat-b-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureC = "feat-c-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureB).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureC).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", featureA)
            .formParam("requiredFeature", featureB)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", featureA)
            .formParam("excludedFeature", featureC)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then().statusCode(lessThan(300));

        when()
            .delete("/products/{productName}", productName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintsForProductWithNoConstraints() {
        String productName = "test-prod-del-none-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        when()
            .delete("/products/{productName}", productName)
        .then()
            .statusCode(204);
    }
}