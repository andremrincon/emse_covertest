package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConstraintsResourceTest {

    @BeforeClass
    public static void setup() {
        String cfg = System.getProperty("API_BASE_URL");
        if (cfg == null || cfg.isEmpty()) cfg = System.getenv("API_BASE_URL");
        if (cfg == null || cfg.isEmpty()) cfg = "http://localhost:8080";
        RestAssured.baseURI = cfg;
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintCreatedStatus() {
        String productName = "prod-requires-" + UUID.randomUUID().toString();
        String src = "src-" + UUID.randomUUID().toString();
        String req = "req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", src)
                .formParam("requiredFeature", req)
                .when().post("/products/{productName}/constraints/requires", productName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintCreatedStatus() {
        String productName = "prod-excludes-" + UUID.randomUUID().toString();
        String src = "src-" + UUID.randomUUID().toString();
        String excl = "excl-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", src)
                .formParam("excludedFeature", excl)
                .when().post("/products/{productName}/constraints/excludes", productName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRequiresConstraintLocationHeaderContainsConstraints() {
        String productName = "prod-req-loc-" + UUID.randomUUID().toString();
        String src = "src-" + UUID.randomUUID().toString();
        String req = "req-" + UUID.randomUUID().toString();
        String featureA = "featureA-" + UUID.randomUUID().toString();
        String featureB = "featureB-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureB).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", src)
                .formParam("requiredFeature", req)
                .when().post("/products/{productName}/constraints/requires", productName);
        act.then().header("Location", containsString("/products/" + productName + "/constraints/"));
    }

    @Test(timeout = 60000)
    public void testExcludesConstraintLocationHeaderContainsConstraintSingular() {
        String productName = "prod-exc-loc-" + UUID.randomUUID().toString();
        String src = "src-" + UUID.randomUUID().toString();
        String excl = "excl-" + UUID.randomUUID().toString();
        String featureA = "featureA-" + UUID.randomUUID().toString();
        String featureB = "featureB-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureB).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", src)
                .formParam("excludedFeature", excl)
                .when().post("/products/{productName}/constraints/excludes", productName);
        act.then().header("Location", containsString("/products/" + productName + "/constraint/"));
    }
}