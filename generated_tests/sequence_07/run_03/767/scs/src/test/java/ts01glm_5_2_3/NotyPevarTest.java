package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class NotyPevarTest {

    private static final String BASE_URL = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testSubjectI28HighString() {
        given()
            .when()
                .get("/api/notypevar/28/zzz")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectI7LowString() {
        given()
            .when()
                .get("/api/notypevar/7/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectI0LowString() {
        given()
            .when()
                .get("/api/notypevar/0/a")
            .then()
                .statusCode(200);
    }
}