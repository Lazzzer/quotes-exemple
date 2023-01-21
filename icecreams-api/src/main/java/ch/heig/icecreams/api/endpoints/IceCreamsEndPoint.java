package ch.heig.icecreams.api.endpoints;

import ch.heig.icecreams.api.entities.OriginEntity;
import ch.heig.icecreams.api.repositories.OriginRepository;
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

    @Autowired
    private OriginRepository originRepository;

    @Override
    public ResponseEntity<List<IceCream>> getIceCreams() {
        List<IceCreamEntity> quoteEntities= iceCreamRepository.findAll();
        List<IceCream> quotes = createIceCreamList(quoteEntities);
        return new ResponseEntity<List<IceCream>>(quotes,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addIceCream(@RequestBody IceCream iceCream) {
        IceCreamEntity iceCreamEntity = new IceCreamEntity();
        iceCreamEntity.setName(iceCream.getName());
        iceCreamEntity.setPrice(iceCream.getPrice());
        iceCreamEntity.setOriginId(iceCream.getOriginId());
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
        //iceCreamEntity.setOrigin(iceCream.getOrigin());
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

    @Override
    public ResponseEntity<List<IceCream>> getIceCreamsFromOrigin(Integer id){
        return iceCreamFromOrigin(id, iceCreamRepository, originRepository);
    }

    public static ResponseEntity<List<IceCream>> iceCreamFromOrigin(Integer id, IceCreamRepository icr, OriginRepository or){
        Optional<OriginEntity> opt = or.findById(id);
        if (opt.isEmpty()){
            throw new IceCreamNotFoundException(id);
        }

        List<IceCreamEntity> iceCreamEntities = icr.findByOriginId(id);
        List<IceCream> iceCreams = createIceCreamList(iceCreamEntities);
        return new ResponseEntity<List<IceCream>>(iceCreams,HttpStatus.OK);
    }

    public static List<IceCream> createIceCreamList(List<IceCreamEntity> iceCreamEntities){
        List<IceCream> iceCreams = new ArrayList<>();
        for (IceCreamEntity iceCreamEntity : iceCreamEntities) {
            IceCream iceCream = new IceCream();
            iceCream.setId(iceCreamEntity.getId());
            iceCream.setName(iceCreamEntity.getName());
            iceCream.setPrice(iceCreamEntity.getPrice());
            iceCream.setOriginId(iceCreamEntity.getOriginId());
            iceCreams.add(iceCream);
        }
        return iceCreams;
    }
}
