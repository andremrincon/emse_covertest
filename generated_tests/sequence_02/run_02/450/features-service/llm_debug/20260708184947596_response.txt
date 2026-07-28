package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("BASE_URL");
        if (env == null || env.isEmpty()) {
            env = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void createRequiresConstraint_withBothSourceAndRequired_shouldReturn201() {
        String productName = "prod-both-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String src = "RAID-" + UUID.randomUUID().toString();
        String req = "RAM-" + UUID.randomUUID().toString();
        Response resp = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", src)
                .formParam("requiredFeature", req)
                .when().post("/products/{productName}/constraints/requires", productName);
        resp.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void createRequiresConstraint_withOnlySourceFeature_shouldReturn201() {
        String productName = "prod-source-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String src = "FeatureA-" + UUID.randomUUID().toString();
        Response resp = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", src)
                .when().post("/products/{productName}/constraints/requires", productName);
        resp.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void createRequiresConstraint_withOnlyRequiredFeature_shouldReturn201() {
        String productName = "prod-required-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String req = "FeatureB-" + UUID.randomUUID().toString();
        Response resp = given().contentType("application/x-www-form-urlencoded")
                .formParam("requiredFeature", req)
                .when().post("/products/{productName}/constraints/requires", productName);
        resp.then().statusCode(201);
    }
}