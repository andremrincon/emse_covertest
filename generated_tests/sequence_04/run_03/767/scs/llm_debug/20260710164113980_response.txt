package ts01gpt_5_mini;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class TitleTest {

    private static final String BASE;
    static {
        String env = System.getenv("BASE_URL");
        if (env != null && !env.isEmpty()) {
            BASE = env;
        } else {
            BASE = System.getProperty("api.base", "http://localhost:8080");
        }
    }

    @Test(timeout = 60000)
    public void testMaleTitleMatches_returnsOne() {
        given().when().get(BASE + "/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/title/{sex}/{title}", "male", "mr");
        assertEquals("1", resp.asString());
    }

    @Test(timeout = 60000)
    public void testMaleTitleNonMatch_returnsMinusOne() {
        given().when().get(BASE + "/api/pat/{txt}", "healthcheck2").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/title/{sex}/{title}", "male", "mrs");
        assertEquals("-1", resp.asString());
    }

    @Test(timeout = 60000)
    public void testFemaleTitleMatches_returnsZero() {
        given().when().get(BASE + "/api/pat/{txt}", "healthcheck3").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/title/{sex}/{title}", "female", "ms");
        assertEquals("0", resp.asString());
    }

    @Test(timeout = 60000)
    public void testFemaleTitleDrUppercase_caseInsensitive_returnsZero() {
        given().when().get(BASE + "/api/pat/{txt}", "healthcheck4").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/title/{sex}/{title}", "FEMALE", "DR");
        assertEquals("0", resp.asString());
    }

    @Test(timeout = 60000)
    public void testNoneTitleMatches_returnsTwo() {
        given().when().get(BASE + "/api/pat/{txt}", "healthcheck5").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/title/{sex}/{title}", "none", "prof");
        assertEquals("2", resp.asString());
    }

    @Test(timeout = 60000)
    public void testUnknownSex_returnsMinusOne() {
        given().when().get(BASE + "/api/pat/{txt}", "healthcheck6").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/title/{sex}/{title}", "other", "dr");
        assertEquals("-1", resp.asString());
    }
}