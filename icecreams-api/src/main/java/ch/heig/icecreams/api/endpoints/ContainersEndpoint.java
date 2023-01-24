package ch.heig.icecreams.api.endpoints;

import ch.heig.icecreams.api.entities.ContainerEntity;
import ch.heig.icecreams.api.repositories.ContainerRepositoy;
import org.openapitools.api.ContainersApi;
import org.openapitools.model.ContainerDTOobj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ContainersEndpoint implements ContainersApi {

    @Autowired
    private ContainerRepositoy containerRepositoy;

    @Override
    public ResponseEntity<List<ContainerDTOobj>> getContainers() {
        List<ContainerEntity> containers = containerRepositoy.findAll();
        List<ContainerDTOobj> containerDtos = new ArrayList<>();
        for (ContainerEntity container : containers) {
            ContainerDTOobj current = new ContainerDTOobj();
            current.setId(container.getId());
            current.setName(container.getName());
            containerDtos.add(current);
        }
        return new ResponseEntity<>(containerDtos,HttpStatus.OK);
    }
}

