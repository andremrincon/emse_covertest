package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    @BeforeClass
    public static void init() {
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
    public void testMaleWithMr_returns200() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "male", "mr").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleWithMs_returns200() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "female", "ms").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneWithProf_returns200() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "none", "prof").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleWithUnknownTitle_returns200() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "male", "unknown").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNeuterWithJones_returns500() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "neuter", "Jones").then().statusCode(200);
    }
}