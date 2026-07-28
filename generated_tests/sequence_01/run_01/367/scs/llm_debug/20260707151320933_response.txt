package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base", System.getenv("API_BASE"));
        if (base == null || base.isEmpty()) {
            RestAssured.baseURI = "http://localhost:8080";
        } else {
            RestAssured.baseURI = base;
        }
    }

    @Test(timeout = 60000)
    public void testSubjectTwoProducesOK() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/two/anything/else");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectFourProducesOK() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/four/x/y");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectYouProducesOK() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/you/are/here");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectArePreventsSeeYouBranchProducesOK() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/are/you/now");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectSeeYouProducesOK() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/see/you/now");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectByTheWayProducesOK() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/by/the/way");
        resp.then().statusCode(200);
    }
}