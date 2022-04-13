package dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingListRequest {
    @JsonProperty("item")
    private String item;

    @JsonProperty("parse")
    private boolean parse;

    @JsonProperty("aisle")
    private String aisle;

}
