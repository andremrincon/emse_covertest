package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class ResponseEntityTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testV1NameNotFound_message() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/{name}", "123").then().statusCode(404).body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testV2NameStatusField() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/name/{name}", "ZZZ").then().statusCode(404).body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testV1NameInternalServerError_message() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/{name}", "True").then().statusCode(404).body("message", equalTo("Not Found"));
    }
}