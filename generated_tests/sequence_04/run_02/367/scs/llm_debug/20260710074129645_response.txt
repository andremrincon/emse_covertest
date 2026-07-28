package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class RegexTest {

    @Test(timeout = 60000)
    public void testSubjectMatchesDate() {
        given()
            .when()
            .get("http://localhost:8080/api/pat/mon01jan")
            .then()
            .statusCode(200);
    }
}