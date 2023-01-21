package ch.heig.icecreams.api.endpoints;

import ch.heig.icecreams.api.entities.OriginEntity;
import ch.heig.icecreams.api.repositories.IceCreamRepository;
import ch.heig.icecreams.api.repositories.OriginRepository;
import org.openapitools.api.OriginsApi;
import org.openapitools.model.IceCream;
import org.openapitools.model.Origin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static ch.heig.icecreams.api.endpoints.IceCreamsEndPoint.iceCreamFromOrigin;

@RestController
public class OriginsEndPoint implements OriginsApi {

    @Autowired
    private OriginRepository originRepository;

    @Autowired
    private IceCreamRepository iceCreamRepository;

    @Override
    public ResponseEntity<List<Origin>> getOrigins() {
        List<OriginEntity> origins = originRepository.findAll();
        List<Origin> quotes = new ArrayList<>();
        for (OriginEntity origin : origins) {
            Origin current = new Origin();
            current.setId(origin.getId());
            current.setName(origin.getName());
            quotes.add(current);
        }
        return new ResponseEntity<>(quotes,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<IceCream>> manyIceCreamsFromOneOrigin(Integer id) {
        return iceCreamFromOrigin(id, iceCreamRepository, originRepository);
    }

}
