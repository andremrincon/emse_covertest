package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TitleTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testMaleMr() {
        given()
            .pathParam("sex", "male")
            .pathParam("title", "mr")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testMaleUnknown() {
        given()
            .pathParam("sex", "male")
            .pathParam("title", "unknown")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testFemaleMrs() {
        given()
            .pathParam("sex", "female")
            .pathParam("title", "mrs")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testFemaleUnknown() {
        given()
            .pathParam("sex", "female")
            .pathParam("title", "unknown")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testNoneDr() {
        given()
            .pathParam("sex", "none")
            .pathParam("title", "dr")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testOtherMr() {
        given()
            .pathParam("sex", "other")
            .pathParam("title", "mr")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .body(equalTo("-1"));
    }
}