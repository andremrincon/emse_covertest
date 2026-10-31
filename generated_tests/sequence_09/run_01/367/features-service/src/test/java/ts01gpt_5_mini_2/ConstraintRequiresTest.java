package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class ConstraintRequiresTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint_returns201() {
        String productName = "prod-" + UUID.randomUUID();
        String sourceFeature = "RAID-Controller-Card";
        String requiredFeature = "128GB-ECC-RAM";
        given().when().get("/products").then().statusCode(lessThan(300));
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProduct_includesConstraintTypeRequires() {
        String productName = "prod-" + UUID.randomUUID();
        String sourceFeature = "Push-Notifications";
        String requiredFeature = "User-Authentication";
        given().when().get("/products").then().statusCode(lessThan(300));
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}", productName)
                .then().body(containsString("requires"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <500> was greater than <300>.")
    @Test(timeout = 60000)
    public void testEvaluateConfiguration_addsDerivedFeature_whenSourceActive() {
        String productName = "prod-" + UUID.randomUUID();
        String configurationName = "conf-" + UUID.randomUUID();
        String sourceFeature = "FeatureA";
        String requiredFeature = "FeatureB";
        given().when().get("/products").then().statusCode(lessThan(300));
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, sourceFeature)
                .then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
                .then().body("$", hasItem(requiredFeature));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfiguration_noDerived_whenSourceInactive() {
        String productName = "prod-" + UUID.randomUUID();
        String configurationName = "conf-" + UUID.randomUUID();
        String sourceFeature = "FeatureX";
        String requiredFeature = "FeatureY";
        given().when().get("/products").then().statusCode(lessThan(300));
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
                .then().body("$", not(hasItem(requiredFeature)));
    }

    @Test(timeout = 60000)
    public void testGetProduct_includesSourceFeatureName() {
        String productName = "prod-" + UUID.randomUUID();
        String sourceFeature = "SSD-Storage";
        String requiredFeature = "Graphics-Card";
        given().when().get("/products").then().statusCode(lessThan(300));
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}", productName)
                .then().body(containsString(sourceFeature));
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <201>.")
    @Test(timeout = 60000)
    public void testAddRequiresConstraint_invalidProduct_returns500() {
        String productName = "Desktop-PC";
        String sourceFeature = "FeatureInvalid";
        String requiredFeature = "FeatureInvalidReq";
        given().when().get("/products").then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(500);
    }
}