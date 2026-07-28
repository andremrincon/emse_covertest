package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetMessageWhenCapitalNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String unique = "123";
        Response act = given().when().get("/v1/capital/" + unique);
        ResponseEntity entity = act.as(ResponseEntity.class);
        Assert.assertEquals("Not Found", entity.getMessage());
    }

    @Test(timeout = 60000)
    public void testGetStatusWhenCapitalNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String unique = "123-" + UUID.randomUUID().toString();
        Response act = given().when().get("/v1/capital/" + unique);
        ResponseEntity entity = act.as(ResponseEntity.class);
        Assert.assertEquals(404, entity.getStatus());
    }

    public static class ResponseEntity {
        private String message;
        private int status;

        public ResponseEntity() {
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}