package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class ConstraintRequiresTest {

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
    public void testAddRequiresConstraintReturns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String source = "src-" + UUID.randomUUID().toString();
        String required = "req-" + UUID.randomUUID().toString();
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", source)
                .formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", productName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreatedRequiresConstraintReflectedInGetProduct() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String source = "src-" + UUID.randomUUID().toString();
        String required = "req-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", source)
                .formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}", productName);
        act.then().body("name", equalTo(productName));
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintWithSpecialCharactersReturns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String source = "src special / ? & = " + UUID.randomUUID().toString();
        String required = "req + % # " + UUID.randomUUID().toString();
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", source)
                .formParam("requiredFeature", required)
                .when().post("/products/{productName}/constraints/requires", productName);
        act.then().statusCode(201);
    }
}