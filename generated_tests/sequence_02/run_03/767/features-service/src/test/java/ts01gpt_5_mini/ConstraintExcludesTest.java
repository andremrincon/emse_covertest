package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class ConstraintExcludesTest {

    private static final List<String> createdProducts = Collections.synchronizedList(new ArrayList<>());

    @BeforeClass
    public static void setup() {
        String uri = System.getProperty("api.baseUri");
        if (uri == null || uri.isEmpty()) {
            uri = System.getenv("API_BASE_URI");
        }
        if (uri == null || uri.isEmpty()) {
            uri = "http://localhost:8080";
        }
        RestAssured.baseURI = uri;
    }

    @AfterClass
    public static void cleanup() {
        for (String product : createdProducts) {
            RestAssured.given().when().delete("/products/{productName}", product).then().statusCode(lessThan(300));
        }
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintWithBothFeatures() {
        String productName = "prod-" + UUID.randomUUID().toString();
        createdProducts.add(productName);
        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String excludedFeature = "excl-" + UUID.randomUUID().toString();
        Response resp = RestAssured.given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when().post("/products/{productName}/constraints/excludes", productName);
        assertEquals(201, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintWithOnlySourceFeature() {
        String productName = "prod-" + UUID.randomUUID().toString();
        createdProducts.add(productName);
        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String sourceFeature = "only-src-" + UUID.randomUUID().toString();
        Response resp = RestAssured.given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .when().post("/products/{productName}/constraints/excludes", productName);
        assertEquals(201, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintWithOnlyExcludedFeature() {
        String productName = "prod-" + UUID.randomUUID().toString();
        createdProducts.add(productName);
        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String excludedFeature = "only-excl-" + UUID.randomUUID().toString();
        Response resp = RestAssured.given().contentType("application/x-www-form-urlencoded")
                .formParam("excludedFeature", excludedFeature)
                .when().post("/products/{productName}/constraints/excludes", productName);
        assertEquals(201, resp.getStatusCode());
    }
}