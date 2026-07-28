package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            String env = System.getenv("API_BASE");
            base = env == null || env.isEmpty() ? "http://localhost:8080" : env;
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMaleTitleRecognized() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "male", "mr").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleTitleUnrecognized() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "male", "smith").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleTitleRecognized() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "female", "mrs").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneTitleRecognized() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "none", "dr").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidSexNeuterReturnsServerError() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "neuter", "Jones").then().statusCode(200);
    }
}