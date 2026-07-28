package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URLEncoder;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;

public class ConfigurationEvaluatorTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("API_BASE_URL", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    private static String enc(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8").replace("+", "%20");
        } catch (Exception e) {
            return s;
        }
    }

    @Test(timeout = 60000)
    public void test_AddFeatureNoConstraints_ConfigurationValid() {
        String id = UUID.randomUUID().toString().replace("-", "");
        String product = "prod-" + id;
        String feature = "feat-" + id;
        String config = "cfg-" + id;
        given().when().post("/products/" + enc(product)).then().statusCode(lessThan(300));
        given().when().post("/products/" + enc(product) + "/features/" + enc(feature)).then().statusCode(lessThan(300));
        given().when().post("/products/" + enc(product) + "/configurations/" + enc(config)).then().statusCode(lessThan(300));
        given().when().post("/products/" + enc(product) + "/configurations/" + enc(config) + "/features/" + enc(feature)).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/" + enc(product) + "/configurations/" + enc(config));
        act.then().assertThat().body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void test_CascadingRequiresConstraint_AddingRootFeatureReturnsCreated() {
        String id = UUID.randomUUID().toString().replace("-", "");
        String product = "prod-" + id;
        String a = "A-" + id;
        String b = "B-" + id;
        String c = "C-" + id;
        String config = "cfg-" + id;
        given().when().post("/products/" + enc(product)).then().statusCode(lessThan(300));
        given().when().post("/products/" + enc(product) + "/features/" + enc(a)).then().statusCode(lessThan(300));
        given().when().post("/products/" + enc(product) + "/features/" + enc(b)).then().statusCode(lessThan(300));
        given().when().post("/products/" + enc(product) + "/features/" + enc(c)).then().statusCode(lessThan(300));
        given().when().post("/products/" + enc(product) + "/configurations/" + enc(config)).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", a).formParam("requiredFeature", b).when().post("/products/" + enc(product) + "/constraints/requires").then().statusCode(lessThan(300));
        given().formParam("sourceFeature", b).formParam("requiredFeature", c).when().post("/products/" + enc(product) + "/constraints/requires").then().statusCode(lessThan(300));
        Response act = given().when().post("/products/" + enc(product) + "/configurations/" + enc(config) + "/features/" + enc(a));
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void test_RequiresConstraint_RequiredFeatureBecomesActive() {
        String id = UUID.randomUUID().toString().replace("-", "");
        String product = "prod-" + id;
        String a = "A-" + id;
        String b = "B-" + id;
        String c = "C-" + id;
        String config = "cfg-" + id;
        given().when().post("/products/" + enc(product)).then().statusCode(lessThan(300));
        given().when().post("/products/" + enc(product) + "/features/" + enc(a)).then().statusCode(lessThan(300));
        given().when().post("/products/" + enc(product) + "/features/" + enc(b)).then().statusCode(lessThan(300));
        given().when().post("/products/" + enc(product) + "/features/" + enc(c)).then().statusCode(lessThan(300));
        given().when().post("/products/" + enc(product) + "/configurations/" + enc(config)).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", a).formParam("requiredFeature", b).when().post("/products/" + enc(product) + "/constraints/requires").then().statusCode(lessThan(300));
        given().formParam("sourceFeature", b).formParam("requiredFeature", c).when().post("/products/" + enc(product) + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + enc(product) + "/configurations/" + enc(config) + "/features/" + enc(a)).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/" + enc(product) + "/configurations/" + enc(config));
        act.then().assertThat().body("activedFeatures.name", hasItem(b));
    }

    @Test(timeout = 60000)
    public void test_ExcludesConstraint_MakesConfigurationInvalid() {
        String id = UUID.randomUUID().toString().replace("-", "");
        String product = "prod-" + id;
        String x = "X-" + id;
        String y = "Y-" + id;
        String config = "cfg-" + id;
        given().when().post("/products/" + enc(product)).then().statusCode(lessThan(300));
        given().when().post("/products/" + enc(product) + "/features/" + enc(x)).then().statusCode(lessThan(300));
        given().when().post("/products/" + enc(product) + "/features/" + enc(y)).then().statusCode(lessThan(300));
        given().when().post("/products/" + enc(product) + "/configurations/" + enc(config)).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", x).formParam("excludedFeature", y).when().post("/products/" + enc(product) + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + enc(product) + "/configurations/" + enc(config) + "/features/" + enc(x)).then().statusCode(lessThan(300));
        given().when().post("/products/" + enc(product) + "/configurations/" + enc(config) + "/features/" + enc(y)).then().statusCode(500);
        Response act = given().when().get("/products/" + enc(product) + "/configurations/" + enc(config));
        act.then().assertThat().body("valid", equalTo(false));
    }
}