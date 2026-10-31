package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class GammqTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE_URL");
        if (env == null || env.isEmpty()) env = System.getProperty("api.base", "http://localhost:8080");
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testGammq_Gser_Success() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/5.5/2.3");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammq_Gcf_Success() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/0.001/1000.0");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammq_Gser_XZero() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/5.5/0.0");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammq_InvalidA_Negative() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/-1.0/2.3");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammq_InvalidX_Negative() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/5.5/-1.0");
        assertEquals(400, resp.getStatusCode());
    }
}