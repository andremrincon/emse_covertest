package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint_withBothSourceAndRequired_createsConstraint() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        Response act = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName);

        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint_withOnlySourceFeature_createsConstraint() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        Response act = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName);

        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint_withOnlyRequiredFeature_createsConstraint() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        Response act = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName);

        act.then().statusCode(201);
    }
}