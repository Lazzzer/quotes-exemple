package ch.heig.icecreams.api.endpoints;

import ch.heig.icecreams.api.entities.OriginEntity;
import ch.heig.icecreams.api.repositories.OriginRepository;
import org.openapitools.api.IceCreamsApi;
import ch.heig.icecreams.api.exceptions.IceCreamNotFoundException;
import org.openapitools.api.OriginsApi;
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

@RestController
public class OriginsEndPoint implements OriginsApi {

    @Autowired
    private OriginRepository originRepository;

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
        return new ResponseEntity<List<Origin>>(quotes,HttpStatus.OK);
    }

    /*@Override
    public ResponseEntity<Void> addIceCream(@RequestBody IceCream iceCream) {
        IceCreamEntity iceCreamEntity = new IceCreamEntity();
        iceCreamEntity.setName(iceCream.getName());
        iceCreamEntity.setPrice(iceCream.getPrice());
        IceCreamEntity iceCreamAdded = iceCreamRepository.save(iceCreamEntity);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(iceCreamAdded.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<IceCream> getIceCream(Integer id) {
        Optional<IceCreamEntity> opt = iceCreamRepository.findById(id);
        if (opt.isPresent()) {
            IceCreamEntity iceCreamEntity = opt.get();
            IceCream iceCream = new IceCream();
            iceCream.setId(iceCreamEntity.getId());
            iceCream.setName(iceCreamEntity.getName());
            iceCream.setPrice(iceCreamEntity.getPrice());
            return new ResponseEntity<IceCream>(iceCream, HttpStatus.OK);
        } else {
//            return ResponseEntity.notFound().build();
            throw new IceCreamNotFoundException(id);
        }
    }*/

}
