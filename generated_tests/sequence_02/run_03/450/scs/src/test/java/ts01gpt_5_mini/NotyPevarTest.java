package ts01gpt_5_mini;

import org.junit.Test;
import org.junit.BeforeClass;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {

    @BeforeClass
    public static void init() {
        String envBase = System.getProperty("api.base");
        if (envBase == null || envBase.isEmpty()) {
            envBase = System.getenv("API_BASE");
        }
        if (envBase == null || envBase.isEmpty()) {
            envBase = "http://localhost:8080";
        }
        RestAssured.baseURI = envBase;
    }

    @Test(timeout = 60000)
    public void testNotypevar_i5_s_z_returns2() {
        given().when().get("/api/title/male/Smith").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/5/z");
        resp.then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testNotypevar_i28_s_a_returns3() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/28/a");
        resp.then().assertThat().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotypevar_i7_s_a_status200() {
        given().when().get("/api/pat/The%20quick%20brown%20fox").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/7/a");
        resp.then().statusCode(200);
    }
}