package com.jw.rs.it;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.given;

/**
 * Establish a Web service call given mock resource endpoint.
 *
 * @author JWang
 * @since Jan 02, 2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { MockApp.class })
@IntegrationTest(value = "server.port=8080")
@WebAppConfiguration
public class MockResourceIT {

    @Value(value = "${server.port:8080}")
    private int port;

    @Before
    public void setup() throws Exception {
        RestAssured.port = port;
    }

    /**
     * Test method {@link com.jw.rs.ws.MockResource#greetings(String)}.
     *
     * @throws Exception
     */
    @Test
    public void testGreetings() throws Exception {
        given().body("").with().contentType(ContentType.JSON).then().expect().statusCode(HttpStatus.SC_OK)
                .and().body(Matchers.any(String.class)).when().get("/api/mock/greetings/jw");
    }

}
