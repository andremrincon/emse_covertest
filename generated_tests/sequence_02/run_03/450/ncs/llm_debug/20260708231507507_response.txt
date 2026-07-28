package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GammqTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("BASE_URL");
        if (env != null && !env.isEmpty()) {
            RestAssured.baseURI = env;
        } else {
            String prop = System.getProperty("baseUrl");
            RestAssured.baseURI = (prop != null && !prop.isEmpty()) ? prop : "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testGammq_gser_branch_returns200_for_x_less_than_a_plus_one() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_gcf_branch_returns200_for_x_ge_a_plus_one() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/5.5/1000.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_gser_x_zero_executes_x_le_zero_branch_and_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/5.5/0.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_invalid_a_less_or_equal_zero_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/-1.0/2.3").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_negative_x_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/5.5/-0.1").then().statusCode(400);
    }
}