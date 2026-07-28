package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("apiBase");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
            if (base == null || base.isEmpty()) {
                base = "http://localhost:8080";
            }
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGammq_gser_path_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/5.5/0.001").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_gcf_path_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/5.5/1000.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_invalid_a_negative_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/-1.0/0.5").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_invalid_x_negative_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/5.0/-0.1").then().statusCode(400);
    }
}