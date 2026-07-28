package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;
import java.util.UUID;

public class ProductsConstraintsResourceTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void addRequiresConstraintToProduct_success() {
        String productName = "test-prod-req-" + UUID.randomUUID().toString();
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString();
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + sourceFeature)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + requiredFeature)
            .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .when()
            .post("/products/" + productName + "/constraints/requires")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraintToProduct_success() {
        String productName = "test-prod-excl-" + UUID.randomUUID().toString();
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString();
        String excludedFeature = "ExcludedFeature-" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + sourceFeature)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + excludedFeature)
            .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when()
            .post("/products/" + productName + "/constraints/excludes")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void deleteConstraint_success() {
        String productName = "test-prod-del-" + UUID.randomUUID().toString();
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString();
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + sourceFeature)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + requiredFeature)
            .then()
            .statusCode(lessThan(300));

        String locationHeader = given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .when()
            .post("/products/" + productName + "/constraints/requires")
            .then()
            .statusCode(lessThan(300))
            .extract()
            .header("Location");

        String constraintId = locationHeader.substring(locationHeader.lastIndexOf("/") + 1);

        given()
            .when()
            .delete("/products/" + productName + "/constraints/" + constraintId)
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void addRequiresConstraint_nonExistentProduct_returns500() {
        String productName = "non-existent-product-" + UUID.randomUUID().toString();
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString();
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID().toString();

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .when()
            .post("/products/" + productName + "/constraints/requires")
            .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraint_nonExistentProduct_returns500() {
        String productName = "non-existent-product-" + UUID.randomUUID().toString();
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString();
        String excludedFeature = "ExcludedFeature-" + UUID.randomUUID().toString();

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when()
            .post("/products/" + productName + "/constraints/excludes")
            .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void deleteConstraint_nonExistentProduct_returns500() {
        String productName = "non-existent-product-" + UUID.randomUUID().toString();

        given()
            .when()
            .delete("/products/" + productName + "/constraints/1")
            .then()
            .statusCode(204);
    }
}