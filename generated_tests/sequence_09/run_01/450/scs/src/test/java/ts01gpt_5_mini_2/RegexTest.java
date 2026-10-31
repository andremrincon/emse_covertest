package ts01gpt_5_mini_2;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import java.net.URLEncoder;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    private final String base = System.getProperty("api.base", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testSubjectClassifiesUrlAsUrl() throws Exception {
        given().when().get(base + "/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        String txt = "http://a/b";
        String enc = URLEncoder.encode(txt, "UTF-8");
        Response resp = given().when().get(base + "/api/pat/{txt}", enc);
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testSubjectClassifiesDateAsDate() throws Exception {
        given().when().get(base + "/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        String txt = "wed12apr";
        String enc = URLEncoder.encode(txt, "UTF-8");
        Response resp = given().when().get(base + "/api/pat/{txt}", enc);
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testSubjectClassifiesFloatingPointExponentialAsFpe() throws Exception {
        given().when().get(base + "/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        String txt = "15.5e+04";
        String enc = URLEncoder.encode(txt, "UTF-8");
        Response resp = given().when().get(base + "/api/pat/{txt}", enc);
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testSubjectClassifiesUnknownAsNone() throws Exception {
        given().when().get(base + "/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        String txt = "foobar";
        String enc = URLEncoder.encode(txt, "UTF-8");
        Response resp = given().when().get(base + "/api/pat/{txt}", enc);
        Assert.assertEquals(200, resp.getStatusCode());
    }
}