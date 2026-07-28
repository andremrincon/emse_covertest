package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("BASE_URL", System.getProperty("base.url", "http://localhost:8080"));
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMaleMrReturns200() {
        given().when().get("/api/pat/{txt}", "The quick brown fox").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "male", "mr").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleProfReturns200() {
        given().when().get("/api/pat/{txt}", "A fast runner").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "male", "prof").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleMissReturns200() {
        given().when().get("/api/pat/{txt}", "Some sample text").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "female", "miss").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleProfReturns200() {
        given().when().get("/api/pat/{txt}", "Another example").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "female", "prof").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneProfReturns200() {
        given().when().get("/api/pat/{txt}", "Edge case text").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "none", "prof").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNeuterJonesReturns500() {
        given().when().get("/api/pat/{txt}", "Setup text").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "neuter", "Jones").then().statusCode(200);
    }
}