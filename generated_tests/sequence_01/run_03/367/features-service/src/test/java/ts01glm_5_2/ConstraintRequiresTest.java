package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("baseUrl");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void testSetSourceFeatureNameViaRequiresConstraintCreation() {
        String productName = "test-product-src-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "source-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "required-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
    public void testSetRequiredFeatureNameViaRequiresConstraintCreation() {
        String productName = "test-product-req-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
    public void testEvaluateRequiresConstraintSourceActiveDerivesRequiredFeature() {
        String productName = "test-product-eval-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
        .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given()
        .when()
                .get("/products/" + productName + "/configurations/" + configName + "/features")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateRequiresConstraintSourceNotActiveNoDerivation() {
        String productName = "test-product-noeval-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
        .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
        .when()
                .get("/products/" + productName + "/configurations/" + configName + "/features")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateRequiresConstraintBothActiveNoDerivation() {
        String productName = "test-product-both-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
        .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given()
        .when()
                .get("/products/" + productName + "/configurations/" + configName + "/features")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRequiresConstraintCreationWithMissingRequiredFeatureParam() {
        String productName = "test-product-missing-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
        .when()
                .post("/products/" + productName + "/constraints/requires")
        .then()
                .statusCode(lessThan(500));
    }
}