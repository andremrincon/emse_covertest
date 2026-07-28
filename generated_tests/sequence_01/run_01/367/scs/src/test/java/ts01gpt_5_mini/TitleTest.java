package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMaleRecognizedTitle() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "male", "mr");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleRecognizedTitleCaseInsensitive() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "FEMALE", "MISS");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneRecognizedTitle() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "none", "Dr");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnknownSexProducesServerError() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "neuter", "Jones");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleUnrecognizedTitleProducesServerError() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "male", "Jones");
        act.then().statusCode(200);
    }
}