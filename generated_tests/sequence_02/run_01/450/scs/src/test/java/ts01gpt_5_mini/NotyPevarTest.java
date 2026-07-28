package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {

    @BeforeClass
    public static void setup() {
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
    public void testNotypevar_i28_triggers_i0_and_i3_status200() {
        given().when().get("/api/dateparse/Wednesday/August").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/notypevar/{i}/{s}", 28, "foo");
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotypevar_i7_triggers_i1_and_final_i3_bodyIs3() {
        given().when().get("/api/calc/add/1/2").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/notypevar/{i}/{s}", 7, "anything");
        r.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotypevar_i2_sZ_triggers_i2_bodyIs2() {
        given().when().get("/api/calc/add/1/2").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/notypevar/{i}/{s}", 2, "z");
        r.then().body(equalTo("2"));
    }
}