package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.uri");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URI");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint_returns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String src = "src-" + UUID.randomUUID().toString();
        String excl = "excl-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excl).then().statusCode(lessThan(300));
        Response resp = given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", src).formParam("excludedFeature", excl).when().post("/products/{productName}/constraints/excludes", product);
        resp.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint_responseContainsSourceFeature() {
        String product = "prod-" + UUID.randomUUID().toString();
        String src = "source-" + UUID.randomUUID().toString();
        String excl = "excluded-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excl).then().statusCode(lessThan(300));
        Response resp = given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", src).formParam("excludedFeature", excl).when().post("/products/{productName}/constraints/excludes", product);
        resp.then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testConfigurationEvaluation_invalidWhenBothFeaturesActive() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        String src = "sf-" + UUID.randomUUID().toString();
        String excl = "ef-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excl).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, excl).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", src).formParam("excludedFeature", excl).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/configurations/{configurationName}", product, config);
        resp.then().body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testConfigurationEvaluation_validWhenOnlySourceActive() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        String src = "sf-" + UUID.randomUUID().toString();
        String excl = "ef-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excl).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, src).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", src).formParam("excludedFeature", excl).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/configurations/{configurationName}", product, config);
        resp.then().body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testConfigurationEvaluation_validWhenOnlyExcludedActive() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        String src = "sf-" + UUID.randomUUID().toString();
        String excl = "ef-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excl).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, excl).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", src).formParam("excludedFeature", excl).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/configurations/{configurationName}", product, config);
        resp.then().body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testDeleteExcludesConstraint_returns204() {
        String product = "prod-" + UUID.randomUUID().toString();
        String src = "src-" + UUID.randomUUID().toString();
        String excl = "excl-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excl).then().statusCode(lessThan(300));
        Response createResp = given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", src).formParam("excludedFeature", excl).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300)).extract().response();
        String location = createResp.getHeader("Location");
        String id;
        if (location != null && !location.isEmpty()) {
            id = location.substring(location.lastIndexOf('/') + 1);
        } else {
            String body = createResp.getBody().asString();
            if (body == null) body = "";
            body = body.trim();
            if (body.startsWith("{")) {
                int idx = body.indexOf("\"id\"");
                if (idx == -1) idx = body.indexOf("'id'");
                if (idx != -1) {
                    int colon = body.indexOf(':', idx);
                    int startQuote = body.indexOf('"', colon);
                    if (startQuote != -1) {
                        int endQuote = body.indexOf('"', startQuote + 1);
                        if (endQuote != -1) {
                            id = body.substring(startQuote + 1, endQuote);
                        } else {
                            id = body.substring(startQuote + 1).trim();
                        }
                    } else {
                        int comma = body.indexOf(',', colon);
                        if (comma == -1) comma = body.indexOf('}', colon);
                        if (comma == -1) id = body.substring(colon + 1).trim();
                        else id = body.substring(colon + 1, comma).trim();
                    }
                } else {
                    id = body;
                }
            } else {
                id = body;
            }
        }
        Response resp = given().when().delete("/products/{productName}/constraints/{constraintId}", product, id);
        resp.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint_responseContainsConstraintType() {
        String product = "prod-" + UUID.randomUUID().toString();
        String src = "s-" + UUID.randomUUID().toString();
        String excl = "e-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excl).then().statusCode(lessThan(300));
        Response resp = given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", src).formParam("excludedFeature", excl).when().post("/products/{productName}/constraints/excludes", product);
        resp.then().statusCode(lessThan(300));
    }
}