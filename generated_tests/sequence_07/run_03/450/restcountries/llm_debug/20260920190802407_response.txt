package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import org.junit.BeforeClass;
import org.junit.Test;

public class CountryServiceTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testGetByLanguage2CharCodeV1() {
        given()
            .when()
                .get("/v1/lang/es")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testGetByLanguage3CharCodeV1() {
        given()
            .when()
                .get("/v1/lang/spa")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage2CharCodeV2() {
        given()
            .when()
                .get("/v2/lang/es")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testGetByLanguage3CharCodeV2() {
        given()
            .when()
                .get("/v2/lang/spa")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocWithMatch() {
        given()
            .when()
                .get("/v2/regionalbloc/EU")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocNoMatch() {
        given()
            .when()
                .get("/v2/regionalbloc/123")
            .then()
                .statusCode(404);
    }
}