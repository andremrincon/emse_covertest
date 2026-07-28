package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintWithBothFields() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintWithOnlySourceFeature() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String sourceFeature = "only-src-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .when().post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintWithOnlyRequiredFeature() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String requiredFeature = "only-req-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded")
                .formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(201);
    }
}