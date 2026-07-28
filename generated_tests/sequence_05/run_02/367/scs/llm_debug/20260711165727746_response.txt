package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CookieTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.baseUrl", System.getenv("API_BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void userid_withValLongerThanSix_andStartsWithUser_returns1() {
        given().when().get("/api/calc/add/1/1").then().statusCode(lessThan(300));
        String unique = UUID.randomUUID().toString();
        String val = "User" + unique;
        given().when().get("/api/cookie/{name}/{val}/{site}", "userid", val, "example.com").then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void userid_withValNotMatchingConditions_returns0() {
        given().when().get("/api/calc/add/1/1").then().statusCode(lessThan(300));
        String val = "foo";
        given().when().get("/api/cookie/{name}/{val}/{site}", "userid", val, "example.com").then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void session_withAmAndAbcDotCom_returns1() {
        given().when().get("/api/calc/add/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com").then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void session_withOtherValues_returns2() {
        given().when().get("/api/calc/add/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "session", "pm", "abc.com").then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void mixedCaseInputs_areHandledCaseInsensitively() {
        given().when().get("/api/calc/add/1/1").then().statusCode(lessThan(300));
        String val = "UsErXYZ123";
        given().when().get("/api/cookie/{name}/{val}/{site}", "USERID", val, "EXAMPLE.COM").then().assertThat().body(equalTo("1"));
    }
}