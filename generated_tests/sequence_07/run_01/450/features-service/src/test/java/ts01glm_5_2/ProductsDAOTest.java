package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

public class ProductsDAOTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void insertConstraint_addRequiresConstraintReturns201() {
        String productName = "req-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "reqF-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void insertConstraint_addExcludesConstraintReturns201() {
        String productName = "exc-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "srcE-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "excF-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void deleteConstraintsForProduct_deleteProductWithRequiresConstraint() {
        String productName = "delR-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "sR-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "rR-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/{productName}", productName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteConstraintsForProduct_deleteProductWithExcludesConstraint() {
        String productName = "delE-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "sE-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "eE-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/{productName}", productName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteConstraintsForProduct_deleteProductWithMultipleConstraints() {
        String productName = "delM-" + UUID.randomUUID().toString().substring(0, 8);
        String feature1 = "m1-" + UUID.randomUUID().toString().substring(0, 8);
        String feature2 = "m2-" + UUID.randomUUID().toString().substring(0, 8);
        String feature3 = "m3-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, feature1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, feature2).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, feature3).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
        .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", feature1)
            .formParam("excludedFeature", feature3)
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/{productName}", productName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void insertConstraint_verifyConstraintPersistedByGettingProduct() {
        String productName = "verC-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "cS-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "cR-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}", productName)
        .then()
            .body("constraints.size()", greaterThan(0));
    }
}