package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", System.getenv().getOrDefault("baseUrl", "http://localhost:8080"));
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintCallsSetters() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString();
        String sourceFeature = "src-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + requiredFeature)
            .then()
                .statusCode(lessThan(300));

        given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
            .when()
                .post("/products/" + productName + "/constraints/requires")
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationSourceActiveRequiredNotActive() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString();
        String sourceFeature = "src-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
            .when()
                .post("/products/" + productName + "/constraints/requires")
            .then()
                .statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given()
            .when()
                .get("/products/" + productName + "/configurations/" + configName)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationSourceNotActive() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString();
        String sourceFeature = "src-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
            .when()
                .post("/products/" + productName + "/constraints/requires")
            .then()
                .statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
            .when()
                .get("/products/" + productName + "/configurations/" + configName)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationBothActive() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString();
        String sourceFeature = "src-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
            .when()
                .post("/products/" + productName + "/constraints/requires")
            .then()
                .statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + requiredFeature).then().statusCode(500);

        given()
            .when()
                .get("/products/" + productName + "/configurations/" + configName)
            .then()
                .statusCode(200);
    }
}