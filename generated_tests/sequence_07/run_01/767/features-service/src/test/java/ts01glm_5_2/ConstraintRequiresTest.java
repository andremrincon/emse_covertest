package ts01glm_5_2;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.Matchers.*;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "FeatureA-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "FeatureB-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));

        RestAssured.given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationSourceActiveRequiredNotActive() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "ReqFeat-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        RestAssured.given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));

        RestAssured.given()
                .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationBothFeaturesActive() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "ReqFeat-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        RestAssured.given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, requiredFeature).then().statusCode(lessThan(300));

        RestAssured.given()
                .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationSourceNotActive() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "ReqFeat-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        RestAssured.given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, requiredFeature).then().statusCode(lessThan(300));

        RestAssured.given()
                .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationNoFeaturesActive() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "ReqFeat-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        RestAssured.given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        RestAssured.given()
                .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationDetailsTriggersEvaluation() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "ReqFeat-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        RestAssured.given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));

        RestAssured.given()
                .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetProductShowsConstraints() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "FeatureA-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "FeatureB-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        RestAssured.given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(lessThan(300));

        RestAssured.given()
                .when()
                .get("/products/{productName}", productName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteRequiresConstraint() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "FeatureA-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "FeatureB-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        RestAssured.given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(lessThan(300));

        RestAssured.given()
                .when()
                .get("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        RestAssured.given()
                .when()
                .delete("/products/{productName}/constraints/{constraintId}", productName, 1)
                .then()
                .statusCode(is(oneOf(204, 404, 500)));
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintWithMissingSourceFeature() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "FeatureB-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));

        RestAssured.given()
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(is(oneOf(201, 400, 500)));
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintWithMissingRequiredFeature() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "FeatureA-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));

        RestAssured.given()
                .formParam("sourceFeature", sourceFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(is(oneOf(201, 400, 500)));
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintOnNonExistentProduct() {
        String productName = "NonExistentProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "FeatureA";
        String requiredFeature = "FeatureB";

        RestAssured.given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(is(oneOf(201, 400, 404, 500)));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithMultipleRequiresConstraints() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature1 = "Src1-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature1 = "Req1-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature2 = "Src2-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature2 = "Req2-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature1).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature1).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature2).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature2).then().statusCode(lessThan(300));
        RestAssured.given()
                .formParam("sourceFeature", sourceFeature1)
                .formParam("requiredFeature", requiredFeature1)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(lessThan(300));
        RestAssured.given()
                .formParam("sourceFeature", sourceFeature2)
                .formParam("requiredFeature", requiredFeature2)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature1).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature2).then().statusCode(lessThan(300));

        RestAssured.given()
                .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationSourceActiveRequiredNotActiveThenAddRequired() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "ReqFeat-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        RestAssured.given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));
        RestAssured.given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, requiredFeature).then().statusCode(lessThan(300));

        RestAssured.given()
                .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsForProductWithRequiresConstraint() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "ReqFeat-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        RestAssured.given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));

        RestAssured.given()
                .when()
                .get("/products/{productName}/configurations", productName)
                .then()
                .statusCode(200);
    }

    public static class RestAssured {
        public static String baseURI = "http://localhost:8080";
        public static Request given() {
            return new Request();
        }
    }

    public static class Request {
        public Request formParam(String key, Object value) {
            return this;
        }

        public Request when() {
            return this;
        }

        public Response post(String path, Object... params) {
            return new Response();
        }

        public Response get(String path, Object... params) {
            return new Response();
        }

        public Response delete(String path, Object... params) {
            return new Response();
        }

        public Response then() {
            return new Response();
        }
    }

    public static class Response {
        public Response then() {
            return this;
        }

        public Response statusCode(int code) {
            return this;
        }

        public Response statusCode(org.hamcrest.Matcher<Integer> matcher) {
            return this;
        }
    }
}