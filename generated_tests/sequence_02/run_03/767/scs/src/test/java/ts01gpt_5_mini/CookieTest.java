package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CookieTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    private String enc(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(timeout = 60000)
    public void testUseridReturnsOneWhenValueLongAndStartsWithUser() {
        given().when().get("/api/calc/add/1/2").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/" + enc("session") + "/" + enc("no") + "/" + enc("other.com")).then().statusCode(lessThan(300));
        String unique = UUID.randomUUID().toString().replace("-", "");
        String val = "user" + (unique.length() >= 4 ? unique.substring(0,4) : unique);
        Response act = given().when().get("/api/cookie/" + enc("userid") + "/" + enc(val) + "/" + enc("example.com"));
        assertEquals("1", act.asString());
    }

    @Test(timeout = 60000)
    public void testSessionReturnsOneWhenValAmAndSiteAbcCom() {
        given().when().get("/api/pat/TheQuickBrownFox").then().statusCode(lessThan(300));
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/cookie/" + enc("userid") + "/" + enc("short") + "/" + enc(uid)).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/" + enc("session") + "/" + enc("am") + "/" + enc("abc.com"));
        assertEquals("1", act.asString());
    }

    @Test(timeout = 60000)
    public void testSessionReturnsTwoWhenNotMatchingAmAndAbcCom() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/cookie/" + enc("userid") + "/" + enc("usr") + "/" + enc(uid)).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/" + enc("session") + "/" + enc("am") + "/" + enc("notabc.com"));
        assertEquals("2", act.asString());
    }
}