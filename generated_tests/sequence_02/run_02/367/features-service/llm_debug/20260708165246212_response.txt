package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;

public class ConstraintRequiresTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String sourceFeature = "Source-" + UUID.randomUUID().toString();
        String requiredFeature = "Required-" + UUID.randomUUID().toString();

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
    public void testEvaluateConfigurationSourceActiveRequiredInactive() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String sourceFeature = "Source-" + UUID.randomUUID().toString();
        String requiredFeature = "Required-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName + "/configurations/" + configName + "/features")
        .then()
            .statusCode(200)
            .body("$", hasItem(requiredFeature));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationSourceActiveRequiredActive() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String sourceFeature = "Source-" + UUID.randomUUID().toString();
        String requiredFeature = "Required-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + requiredFeature).then().statusCode(500);

        given()
        .when()
            .get("/products/" + productName + "/configurations/" + configName + "/features")
        .then()
            .statusCode(200)
            .body("$", hasItem(requiredFeature));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationSourceInactive() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String sourceFeature = "Source-" + UUID.randomUUID().toString();
        String requiredFeature = "Required-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName + "/configurations/" + configName + "/features")
        .then()
            .statusCode(200)
            .body("$", not(hasItem(requiredFeature)));
    }
}