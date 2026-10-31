package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PatTest {

    @Test(timeout = 60000)
    public void testPatLenLessThanOrEqualTo2() {
        given()
            .when()
                .get("http://localhost:8080/api/pat/abc/ab")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundPatRevNotFound() {
        given()
            .when()
                .get("http://localhost:8080/api/pat/xyzABABxyz/ABAB")
            .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testPatFoundPatRevFoundJGreaterThanIPlusPatLen() {
        given()
            .when()
                .get("http://localhost:8080/api/pat/ABABxyzBABA/ABAB")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatRevFoundPatNotFound() {
        given()
            .when()
                .get("http://localhost:8080/api/pat/xyzBABAxyz/ABAB")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testPatRevFoundPatFoundJEqualsIPlusPatLen() {
        given()
            .when()
                .get("http://localhost:8080/api/pat/BABAABAB/ABAB")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatRevFoundPatFoundJGreaterThanIPlusPatLen() {
        given()
            .when()
                .get("http://localhost:8080/api/pat/BABAxyzABAB/ABAB")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundPatRevFoundJEqualsIPlusPatLen() {
        given()
            .when()
                .get("http://localhost:8080/api/pat/ABABBABA/ABAB")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }
}