package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBessj_axEqualsZero_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 3, 0);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_nLessThan2_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 1, 2.5);
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_axGreaterThanN_asymptoticBranch_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 3, 10.0);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_negativeX_oddN_signBranch_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 3, -2.5);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_smallX_recursiveBranch_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 10, "1e-10");
        resp.then().statusCode(200);
    }
}