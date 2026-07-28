package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Ordered4Test {

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given()
            .baseUri("http://localhost:8080")
            .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "bbbbb", "ddddd", "ccccc")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given()
            .baseUri("http://localhost:8080")
            .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}", "ddddd", "ccccc", "aaaaa", "bbbbb")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedInnerIfFalseAtThirdCondition() {
        given()
            .baseUri("http://localhost:8080")
            .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}", "ccccc", "bbbbb", "eeeee", "ddddd")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedInnerIfFalseAtSecondCondition() {
        given()
            .baseUri("http://localhost:8080")
            .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "ddddd", "ccccc", "bbbbb")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedInnerElseIfFalseAtThirdCondition() {
        given()
            .baseUri("http://localhost:8080")
            .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}", "ddddd", "ccccc", "eeeee", "bbbbb")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedOuterIfFalse() {
        given()
            .baseUri("http://localhost:8080")
            .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}", "aaa", "bbbbb", "ddddd", "ccccc")
            .then()
            .statusCode(200);
    }
}