package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ResponseEntityTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetNameNotFoundReturnsResponseEntityWithStatusAndMessage() {
        given()
                .pathParam("name", "123")
                .when()
                .get("/v1/name/{name}")
                .then()
                .statusCode(404)
                .body("status", equalTo(404))
                .body("message", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetCapitalNotFoundReturnsResponseEntityWithStatusAndMessage() {
        given()
                .pathParam("capital", "123")
                .when()
                .get("/v1/capital/{capital}")
                .then()
                .statusCode(404)
                .body("status", equalTo(404))
                .body("message", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetRegionNotFoundReturnsResponseEntityWithStatusAndMessage() {
        given()
                .pathParam("region", "123")
                .when()
                .get("/v1/region/{region}")
                .then()
                .statusCode(404)
                .body("status", equalTo(404))
                .body("message", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetCallingCodeNotFoundReturnsResponseEntityWithStatusAndMessage() {
        given()
                .pathParam("callingcode", "abc")
                .when()
                .get("/v1/callingcode/{callingcode}")
                .then()
                .statusCode(404)
                .body("status", equalTo(404))
                .body("message", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetV2RegionalBlocNotFoundReturnsResponseEntityWithStatusAndMessage() {
        given()
                .pathParam("regionalbloc", "123")
                .when()
                .get("/v2/regionalbloc/{regionalbloc}")
                .then()
                .statusCode(404)
                .body("status", equalTo(404))
                .body("message", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetV2NameNotFoundReturnsResponseEntityWithStatusAndMessage() {
        given()
                .pathParam("name", "123")
                .when()
                .get("/v2/name/{name}")
                .then()
                .statusCode(404)
                .body("status", equalTo(404))
                .body("message", notNullValue());
    }
}