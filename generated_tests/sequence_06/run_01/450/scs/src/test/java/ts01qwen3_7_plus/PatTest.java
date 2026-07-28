package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class PatTest {

    private static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testSubjectFoundPatrevAfterPat() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/pat/ABCBACBA/ABC")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectFoundPatAfterPatrev() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/pat/CBAABC/ABC")
        .then()
            .statusCode(200);
    }
}