package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class SampleControllerTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testSayHelloWithNameJohnSmith() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().param("name", "John Smith").when().get("/");
        act.then().assertThat().body(equalTo("Hello John Smith!!"));
    }

    @Test(timeout = 60000)
    public void testSayHelloDefaultGuest() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().when().get("/");
        act.then().assertThat().body(equalTo("Hello Guest!!"));
    }

    @Test(timeout = 60000)
    public void testSayHelloWithSpecialName() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().param("name", "María-José O'Connor-Smith III").when().get("/");
        act.then().assertThat().body(equalTo("Hello María-José O'Connor-Smith III!!"));
    }

    @Test(timeout = 60000)
    public void testTimeConsumingApiWithDelayZeroReturns200() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().param("delay", "0").when().get("/slowApi");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTimeConsumingApiWithDelayOneReturns200() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().param("delay", "1").when().get("/slowApi");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTimeConsumingApiWithNegativeDelayReturns500() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().param("delay", "-1").when().get("/slowApi");
        act.then().statusCode(200);
    }
}