package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class ProductsConstraintsResourceTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintReturns201() {
        String uuid = UUID.randomUUID().toString();
        String product = "prod-" + uuid;
        String src = "src-" + uuid;
        String req = "req-" + uuid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, req).then().statusCode(lessThan(300));
        Response resp = given().formParam("sourceFeature", src).formParam("requiredFeature", req).when().post("/products/{productName}/constraints/requires", product);
        assertEquals(201, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintReturns201() {
        String uuid = UUID.randomUUID().toString();
        String product = "prod-" + uuid;
        String src = "src-" + uuid;
        String excl = "excl-" + uuid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excl).then().statusCode(lessThan(300));
        Response resp = given().formParam("sourceFeature", src).formParam("excludedFeature", excl).when().post("/products/{productName}/constraints/excludes", product);
        assertEquals(201, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintLocationHeaderContainsConstraintsPath() {
        String uuid = UUID.randomUUID().toString();
        String product = "prod-" + uuid;
        String src = "src-" + uuid;
        String req = "req-" + uuid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, req).then().statusCode(lessThan(300));
        Response resp = given().formParam("sourceFeature", src).formParam("requiredFeature", req).when().post("/products/{productName}/constraints/requires", product);
        assertThat(resp.getHeader("Location"), containsString("/products/" + product + "/constraints/"));
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintLocationHeaderContainsSingularConstraintPath() {
        String uuid = UUID.randomUUID().toString();
        String product = "prod-" + uuid;
        String src = "src-" + uuid;
        String excl = "excl-" + uuid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, excl).then().statusCode(lessThan(300));
        Response resp = given().formParam("sourceFeature", src).formParam("excludedFeature", excl).when().post("/products/{productName}/constraints/excludes", product);
        assertThat(resp.getHeader("Location"), containsString("/products/" + product + "/constraint/"));
    }
}