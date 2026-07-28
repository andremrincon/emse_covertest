package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintCoversSetters() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "Src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "Req-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .when()
                .post("/products/{productName}/constraints/requires", productName)
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigSourceActiveRequiredNotActive() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Cfg-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "Src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "Req-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature)
            .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));

        given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigSourceNotActive() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Cfg-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "Src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "Req-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature)
            .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigBothActive() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Cfg-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "Src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "Req-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature)
            .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, requiredFeature).then().statusCode(500);

        given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetActiveFeaturesSourceActiveRequiredNotActive() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Cfg-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "Src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "Req-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature)
            .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));

        given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
            .then()
                .statusCode(200);
    }
}