package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;
import org.junit.BeforeClass;
import org.junit.Test;

public class NotyPevarTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testNotyPevarDefaultPath() {
        given()
            .accept("**")
        .when()
            .get("/api/notypevar/3/z")
        .then()
            .body(equalTo(""));
    }

    @Test(timeout = 60000)
    public void testNotyPevarGreaterThanBranch() {
        given()
            .accept("*/*")
        .when()
            .get("/api/notypevar/28/a")
        .then()
            .body(equalTo("3"));
    }
}