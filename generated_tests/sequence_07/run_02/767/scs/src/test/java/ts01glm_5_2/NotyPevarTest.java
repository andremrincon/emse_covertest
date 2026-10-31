package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {

    @Before
    public void setUp() {
        String host = System.getenv("API_HOST");
        if (host == null) {
            host = "localhost";
        }
        String port = System.getenv("API_PORT");
        if (port == null) {
            port = "8080";
        }
        RestAssured.baseURI = "http://" + host;
        RestAssured.port = Integer.parseInt(port);
    }

    @Test(timeout = 60000)
    public void testBranchI0True_XPlusYEquals56() {
        given()
            .when()
                .get("/api/notypevar/28/a")
            .then()
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBranchI1True_Hello7() {
        given()
            .when()
                .get("/api/notypevar/7/a")
            .then()
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBranchI2True_I3False() {
        given()
            .when()
                .get("/api/notypevar/0/zzzzz")
            .then()
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testAllBranchesFalse() {
        given()
            .when()
                .get("/api/notypevar/3/a")
            .then()
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testBranchI2True_I3True() {
        given()
            .when()
                .get("/api/notypevar/6/z")
            .then()
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testBranchI3Boundary_YEqualsX() {
        given()
            .when()
                .get("/api/notypevar/5/a")
            .then()
                .body(equalTo("0"));
    }
}