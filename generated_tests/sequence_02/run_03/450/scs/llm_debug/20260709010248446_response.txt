package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    private String getBaseUrl() {
        String baseUrl = System.getenv("BASE_URL");
        return (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testMaleMr() {
        RestAssured.given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/title/male/mr")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleMs() {
        RestAssured.given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/title/male/ms")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleMrs() {
        RestAssured.given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/title/female/mrs")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleMr() {
        RestAssured.given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/title/female/mr")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneDr() {
        RestAssured.given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/title/none/dr")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNeuterMr() {
        RestAssured.given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/title/neuter/mr")
        .then()
            .statusCode(200);
    }
}