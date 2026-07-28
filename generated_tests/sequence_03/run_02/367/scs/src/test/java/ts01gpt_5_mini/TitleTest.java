package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    private final String base = System.getenv().getOrDefault("BASE_URL", System.getProperty("BASE_URL", "http://localhost:8080"));

    @Test(timeout = 60000)
    public void testMaleWithKnownPersonalTitleReturnsOk() {
        given().when().get(base + "/api/pat/healthcheck").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/title/male/mr");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleWithUnknownTitleReturnsOk() {
        given().when().get(base + "/api/pat/prepare").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/title/male/smith");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleWithKnownPersonalTitleReturnsOk() {
        given().when().get(base + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/title/female/ms");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleWithUnknownTitleReturnsOk() {
        given().when().get(base + "/api/text2txt/A/B/C").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/title/female/doe");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneWithNeutralPersonalTitleReturnsOk() {
        given().when().get(base + "/api/pat/sample").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/title/none/dr");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneWithUnknownTitleReturnsOk() {
        given().when().get(base + "/api/pat/check").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/title/none/doe");
        resp.then().statusCode(200);
    }
}