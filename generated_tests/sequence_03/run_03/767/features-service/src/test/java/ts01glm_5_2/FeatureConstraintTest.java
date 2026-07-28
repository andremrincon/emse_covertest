package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import org.junit.BeforeClass;
import org.junit.Test;

public class FeatureConstraintTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSetIdViaProductRetrievalWithRequiresConstraint() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProd-" + uuid;
        String sourceFeature = "SrcFeat-" + uuid;
        String requiredFeature = "ReqFeat-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then().statusCode(lessThan(300));

        given().when().get("/products/" + productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSetIdViaProductRetrievalWithExcludesConstraint() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProd-" + uuid;
        String sourceFeature = "SrcFeat-" + uuid;
        String excludedFeature = "ExcFeat-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then().statusCode(lessThan(300));

        given().when().get("/products/" + productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSetIdViaConstraintDeletion() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProd-" + uuid;
        String sourceFeature = "SrcFeat-" + uuid;
        String excludedFeature = "ExcFeat-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then().statusCode(lessThan(300));

        String constraintId = given().when().get("/products/" + productName)
            .then().statusCode(lessThan(300))
            .extract().path("constraints[0].id").toString();

        given().when().delete("/products/" + productName + "/constraints/" + constraintId).then().statusCode(204);
    }
}