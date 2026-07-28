package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv().getOrDefault("BASE_URL", "http://localhost");
        RestAssured.port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "Prod-" + uuid;
        String sourceFeature = "Src-" + uuid;
        String excludedFeature = "Exc-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRetrieveProductWithExcludesConstraint() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "Prod-" + uuid;
        String sourceFeature = "Src-" + uuid;
        String excludedFeature = "Exc-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/" + productName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithBothExcludedFeaturesActive() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "Prod-" + uuid;
        String configName = "Cfg-" + uuid;
        String sourceFeature = "Src-" + uuid;
        String excludedFeature = "Exc-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + excludedFeature).then().statusCode(500);

        given()
            .when()
            .get("/products/" + productName + "/configurations/" + configName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesWithExcludesConstraint() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "Prod-" + uuid;
        String configName = "Cfg-" + uuid;
        String sourceFeature = "Src-" + uuid;
        String excludedFeature = "Exc-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/" + productName + "/configurations/" + configName + "/features")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsListWithExcludesConstraint() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "Prod-" + uuid;
        String configName = "Cfg-" + uuid;
        String sourceFeature = "Src-" + uuid;
        String excludedFeature = "Exc-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/" + productName + "/configurations")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithOnlySourceFeatureActive() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "Prod-" + uuid;
        String configName = "Cfg-" + uuid;
        String sourceFeature = "Src-" + uuid;
        String excludedFeature = "Exc-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/" + productName + "/configurations/" + configName)
        .then()
            .statusCode(200);
    }
}