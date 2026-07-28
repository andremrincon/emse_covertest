package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import org.junit.Assert;

public class NotyPevarTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNotyPevar_iGreaterThan5_resultsInThree() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 7, "a");
        Assert.assertEquals("3", act.asString());
    }

    @Test(timeout = 60000)
    public void testNotyPevar_iEqualsFive_and_sLexGreater_resultsInTwo() {
        given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 5, "zoo");
        Assert.assertEquals("2", act.asString());
    }

    @Test(timeout = 60000)
    public void testNotyPevar_iTwentyEight_triggersInitialSumBranch() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "15.5", "4.5").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", 28, "a").then().statusCode(200);
    }
}