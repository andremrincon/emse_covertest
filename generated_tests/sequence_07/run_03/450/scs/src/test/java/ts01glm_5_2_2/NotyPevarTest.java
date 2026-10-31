package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class NotyPevarTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i28_sZzzz_coversI0TrueI2TrueI3True() {
        given()
                .when()
                .get("/api/notypevar/28/zzzz")
                .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i7_sA_coversI1TrueI2FalseI3True() {
        given()
                .when()
                .get("/api/notypevar/7/a")
                .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i0_sA_coversAllFalseBranches() {
        given()
                .when()
                .get("/api/notypevar/0/a")
                .then()
                .statusCode(200)
                .body(equalTo("0"));
    }
}