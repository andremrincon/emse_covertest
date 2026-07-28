package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URL;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    @BeforeClass
    public static void setup() {
        String configured = System.getProperty("API_BASE_URL", System.getenv("API_BASE_URL"));
        if (configured == null) configured = "http://localhost:8080";
        try {
            URL u = new URL(configured);
            RestAssured.baseURI = u.getProtocol() + "://" + u.getHost();
            RestAssured.port = u.getPort() == -1 ? u.getDefaultPort() : u.getPort();
        } catch (Exception e) {
            RestAssured.baseURI = configured;
        }
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintReturns201_whenBothNamesProvided() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, "SourceFeature-" + UUID.randomUUID()).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, "ExcludedFeature-" + UUID.randomUUID()).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", "SourceA").formParam("excludedFeature", "ExcludedA").when().post("/products/{productName}/constraints/excludes", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintReturns201_whenExcludedMissing() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", "OnlySource").when().post("/products/{productName}/constraints/excludes", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testConfigurationInvalidWhenBothFeaturesActive() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        String sourceFeat = "src-" + UUID.randomUUID().toString();
        String exclFeat = "excl-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, sourceFeat).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, exclFeat).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", sourceFeat).formParam("excludedFeature", exclFeat).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, sourceFeat).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, exclFeat).then().statusCode(500);
        given().when().get("/products/{productName}/configurations/{configurationName}", product, config).then().body("valid", equalTo(false));
    }

    @Test(timeout = 60000)
    public void testConfigurationValidWhenOnlySourceActive() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        String sourceFeat = "src-" + UUID.randomUUID().toString();
        String exclFeat = "excl-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, sourceFeat).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, exclFeat).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", sourceFeat).formParam("excludedFeature", exclFeat).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, sourceFeat).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", product, config).then().body("valid", equalTo(true));
    }
}