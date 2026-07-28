package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testOrdered4LengthTooShort() {
        given()
            .when()
                .get("/api/ordered4/app/berry/candy/delta")
            .then()
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testOrdered4LengthTooLong() {
        given()
            .when()
                .get("/api/ordered4/appless/berry/candy/delta")
            .then()
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testOrdered4ValidLengthsUnordered() {
        given()
            .when()
                .get("/api/ordered4/apple/apple/apple/apple")
            .then()
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testOrdered4Increasing() {
        given()
            .when()
                .get("/api/ordered4/apple/berry/candy/delta")
            .then()
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testOrdered4Decreasing() {
        given()
            .when()
                .get("/api/ordered4/zebra/yacht/x-ray/apple")
            .then()
                .body(equalTo("unordered"));
    }
}