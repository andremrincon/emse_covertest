package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class NotFoundExceptionMapperTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080/rest";
        }
    }

    @Test(timeout = 60000)
    public void toResponse_returns404_whenNonExistentPathIsRequested() {
        given()
            .when()
                .get("/v1/nonexistentpath/" + java.util.UUID.randomUUID().toString())
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void toResponse_returns404_whenRootLevelUnknownPathIsRequested() {
        given()
            .when()
                .get("/unknownendpoint" + java.util.UUID.randomUUID().toString())
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void toResponse_returns404_whenDeepNonExistentPathIsRequested() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(lessThan(300));

        Response response = given()
            .when()
                .get("/v3/nonexistent/" + java.util.UUID.randomUUID().toString());

        assertEquals(404, response.getStatusCode());
    }
}