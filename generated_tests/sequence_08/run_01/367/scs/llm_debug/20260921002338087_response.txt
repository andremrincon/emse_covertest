package ts01qwen3_7_plus_2;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class RegexTest {

    @Test(timeout = 60000)
    public void testSubjectReturnsDate() {
        given().when().get("http://localhost:8080/api/pat/mon15aug").then().statusCode(200);
    }
}