package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import java.util.UUID;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class FeatureConstraintTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void addExcludesConstraint_returns201() {
        String productName = "test-prod-excl-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", "CPU-i9-13900H")
            .formParam("excludedFeature", "Integrated-Graphics-Only")
            .when()
            .post("/products/{productName}/constraints/excludes", productName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addRequiresConstraint_returns201() {
        String productName = "test-prod-req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", "RAID-Controller-Card")
            .formParam("requiredFeature", "128GB-ECC-RAM")
            .when()
            .post("/products/{productName}/constraints/requires", productName)
            .then()
            .statusCode(201);
    }
}