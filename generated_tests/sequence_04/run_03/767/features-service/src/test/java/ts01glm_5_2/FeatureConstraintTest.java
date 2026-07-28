package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class FeatureConstraintTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void createRequiresConstraintTriggersFeatureConstraintSetId() {
        String productName = "TestProductRequires_" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeature_" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RequiredFeature_" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
    public void createExcludesConstraintTriggersFeatureConstraintSetId() {
        String productName = "TestProductExcludes_" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "ExclSource_" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExclTarget_" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
    public void deleteConstraintTriggersFeatureConstraintSetIdOnLoad() {
        String productName = "TestProductDelete_" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "DelSource_" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "DelRequired_" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
            .statusCode(201)
            .extract().header("Location");

        String constraintId = location.substring(location.lastIndexOf("/") + 1);

        given()
            .when()
            .delete("/products/" + productName + "/constraints/" + constraintId)
        .then()
            .statusCode(204);
    }
}