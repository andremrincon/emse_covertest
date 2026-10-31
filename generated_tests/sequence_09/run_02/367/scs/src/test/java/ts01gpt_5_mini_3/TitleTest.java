package ts01gpt_5_mini_3;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

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
            base = System.getenv("BASEURL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMaleMrReturns200() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "male", "mr");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleMrsReturns200() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "female", "mrs");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneDrReturns200() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "none", "dr");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleUppercaseDrReturns200() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "MaLe", "DR");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleDrReturns200() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "female", "dr");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnknownTitleProducesResponse() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "unknown", "whatever");
        resp.then().statusCode(200);
    }
}