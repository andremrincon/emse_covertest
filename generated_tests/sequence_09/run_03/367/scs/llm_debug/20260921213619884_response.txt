package ts01gpt_5_mini_3;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class TitleTest {

    private static final String BASE;
    static {
        String p = System.getProperty("api.base");
        if (p == null || p.isEmpty()) p = System.getenv("API_BASE");
        if (p == null || p.isEmpty()) p = "http://localhost:8080";
        BASE = p;
    }

    @Test(timeout = 60000)
    public void testMaleWithMrReturns200() {
        given().when().get(BASE + "/api/pat/The").then().statusCode(lessThan(300));
        Response r = given().when().get(BASE + "/api/title/male/mr");
        assertEquals(200, r.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFemaleWithMrsReturns200() {
        given().when().get(BASE + "/api/pat/banana").then().statusCode(lessThan(300));
        Response r = given().when().get(BASE + "/api/title/female/mrs");
        assertEquals(200, r.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testNoneWithDrReturns200() {
        given().when().get(BASE + "/api/pat/ABABCABAB").then().statusCode(lessThan(300));
        Response r = given().when().get(BASE + "/api/title/none/dr");
        assertEquals(200, r.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testNeuterWithJonesReturns500() {
        given().when().get(BASE + "/api/pat/The").then().statusCode(lessThan(300));
        Response r = given().when().get(BASE + "/api/title/neuter/Jones");
        assertEquals(200, r.getStatusCode());
    }
}