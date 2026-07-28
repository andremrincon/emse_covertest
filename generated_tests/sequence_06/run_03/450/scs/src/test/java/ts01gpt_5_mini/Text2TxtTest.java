package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class Text2TxtTest {

    private static String base;

    @BeforeClass
    public static void init() {
        String env = System.getenv("BASE_URL");
        base = System.getProperty("baseUrl", env != null ? env : "http://localhost:8080");
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test(timeout = 60000)
    public void testWordTwoReturns2() {
        String marker = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/" + marker).then().statusCode(lessThan(300));
        given().when().get(base + "/api/text2txt/for/x/x").then().statusCode(lessThan(300));
        Response res = given().when().get(base + "/api/text2txt/two/anything/else");
        assertEquals("2", res.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testWordForReturns4() {
        String marker = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/" + marker).then().statusCode(lessThan(300));
        given().when().get(base + "/api/text2txt/two/a/b").then().statusCode(lessThan(300));
        given().when().get(base + "/api/text2txt/by/the/way").then().statusCode(lessThan(300));
        Response res = given().when().get(base + "/api/text2txt/for/x/x");
        assertEquals("4", res.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testWordYouReturnsU() {
        String marker = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/" + marker).then().statusCode(lessThan(300));
        given().when().get(base + "/api/text2txt/and/x/x").then().statusCode(lessThan(300));
        Response res = given().when().get(base + "/api/text2txt/you/ignored/ignored");
        assertEquals("u", res.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testWordAndReturnsN() {
        String marker = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/" + marker).then().statusCode(lessThan(300));
        given().when().get(base + "/api/text2txt/you/x/x").then().statusCode(lessThan(300));
        Response res = given().when().get(base + "/api/text2txt/and/foo/bar");
        assertEquals("n", res.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testWordAreReturnsR() {
        String marker = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/" + marker).then().statusCode(lessThan(300));
        given().when().get(base + "/api/text2txt/see/you/x").then().statusCode(lessThan(300));
        Response res = given().when().get(base + "/api/text2txt/are/some/thing");
        assertEquals("r", res.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCuAndBtwCoveredInArrange() {
        String marker = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/" + marker).then().statusCode(lessThan(300));
        given().when().get(base + "/api/text2txt/are/x/x").then().statusCode(lessThan(300));
        given().when().get(base + "/api/text2txt/by/the/way").then().statusCode(lessThan(300));
        Response res = given().when().get(base + "/api/text2txt/see/you/now");
        assertEquals("cu", res.getBody().asString());
    }
}