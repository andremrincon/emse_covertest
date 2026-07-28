package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsDAOTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testInsertRequiresConstraintReturns201() {
        String productName = "test-product-requires-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", "RAID-Controller-Card")
               .formParam("requiredFeature", "128GB-ECC-RAM")
               .when()
               .post("/products/{productName}/constraints/requires", productName)
               .then()
               .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testInsertExcludesConstraintReturns201() {
        String productName = "test-product-excludes-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", "CPU-i9-13900H")
               .formParam("excludedFeature", "Integrated-Graphics-Only")
               .when()
               .post("/products/{productName}/constraints/excludes", productName)
               .then()
               .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteProductClearsConstraintsAndReturns204() {
        String productName = "test-product-delete-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", "FeatureA")
               .formParam("requiredFeature", "FeatureB")
               .when()
               .post("/products/{productName}/constraints/requires", productName)
               .then()
               .statusCode(lessThan(300));
        given().when().delete("/products/{productName}", productName).then().statusCode(204);
    }

}