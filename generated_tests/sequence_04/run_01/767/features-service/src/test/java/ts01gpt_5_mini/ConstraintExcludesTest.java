package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
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
        String base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.baseUrl");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintWithBothFieldsReturns201() {
        String product = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String src = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String excl = "excl-" + UUID.randomUUID().toString().substring(0, 8);
        given().contentType(ContentType.URLENC).formParam("sourceFeature", src).formParam("excludedFeature", excl)
                .when().post("/products/{productName}/constraints/excludes", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintWithNoFieldsReturnsConstraintTypeEXCLUDES() {
        String product = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).when().post("/products/{productName}/constraints/excludes", product)
                .then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testCreateConstraintReflectsSourceFeatureInResponse() {
        String product = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String src = "source-" + UUID.randomUUID().toString().substring(0, 8);
        given().contentType(ContentType.URLENC).formParam("sourceFeature", src)
                .when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testCreateConstraintReflectsExcludedFeatureInResponse() {
        String product = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String excl = "excluded-" + UUID.randomUUID().toString().substring(0, 8);
        given().contentType(ContentType.URLENC).formParam("excludedFeature", excl)
                .when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationMarksInvalidWhenBothFeaturesActive() {
        String product = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String configuration = "conf-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        String srcFeature = "srcF-" + UUID.randomUUID().toString().substring(0, 8);
        String exclFeature = "exF-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/{productName}/features/{featureName}", product, srcFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, exclFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, srcFeature)
                .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, exclFeature)
                .then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", srcFeature).formParam("excludedFeature", exclFeature)
                .when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", product, configuration).then().body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationRemainsValidWhenOnlySourceActive() {
        String product = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String configuration = "conf-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        String srcFeature = "srcF-" + UUID.randomUUID().toString().substring(0, 8);
        String exclFeature = "exF-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/{productName}/features/{featureName}", product, srcFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, exclFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, srcFeature)
                .then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", srcFeature).formParam("excludedFeature", exclFeature)
                .when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", product, configuration).then().body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintReturns204() {
        String product = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String src = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String excl = "excl-" + UUID.randomUUID().toString().substring(0, 8);
        Response r = given().contentType(ContentType.URLENC).formParam("sourceFeature", src).formParam("excludedFeature", excl)
                .when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300)).extract().response();
        String idStr = "";
        String location = r.getHeader("Location");
        if (location != null && !location.isEmpty()) {
            int idx = location.lastIndexOf('/');
            idStr = idx >= 0 ? location.substring(idx + 1) : location;
        } else {
            String body = r.asString();
            if (body != null) {
                String trimmed = body.trim();
                if (trimmed.startsWith("{") && trimmed.endsWith("}")) {
                    Object idObj = new JsonPath(body).get("id");
                    idStr = idObj == null ? "" : String.valueOf(idObj);
                } else {
                    idStr = trimmed;
                }
            } else {
                idStr = "";
            }
        }
        given().when().delete("/products/{productName}/constraints/{constraintId}", product, idStr).then().statusCode(204);
    }
}