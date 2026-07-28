package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class TitleTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void maleWithKnownTitleReturnsOne() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "male", "dr");
        resp.then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void maleWithUnknownTitleReturnsMinusOne() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "male", "mrs");
        resp.then().assertThat().body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void femaleWithKnownTitleReturnsZero() {
        given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "female", "ms");
        resp.then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void femaleWithUnknownTitleReturnsMinusOne() {
        given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "female", "mr");
        resp.then().assertThat().body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void noneWithKnownHonorificReturnsTwo() {
        given().when().get("/api/costfuns/{i}/{s}", 1, "algorithm").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "none", "prof");
        resp.then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void noneWithNonHonorificReturnsMinusOne() {
        given().when().get("/api/costfuns/{i}/{s}", 1, "algorithm").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "none", "mr");
        resp.then().assertThat().body(equalTo("-1"));
    }
}