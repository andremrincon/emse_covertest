package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class NotyPevarTest {

    @Before
    public void setUp() {
        String host = System.getenv("APP_HOST");
        if (host == null || host.isEmpty()) {
            host = "localhost";
        }
        String port = System.getenv("APP_PORT");
        if (port == null || port.isEmpty()) {
            port = "8080";
        }
        RestAssured.baseURI = "http://" + host;
        RestAssured.port = Integer.parseInt(port);
        RestAssured.basePath = "";
    }

    @Test(timeout = 60000)
    public void testNoBranchTaken() {
        given()
                .when()
                .get("/api/notypevar/0/a")
                .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testHello7AndCompareToAndGreaterThanBranches() {
        given()
                .when()
                .get("/api/notypevar/7/zzz")
                .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testSumEquals56Branch() {
        given()
                .when()
                .get("/api/notypevar/28/a")
                .then()
                .statusCode(200)
                .body(equalTo("3"));
    }
}