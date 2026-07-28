package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("baseUrl");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBessj_NLessThan2_Returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/1/2.5").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_XZero_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/3/0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_AxGreaterThanN_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/3/5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_AxLessOrEqualN_SmallX_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/3/1e-10").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_NegativeX_OddN_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/3/-5").then().statusCode(200);
    }
}