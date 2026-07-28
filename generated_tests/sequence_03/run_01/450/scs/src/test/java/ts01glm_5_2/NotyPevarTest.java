package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class NotyPevarTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testNotyPevarBranchXPlusYEquals56() {
        given()
            .when()
                .get("/api/notypevar/28/hello")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevarBranchHello7AndCompareTo() {
        given()
            .when()
                .get("/api/notypevar/7/zzz")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevarAllBranchesFalse() {
        given()
            .when()
                .get("/api/notypevar/3/aaa")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }
}