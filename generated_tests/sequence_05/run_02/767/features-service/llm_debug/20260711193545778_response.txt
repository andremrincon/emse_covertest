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
        String configured = System.getProperty("api.baseUrl");
        if (configured == null || configured.isEmpty()) {
            configured = System.getenv("API_BASE_URL");
        }
        if (configured == null || configured.isEmpty()) {
            configured = "http://localhost:8080";
        }
        RestAssured.baseURI = configured;
    }

    @Test(timeout = 60000)
    public void testInsertConstraint_requires_returns201() {
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
    public void testDeleteConstraintsForProduct_whenProductHasConstraints_deleteProduct_returns204() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", "RAID-Controller-Card")
               .formParam("requiredFeature", "128GB-ECC-RAM")
               .when()
               .post("/products/{productName}/constraints/requires", product)
               .then()
               .statusCode(lessThan(300));
        given().when().delete("/products/{productName}", product).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_returns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("description", "Test feature description")
               .when()
               .post("/products/{productName}/features/{featureName}", product, feature)
               .then()
               .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProduct_returns204() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("description", "To be deleted")
               .when()
               .post("/products/{productName}/features/{featureName}", product, feature)
               .then()
               .statusCode(lessThan(300));
        given().when().delete("/products/{productName}/features/{featureName}", product, feature).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetProductByName_returns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}", product).then().statusCode(200);
    }
}