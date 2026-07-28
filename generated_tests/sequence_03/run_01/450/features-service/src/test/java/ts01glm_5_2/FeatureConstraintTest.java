package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;

import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.given;

public class FeatureConstraintTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetProductWithRequiresConstraintTriggersSetId() {
        String suffix = UUID.randomUUID().toString().substring(0, 8);
        String productName = "ReqProduct-" + suffix;
        String sourceFeature = "SrcFeature-" + suffix;
        String requiredFeature = "ReqFeature-" + suffix;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then().statusCode(lessThan(300));

        given().when().get("/products/" + productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetProductWithExcludesConstraintTriggersSetId() {
        String suffix = UUID.randomUUID().toString().substring(0, 8);
        String productName = "ExclProduct-" + suffix;
        String sourceFeature = "ExclSrc-" + suffix;
        String excludedFeature = "ExcludedFeat-" + suffix;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then().statusCode(lessThan(300));

        given().when().get("/products/" + productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintTriggersSetId() {
        String suffix = UUID.randomUUID().toString().substring(0, 8);
        String productName = "DelProduct-" + suffix;
        String sourceFeature = "DelSrc-" + suffix;
        String excludedFeature = "DelExcl-" + suffix;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then().statusCode(lessThan(300));

        Number constraintId = given().when().get("/products/" + productName).then().statusCode(lessThan(300)).extract().path("constraints[0].id");

        given().when().delete("/products/" + productName + "/constraints/" + constraintId).then().statusCode(204);
    }
}