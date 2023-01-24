package ch.heig.icecreams.api.endpoints;

import ch.heig.icecreams.api.entities.OriginEntity;
import ch.heig.icecreams.api.repositories.OriginRepository;
import org.openapitools.api.OriginsApi;
import org.openapitools.model.OriginDTOobj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class OriginsEndpoint implements OriginsApi {

    @Autowired
    private OriginRepository originRepository;

    @Override
    public ResponseEntity<List<OriginDTOobj>> getOrigins() {
        List<OriginEntity> origins = originRepository.findAll();
        List<OriginDTOobj> originDtos = new ArrayList<>();
        for (OriginEntity origin : origins) {
            OriginDTOobj current = new OriginDTOobj();
            current.setId(origin.getId());
            current.setName(origin.getName());
            originDtos.add(current);
        }
        return new ResponseEntity<>(originDtos,HttpStatus.OK);
    }
}
