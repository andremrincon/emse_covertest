package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    @Before
    public void setUp() {
        String host = System.getenv("APP_HOST");
        String port = System.getenv("APP_PORT");
        if (host == null) host = "localhost";
        if (port == null) port = "8080";
        RestAssured.baseURI = "http://" + host + ":" + port;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given()
            .when()
                .get("/api/ordered4/apple/mango/zebra/peach")
            .then()
                .statusCode(200)
                .body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given()
            .when()
                .get("/api/ordered4/zebra/peach/apple/mango")
            .then()
                .statusCode(200)
                .body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnorderedShortStrings() {
        given()
            .when()
                .get("/api/ordered4/cat/dog/bat/fox")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedEqualStrings() {
        given()
            .when()
                .get("/api/ordered4/apple/apple/apple/apple")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedBoundaryLength4() {
        given()
            .when()
                .get("/api/ordered4/abcd/mango/zebra/peach")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedBoundaryLength7() {
        given()
            .when()
                .get("/api/ordered4/apples/mangos/zebraaa/peachy")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }
}