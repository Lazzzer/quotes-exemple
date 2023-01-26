package ch.heig.icecreams.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class IceCreamBadRequestException extends RuntimeException {
    public IceCreamBadRequestException() {
        super("Bad request");
    }
}
