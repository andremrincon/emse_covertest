package ts01glm_5_2;

import org.junit.Test;
import org.junit.Before;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintCoversSetters() {
        String uuid = java.util.UUID.randomUUID().toString();
        String productName = "TestProduct-" + uuid;
        String sourceFeature = "SourceFeature-" + uuid;
        String excludedFeature = "ExcludedFeature-" + uuid;

        given().baseUri(baseUrl).when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));

        given().baseUri(baseUrl)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when()
            .post("/products/" + productName + "/constraints/excludes")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigBothFeaturesActiveCoversIfTrueBranch() {
        String uuid = java.util.UUID.randomUUID().toString();
        String productName = "TestProduct-" + uuid;
        String sourceFeature = "SourceFeature-" + uuid;
        String excludedFeature = "ExcludedFeature-" + uuid;
        String configName = "TestConfig-" + uuid;

        given().baseUri(baseUrl).when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given().baseUri(baseUrl)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when()
            .post("/products/" + productName + "/constraints/excludes")
            .then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName + "/features/" + excludedFeature).then().statusCode(500);

        given().baseUri(baseUrl).when().get("/products/" + productName + "/configurations/" + configName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigOnlySourceFeatureActiveCoversIfFalseBranch() {
        String uuid = java.util.UUID.randomUUID().toString();
        String productName = "TestProduct-" + uuid;
        String sourceFeature = "SourceFeature-" + uuid;
        String excludedFeature = "ExcludedFeature-" + uuid;
        String configName = "TestConfig-" + uuid;

        given().baseUri(baseUrl).when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given().baseUri(baseUrl)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when()
            .post("/products/" + productName + "/constraints/excludes")
            .then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given().baseUri(baseUrl).when().get("/products/" + productName + "/configurations/" + configName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigOnlyExcludedFeatureActiveCoversShortCircuitFalse() {
        String uuid = java.util.UUID.randomUUID().toString();
        String productName = "TestProduct-" + uuid;
        String sourceFeature = "SourceFeature-" + uuid;
        String excludedFeature = "ExcludedFeature-" + uuid;
        String configName = "TestConfig-" + uuid;

        given().baseUri(baseUrl).when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given().baseUri(baseUrl)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when()
            .post("/products/" + productName + "/constraints/excludes")
            .then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName + "/features/" + excludedFeature).then().statusCode(lessThan(300));

        given().baseUri(baseUrl).when().get("/products/" + productName + "/configurations/" + configName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigNeitherFeatureActiveCoversIfFalseBranch() {
        String uuid = java.util.UUID.randomUUID().toString();
        String productName = "TestProduct-" + uuid;
        String sourceFeature = "SourceFeature-" + uuid;
        String excludedFeature = "ExcludedFeature-" + uuid;
        String configName = "TestConfig-" + uuid;

        given().baseUri(baseUrl).when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given().baseUri(baseUrl)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when()
            .post("/products/" + productName + "/constraints/excludes")
            .then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().baseUri(baseUrl).when().get("/products/" + productName + "/configurations/" + configName).then().statusCode(200);
    }
}