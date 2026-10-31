package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
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
    public void testMaleTitleMatchesKnownMaleTitles() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "male", "mr").then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testFemaleTitleMatchesKnownFemaleTitles() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "female", "mrs").then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testNoneSexMatchesNeutralTitles() {
        given().when().get("/api/pat/{txt}", "ABABCABAB").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "none", "dr").then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testUnmatchedTitleReturnsNegativeOne() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "male", "queen").then().assertThat().body(equalTo("-1"));
    }
}