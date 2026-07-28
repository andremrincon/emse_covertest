package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TitleTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null) base = System.getenv("API_BASE");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMaleValidTitleReturnsOne() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "male", "mr").then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testFemaleValidTitleReturnsZero() {
        given().when().get("/api/pat/{txt}", "setup").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "female", "miss").then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testNoneValidTitleReturnsTwo() {
        given().when().get("/api/pat/{txt}", "ready").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "none", "prof").then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testUnknownSexReturnsMinusOne() {
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "alien", "mr").then().assertThat().body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testMaleInvalidTitleReturnsMinusOne() {
        given().when().get("/api/pat/{txt}", "init").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "male", "queen").then().assertThat().body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testCaseInsensitivityHandlesUppercaseInputs() {
        given().when().get("/api/pat/{txt}", "check").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "MaLe", "DR").then().assertThat().body(equalTo("1"));
    }
}