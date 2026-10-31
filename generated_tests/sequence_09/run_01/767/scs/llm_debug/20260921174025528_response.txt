package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NotyPevarTest {

    @BeforeClass
    public static void init() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_NotyPevar_i28_results_200() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", 28, "a").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_NotyPevar_i5_s_zoo_results_200() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "15.5", "4.5").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", 5, "zoo").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_NotyPevar_i7_triggers_inner_condition_results_200() {
        given().when().get("/api/title/{sex}/{title}", "male", "Smith").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", 7, "a").then().statusCode(200);
    }
}