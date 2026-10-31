package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url");
        }
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTwoReturns2() {
        given().when().get("/api/text2txt/for/anything/else").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/and/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/two/x/y").then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testForReturns4() {
        given().when().get("/api/text2txt/two/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/you/a/b").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/for/x/y").then().assertThat().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testYouReturnsU() {
        given().when().get("/api/text2txt/and/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/for/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/you/x/y").then().assertThat().body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testAreReturnsR() {
        given().when().get("/api/text2txt/see/you/z").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/by/the/way").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/are/x/y").then().assertThat().body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu() {
        given().when().get("/api/text2txt/are/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/and/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/see/you/z").then().assertThat().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsBtw() {
        given().when().get("/api/text2txt/see/you/z").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/for/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/by/the/way").then().assertThat().body(equalTo("btw"));
    }
}