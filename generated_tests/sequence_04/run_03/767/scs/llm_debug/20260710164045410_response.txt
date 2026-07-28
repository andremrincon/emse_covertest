package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_notypevar_i28_triggers_i0_and_i3_returns_3() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/28/aaa");
        resp.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void test_notypevar_i7_executes_i1_branch_status_ok() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/7/hello");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_notypevar_i0_s_world_triggers_i2_returns_2() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/0/world");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void test_notypevar_i5_s_aaa_all_conditions_false_returns_0() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/5/aaa");
        resp.then().body(equalTo("0"));
    }
}