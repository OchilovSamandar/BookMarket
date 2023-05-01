package uz.pdp.bookmarket.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class BookDto {
    @NotNull
    private String name;

    @NotNull
    private String author;

    @NotNull
    private String description;

    @NotNull
    private double price;

}
