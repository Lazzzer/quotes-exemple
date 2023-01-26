package ch.heig.icecreams.api.endpoints;

import ch.heig.icecreams.api.repositories.IceCreamRepository;
import ch.heig.icecreams.api.services.IceCreamsService;
import org.openapitools.api.IceCreamsApi;
import ch.heig.icecreams.api.exceptions.IceCreamNotFoundException;
import org.openapitools.model.IceCreamDTOid;
import org.openapitools.model.IceCreamDTOobj;
import ch.heig.icecreams.api.entities.IceCreamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class IceCreamsEndpoint implements IceCreamsApi {
    private final IceCreamsService iceCreamService;

    @Autowired
    private IceCreamRepository iceCreamRepository;

    public IceCreamsEndpoint(IceCreamsService iceCreamService) {
        this.iceCreamService = iceCreamService;
    }

    @Override
    public ResponseEntity<List<IceCreamDTOobj>> getIceCreams() {
        var iceCreams = iceCreamService.getIceCreams();
        return ResponseEntity.ok(iceCreams);
    }

    @Override
    public ResponseEntity<IceCreamDTOobj> getIceCream(Integer id) {
        var iceCream = iceCreamService.getIceCream(id);
        return ResponseEntity.ok(iceCream);
    }

    @Override
    public ResponseEntity<Void> addIceCream(@RequestBody IceCreamDTOid iceCream) {
        var createdIceCream = iceCreamService.addIceCream(iceCream);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdIceCream.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
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
