package ts01gpt_5_mini_2;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    private static String BASE;

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("base.url");
        if (env == null || env.isEmpty()) env = System.getenv("BASE_URL");
        if (env == null || env.isEmpty()) env = "http://localhost:8080";
        BASE = env;
    }

    @Test(timeout = 60000)
    public void testGammq_Gser_Path_Status200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/gammq/5.5/2.3");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_Gcf_Path_Status200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/gammq/0.001/1000.0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_XZero_Status200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/gammq/5.5/0.0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_NegativeX_Status400() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/gammq/5.5/-1.0");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_NonPositiveA_Status400() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/gammq/-1.0/2.3");
        act.then().statusCode(400);
    }
}