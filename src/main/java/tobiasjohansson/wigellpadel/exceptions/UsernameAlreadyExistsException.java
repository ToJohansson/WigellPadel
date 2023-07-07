package tobiasjohansson.wigellpadel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityExistsException;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UsernameAlreadyExistsException extends EntityExistsException {

    private static final long serialVersionUID = 1L;
    private String type;

    public UsernameAlreadyExistsException() {
    }

    public UsernameAlreadyExistsException(String type) {
        super(String.format("Username: %s, already exists", type));
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
