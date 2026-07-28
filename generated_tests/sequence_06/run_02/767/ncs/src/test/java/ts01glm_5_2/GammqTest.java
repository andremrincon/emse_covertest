package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

public class GammqTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    private static RequestSpecification givenReq() {
        return RestAssured.given();
    }

    @Test(timeout = 60000)
    public void testGserNormalPath_xLessThanAPlusOne() {
        givenReq()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/5.5/2.3")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfNormalPath_xGreaterThanOrEqualAPlusOne() {
        givenReq()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/0.001/1000.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExeInvalidA_ThrowsException() {
        givenReq()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/-1.0/2.3")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExeInvalidX_ThrowsException() {
        givenReq()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/5.5/-1.0")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGserXEqualsZero() {
        givenReq()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/5.5/0.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidParameterType_NonNumericA() {
        givenReq()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/abc/2.3")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGserSmallA_xLessThanAPlusOne() {
        givenReq()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/0.001/0.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfLargeX() {
        givenReq()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/5.5/1000.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserXEqualsAPlusOne_Boundary() {
        givenReq()
            .accept(ContentType.JSON)
        .when()
            .get("/api/gammq/1.0/2.0")
        .then()
            .statusCode(200);
    }
}