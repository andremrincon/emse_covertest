package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class NotyPevarTest {

    @Before
    public void setUp() {
        String host = System.getProperty("server.host", "localhost");
        String port = System.getProperty("server.port", "8080");
        RestAssured.baseURI = "http://" + host;
        RestAssured.port = Integer.parseInt(port);
        RestAssured.basePath = "/";
    }

    @Test(timeout = 60000)
    public void testSubjectBranchXPlusYEquals56() {
        given()
            .when()
                .get("/api/notypevar/28/hello")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testSubjectBranchXsPlusYEqualsHello7() {
        given()
            .when()
                .get("/api/notypevar/7/hello")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testSubjectBranchXsCompareToSLessThanZero() {
        given()
            .when()
                .get("/api/notypevar/3/world")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testSubjectAllFalseBranches() {
        given()
            .when()
                .get("/api/notypevar/0/hello")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSubjectInvalidIntegerReturns400() {
        given()
            .when()
                .get("/api/notypevar/abc/hello")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testSubjectLargeIntegerReturns400() {
        given()
            .when()
                .get("/api/notypevar/2147483648/hello")
            .then()
                .statusCode(400);
    }
}