package tobiasjohansson.wigellpadel.exceptions;

import jakarta.persistence.EntityExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceExsistException extends EntityExistsException {
    private static final long serialVersionUID = 1L;
    private String name, field;
    private Object value;

    public ResourceExsistException() {
    }

    public ResourceExsistException(String name, String field,  Object value) {
        super(String.format("%s is not available. %s : '%s'", name, field, value));
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
