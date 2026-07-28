package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class ProductsDAOTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testInsertRequiresConstraint() {
        String productName = "product-requires-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "RAID-Controller-Card")
                .formParam("requiredFeature", "128GB-ECC-RAM")
                .when().post("/products/{productName}/constraints/requires", productName);
        assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDeleteProductWithConstraints() {
        String productName = "product-del-constraints-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "FeatureA")
                .formParam("requiredFeature", "FeatureB")
                .when().post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}", productName);
        assertEquals(204, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDeleteProductWithoutConstraints() {
        String productName = "product-del-no-constraints-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}", productName);
        assertEquals(204, act.getStatusCode());
    }
}