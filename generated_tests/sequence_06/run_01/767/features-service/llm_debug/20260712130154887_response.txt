package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintWithBothFields() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "CPU-i9-13900H")
                .formParam("excludedFeature", "Integrated-Graphics-Only")
                .when()
                .post("/products/{productName}/constraints/excludes", productName);
        Assert.assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintWithOnlySourceFeature() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "Feature-Only-Source")
                .when()
                .post("/products/{productName}/constraints/excludes", productName);
        Assert.assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintWithOnlyExcludedFeature() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("excludedFeature", "Feature-Only-Excluded")
                .when()
                .post("/products/{productName}/constraints/excludes", productName);
        Assert.assertEquals(201, act.getStatusCode());
    }
}