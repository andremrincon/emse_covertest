package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_v1_name_notFound_returns_status_field_404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String unique = "NoSuchCountry-" + UUID.randomUUID().toString();
        Response act = given().when().get("/v1/name/{name}", unique);
        act.then().body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void test_v1_capital_notFound_returns_message_NotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String unique = "NoSuchCapital-" + UUID.randomUUID().toString();
        Response act = given().when().get("/v1/capital/{capital}", unique);
        act.then().body("message", equalTo("Not Found"));
    }
}