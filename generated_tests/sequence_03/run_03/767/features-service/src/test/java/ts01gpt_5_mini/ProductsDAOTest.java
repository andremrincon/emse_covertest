package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.lessThan;
import java.util.UUID;

public class ProductsDAOTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url", System.getenv("BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testInsertRequiresConstraintReturns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", "RAID-Controller-Card")
               .formParam("requiredFeature", "128GB-ECC-RAM")
               .when()
               .post("/products/{productName}/constraints/requires", product)
               .then()
               .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testInsertExcludesConstraintReturns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", "CPU-i9-13900H")
               .formParam("excludedFeature", "Integrated-Graphics-Only")
               .when()
               .post("/products/{productName}/constraints/excludes", product)
               .then()
               .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteProductClearsConstraintsAndReturns204() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", "FeatureA")
               .formParam("requiredFeature", "FeatureB")
               .when()
               .post("/products/{productName}/constraints/requires", product)
               .then()
               .statusCode(lessThan(300));
        given().when().delete("/products/{productName}", product).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraintByIdReturns204() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/constraints/{constraintId}", product, 1).then().statusCode(204);
    }
}