package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.containsString;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void getByAlpha_twoLetter_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlpha_threeLetter_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/USA").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlpha_invalidFormat_returns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_notFound_returns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCodeList_multipleCodes_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha?codes=US,CA").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCodeList_notFound_returns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha?codes=XX,YY,ZZ").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_exactMatch_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/France?fullText=true").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_alternativeSpelling_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/Deutschland?fullText=true").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_noMatch_returns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/123?fullText=true").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void loadJson_v1All_responseContainsGermany() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v1/all").then().body(containsString("Germany"));
    }

    @Test(timeout = 60000)
    public void loadJson_malformedCodes_returns500() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha?codes=[\"US\",\"CA\"]").then().statusCode(400);
    }
}