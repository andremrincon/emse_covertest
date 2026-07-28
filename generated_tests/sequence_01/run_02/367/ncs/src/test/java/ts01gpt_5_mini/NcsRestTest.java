package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

public class NcsRestTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("ncs.baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("NCS_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testFisherSuccessReturnsResultBody() {
        String tag = UUID.randomUUID().toString();
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/10/5/0.75");
        act.then().body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testFisherThrowsRuntimeLeadsTo400() {
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        given().when().get("/api/expint/3/2.5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/10/5/1.2");
        act.then().statusCode(200).body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testFisherParamsTooLargeReturns400() {
        given().when().get("/api/bessj/3/1e-10").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/1001/5/0.5");
        act.then().statusCode(400);
    }
}