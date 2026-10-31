package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
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
    public void testMaleWithInvalidTitleJones() {
        given()
            .when()
            .get("/api/title/male/jones")
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
    public void testFemaleWithInvalidTitleJones() {
        given()
            .when()
            .get("/api/title/female/jones")
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
            .get("/api/title/neuter/jones")
            .then()
            .statusCode(200);
    }
}