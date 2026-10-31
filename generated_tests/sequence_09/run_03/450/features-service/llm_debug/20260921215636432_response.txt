package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsDAOTest {

    private static final String BASE_URL;
    static {
        String v = System.getProperty("api.base");
        if (v == null || v.isEmpty()) v = System.getenv("API_BASE_URL");
        if (v == null || v.isEmpty()) v = "http://localhost:8080";
        BASE_URL = v;
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint_returns201() {
        String productName = "test-product-req-" + UUID.randomUUID().toString();
        given().baseUri(BASE_URL).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(BASE_URL)
                .formParam("sourceFeature", "RAID-Controller-Card")
                .formParam("requiredFeature", "128GB-ECC-RAM")
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint_returns201() {
        String productName = "test-product-excl-" + UUID.randomUUID().toString();
        given().baseUri(BASE_URL).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(BASE_URL)
                .formParam("sourceFeature", "CPU-i9-13900H")
                .formParam("excludedFeature", "Integrated-Graphics-Only")
                .when()
                .post("/products/{productName}/constraints/excludes", productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteProduct_withConstraints_returns204() {
        String productName = "test-product-del-" + UUID.randomUUID().toString();
        given().baseUri(BASE_URL).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(BASE_URL)
                .formParam("sourceFeature", "Push-Notifications")
                .formParam("requiredFeature", "User-Authentication")
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(lessThan(300));
        given().baseUri(BASE_URL)
                .formParam("sourceFeature", "Color-Black")
                .formParam("excludedFeature", "Logo-White-On-Black")
                .when()
                .post("/products/{productName}/constraints/excludes", productName)
                .then()
                .statusCode(lessThan(300));
        given().baseUri(BASE_URL)
                .when()
                .delete("/products/{productName}", productName)
                .then()
                .statusCode(204);
    }
}