package ch.heig.icecreams.api.endpoints;

import ch.heig.icecreams.api.services.ContainersService;
import org.openapitools.api.ContainersApi;
import org.openapitools.model.ContainerDTOobj;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContainersEndpoint implements ContainersApi {

    private final ContainersService containersService;
    public ContainersEndpoint(ContainersService containersService) {
        this.containersService = containersService;
    }

    @Override
    public ResponseEntity<List<ContainerDTOobj>> getContainers() {
        var containers = containersService.getContainers();
        return ResponseEntity.ok(containers);
    }
}

