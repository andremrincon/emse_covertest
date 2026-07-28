package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NcsRestTest {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void test01_bessj_validN_validX_returns200() {
        given()
                .accept("application/json")
        .when()
                .get("/api/bessj/3/2.5")
        .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void test02_bessj_validN_verySmallX_returns200() {
        given()
                .accept("application/json")
        .when()
                .get("/api/bessj/3/1e-10")
        .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void test03_bessj_nEquals2_returns400() {
        given()
                .accept("application/json")
        .when()
                .get("/api/bessj/2/2.5")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void test04_fisher_validParams_returns200() {
        given()
                .accept("application/json")
        .when()
                .get("/api/fisher/10/5/0.75")
        .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void test05_fisher_xEqualsZero_returns200() {
        given()
                .accept("application/json")
        .when()
                .get("/api/fisher/1/1/0.0")
        .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void test06_fisher_mGreaterThan1000_returns400() {
        given()
                .accept("application/json")
        .when()
                .get("/api/fisher/1001/5/0.75")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void test07_fisher_nGreaterThan1000_returns400() {
        given()
                .accept("application/json")
        .when()
                .get("/api/fisher/10/1001/0.75")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void test08_remainder_validPositiveParams_returns200() {
        given()
                .accept("application/json")
        .when()
                .get("/api/remainder/17/5")
        .then()
                .statusCode(200)
                .body("resultAsInt", notNullValue());
    }

    @Test(timeout = 60000)
    public void test09_remainder_negativeA_returns200() {
        given()
                .accept("application/json")
        .when()
                .get("/api/remainder/-9/4")
        .then()
                .statusCode(200)
                .body("resultAsInt", notNullValue());
    }
}