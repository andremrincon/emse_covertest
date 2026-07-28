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
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void getNameNotFound_invokesGetMessage() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/123");
        act.then().body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void getNameNotFound_invokesGetStatus() {
        given().when().get("/v1/alpha/GB").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/123");
        act.then().body("status", equalTo(404));
    }
}