package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        try {
            URL u = new URL(base);
            RestAssured.baseURI = u.getProtocol() + "://" + u.getHost();
            int p = u.getPort();
            if (p == -1) p = u.getDefaultPort();
            RestAssured.port = p;
        } catch (MalformedURLException e) {
            RestAssured.baseURI = base;
        }
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_returnsConstraintTypeEXCLUDES() {
        String product = "prod-" + UUID.randomUUID();
        String src = "SRC-" + UUID.randomUUID();
        String excl = "EXCL-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excl).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", src).formParam("excludedFeature", excl).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_persistsSourceFeatureName() {
        String product = "prod-" + UUID.randomUUID();
        String src = "SRC-" + UUID.randomUUID();
        String excl = "EXCL-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excl).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", src).formParam("excludedFeature", excl).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_persistsExcludedFeatureName() {
        String product = "prod-" + UUID.randomUUID();
        String src = "SRC-" + UUID.randomUUID();
        String excl = "EXCL-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excl).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", src).formParam("excludedFeature", excl).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfiguration_valid_when_only_source_active() {
        String product = "prod-" + UUID.randomUUID();
        String config = "cfg-" + UUID.randomUUID();
        String src = "SRC-" + UUID.randomUUID();
        String excl = "EXCL-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excl).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, src).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", src).formParam("excludedFeature", excl).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", product, config).then().body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfiguration_invalid_when_both_features_active() {
        String product = "prod-" + UUID.randomUUID();
        String config = "cfg-" + UUID.randomUUID();
        String src = "SRC-" + UUID.randomUUID();
        String excl = "EXCL-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excl).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, excl).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", src).formParam("excludedFeature", excl).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", product, config).then().body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testDeleteConstraint_returns204() {
        String product = "prod-" + UUID.randomUUID();
        String src = "SRC-" + UUID.randomUUID();
        String excl = "EXCL-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excl).then().statusCode(lessThan(300));
        Response resp = given().formParam("sourceFeature", src).formParam("excludedFeature", excl).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300)).extract().response();
        String id = null;
        String loc = resp.getHeader("Location");
        if (loc != null && !loc.isEmpty()) {
            id = loc.substring(loc.lastIndexOf('/') + 1);
        } else {
            String body = resp.asString();
            if (body != null && !body.isEmpty()) {
                try {
                    id = new JsonPath(body).getString("id");
                } catch (Exception e) {
                    id = null;
                }
            }
            if (id == null || id.isEmpty()) {
                Response listResp = given().when().get("/products/{productName}/constraints", product).then().statusCode(lessThan(300)).extract().response();
                String listBody = listResp.asString();
                if (listBody != null && !listBody.isEmpty()) {
                    List<Map<String, Object>> items = new JsonPath(listBody).getList("$");
                    if (items != null) {
                        for (Map<String, Object> m : items) {
                            Object s = m.get("sourceFeature");
                            Object e = m.get("excludedFeature");
                            Object iid = m.get("id");
                            if (s != null && e != null && iid != null && s.toString().equals(src) && e.toString().equals(excl)) {
                                id = iid.toString();
                                break;
                            }
                        }
                    }
                }
            }
        }
        given().when().delete("/products/{productName}/constraints/{constraintId}", product, id).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_returns201() {
        String product = "prod-" + UUID.randomUUID();
        String config = "cfg-" + UUID.randomUUID();
        String feat = "FEAT-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feat).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feat).then().statusCode(201);
    }
}