package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class FisherTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("BASE_URL");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = System.getProperty("baseUrl");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testFisher_BothOdd_PathAAndB() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/5/3/0.75");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_MOdd_NEven_PathALoop() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/5/4/0.5");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_MEven_NOdd_PathBLoop() {
        given().when().get("/api/expint/3/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/4/3/0.2");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_BothEven_ElseBranch() {
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/6/4/0.9");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_LargeM_ExerciseLoops() {
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/101/3/10.0");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_ZeroX_EdgeCase() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/10/1/0.0");
        assertEquals(200, resp.getStatusCode());
    }
}