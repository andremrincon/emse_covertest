package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    static {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testFisher_m1_n1_x05() {
        given()
            .when()
            .get("/api/fisher/1/1/0.5")
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/api/fisher/1/1/0.5")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_m3_n3_x05() {
        given()
            .when()
            .get("/api/fisher/3/3/0.5")
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/api/fisher/3/3/0.5")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_m1_n4_x05() {
        given()
            .when()
            .get("/api/fisher/1/4/0.5")
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/api/fisher/1/4/0.5")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_m4_n1_x05() {
        given()
            .when()
            .get("/api/fisher/4/1/0.5")
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/api/fisher/4/1/0.5")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_m4_n2_x05() {
        given()
            .when()
            .get("/api/fisher/4/2/0.5")
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/api/fisher/4/2/0.5")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_m100_n1_x10000() {
        given()
            .when()
            .get("/api/fisher/100/1/10000.0")
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/api/fisher/100/1/10000.0")
            .then()
            .statusCode(200);
    }
}