package uz.pdp.bookmarket.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull(message = "firstname bo'sh bo'lishi mumkin emas")
    private String firstname;

    @NotNull(message = "lastname bo'sh bo'lishi mumkin emas")
    private String lastname;

    @NotNull(message = "username bo'sh bo'lishi mumkin emas")
    private String username;

    @NotNull(message = "password bo'sh bo'lishi mumkin emas")
    private String password;

    @NotNull(message = "role bo'sh bo'lishi mumkin emas")
    private Integer roleId;
}
