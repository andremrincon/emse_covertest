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
    public void testNotyPevar_whenI28_returns3() {
        given().when().get("/api/costfuns/1/algorithm").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/28/zzz");
        resp.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_whenI3_returns2() {
        given().when().get("/api/costfuns/1/algorithm").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/3/z");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_whenI1_returns0() {
        given().when().get("/api/costfuns/1/algorithm").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/1/a");
        resp.then().body(equalTo("0"));
    }
}