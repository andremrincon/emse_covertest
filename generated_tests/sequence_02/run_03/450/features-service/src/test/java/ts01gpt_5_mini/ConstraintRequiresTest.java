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
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintWithBothParameters() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String source = "SourceFeature-" + UUID.randomUUID().toString();
        String required = "RequiredFeature-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source).formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintWithOnlySourceFeature() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String source = "OnlySource-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", source)
                .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintWithOnlyRequiredFeature() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String required = "OnlyRequired-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded").formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(201);
    }
}