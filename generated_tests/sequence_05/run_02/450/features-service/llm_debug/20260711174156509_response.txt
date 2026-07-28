package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_withBothFeatures_shouldReturn201() {
        String base = System.getProperty("api.base", System.getenv("API_BASE_URL"));
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
        String product = "prod-" + UUID.randomUUID().toString();
        String src = "src-" + UUID.randomUUID().toString();
        String excl = "excl-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response resp = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", src)
                .formParam("excludedFeature", excl)
                .when().post("/products/{productName}/constraints/excludes", product);
        Assert.assertEquals(201, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_withOnlySourceFeature_shouldReturn201() {
        String base = System.getProperty("api.base", System.getenv("API_BASE_URL"));
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
        String product = "prod-" + UUID.randomUUID().toString();
        String src = "src-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response resp = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", src)
                .when().post("/products/{productName}/constraints/excludes", product);
        Assert.assertEquals(201, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_withOnlyExcludedFeature_shouldReturn201() {
        String base = System.getProperty("api.base", System.getenv("API_BASE_URL"));
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
        String product = "prod-" + UUID.randomUUID().toString();
        String excl = "excl-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response resp = given().contentType("application/x-www-form-urlencoded")
                .formParam("excludedFeature", excl)
                .when().post("/products/{productName}/constraints/excludes", product);
        Assert.assertEquals(201, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_withoutFeatures_shouldReturn201() {
        String base = System.getProperty("api.base", System.getenv("API_BASE_URL"));
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response resp = given().contentType("application/x-www-form-urlencoded")
                .when().post("/products/{productName}/constraints/excludes", product);
        Assert.assertEquals(201, resp.getStatusCode());
    }
}