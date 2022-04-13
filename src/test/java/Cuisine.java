import extensions.SpoonApiTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import java.util.Map;

import static io.restassured.RestAssured.given;

@SpoonApiTest
public class Cuisine {
    private static RequestSpecification requestSpecification;
    private static ResponseSpecification responseSpecification;

    @BeforeAll
    static void beforeAll() {
        requestSpecification = new RequestSpecBuilder()
                .addQueryParam("offset", 0)
                .addQueryParam("confidence", 0.0F)
                .build();
        responseSpecification = new ResponseSpecBuilder()
                .expectBody("confidence", Matchers.equalTo(0.0F))
                .expectBody("offset", Matchers.equalTo(null))
                .build();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Mediterranean", "Italian"})
    public void cuisineTest(String queryParameter) {
        given()
                .queryParams("query", queryParameter)
                .spec(requestSpecification)
                .post("/recipes/cuisine")
                .then()
                .spec(responseSpecification)
                .statusCode(200);

    }
}
