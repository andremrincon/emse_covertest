package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertTrue;

public class ProductsConstraintsResourceTest {

    @Before
    public void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl", "http://localhost:8080");
        }
        try {
            URI uri = new URI(base);
            String scheme = uri.getScheme() != null ? uri.getScheme() : "http";
            String host = uri.getHost() != null ? uri.getHost() : "localhost";
            int port = uri.getPort();
            RestAssured.baseURI = scheme + "://" + host;
            if (port != -1) {
                RestAssured.port = port;
            } else {
                RestAssured.port = scheme.equals("https") ? 443 : 80;
            }
        } catch (Exception e) {
            RestAssured.baseURI = base;
        }
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintReturns201() {
        String uuid = UUID.randomUUID().toString();
        String productName = "prod-" + uuid;
        String sourceFeature = "src-" + uuid;
        String requiredFeature = "req-" + uuid;
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintLocationHeaderContainsConstraintsPath() {
        String uuid = UUID.randomUUID().toString();
        String productName = "prod-" + uuid;
        String sourceFeature = "src-" + uuid;
        String requiredFeature = "req-" + uuid;
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        Response resp = given().formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", productName);
        assertTrue(resp.getHeader("Location") != null && resp.getHeader("Location").contains("/products/" + productName + "/constraints/"));
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintReturns201() {
        String uuid = UUID.randomUUID().toString();
        String productName = "prod-" + uuid;
        String sourceFeature = "src-" + uuid;
        String excludedFeature = "excl-" + uuid;
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature)
                .when().post("/products/{productName}/constraints/excludes", productName)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintLocationHeaderContainsConstraintPath() {
        String uuid = UUID.randomUUID().toString();
        String productName = "prod-" + uuid;
        String sourceFeature = "src-" + uuid;
        String excludedFeature = "excl-" + uuid;
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        Response resp = given().formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature)
                .when().post("/products/{productName}/constraints/excludes", productName);
        assertTrue(resp.getHeader("Location") != null && resp.getHeader("Location").contains("/products/" + productName + "/constraint/"));
    }
}