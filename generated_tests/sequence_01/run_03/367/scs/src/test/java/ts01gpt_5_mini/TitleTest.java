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

    private static String BASE;

    @BeforeClass
    public static void init() {
        String b = System.getProperty("api.base");
        if (b == null || b.isEmpty()) b = System.getenv("API_BASE_URL");
        if (b == null || b.isEmpty()) b = "http://localhost:8080";
        BASE = b;
        RestAssured.baseURI = BASE;
    }

    @Test(timeout = 60000)
    public void testMaleTitleReturnsOne() {
        given().when().get(BASE + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/title/male/Mr");
        String body = act.then().extract().asString();
        assertEquals("1", body);
    }

    @Test(timeout = 60000)
    public void testFemaleTitleReturnsZeroCaseInsensitive() {
        given().when().get(BASE + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/title/FEMALE/MISS");
        String body = act.then().extract().asString();
        assertEquals("0", body);
    }

    @Test(timeout = 60000)
    public void testNoneSexWithDrReturnsTwo() {
        given().when().get(BASE + "/api/text2txt/Another/example/test").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/title/none/Dr");
        String body = act.then().extract().asString();
        assertEquals("2", body);
    }

    @Test(timeout = 60000)
    public void testMaleWithNonMatchingTitleReturnsMinusOne() {
        given().when().get(BASE + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        String unique = UUID.randomUUID().toString();
        Response act = given().when().get(BASE + "/api/title/male/queen-" + unique);
        String body = act.then().extract().asString();
        assertEquals("-1", body);
    }

    @Test(timeout = 60000)
    public void testUnknownSexReturnsMinusOne() {
        given().when().get(BASE + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/title/unknown/dr");
        String body = act.then().extract().asString();
        assertEquals("-1", body);
    }
}