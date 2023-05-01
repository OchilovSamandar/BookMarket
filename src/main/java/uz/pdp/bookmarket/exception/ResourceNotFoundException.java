package uz.pdp.bookmarket.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException{
    private final String resourceName;//role

    private final String resourceField;//name

    private final Object object;//admin, user,1,500

    public ResourceNotFoundException(String resourceName, String resourceField, String object) {
        this.resourceName = resourceName;
        this.resourceField = resourceField;
        this.object = object;
    }
}
