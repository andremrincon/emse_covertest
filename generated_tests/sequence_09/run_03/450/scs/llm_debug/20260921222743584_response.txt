package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.Assert;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    private static final String BASE;
    static {
        String tmp = System.getProperty("API_BASE_URL");
        if (tmp == null || tmp.isEmpty()) tmp = System.getenv("API_BASE_URL");
        if (tmp == null || tmp.isEmpty()) tmp = System.getProperty("api.base");
        if (tmp == null || tmp.isEmpty()) tmp = System.getenv("API_BASE");
        if (tmp == null || tmp.isEmpty()) tmp = "http://localhost:8080";
        BASE = tmp;
        RestAssured.baseURI = BASE;
    }

    @Test(timeout = 60000)
    public void testPatEndpoint_withUrlInput_returns200() throws Exception {
        String uuid = UUID.randomUUID().toString();
        given().baseUri(BASE).when().get("/api/text2txt/The/" + uuid + "/setup").then().statusCode(lessThan(300));
        String txt = "http://abc/def";
        String enc = URLEncoder.encode(txt, StandardCharsets.UTF_8.name());
        Response resp = given().baseUri(BASE).when().get("/api/pat/" + enc);
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPatEndpoint_withDateLikeInput_returns200() throws Exception {
        String uuid = UUID.randomUUID().toString();
        given().baseUri(BASE).when().get("/api/text2txt/The/" + uuid + "/setup").then().statusCode(lessThan(300));
        String txt = "tue07jul";
        String enc = URLEncoder.encode(txt, StandardCharsets.UTF_8.name());
        Response resp = given().baseUri(BASE).when().get("/api/pat/" + enc);
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPatEndpoint_withFloatingPointExponent_returns200() throws Exception {
        String uuid = UUID.randomUUID().toString();
        given().baseUri(BASE).when().get("/api/text2txt/The/" + uuid + "/setup").then().statusCode(lessThan(300));
        String txt = "15.5e+04";
        String enc = URLEncoder.encode(txt, StandardCharsets.UTF_8.name());
        Response resp = given().baseUri(BASE).when().get("/api/pat/" + enc);
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPatEndpoint_withNonMatchingInput_returns200() throws Exception {
        String uuid = UUID.randomUUID().toString();
        given().baseUri(BASE).when().get("/api/text2txt/The/" + uuid + "/setup").then().statusCode(lessThan(300));
        String txt = "thisDoesNotMatchAnyPattern123";
        String enc = URLEncoder.encode(txt, StandardCharsets.UTF_8.name());
        Response resp = given().baseUri(BASE).when().get("/api/pat/" + enc);
        Assert.assertEquals(200, resp.getStatusCode());
    }
}