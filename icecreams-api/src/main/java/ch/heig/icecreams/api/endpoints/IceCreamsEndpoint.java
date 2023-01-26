package ch.heig.icecreams.api.endpoints;

import ch.heig.icecreams.api.services.IceCreamsService;
import org.openapitools.api.IceCreamsApi;
import org.openapitools.model.IceCreamDTOid;
import org.openapitools.model.IceCreamDTOobj;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class IceCreamsEndpoint implements IceCreamsApi {
    private final IceCreamsService iceCreamService;

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
        var id = iceCreamService.addIceCream(iceCream);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).build();
    }


    @Override
    public ResponseEntity<Void> updateCreateIceCream(@RequestBody IceCreamDTOid iceCream){
        var createdId = iceCreamService.updateCreateIceCream(iceCream);
        if (createdId.isPresent()) {
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdId.get())
                    .toUri();
            return ResponseEntity.created(uri).build();
        }
        return ResponseEntity.ok().build();
    }
    @Override
    public ResponseEntity<Void> deleteIceCream(Integer id) {
        iceCreamService.deleteIceCream(id);
        return ResponseEntity.noContent().build();
    }
}
