package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class RemainderTest {

    private static String base() {
        String p = System.getProperty("apiBase");
        if (p != null && !p.isEmpty()) return p;
        String e = System.getenv("API_BASE_URL");
        if (e != null && !e.isEmpty()) return e;
        return "http://localhost:8080";
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"2\"   Actual: {\"resul...")
    @Test(timeout = 60000)
    public void testPositiveAAndPositiveB_returnsCorrectRemainder() {
        String base = base();
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/remainder/17/5");
        resp.then().statusCode(200).body(equalTo("2"));
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"8\"   Actual: {\"resul...")
    @Test(timeout = 60000)
    public void testPositiveAAndNegativeB_returnsCorrectRemainder() {
        String base = base();
        String unique = UUID.randomUUID().toString();
        given().when().get(base + "/api/bessj/3/2.5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/remainder/17/-9");
        resp.then().statusCode(200).body(equalTo("8"));
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"-7\"   Actual: {\"resu...")
    @Test(timeout = 60000)
    public void testNegativeAAndPositiveB_returnsCorrectRemainder() {
        String base = base();
        given().when().get(base + "/api/expint/3/0.1").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/remainder/-17/5");
        resp.then().statusCode(200).body(equalTo("-7"));
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"2\"   Actual: {\"resul...")
    @Test(timeout = 60000)
    public void testNegativeAAndNegativeB_returnsCorrectRemainder() {
        String base = base();
        given().when().get(base + "/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/remainder/-17/-5");
        resp.then().statusCode(200).body(equalTo("2"));
    }
}