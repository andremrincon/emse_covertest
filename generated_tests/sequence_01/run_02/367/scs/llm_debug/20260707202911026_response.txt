package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TitleTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testTitleMaleValid() {
        String[] titles = {"mr", "dr", "sir", "rev", "rthon", "prof"};
        for (String title : titles) {
            given()
                .pathParam("sex", "male")
                .pathParam("title", title)
            .when()
                .get("/api/title/{sex}/{title}")
            .then()
                .statusCode(200)
                .body(equalTo("1"));
        }
    }

    @Test(timeout = 60000)
    public void testTitleMaleInvalid() {
        given()
            .pathParam("sex", "male")
            .pathParam("title", "invalid")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200)
            .body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testTitleFemaleValid() {
        String[] titles = {"mrs", "miss", "ms", "dr", "lady", "rev", "rthon", "prof"};
        for (String title : titles) {
            given()
                .pathParam("sex", "female")
                .pathParam("title", title)
            .when()
                .get("/api/title/{sex}/{title}")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
        }
    }

    @Test(timeout = 60000)
    public void testTitleFemaleInvalid() {
        given()
            .pathParam("sex", "female")
            .pathParam("title", "invalid")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200)
            .body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testTitleNoneValid() {
        String[] titles = {"dr", "rev", "rthon", "prof"};
        for (String title : titles) {
            given()
                .pathParam("sex", "none")
                .pathParam("title", title)
            .when()
                .get("/api/title/{sex}/{title}")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
        }
    }

    @Test(timeout = 60000)
    public void testTitleNoneInvalid() {
        given()
            .pathParam("sex", "none")
            .pathParam("title", "invalid")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200)
            .body(equalTo("-1"));
    }
}