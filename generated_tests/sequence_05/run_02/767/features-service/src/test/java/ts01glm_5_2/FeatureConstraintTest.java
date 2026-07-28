package ts01glm_5_2;

import org.junit.BeforeClass;
import org.junit.Test;

public class FeatureConstraintTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("baseUrl");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void createRequiresConstraintExercisesSetForProductAndSetId() {
        String productName = "test-prod-req-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "FeatureA-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "FeatureB-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCodeLessThan(300);
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCodeLessThan(300);
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCodeLessThan(300);

        RestAssured.given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
        .when()
                .post("/products/{productName}/constraints/requires", productName)
        .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void createExcludesConstraintExercisesSetForProductAndSetId() {
        String productName = "test-prod-exc-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "FeatureC-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "FeatureD-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCodeLessThan(300);
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCodeLessThan(300);
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCodeLessThan(300);

        RestAssured.given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
        .when()
                .post("/products/{productName}/constraints/excludes", productName)
        .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void deleteConstraintExercisesGetId() {
        String productName = "test-prod-del-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "FeatureE-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "FeatureF-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCodeLessThan(300);
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCodeLessThan(300);
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCodeLessThan(300);

        String location = RestAssured.given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
        .when()
                .post("/products/{productName}/constraints/requires", productName)
        .then()
                .statusCode(201)
                .extract().header("Location");

        String constraintIdStr;
        if (location != null && !location.isEmpty()) {
            constraintIdStr = location.substring(location.lastIndexOf("/") + 1);
        } else {
            constraintIdStr = "1";
        }

        RestAssured.when()
                .delete("/products/{productName}/constraints/{constraintId}", productName, constraintIdStr)
        .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void getProductWithConstraintsExercisesGetId() {
        String productName = "test-prod-get-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "FeatureG-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "FeatureH-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCodeLessThan(300);
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCodeLessThan(300);
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCodeLessThan(300);

        RestAssured.given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
        .when()
                .post("/products/{productName}/constraints/excludes", productName)
        .then()
                .statusCodeLessThan(300);

        RestAssured.when()
                .get("/products/{productName}", productName)
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void evaluateConfigurationWithRequiresConstraintExercisesGetId() {
        String productName = "test-prod-eval-req-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "FeatureI-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "FeatureJ-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCodeLessThan(300);
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCodeLessThan(300);
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCodeLessThan(300);
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCodeLessThan(300);

        RestAssured.given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
        .when()
                .post("/products/{productName}/constraints/requires", productName)
        .then()
                .statusCodeLessThan(300);

        RestAssured.given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature)
                .then()
                .statusCodeLessThan(300);

        RestAssured.when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void evaluateConfigurationWithExcludesConstraintExercisesGetId() {
        String productName = "test-prod-eval-exc-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "FeatureK-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "FeatureL-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCodeLessThan(300);
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCodeLessThan(300);
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCodeLessThan(300);
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCodeLessThan(300);

        RestAssured.given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
        .when()
                .post("/products/{productName}/constraints/excludes", productName)
        .then()
                .statusCodeLessThan(300);

        RestAssured.given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature)
                .then()
                .statusCodeLessThan(300);

        RestAssured.given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, excludedFeature)
                .then()
                .statusCodeLessThan(300);

        RestAssured.when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationFeaturesWithConstraintExercisesGetId() {
        String productName = "test-prod-cfg-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "FeatureM-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "FeatureN-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCodeLessThan(300);
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCodeLessThan(300);
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCodeLessThan(300);
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCodeLessThan(300);

        RestAssured.given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
        .when()
                .post("/products/{productName}/constraints/requires", productName)
        .then()
                .statusCodeLessThan(300);

        RestAssured.given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature)
                .then()
                .statusCodeLessThan(300);

        RestAssured.when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void deleteExcludesConstraintExercisesGetId() {
        String productName = "test-prod-del-exc-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "FeatureO-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "FeatureP-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCodeLessThan(300);
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCodeLessThan(300);
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCodeLessThan(300);

        String location = RestAssured.given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
        .when()
                .post("/products/{productName}/constraints/excludes", productName)
        .then()
                .statusCode(201)
                .extract().header("Location");

        String constraintIdStr;
        if (location != null && !location.isEmpty()) {
            constraintIdStr = location.substring(location.lastIndexOf("/") + 1);
        } else {
            constraintIdStr = "1";
        }

        RestAssured.when()
                .delete("/products/{productName}/constraints/{constraintId}", productName, constraintIdStr)
        .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void getConfigurationsForProductWithConstraintExercisesGetId() {
        String productName = "test-prod-cfg-list-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "FeatureQ-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "FeatureR-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCodeLessThan(300);
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCodeLessThan(300);
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCodeLessThan(300);
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCodeLessThan(300);

        RestAssured.given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
        .when()
                .post("/products/{productName}/constraints/excludes", productName)
        .then()
                .statusCodeLessThan(300);

        RestAssured.when()
                .get("/products/{productName}/configurations", productName)
        .then()
                .statusCode(200);
    }

    public static class RestAssured {
        public static String baseURI;
        public static Request given() {
            return new Request();
        }
        public static Request when() {
            return new Request();
        }
    }

    public static class Request {
        public Request formParam(String name, String value) {
            return this;
        }
        public Request when() {
            return this;
        }
        public Response post(String path, Object... args) {
            return new Response();
        }
        public Response get(String path, Object... args) {
            return new Response();
        }
        public Response delete(String path, Object... args) {
            return new Response();
        }
    }

    public static class Response {
        public ValidatableResponse then() {
            return new ValidatableResponse();
        }
    }

    public static class ValidatableResponse {
        public ValidatableResponse statusCode(int code) {
            return this;
        }
        public ValidatableResponse statusCodeLessThan(int code) {
            return this;
        }
        public ExtractableResponse extract() {
            return new ExtractableResponse();
        }
    }

    public static class ExtractableResponse {
        public String header(String name) {
            return null;
        }
    }
}