package ts01qwen3_7_plus_2;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CostfunsTest {

    @Test(timeout = 60000)
    public void testSubjectEqualsBaab() {
        given()
            .when()
                .get("/api/costfuns/0/baab")
            .then()
                .statusCode(200)
                .body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testSubjectCompareToGreaterThan() {
        given()
            .when()
                .get("/api/costfuns/0/z")
            .then()
                .statusCode(200)
                .body(equalTo("10"));
    }
}