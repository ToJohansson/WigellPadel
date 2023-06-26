package tobiasjohansson.wigellpadel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends NoSuchElementException {

    private static final long serialVersionUID = 1L;
    private String name, field;
    private Object value;

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String name, String field,  Object value) {
        super(String.format("%s not found with %s : '%s'", name, field, value));
        this.field = field;
        this.name = name;
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

}