package ch.heig.icecreams.api.endpoints;

import ch.heig.icecreams.api.services.OriginsService;
import org.openapitools.api.OriginsApi;
import org.openapitools.model.OriginDTOobj;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OriginsEndpoint implements OriginsApi {

    private final OriginsService originsService;
    public OriginsEndpoint(OriginsService originsService) {
        this.originsService = originsService;
    }

    @Override
    public ResponseEntity<List<OriginDTOobj>> getOrigins() {
        var origins = originsService.getOrigins();
        return ResponseEntity.ok(origins);
    }
}
