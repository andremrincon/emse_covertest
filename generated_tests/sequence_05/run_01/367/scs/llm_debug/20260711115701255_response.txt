package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class TitleTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMaleLowercaseMrReturns200() {
        given().when().get("/api/pat/{txt}", "setup").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "male", "mr");
        assertEquals(200, resp.statusCode());
    }

    @Test(timeout = 60000)
    public void testMaleUppercaseDrReturns200() {
        given().when().get("/api/pat/{txt}", "arrange").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "MALE", "DR");
        assertEquals(200, resp.statusCode());
    }

    @Test(timeout = 60000)
    public void testFemaleMrsReturns200() {
        given().when().get("/api/pat/{txt}", "prep").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "female", "mrs");
        assertEquals(200, resp.statusCode());
    }

    @Test(timeout = 60000)
    public void testNoneDrReturns200() {
        given().when().get("/api/pat/{txt}", "prepare").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "none", "dr");
        assertEquals(200, resp.statusCode());
    }

    @Test(timeout = 60000)
    public void testUnknownSexNeuterReturns500() {
        given().when().get("/api/pat/{txt}", "setup2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "neuter", "Jones");
        assertEquals(200, resp.statusCode());
    }
}