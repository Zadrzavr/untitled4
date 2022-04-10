import extensions.SpoonApiTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import java.util.Map;

import static io.restassured.RestAssured.given;



@SpoonApiTest
public class Offset {

    @Test
    public void OffsetData() {
        given()
                .queryParams(Map.of(
                        "offset", 0))
                .get("/recipes/complexSearch")
                .then()
                .statusCode(200)
                .contentType("application/json");
    }
}
