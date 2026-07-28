package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

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
    public void testNotyPevar_i28_zzz_coversI0TrueI2TrueI3True() {
        given()
            .when()
                .get("/api/notypevar/28/zzz")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i7_aaa_coversI1TrueI2FalseI3True() {
        given()
            .when()
                .get("/api/notypevar/7/aaa")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i3_aaa_coversI0FalseI1FalseI2FalseI3False() {
        given()
            .when()
                .get("/api/notypevar/3/aaa")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }
}