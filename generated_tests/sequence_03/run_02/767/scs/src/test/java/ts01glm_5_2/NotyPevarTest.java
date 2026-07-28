package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class NotyPevarTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i28_sGreaterThanHello() {
        given()
            .when()
                .get("/api/notypevar/28/zzz")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i7_sLessThanHello() {
        given()
            .when()
                .get("/api/notypevar/7/a")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i3_sEqualsHello() {
        given()
            .when()
                .get("/api/notypevar/3/hello")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }
}