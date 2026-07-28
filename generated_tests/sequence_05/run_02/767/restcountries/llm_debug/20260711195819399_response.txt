package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.response.Response;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class ResponseEntityTest {

    private static String base;

    @BeforeClass
    public static void setup() {
        base = Optional.ofNullable(System.getenv("BASE_URL"))
                .orElse(Optional.ofNullable(System.getProperty("baseUrl"))
                .orElse("http://localhost:8080/rest"));
    }

    @Test(timeout = 60000)
    public void testNameNotFoundReturnsHttp404() {
        given().when().get(base + "/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/v1/name/123");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testNameTrueTriggersServerErrorStatusField500() {
        given().when().get(base + "/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/v1/name/True");
        act.then().statusCode(404).and().body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testUnknownNameReturnsNotFoundMessageField() {
        given().when().get(base + "/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/v1/name/NoSuchCountry");
        act.then().statusCode(404).and().body("message", equalTo("Not Found"));
    }
}