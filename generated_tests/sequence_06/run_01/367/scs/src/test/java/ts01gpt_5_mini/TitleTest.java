package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class TitleTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl");
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
    public void testMaleRecognizedTitleReturnsOne() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "male", "mr").then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testFemaleRecognizedTitleReturnsZero() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "female", "ms").then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testNoneRecognizedTitleReturnsTwo() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "none", "dr").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testMaleUnrecognizedTitleReturnsMinusOne() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "male", "mrs").then().body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testFemaleUnrecognizedTitleReturnsMinusOne() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "female", "mr").then().body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testNoneUnrecognizedTitleReturnsMinusOne() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "none", "unknown").then().body(equalTo("-1"));
    }
}