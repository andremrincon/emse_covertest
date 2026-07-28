package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("baseUrl");
        if (env == null || env.isEmpty()) {
            env = System.getenv("BASE_URL");
        }
        if (env == null || env.isEmpty()) {
            env = "http://localhost:8080";
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintWithBothNames() {
        String productName = "prod-" + UUID.randomUUID().toString();
        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String source = "RAID-Controller-" + UUID.randomUUID().toString();
        String required = "128GB-ECC-RAM-" + UUID.randomUUID().toString();
        RestAssured.given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", source)
                .formParam("requiredFeature", required)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintWithOnlySourceFeature() {
        String productName = "prod-" + UUID.randomUUID().toString();
        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String source = "FeatureA-" + UUID.randomUUID().toString();
        RestAssured.given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", source)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintWithOnlyRequiredFeature() {
        String productName = "prod-" + UUID.randomUUID().toString();
        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String required = "FeatureB-" + UUID.randomUUID().toString();
        RestAssured.given().contentType("application/x-www-form-urlencoded")
                .formParam("requiredFeature", required)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(201);
    }
}