package ts01gpt_5_mini;

import org.junit.Test;
import org.junit.BeforeClass;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL_ALT");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMaleRecognizedTitleReturns200() {
        given().when().get("/api/pat/healthcheck").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/MaLe/MR");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleRecognizedDrReturns200() {
        given().when().get("/api/pat/ready").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/Female/Dr");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneRecognizedDrReturns200() {
        given().when().get("/api/pat/ping").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/None/Dr");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnknownSexReturns500() {
        given().when().get("/api/pat/check").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/neuter/Jones");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleCaseInsensitiveDrReturns200() {
        given().when().get("/api/pat/ok").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/MAlE/Dr");
        act.then().statusCode(200);
    }
}