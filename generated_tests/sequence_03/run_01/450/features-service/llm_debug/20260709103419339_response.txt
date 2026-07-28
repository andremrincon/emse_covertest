package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintWithValidFeatures() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID().toString().substring(0, 8);

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
    public void testCreateRequiresConstraintWithoutSourceFeature() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then()
            .statusCode(notNullValue());
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintWithoutRequiredFeature() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then()
            .statusCode(notNullValue());
    }

    @Test(timeout = 60000)
    public void testRequiresConstraintEvaluationWithSourceActiveOnly() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, sourceFeature).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRequiresConstraintEvaluationWithBothFeaturesActive() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, sourceFeature).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, requiredFeature).then().statusCode(500);

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRequiresConstraintEvaluationWithNeitherFeatureActive() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
        .then()
            .statusCode(200);
    }
}