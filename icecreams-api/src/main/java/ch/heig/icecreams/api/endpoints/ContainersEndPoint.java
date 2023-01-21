package ch.heig.icecreams.api.endpoints;

import ch.heig.icecreams.api.entities.ContainerEntity;
import ch.heig.icecreams.api.repositories.ContainerRepositoy;
import org.openapitools.api.ContainersApi;
import org.openapitools.model.Container;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
        return new ResponseEntity<>(quotes,HttpStatus.OK);
    }

}

