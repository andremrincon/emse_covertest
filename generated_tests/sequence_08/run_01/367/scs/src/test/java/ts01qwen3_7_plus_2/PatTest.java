package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PatTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testPatLenLessThanOrEqualTo2() {
        given()
            .when()
            .get("/api/pat/abc/ab")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundNoPatRev() {
        given()
            .when()
            .get("/api/pat/ABABxx/ABAB")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevFoundNoPat() {
        given()
            .when()
            .get("/api/pat/xxBABA/ABAB")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatAndPatRevAdjacent() {
        given()
            .when()
            .get("/api/pat/ABABBABA/ABAB")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevAndPatAdjacent() {
        given()
            .when()
            .get("/api/pat/BABAABAB/ABAB")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatAndPatRevNotAdjacent() {
        given()
            .when()
            .get("/api/pat/ABABxxBABA/ABAB")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevAndPatNotAdjacent() {
        given()
            .when()
            .get("/api/pat/BABAxxABAB/ABAB")
            .then()
            .statusCode(200);
    }
}