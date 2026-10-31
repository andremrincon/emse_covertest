package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class TitleTest {

    private static String BASE;

    @BeforeClass
    public static void setup() {
        String cfg = System.getProperty("api.base");
        if (cfg == null || cfg.isEmpty()) {
            cfg = System.getenv("API_BASE");
        }
        if (cfg == null || cfg.isEmpty()) {
            cfg = "http://localhost:8080";
        }
        BASE = cfg;
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test(timeout = 60000)
    public void testMaleWithMrReturnsOne() {
        given().when().get(BASE + "/api/pat/The%20quick%20brown%20fox%20jumps%20over%20the%20lazy%20dog.").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/title/male/mr");
        resp.then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testFemaleWithMissReturnsZero() {
        given().when().get(BASE + "/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/title/female/miss");
        resp.then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testNoneWithProfReturnsTwo() {
        given().when().get(BASE + "/api/pat/banana").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/title/none/prof");
        resp.then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testUnknownSexReturnsMinusOne() {
        given().when().get(BASE + "/api/pat/ABABCABAB").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/title/other/mr");
        resp.then().assertThat().body(equalTo("-1"));
    }
}