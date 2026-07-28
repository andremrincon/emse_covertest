package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintTriggersFeatureConstraintSetId() {
        String productName = "TestProduct-Requires-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID().toString().substring(0, 8);

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
    public void testAddExcludesConstraintTriggersFeatureConstraintSetId() {
        String productName = "TestProduct-Excludes-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeature-" + UUID.randomUUID().toString().substring(0, 8);

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
    public void testDeleteConstraintAfterCreationTriggersFeatureConstraintSetId() {
        String productName = "TestProduct-Delete-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        String location = given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/" + productName + "/constraints/requires")
                .then()
                .statusCode(201)
                .extract()
                .header("Location");

        String constraintId = location.substring(location.lastIndexOf("/") + 1);

        given()
                .when()
                .delete("/products/" + productName + "/constraints/" + constraintId)
                .then()
                .statusCode(204);
    }
}