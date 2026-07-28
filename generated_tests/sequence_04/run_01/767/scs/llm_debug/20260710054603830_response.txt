package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class TitleTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("baseUrl");
        if (env == null || env.isEmpty()) {
            env = System.getenv("BASE_URL");
        }
        if (env == null || env.isEmpty()) {
            env = "http://localhost:8080";
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testMaleWithValidTitleReturnsOne() {
        String arrangeToken = "arrange-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeToken).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "male", "Mr");
        assertEquals("1", act.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testMaleWithInvalidTitleReturnsMinusOne() {
        String arrangeToken = "arrange-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeToken).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "male", "queen");
        assertEquals("-1", act.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testFemaleWithValidTitleReturnsZero() {
        String arrangeToken = "arrange-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeToken).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "female", "Ms");
        assertEquals("0", act.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testFemaleWithInvalidTitleReturnsMinusOne() {
        String arrangeToken = "arrange-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeToken).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "female", "mr");
        assertEquals("-1", act.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testNoneWithValidTitleReturnsTwo() {
        String arrangeToken = "arrange-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeToken).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "none", "Prof");
        assertEquals("2", act.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testNoneWithInvalidTitleReturnsMinusOne() {
        String arrangeToken = "arrange-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeToken).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "none", "madam");
        assertEquals("-1", act.getBody().asString().trim());
    }
}