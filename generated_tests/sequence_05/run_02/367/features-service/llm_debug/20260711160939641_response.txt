package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsDAOTest {

    @BeforeClass
    public static void setup() {
        String envUrl = System.getProperty("baseUrl");
        if (envUrl == null || envUrl.isEmpty()) {
            String env = System.getenv("BASE_URL");
            if (env != null && !env.isEmpty()) {
                envUrl = env;
            } else {
                envUrl = "http://localhost:8080";
            }
        }
        RestAssured.baseURI = envUrl;
    }

    @Test(timeout = 60000)
    public void testInsertRequiresConstraintThenDeleteProduct() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", "RAID-Controller-Card").formParam("requiredFeature", "128GB-ECC-RAM").when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}", productName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testInsertExcludesConstraintThenDeleteProduct() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", "CPU-i9-13900H").formParam("excludedFeature", "Integrated-Graphics-Only").when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}", productName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteProductWithoutConstraints() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}", productName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddMultipleConstraintsThenDeleteProduct() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", "FeatureA").formParam("requiredFeature", "FeatureB").when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", "FeatureC").formParam("excludedFeature", "FeatureD").when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}", productName).then().statusCode(204);
    }
}