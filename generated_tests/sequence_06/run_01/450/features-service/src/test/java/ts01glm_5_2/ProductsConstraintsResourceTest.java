package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConstraintsResourceTest {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProductSuccess() {
        String productName = "TestReq-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "ReqFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

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
    public void testAddExcludesConstraintToProductSuccess() {
        String productName = "TestExc-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));

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
    public void testAddRequiresConstraintToProductServerError() {
        String productName = "NonExistent-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", "FeatureA")
            .formParam("requiredFeature", "FeatureB")
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProductServerError() {
        String productName = "NonExistent-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", "FeatureA")
            .formParam("excludedFeature", "FeatureB")
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintSuccess() {
        String productName = "TestDel-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "ReqFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        String location = given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(lessThan(300))
            .extract().header("Location");

        String constraintId = location.substring(location.lastIndexOf("/") + 1);

        given()
        .when()
            .delete("/products/" + productName + "/constraints/" + constraintId)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintServerError() {
        String productName = "TestDelErr-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/" + productName + "/constraints/999999")
        .then()
            .statusCode(204);
    }
}