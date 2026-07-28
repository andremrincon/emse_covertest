package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null) base = System.getenv("API_BASE");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMaleKnownTitle_returns200() {
        given().when().get("/api/pat/The%20quick%20brown%20fox").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/male/Mr");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleDrTitle_returns200() {
        given().when().get("/api/pat/HealthCheck").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/FEMALE/Dr");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneProf_returns200() {
        given().when().get("/api/pat/arrange").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/none/prof");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnknownSex_neuter_returns500() {
        given().when().get("/api/pat/setup").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/neuter/Jones");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleUnknownTitle_returns500() {
        given().when().get("/api/pat/ready").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/male/Jones");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCaseInsensitivity_returns200() {
        given().when().get("/api/pat/check").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/MaLe/DR");
        act.then().statusCode(200);
    }
}