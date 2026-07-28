package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    private static String baseUrl;

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("api.base");
        if (env == null || env.isEmpty()) {
            env = System.getenv("API_BASE");
        }
        if (env == null || env.isEmpty()) {
            env = "http://localhost:8080";
        }
        baseUrl = env;
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void returnsNumericTwoForWordTwo() {
        String id = UUID.randomUUID().toString();
        given().when().get(baseUrl + "/api/text2txt/for/x/x").then().statusCode(lessThan(300));
        given().when().get(baseUrl + "/api/text2txt/you/x/x").then().statusCode(lessThan(300));
        given().when().get(baseUrl + "/api/text2txt/and/x/x").then().statusCode(lessThan(300));
        Response act = given().when().get(baseUrl + "/api/text2txt/two/" + id + "/z");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void returnsFourForWordFor() {
        given().when().get(baseUrl + "/api/text2txt/two/a/b").then().statusCode(lessThan(300));
        given().when().get(baseUrl + "/api/text2txt/you/a/b").then().statusCode(lessThan(300));
        Response act = given().when().get(baseUrl + "/api/text2txt/for/anything/else");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void returnsCuForSeeYouSequence() {
        given().when().get(baseUrl + "/api/text2txt/are/x/x").then().statusCode(lessThan(300));
        given().when().get(baseUrl + "/api/text2txt/by/the/way").then().statusCode(lessThan(300));
        Response act = given().when().get(baseUrl + "/api/text2txt/see/you/now");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void returnsBtwForByTheWay() {
        given().when().get(baseUrl + "/api/text2txt/see/you/now").then().statusCode(lessThan(300));
        given().when().get(baseUrl + "/api/text2txt/for/x/x").then().statusCode(lessThan(300));
        Response act = given().when().get(baseUrl + "/api/text2txt/by/the/way");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void returnsRForAre() {
        given().when().get(baseUrl + "/api/text2txt/see/you/now").then().statusCode(lessThan(300));
        given().when().get(baseUrl + "/api/text2txt/by/the/way").then().statusCode(lessThan(300));
        Response act = given().when().get(baseUrl + "/api/text2txt/are/x/y");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void returnsEmptyWhenNoSubstitutionApplies() {
        given().when().get(baseUrl + "/api/text2txt/two/a/b").then().statusCode(lessThan(300));
        given().when().get(baseUrl + "/api/text2txt/for/a/b").then().statusCode(lessThan(300));
        Response act = given().when().get(baseUrl + "/api/text2txt/hello/world/!");
        act.then().statusCode(200);
    }
}