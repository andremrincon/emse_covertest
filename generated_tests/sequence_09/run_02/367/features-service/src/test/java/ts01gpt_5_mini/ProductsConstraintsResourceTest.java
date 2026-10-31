package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConstraintsResourceTest {

    @BeforeClass
    public static void setup() {
        String envBase = System.getProperty("api.base");
        if (envBase == null || envBase.isEmpty()) {
            envBase = System.getenv("API_BASE");
        }
        if (envBase == null || envBase.isEmpty()) {
            envBase = "http://localhost:8080";
        }
        RestAssured.baseURI = envBase;
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintCreated() {
        String productName = "product-req-" + UUID.randomUUID().toString();
        String srcFeature = "src-" + UUID.randomUUID().toString();
        String reqFeature = "req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "feature A").when().post("/products/{productName}/features/{featureName}", productName, srcFeature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "feature B").when().post("/products/{productName}/features/{featureName}", productName, reqFeature).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", srcFeature).formParam("requiredFeature", reqFeature).when().post("/products/{productName}/constraints/requires", productName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintCreated() {
        String productName = "product-excl-" + UUID.randomUUID().toString();
        String srcFeature = "src-" + UUID.randomUUID().toString();
        String exclFeature = "excl-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "feature A").when().post("/products/{productName}/features/{featureName}", productName, srcFeature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "feature B").when().post("/products/{productName}/features/{featureName}", productName, exclFeature).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", srcFeature).formParam("excludedFeature", exclFeature).when().post("/products/{productName}/constraints/excludes", productName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintWithMinimalInputs() {
        String productName = "product-req-min-" + UUID.randomUUID().toString();
        String srcFeature = "src-min-" + UUID.randomUUID().toString();
        String reqFeature = "req-min-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", productName, srcFeature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", productName, reqFeature).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", srcFeature).formParam("requiredFeature", reqFeature).when().post("/products/{productName}/constraints/requires", productName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintWithMinimalInputs() {
        String productName = "product-excl-min-" + UUID.randomUUID().toString();
        String srcFeature = "src-min-" + UUID.randomUUID().toString();
        String exclFeature = "excl-min-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", productName, srcFeature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", productName, exclFeature).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", srcFeature).formParam("excludedFeature", exclFeature).when().post("/products/{productName}/constraints/excludes", productName);
        act.then().statusCode(201);
    }
}