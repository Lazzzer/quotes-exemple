package ch.heig.icecreams.api.endpoints;

import org.openapitools.api.IceCreamsApi;
import ch.heig.icecreams.api.exceptions.IceCreamNotFoundException;
import org.openapitools.model.IceCreamDTOid;
import org.openapitools.model.IceCreamDTOobj;
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
public class IceCreamsEndpoint implements IceCreamsApi {

    @Autowired
    private IceCreamRepository iceCreamRepository;

    @Override
    public ResponseEntity<List<IceCreamDTOobj>> getIceCreams() {
        List<IceCreamEntity> quoteEntities= iceCreamRepository.findAll();
        List<IceCreamDTOobj> icecreams = createIceCreamList(quoteEntities);
        return new ResponseEntity<>(icecreams,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<IceCreamDTOobj> getIceCream(Integer id) {
        Optional<IceCreamEntity> opt = iceCreamRepository.findById(id);
        if (opt.isPresent()) {
            IceCreamEntity iceCreamEntity = opt.get();
            IceCreamDTOobj iceCream = new IceCreamDTOobj();
            iceCream.setId(iceCreamEntity.getId());
            iceCream.setName(iceCreamEntity.getName());
            iceCream.setPrice(iceCreamEntity.getPrice());
            return new ResponseEntity<>(iceCream, HttpStatus.OK);
        } else {
            throw new IceCreamNotFoundException(id);
        }
    }

    @Override
    public ResponseEntity<Void> addIceCream(@RequestBody IceCreamDTOid iceCream) {
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

    public static List<IceCreamDTOobj> createIceCreamList(List<IceCreamEntity> iceCreamEntities){
        List<IceCreamDTOobj> iceCreams = new ArrayList<>();
        for (IceCreamEntity iceCreamEntity : iceCreamEntities) {
            IceCreamDTOobj iceCream = new IceCreamDTOobj();
            iceCream.setId(iceCreamEntity.getId());
            iceCream.setName(iceCreamEntity.getName());
            iceCream.setPrice(iceCreamEntity.getPrice());
            iceCreams.add(iceCream);
        }
        return iceCreams;
    }

    @Override
    public ResponseEntity<Void> updateCreateIceCream(@RequestBody IceCreamDTOid iceCream){
        Optional<IceCreamEntity> opt = iceCreamRepository.findById(iceCream.getId());
        if (opt.isEmpty()){
            throw new IceCreamNotFoundException(iceCream.getId());
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
