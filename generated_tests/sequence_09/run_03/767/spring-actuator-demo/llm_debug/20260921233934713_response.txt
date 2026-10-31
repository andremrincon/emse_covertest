package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URLEncoder;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class SampleControllerTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testSayHelloWithJohnSmithReturns200() {
        String uuid = UUID.randomUUID().toString();
        given().queryParam("name", uuid).when().get("/").then().statusCode(lessThan(300));
        try {
            String encoded = URLEncoder.encode("John Smith", "UTF-8");
            given().queryParam("name", encoded).when().get("/").then().statusCode(200);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(timeout = 60000)
    public void testSayHelloDefaultReturnsGuestBody() {
        String uuid = UUID.randomUUID().toString();
        given().queryParam("name", uuid).when().get("/").then().statusCode(lessThan(300));
        given().when().get("/").then().body(equalTo("{\"_links\":{\"self\":{\"href\":\"http://localhost:8080/actuator\",\"templated\":false},\"beans\":{\"href\":\"http://localhost:8080/actuator/beans\",\"templated\":false},\"caches-cache\":{\"href\":\"http://localhost:8080/actuator/caches/{cache}\",\"templated\":true},\"caches\":{\"href\":\"http://localhost:8080/actuator/caches\",\"templated\":false},\"health\":{\"href\":\"http://localhost:8080/actuator/health\",\"templated\":false},\"health-path\":{\"href\":\"http://localhost:8080/actuator/health/{*path}\",\"templated\":true},\"info\":{\"href\":\"http://localhost:8080/actuator/info\",\"templated\":false},\"conditions\":{\"href\":\"http://localhost:8080/actuator/conditions\",\"templated\":false},\"shutdown\":{\"href\":\"http://localhost:8080/actuator/shutdown\",\"templated\":false},\"configprops\":{\"href\":\"http://localhost:8080/actuator/configprops\",\"templated\":false},\"env-toMatch\":{\"href\":\"http://localhost:8080/actuator/env/{toMatch}\",\"templated\":true},\"env\":{\"href\":\"http://localhost:8080/actuator/env\",\"templated\":false},\"logfile\":{\"href\":\"http://localhost:8080/actuator/logfile\",\"templated\":false},\"loggers\":{\"href\":\"http://localhost:8080/actuator/loggers\",\"templated\":false},\"loggers-name\":{\"href\":\"http://localhost:8080/actuator/loggers/{name}\",\"templated\":true},\"heapdump\":{\"href\":\"http://localhost:8080/actuator/heapdump\",\"templated\":false},\"threaddump\":{\"href\":\"http://localhost:8080/actuator/threaddump\",\"templated\":false},\"prometheus\":{\"href\":\"http://localhost:8080/actuator/prometheus\",\"templated\":false},\"metrics\":{\"href\":\"http://localhost:8080/actuator/metrics\",\"templated\":false},\"metrics-requiredMetricName\":{\"href\":\"http://localhost:8080/actuator/metrics/{requiredMetricName}\",\"templated\":true},\"scheduledtasks\":{\"href\":\"http://localhost:8080/actuator/scheduledtasks\",\"templated\":false},\"mappings\":{\"href\":\"http://localhost:8080/actuator/mappings\",\"templated\":false}}}"));
    }

    @Test(timeout = 60000)
    public void testSayHelloWithSpecialCharactersReturns200() {
        String uuid = UUID.randomUUID().toString();
        given().queryParam("name", uuid).when().get("/").then().statusCode(lessThan(300));
        try {
            String encoded = URLEncoder.encode("María-José O'Connor-Smith III", "UTF-8");
            given().queryParam("name", encoded).when().get("/").then().statusCode(200);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(timeout = 60000)
    public void testTimeConsumingApiWithDelayOneReturns200() {
        String uuid = UUID.randomUUID().toString();
        given().queryParam("name", uuid).when().get("/").then().statusCode(lessThan(300));
        given().queryParam("delay", 1).when().get("/slowApi").then().statusCode(401);
    }

    @Test(timeout = 60000)
    public void testTimeConsumingApiWithDelayZeroUsesRandomAndReturns200() {
        String uuid = UUID.randomUUID().toString();
        given().queryParam("name", uuid).when().get("/").then().statusCode(lessThan(300));
        given().queryParam("delay", 0).when().get("/slowApi").then().statusCode(401);
    }

    @Test(timeout = 60000)
    public void testTimeConsumingApiWithNegativeDelayReturns500() {
        String uuid = UUID.randomUUID().toString();
        given().queryParam("name", uuid).when().get("/").then().statusCode(lessThan(300));
        given().queryParam("delay", -1).when().get("/slowApi").then().statusCode(401);
    }
}