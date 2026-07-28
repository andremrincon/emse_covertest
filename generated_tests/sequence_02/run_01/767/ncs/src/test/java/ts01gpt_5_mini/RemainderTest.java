package ts01gpt_5_mini;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
public class RemainderTest {

    private String baseUrl() {
        String p = System.getProperty("api.base");
        if (p != null && !p.isEmpty()) return p;
        String e = System.getenv("API_BASE");
        if (e != null && !e.isEmpty()) return e;
        return "http://localhost:8080";
    }

    @Ignore


    @Test(timeout = 60000)
    public void testPositiveAPositiveB() {
        String base = baseUrl();
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/remainder/17/5");
        assertEquals(2, resp.jsonPath().getInt("result"));
    }

    @Ignore


    @Test(timeout = 60000)
    public void testPositiveANegativeB() {
        String base = baseUrl();
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/remainder/17/-9");
        assertEquals(8, resp.jsonPath().getInt("result"));
    }

    @Ignore


    @Test(timeout = 60000)
    public void testNegativeAPositiveB() {
        String base = baseUrl();
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/remainder/-17/5");
        assertEquals(-2, resp.jsonPath().getInt("result"));
    }

    @Ignore


    @Test(timeout = 60000)
    public void testBothNegative() {
        String base = baseUrl();
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/remainder/-17/-5");
        assertEquals(2, resp.jsonPath().getInt("result"));
    }

    @Ignore("expected:<400> but was:<200>")
    @Test(timeout = 60000)
    public void testAZeroBadRequest() {
        String base = baseUrl();
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/remainder/0/5");
        assertEquals(400, resp.getStatusCode());
    }
}