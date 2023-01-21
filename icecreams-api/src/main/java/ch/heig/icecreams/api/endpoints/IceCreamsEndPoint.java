package ch.heig.icecreams.api.endpoints;

import org.openapitools.api.IceCreamsApi;
import ch.heig.icecreams.api.exceptions.IceCreamNotFoundException;
import org.openapitools.model.IceCream;
import ch.heig.icecreams.api.entities.IceCreamEntity;
import ch.heig.icecreams.api.repositories.IceCreamRepository;
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
public class IceCreamsEndPoint implements IceCreamsApi {

    @Autowired
    private IceCreamRepository iceCreamRepository;

    @Override
    public ResponseEntity<List<IceCream>> getIceCreams() {
        List<IceCreamEntity> quoteEntities= iceCreamRepository.findAll();
        List<IceCream> quotes  = new ArrayList<>();
        for (IceCreamEntity iceCreamEntity : quoteEntities) {
            IceCream quote = new IceCream();
            quote.setId(iceCreamEntity.getId());
            quote.setName(iceCreamEntity.getName());
            quote.setPrice(iceCreamEntity.getPrice());
            quotes.add(quote);
        }
        return new ResponseEntity<List<IceCream>>(quotes,HttpStatus.OK);
    }

    @Override
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
    }

    @Override
    public ResponseEntity<Void> modifyIceCream(Integer id, @RequestBody IceCream iceCream){
        Optional<IceCreamEntity> opt = iceCreamRepository.findById(id);
        if (opt.isEmpty()){
            throw new IceCreamNotFoundException(id);
        }
        IceCreamEntity iceCreamEntity = opt.get();
        iceCreamEntity.setName(iceCream.getName());
        iceCreamEntity.setPrice(iceCream.getPrice());
        iceCreamRepository.save(iceCreamEntity);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteIceCream(Integer id) {
        Optional<IceCreamEntity> opt = iceCreamRepository.findById(id);
        if (opt.isEmpty()){
            throw new IceCreamNotFoundException(id);
        }
        iceCreamRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
