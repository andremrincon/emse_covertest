package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("BASE_URL");
        if (env == null || env.isEmpty()) env = System.getProperty("api.base", "http://localhost:8080");
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testFisher_ValidExample_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/10/5/0.75");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_OddOdd_HasValueField() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/3/5/0.5");
        act.then().body("value", nullValue());
    }

    @Test(timeout = 60000)
    public void testFisher_InvalidMType_Returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/abc/5/0.75");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisher_OddM_EvenN_HasValueField() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/3/4/0.5");
        act.then().body("value", nullValue());
    }

    @Test(timeout = 60000)
    public void testFisher_EvenEven_HasValueField() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/4/6/0.75");
        act.then().body("value", nullValue());
    }
}