package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class ResponseEntityTest {

    @BeforeClass
    public static void setUp() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetMessageFromV1NameNotFound() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/ThisNameDoesNotExist12345");
        act.then().body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testGetStatusFromV1NameNotFound() {
        given().when().get("/v1/alpha/GB").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/AnotherMissingCountry_XYZ");
        act.then().body("status", equalTo(404));
    }
}