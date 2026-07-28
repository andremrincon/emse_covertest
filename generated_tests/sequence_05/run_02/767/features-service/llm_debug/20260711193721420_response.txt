package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    @BeforeClass
    public static void setUp() throws MalformedURLException {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        URL url = new URL(base);
        String baseUri = url.getProtocol() + "://" + url.getHost();
        RestAssured.baseURI = baseUri;
        int port = url.getPort();
        if (port == -1) {
            if ("http".equalsIgnoreCase(url.getProtocol())) {
                RestAssured.port = 80;
            } else if ("https".equalsIgnoreCase(url.getProtocol())) {
                RestAssured.port = 443;
            }
        } else {
            RestAssured.port = port;
        }
    }

    @Test(timeout = 60000)
    public void test_conflict_makes_configuration_invalid() {
        String product = "prod-" + UUID.randomUUID();
        String config = "cfg-" + UUID.randomUUID();
        String srcFeature = "src-" + UUID.randomUUID();
        String exclFeature = "excl-" + UUID.randomUUID();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, srcFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, exclFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, srcFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, exclFeature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", srcFeature).formParam("excludedFeature", exclFeature).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", product, config).then().body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void test_no_conflict_with_only_source_active_keeps_configuration_valid() {
        String product = "prod-" + UUID.randomUUID();
        String config = "cfg-" + UUID.randomUUID();
        String srcFeature = "src-" + UUID.randomUUID();
        String exclFeature = "excl-" + UUID.randomUUID();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, srcFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, exclFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, srcFeature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", srcFeature).formParam("excludedFeature", exclFeature).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", product, config).then().body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void test_no_conflict_with_only_excluded_active_keeps_configuration_valid() {
        String product = "prod-" + UUID.randomUUID();
        String config = "cfg-" + UUID.randomUUID();
        String srcFeature = "src-" + UUID.randomUUID();
        String exclFeature = "excl-" + UUID.randomUUID();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, srcFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, exclFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, exclFeature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", srcFeature).formParam("excludedFeature", exclFeature).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", product, config).then().body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void test_create_excludes_constraint_returns_201() {
        String product = "prod-" + UUID.randomUUID();
        String srcFeature = "src-" + UUID.randomUUID();
        String exclFeature = "excl-" + UUID.randomUUID();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, srcFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, exclFeature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", srcFeature).formParam("excludedFeature", exclFeature).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void test_create_excludes_constraint_response_contains_feature_names() {
        String product = "prod-" + UUID.randomUUID();
        String srcFeature = "src-" + UUID.randomUUID();
        String exclFeature = "excl-" + UUID.randomUUID();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, srcFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, exclFeature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", srcFeature).formParam("excludedFeature", exclFeature).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void test_delete_excludes_constraint_returns_204() {
        String product = "prod-" + UUID.randomUUID();
        String srcFeature = "src-" + UUID.randomUUID();
        String exclFeature = "excl-" + UUID.randomUUID();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response createResp = given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", srcFeature).formParam("excludedFeature", exclFeature).when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300)).extract().response();
        String id = "";
        String location = createResp.getHeader("Location");
        if (location != null && !location.isEmpty()) {
            int idx = location.lastIndexOf('/');
            if (idx != -1 && idx < location.length() - 1) {
                id = location.substring(idx + 1);
            } else {
                id = location;
            }
        } else {
            String body = createResp.asString();
            if (body != null && !body.trim().isEmpty()) {
                java.util.regex.Matcher m = java.util.regex.Pattern.compile("\"id\"\\s*:\\s*\"([^\"]+)\"").matcher(body);
                if (m.find()) {
                    id = m.group(1);
                } else {
                    java.util.regex.Matcher m2 = java.util.regex.Pattern.compile("\"id\"\\s*:\\s*(\\d+)").matcher(body);
                    if (m2.find()) {
                        id = m2.group(1);
                    }
                }
            }
        }
        given().when().delete("/products/{productName}/constraints/{constraintId}", product, id).then().statusCode(204);
    }
}