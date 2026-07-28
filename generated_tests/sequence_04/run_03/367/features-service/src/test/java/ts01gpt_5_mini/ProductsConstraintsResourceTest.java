package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConstraintsResourceTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE_URL");
        String prop = System.getProperty("api.base");
        String base = env != null && !env.isEmpty() ? env : (prop != null && !prop.isEmpty() ? prop : "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintReturns201() {
        String product = "product-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", product, sourceFeature).then().statusCode(lessThan(300));
        given().formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", product, requiredFeature).then().statusCode(lessThan(300));
        Response resp = given().formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/{productName}/constraints/requires", product);
        Assert.assertEquals(201, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintLocationContainsConstraints() {
        String product = "product-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("description", "d").when().post("/products/{productName}/features/{featureName}", product, sourceFeature).then().statusCode(lessThan(300));
        given().formParam("description", "d").when().post("/products/{productName}/features/{featureName}", product, requiredFeature).then().statusCode(lessThan(300));
        Response resp = given().formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/{productName}/constraints/requires", product);
        Assert.assertTrue(resp.getHeader("Location") != null && resp.getHeader("Location").contains("/constraints/"));
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintLocationContainsSingularConstraint() {
        String product = "product-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String excludedFeature = "excl-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("description", "d").when().post("/products/{productName}/features/{featureName}", product, sourceFeature).then().statusCode(lessThan(300));
        given().formParam("description", "d").when().post("/products/{productName}/features/{featureName}", product, excludedFeature).then().statusCode(lessThan(300));
        Response resp = given().formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature).when().post("/products/{productName}/constraints/excludes", product);
        Assert.assertTrue(resp.getHeader("Location") != null && resp.getHeader("Location").contains("/constraint/"));
    }
}