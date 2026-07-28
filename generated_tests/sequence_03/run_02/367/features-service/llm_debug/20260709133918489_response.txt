package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsDAOTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testInsertRequiresConstraintCreatesConstraint() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", "RAID-Controller-Card").formParam("requiredFeature", "128GB-ECC-RAM").when().post("/products/{productName}/constraints/requires", productName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testInsertExcludesConstraintCreatesConstraint() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", "CPU-i9-13900H").formParam("excludedFeature", "Integrated-Graphics-Only").when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteProductRemovesConstraintsWhenPresent() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", "CPU-XYZ").formParam("excludedFeature", "Feature-A").when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}", productName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteProductWithoutConstraints() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}", productName).then().statusCode(204);
    }
}