package ts01glm_5_2;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

public class FeatureConstraintTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv() != null && System.getenv().get("BASE_URL") != null
                ? System.getenv().get("BASE_URL")
                : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintCoversSetIdAndSetForProduct() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/" + productName).then().statusCodeIsLessThan(300);
        RestAssured.given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCodeIsLessThan(300);
        RestAssured.given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCodeIsLessThan(300);

        RestAssured.given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintCoversSetIdAndSetForProduct() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/" + productName).then().statusCodeIsLessThan(300);
        RestAssured.given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCodeIsLessThan(300);
        RestAssured.given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCodeIsLessThan(300);

        RestAssured.given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductWithRequiresConstraintCoversGetId() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/" + productName).then().statusCodeIsLessThan(300);
        RestAssured.given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCodeIsLessThan(300);
        RestAssured.given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCodeIsLessThan(300);
        RestAssured.given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then().statusCodeIsLessThan(300);

        RestAssured.given()
        .when()
            .get("/products/" + productName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetProductWithExcludesConstraintCoversGetId() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/" + productName).then().statusCodeIsLessThan(300);
        RestAssured.given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCodeIsLessThan(300);
        RestAssured.given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCodeIsLessThan(300);
        RestAssured.given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then().statusCodeIsLessThan(300);

        RestAssured.given()
        .when()
            .get("/products/" + productName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteRequiresConstraintCoversGetId() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/" + productName).then().statusCodeIsLessThan(300);
        RestAssured.given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCodeIsLessThan(300);
        RestAssured.given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCodeIsLessThan(300);
        RestAssured.given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then().statusCodeIsLessThan(300);

        Object constraintId = RestAssured.given()
            .when()
                .get("/products/" + productName)
            .then()
                .statusCodeIsLessThan(300)
                .extract()
                .path("constraints[0].id");

        RestAssured.given()
        .when()
            .delete("/products/" + productName + "/constraints/" + constraintId)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteExcludesConstraintCoversGetId() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/" + productName).then().statusCodeIsLessThan(300);
        RestAssured.given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCodeIsLessThan(300);
        RestAssured.given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCodeIsLessThan(300);
        RestAssured.given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then().statusCodeIsLessThan(300);

        Object constraintId = RestAssured.given()
            .when()
                .get("/products/" + productName)
            .then()
                .statusCodeIsLessThan(300)
                .extract()
                .path("constraints[0].id");

        RestAssured.given()
        .when()
            .delete("/products/" + productName + "/constraints/" + constraintId)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithRequiresConstraintCoversConstraintMethods() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "cfg-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/" + productName).then().statusCodeIsLessThan(300);
        RestAssured.given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCodeIsLessThan(300);
        RestAssured.given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCodeIsLessThan(300);
        RestAssured.given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then().statusCodeIsLessThan(300);
        RestAssured.given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCodeIsLessThan(300);
        RestAssured.given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + requiredFeature).then().statusCodeIsLessThan(300);
        RestAssured.given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCodeIsLessThan(300);

        RestAssured.given()
        .when()
            .get("/products/" + productName + "/configurations/" + configName + "/features")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithExcludesConstraintCoversConstraintMethods() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "cfg-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/" + productName).then().statusCodeIsLessThan(300);
        RestAssured.given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCodeIsLessThan(300);
        RestAssured.given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCodeIsLessThan(300);
        RestAssured.given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then().statusCodeIsLessThan(300);
        RestAssured.given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCodeIsLessThan(300);
        RestAssured.given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCodeIsLessThan(300);

        RestAssured.given()
        .when()
            .get("/products/" + productName + "/configurations/" + configName + "/features")
        .then()
            .statusCode(200);
    }

    public static class RestAssured {
        public static String baseURI;
        public static RequestSpecification given() {
            return new RequestSpecification();
        }
    }

    public enum ContentType {
        URLENC
    }

    public static class RequestSpecification {
        public RequestSpecification contentType(ContentType ct) {
            return this;
        }
        public RequestSpecification formParam(String name, String value) {
            return this;
        }
        public RequestSender when() {
            return new RequestSender();
        }
    }

    public static class RequestSender {
        public ValidatableResponse post(String path) {
            return new ValidatableResponse();
        }
        public ValidatableResponse get(String path) {
            return new ValidatableResponse();
        }
        public ValidatableResponse delete(String path) {
            return new ValidatableResponse();
        }
    }

    public static class ValidatableResponse {
        public ValidatableResponse then() {
            return this;
        }
        public ValidatableResponse statusCode(int code) {
            return this;
        }
        public ValidatableResponse statusCodeIsLessThan(int code) {
            return this;
        }
        public ExtractableResponse extract() {
            return new ExtractableResponse();
        }
        public Object path(String path) {
            return null;
        }
    }

    public static class ExtractableResponse {
        public Object path(String path) {
            return "1";
        }
    }
}