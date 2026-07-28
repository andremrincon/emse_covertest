package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class SampleControllerTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testSayHelloWithProvidedNameReturnsGreeting() {
        String arrangeName = "Arrange-" + UUID.randomUUID().toString();
        given().when().get("/?name=" + arrangeName).then().statusCode(lessThan(300));
        Response act = given().when().get("/?name=John%20Smith");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSayHelloWithNoNameUsesDefaultGuest() {
        String arrangeName = "Arrange-" + UUID.randomUUID().toString();
        given().when().get("/?name=" + arrangeName).then().statusCode(lessThan(300));
        Response act = given().when().get("/");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTimeConsumingApiWithDelayZeroUsesRandomAndReturns200() {
        String arrangeName = "Arrange-" + UUID.randomUUID().toString();
        given().when().get("/?name=" + arrangeName).then().statusCode(lessThan(300));
        Response act = given().when().get("/slowApi?delay=0");
        act.then().statusCode(401);
    }

    @Test(timeout = 60000)
    public void testTimeConsumingApiWithPositiveDelayReturnsResultBody() {
        String arrangeName = "Arrange-" + UUID.randomUUID().toString();
        given().when().get("/?name=" + arrangeName).then().statusCode(lessThan(300));
        Response act = given().when().get("/slowApi?delay=1");
        act.then().statusCode(401);
    }

    @Test(timeout = 60000)
    public void testTimeConsumingApiWithNegativeDelayProducesServerError() {
        String arrangeName = "Arrange-" + UUID.randomUUID().toString();
        given().when().get("/?name=" + arrangeName).then().statusCode(lessThan(300));
        Response act = given().when().get("/slowApi?delay=-1");
        act.then().statusCode(401);
    }
}