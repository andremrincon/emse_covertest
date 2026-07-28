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
        String base = System.getProperty("api.base");
        if (base == null) base = System.getenv("API_BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMaleTitleMatch() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "male", "Mr").then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testMaleTitleNoMatch() {
        given().when().get("/api/pat/{txt}", "The quick brown").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "male", "Champion").then().assertThat().body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testFemaleTitleMatch() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "female", "Mrs").then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testFemaleTitleNoMatch() {
        given().when().get("/api/pat/{txt}", "Sample text").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "female", "Explorer").then().assertThat().body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testNoneTitleMatch() {
        given().when().get("/api/pat/{txt}", "Health check").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "none", "Dr").then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testSexCaseInsensitiveDrFemale() {
        given().when().get("/api/pat/{txt}", "Ping").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "FeMaLe", "DR").then().assertThat().body(equalTo("0"));
    }
}