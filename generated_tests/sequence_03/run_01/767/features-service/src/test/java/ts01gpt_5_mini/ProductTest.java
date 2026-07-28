package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

public class ProductTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    private String enc(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (Exception e) {
            return s;
        }
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_returns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/" + enc(product)).then().statusCode(lessThan(300));
        Response act = given().when().post("/products/" + enc(product) + "/features/" + enc(feature));
        assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProduct_includesAddedFeature() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/" + enc(product)).then().statusCode(lessThan(300));
        given().formParam("description", "desc").when().post("/products/" + enc(product) + "/features/" + enc(feature)).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/" + enc(product) + "/features");
        assertTrue(act.getBody().asString().contains(feature));
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProduct_returns204() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/" + enc(product)).then().statusCode(lessThan(300));
        given().when().post("/products/" + enc(product) + "/features/" + enc(feature)).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/" + enc(product) + "/features/" + enc(feature));
        assertEquals(204, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureOfProduct_returns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/" + enc(product)).then().statusCode(lessThan(300));
        given().when().post("/products/" + enc(product) + "/features/" + enc(feature)).then().statusCode(lessThan(300));
        Response act = given().formParam("description", "Updated description").when().put("/products/" + enc(product) + "/features/" + enc(feature));
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDeleteNonExistentFeature_returns500() {
        String product = "prod-" + UUID.randomUUID().toString();
        String missingFeature = "missing-" + UUID.randomUUID().toString();
        given().when().post("/products/" + enc(product)).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/" + enc(product) + "/features/" + enc(missingFeature));
        assertEquals(500, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct_returns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/" + enc(product)).then().statusCode(lessThan(300));
        Response act = given().formParam("sourceFeature", "src-" + UUID.randomUUID().toString()).formParam("requiredFeature", "req-" + UUID.randomUUID().toString()).when().post("/products/" + enc(product) + "/constraints/requires");
        assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct_returns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/" + enc(product)).then().statusCode(lessThan(300));
        Response act = given().formParam("sourceFeature", "src-" + UUID.randomUUID().toString()).formParam("excludedFeature", "excl-" + UUID.randomUUID().toString()).when().post("/products/" + enc(product) + "/constraints/excludes");
        assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProduct_doesNotContainOtherFeature() {
        String product = "prod-" + UUID.randomUUID().toString();
        String otherFeature = "other-" + UUID.randomUUID().toString();
        given().when().post("/products/" + enc(product)).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/" + enc(product) + "/features");
        assertFalse(act.getBody().asString().contains(otherFeature));
    }

    @Test(timeout = 60000)
    public void testGetProduct_includesFeatureAfterAdd() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/" + enc(product)).then().statusCode(lessThan(300));
        given().when().post("/products/" + enc(product) + "/features/" + enc(feature)).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/" + enc(product));
        assertTrue(act.getBody().asString().contains(feature));
    }
}