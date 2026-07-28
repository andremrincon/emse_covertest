package ts01qwen3_7_plus;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class LanguageTest {

    @Test(timeout = 60000)
    public void testLanguageSettersViaAlphaEndpoint() {
        Response response = given()
            .when()
            .get("/v1/alpha/US");

        response.then().statusCode(404);
    }
}