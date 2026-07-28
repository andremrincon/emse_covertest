package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

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

    @Test(timeout = 60000)
    public void testGetFeaturesAddsAccessControlAllowOriginHeader() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response response = given().when().get("/products/{productName}/features", productName);
        Assert.assertEquals("*", response.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testOptionsRequestAddsAllowMethodsHeader() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response response = given().when().options("/products/{productName}/features", productName);
        Assert.assertEquals("POST, PUT, GET, OPTIONS, DELETE", response.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testPostFeatureAddsAccessControlAllowHeadersHeader() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response response = given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "Test feature description")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName);
        Assert.assertEquals("x-requested-with", response.getHeader("Access-Control-Allow-Headers"));
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureReturns204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "to be deleted")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));
        Response response = given().when().delete("/products/{productName}/features/{featureName}", productName, featureName);
        Assert.assertEquals(204, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetConfigurationReturnsAccessControlMaxAgeHeader() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configurationName = "conf-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(lessThan(300));
        Response response = given().when().get("/products/{productName}/configurations/{configurationName}", productName, configurationName);
        Assert.assertEquals("3600", response.getHeader("Access-Control-Max-Age"));
    }
}