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
    public static void init() {
        String base = System.getProperty("baseUrl");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_i28_triggers_i0_and_final_y_greater_than_x_returns_3() {
        given().when().get("/api/pat/{txt}", "setup1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 28, "a");
        resp.then().assertThat().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void test_i7_triggers_hello7_branch_and_final_overridden_returns_3() {
        given().when().get("/api/text2txt/{w1}/{w2}/{w3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 7, "a");
        resp.then().assertThat().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void test_i5_with_s_greater_than_hello_returns_2() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "1", "2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 5, "world");
        resp.then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void test_i3_with_s_not_greater_than_hello_returns_0() {
        given().when().get("/api/title/{sex}/{title}", "male", "Smith").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 3, "alpha");
        resp.then().assertThat().body(equalTo("0"));
    }
}