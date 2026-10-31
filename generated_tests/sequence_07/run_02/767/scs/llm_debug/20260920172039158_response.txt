package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    @Before
    public void setUp() {
        String host = System.getenv().getOrDefault("HOST", "localhost");
        String port = System.getenv().getOrDefault("PORT", "8080");
        RestAssured.baseURI = "http://" + host + ":" + port;
    }

    @Test(timeout = 60000)
    public void testMaleWithValidTitleMr() {
        given()
            .when()
                .get("/api/title/male/mr")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testMaleWithInvalidTitleSmith() {
        given()
            .when()
                .get("/api/title/male/smith")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleWithValidTitleMrs() {
        given()
            .when()
                .get("/api/title/female/mrs")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFemaleWithInvalidTitleSmith() {
        given()
            .when()
                .get("/api/title/female/smith")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneWithValidTitleDr() {
        given()
            .when()
                .get("/api/title/none/dr")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testNeuterSexReturnsError() {
        given()
            .when()
                .get("/api/title/neuter/dr")
            .then()
                .statusCode(200);
    }
}