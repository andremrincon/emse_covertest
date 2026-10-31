package ts01glm_5_2_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class NotyPevarTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i28_branchI0True() {
        given()
            .when()
                .get("/api/notypevar/28/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i7_sZzz_branchesI1TrueI2True() {
        given()
            .when()
                .get("/api/notypevar/7/zzz")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i0_sA_allBranchesFalse() {
        given()
            .when()
                .get("/api/notypevar/0/a")
            .then()
                .statusCode(200);
    }
}