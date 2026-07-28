package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testV1NameNotFoundMessage() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/123").then().body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testV1NameNotFoundStatus() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/123").then().body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testV1PostMethodNotAllowedMessage() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().contentType(ContentType.JSON).when().post("/v1").then().body("message", equalTo("Method Not Allowed"));
    }

    @Test(timeout = 60000)
    public void testContributePostAcceptedStatus() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        String id = UUID.randomUUID().toString();
        given().contentType(ContentType.JSON).body("{\"amount\":1,\"currency\":\"USD\",\"token\":\"tok_"+id+"\"}").when().post("/contribute").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testV1AlphaBadRequestMessage() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/123").then().body("message", equalTo("Not Found"));
    }
}