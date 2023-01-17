package ch.heig.icecreams.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IceCreamNotFoundException extends RuntimeException {
    public IceCreamNotFoundException(Integer id) {
        super("Glace " + id + " non trouvée");
    }
}
