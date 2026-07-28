package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsDAOTest {

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
    public void testInsertRequiresConstraint() {
        String productName = "prod-requires-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().formParam("sourceFeature", "RAID-Controller-Card")
                .formParam("requiredFeature", "128GB-ECC-RAM")
                .when().post("/products/{productName}/constraints/requires", productName);
        Assert.assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testInsertExcludesConstraint() {
        String productName = "prod-excludes-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().formParam("sourceFeature", "CPU-i9-13900H")
                .formParam("excludedFeature", "Integrated-Graphics-Only")
                .when().post("/products/{productName}/constraints/excludes", productName);
        Assert.assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDeleteProductWithConstraintsRemovesConstraints() {
        String productName = "prod-delete-with-constraints-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", "FeatA")
                .formParam("excludedFeature", "FeatB")
                .when().post("/products/{productName}/constraints/excludes", productName)
                .then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}", productName);
        Assert.assertEquals(204, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDeleteProductWithoutConstraints() {
        String productName = "prod-delete-no-constraints-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}", productName);
        Assert.assertEquals(204, act.getStatusCode());
    }
}