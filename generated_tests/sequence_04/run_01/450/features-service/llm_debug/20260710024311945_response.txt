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
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void insertRequiresConstraint_shouldReturn201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "RAID-Controller-Card")
                .formParam("requiredFeature", "128GB-ECC-RAM")
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void deleteProduct_withExistingConstraints_shouldReturn204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "FeatureX")
                .formParam("requiredFeature", "FeatureY")
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}", productName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void insertExcludesConstraint_shouldReturn201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "CPU-i9-13900H")
                .formParam("excludedFeature", "Integrated-Graphics-Only")
                .when()
                .post("/products/{productName}/constraints/excludes", productName)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void deleteConstraintEndpoint_shouldReturn204_whenConstraintIdProvided() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "A")
                .formParam("excludedFeature", "B")
                .when()
                .post("/products/{productName}/constraints/excludes", productName)
                .then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/constraints/{constraintId}", productName, 1).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/constraints/{constraintId}", productName, 1).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void addFeatureToProduct_thenDeleteFeature_shouldReturn201_and_204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "Test feature")
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(201);
        given().when().delete("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(204);
    }
}