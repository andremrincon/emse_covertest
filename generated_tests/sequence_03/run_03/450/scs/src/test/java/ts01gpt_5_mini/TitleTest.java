package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.UUID;
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
            String prop = System.getProperty("baseUrl");
            if (prop != null && !prop.isEmpty()) {
                BASE = prop;
            } else {
                BASE = "http://localhost:8080";
            }
        }
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void testMaleKnownTitleReturnsOne() {
        given().when().get(BASE + "/api/pat/{txt}", "arrange").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/title/{sex}/{title}", "male", "Mr");
        assertEquals("1", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testFemaleKnownTitleReturnsZero() {
        given().when().get(BASE + "/api/pat/{txt}", "setup-" + UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/title/{sex}/{title}", "female", "Dr");
        assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testNoneKnownTitleReturnsTwo() {
        given().when().get(BASE + "/api/text2txt/{w}/{x}/{y}", "a", "b", "c").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/title/{sex}/{title}", "none", "Prof");
        assertEquals("2", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testMaleUnknownTitleReturnsMinusOne() {
        given().when().get(BASE + "/api/pat/{txt}", "seed-" + UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/title/{sex}/{title}", "male", "queen");
        assertEquals("-1", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testCaseInsensitivityForSexAndTitle() {
        given().when().get(BASE + "/api/text2txt/{w}/{x}/{y}", "x", "y", "z").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/title/{sex}/{title}", "FeMaLe", "MISS");
        assertEquals("0", resp.getBody().asString());
    }
}