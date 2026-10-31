package ts01qwen3_7_plus_2;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TitleTest {

    private String getBaseUrl() {
        String envUrl = System.getenv("BASE_URL");
        return (envUrl != null && !envUrl.isEmpty()) ? envUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testMaleMr() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("sex", "male")
            .pathParam("title", "mr")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testMaleMrs() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("sex", "male")
            .pathParam("title", "mrs")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200)
            .body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testFemaleMrs() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("sex", "female")
            .pathParam("title", "mrs")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testFemaleMr() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("sex", "female")
            .pathParam("title", "mr")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200)
            .body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testNoneDr() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("sex", "none")
            .pathParam("title", "dr")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200)
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testNoneMr() {
        given()
            .baseUri(getBaseUrl())
            .pathParam("sex", "none")
            .pathParam("title", "mr")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200)
            .body(equalTo("-1"));
    }
}