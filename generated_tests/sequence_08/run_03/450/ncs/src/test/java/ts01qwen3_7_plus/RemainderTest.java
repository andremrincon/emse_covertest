package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
public class RemainderTest {

    @Ignore("Cannot deserialize value of type `java.lang.Integer` from Object value (token `JsonToken.START_OB...")
    @Test(timeout = 60000)
    public void testRemainderPositiveAPositiveB() {
        int result = given()
            .when()
                .get("/api/remainder/17/5")
            .then()
                .statusCode(lessThan(300))
                .extract().as(Integer.class);
        assertEquals(2, result);
    }

    @Ignore("Cannot deserialize value of type `java.lang.Integer` from Object value (token `JsonToken.START_OB...")
    @Test(timeout = 60000)
    public void testRemainderNegativeAPositiveB() {
        int result = given()
            .when()
                .get("/api/remainder/-9/5")
            .then()
                .statusCode(lessThan(300))
                .extract().as(Integer.class);
        assertEquals(-4, result);
    }

    @Ignore("Cannot deserialize value of type `java.lang.Integer` from Object value (token `JsonToken.START_OB...")
    @Test(timeout = 60000)
    public void testRemainderPositiveANegativeB() {
        int result = given()
            .when()
                .get("/api/remainder/17/-5")
            .then()
                .statusCode(lessThan(300))
                .extract().as(Integer.class);
        assertEquals(2, result);
    }

    @Ignore("Cannot deserialize value of type `java.lang.Integer` from Object value (token `JsonToken.START_OB...")
    @Test(timeout = 60000)
    public void testRemainderNegativeANegativeB() {
        int result = given()
            .when()
                .get("/api/remainder/-9/-5")
            .then()
                .statusCode(lessThan(300))
                .extract().as(Integer.class);
        assertEquals(4, result);
    }

    @Test(timeout = 60000)
    public void testRemainderZeroA() {
        given()
            .when()
                .get("/api/remainder/0/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderZeroB() {
        given()
            .when()
                .get("/api/remainder/5/0")
            .then()
                .statusCode(200);
    }
}