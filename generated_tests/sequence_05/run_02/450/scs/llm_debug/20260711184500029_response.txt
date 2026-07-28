package ts01gpt_5_mini;

import org.junit.Test;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {
    private static final String BASE;
    static {
        String b = System.getProperty("base.url");
        if (b == null) b = System.getenv("BASE_URL");
        if (b == null) b = "http://localhost:8080";
        BASE = b;
    }

    @Test(timeout = 60000)
    public void testUseridValidReturnsOne() {
        String unique = UUID.randomUUID().toString().replace("-", "");
        String val = ("user" + unique).substring(0, Math.max(7, ("user" + unique).length()));
        given().when().get(BASE + "/api/cookie/session/am/abc.com").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/cookie/userid/u123/localhost").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/cookie/userid/" + val + "/example.com");
        act.then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionNonMatchingReturnsTwo() {
        given().when().get(BASE + "/api/pat/hello").then().statusCode(lessThan(300));
        String val = "notam";
        Response act = given().when().get(BASE + "/api/cookie/session/" + val + "/notabc.com");
        act.then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testUnknownNameReturnsZero() {
        given().when().get(BASE + "/api/calc/add/1/2").then().statusCode(lessThan(300));
        String name = "unknown" + UUID.randomUUID().toString().substring(0, 4);
        Response act = given().when().get(BASE + "/api/cookie/" + name + "/anyvalue/anysite");
        act.then().assertThat().body(equalTo("0"));
    }
}