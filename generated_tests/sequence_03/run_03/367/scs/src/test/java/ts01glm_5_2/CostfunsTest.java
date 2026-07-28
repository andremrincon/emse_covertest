package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CostfunsTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testSubjectIEqualsFiveSEqualsBaab() {
        given()
            .pathParam("i", 5)
            .pathParam("s", "baab")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectILessThanNeg445() {
        given()
            .pathParam("i", -445)
            .pathParam("s", "b")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIEqualsNeg333SEqualsAbabba() {
        given()
            .pathParam("i", -333)
            .pathParam("s", "ababba")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIGreaterThan666() {
        given()
            .pathParam("i", 667)
            .pathParam("s", "a")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIEqualsNeg4() {
        given()
            .pathParam("i", -4)
            .pathParam("s", "a")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectIEquals555() {
        given()
            .pathParam("i", 555)
            .pathParam("s", "a")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }
}