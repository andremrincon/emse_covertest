package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class NotyPevarTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testNotyPevar_allBranchesFalse_returnsZero() {
        given()
            .when()
                .get("/api/notypevar/3/aaa")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_compareToBranchTrue_returnsTwo() {
        given()
            .when()
                .get("/api/notypevar/3/zzz")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_yGreaterThanFive_returnsThree() {
        given()
            .when()
                .get("/api/notypevar/28/hello")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }
}