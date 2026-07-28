package ts01gpt_5_mini;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    private static final String BASE;
    static {
        String b = System.getProperty("base.url");
        if (b == null || b.isEmpty()) {
            b = System.getenv("BASE_URL");
        }
        if (b == null || b.isEmpty()) {
            b = "http://localhost:8080";
        }
        BASE = b;
    }

    @Test(timeout = 60000)
    public void testNonPositiveSide_returns200() {
        given().when().get(BASE + "/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/triangle/0/5/5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateral_returns200() {
        given().when().get(BASE + "/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/triangle/3/3/3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangle_returns200() {
        given().when().get(BASE + "/api/expint/1/0.1").then().statusCode(lessThan(300));
        String unique = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        given().when().get(BASE + "/api/triangle/10/3/3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsosceles_returns200() {
        given().when().get(BASE + "/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/triangle/5/5/3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScalene_returns200() {
        given().when().get(BASE + "/api/fisher/10/5/0.75").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(200);
    }
}