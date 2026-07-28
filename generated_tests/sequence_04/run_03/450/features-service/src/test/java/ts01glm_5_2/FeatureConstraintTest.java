package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class FeatureConstraintTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintTriggersSetId() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestReq-" + uuid;
        String sourceFeature = "SrcF-" + uuid;
        String requiredFeature = "ReqF-" + uuid;

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
    public void testCreateExcludesConstraintTriggersSetId() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestExc-" + uuid;
        String sourceFeature = "SrcF-" + uuid;
        String excludedFeature = "ExcF-" + uuid;

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
    public void testGetProductWithConstraintsTriggersSetId() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestGet-" + uuid;
        String sourceFeature = "SrcF-" + uuid;
        String requiredFeature = "ReqF-" + uuid;

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then()
            .statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}", productName)
        .then()
            .statusCode(200);
    }
}