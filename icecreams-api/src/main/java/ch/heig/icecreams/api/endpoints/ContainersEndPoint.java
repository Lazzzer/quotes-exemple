package ch.heig.icecreams.api.endpoints;

import ch.heig.icecreams.api.entities.ContainerEntity;
import ch.heig.icecreams.api.entities.OriginEntity;
import ch.heig.icecreams.api.repositories.ContainerRepositoy;
import ch.heig.icecreams.api.repositories.IceCreamRepository;
import ch.heig.icecreams.api.repositories.OriginRepository;
import org.openapitools.api.ContainersApi;
import org.openapitools.api.IceCreamsApi;
import ch.heig.icecreams.api.exceptions.IceCreamNotFoundException;
import org.openapitools.api.OriginsApi;
import org.openapitools.model.Container;
import org.openapitools.model.IceCream;
import ch.heig.icecreams.api.entities.IceCreamEntity;
import org.openapitools.model.Origin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ch.heig.icecreams.api.endpoints.IceCreamsEndPoint.iceCreamFromOrigin;

@RestController
public class ContainersEndPoint implements ContainersApi {

    @Autowired
    private ContainerRepositoy containerRepositoy;

    @Override
    public ResponseEntity<List<Container>> getContainers() {
        List<ContainerEntity> containers = containerRepositoy.findAll();
        List<Container> quotes = new ArrayList<>();
        for (ContainerEntity container : containers) {
            Container current = new Container();
            current.setId(container.getId());
            current.setName(container.getName());
            quotes.add(current);
        }
        return new ResponseEntity<List<Container>>(quotes,HttpStatus.OK);
    }

}

