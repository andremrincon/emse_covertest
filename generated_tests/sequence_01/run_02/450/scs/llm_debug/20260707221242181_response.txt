package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NotyPevarTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_notypevar_with_i28_should_return_200() {
        given().when().get("/api/pat/Hello").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/28/a");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_notypevar_with_i7_and_world_should_return_200() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/7/world");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_notypevar_with_i5_and_z_should_return_200() {
        given().when().get("/api/costfuns/1/algorithm").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/5/z");
        resp.then().statusCode(200);
    }
}