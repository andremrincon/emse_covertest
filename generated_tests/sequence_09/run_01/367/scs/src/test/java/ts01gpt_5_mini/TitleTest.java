package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class TitleTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base",
                System.getenv().containsKey("API_BASE") ? System.getenv("API_BASE") : "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMaleDrReturnsOne() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "male", "dr").then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testFemaleDrReturnsZero() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "female", "dr").then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testNoneDrReturnsTwo() {
        given().when().get("/api/pat/{txt}", "banana").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "none", "dr").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testMaleNonMatchingTitleReturnsMinusOne() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "male", "queen").then().body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testFemaleMrsReturnsZero() {
        given().when().get("/api/pat/{txt}", "ABABCABAB").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "female", "mrs").then().body(equalTo("0"));
    }
}