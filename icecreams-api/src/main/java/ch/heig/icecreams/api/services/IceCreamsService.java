package ch.heig.icecreams.api.services;

import ch.heig.icecreams.api.entities.IceCreamEntity;
import ch.heig.icecreams.api.exceptions.IceCreamNotFoundException;
import ch.heig.icecreams.api.repositories.IceCreamRepository;
import org.modelmapper.ModelMapper;
import org.openapitools.model.IceCreamDTOid;
import org.openapitools.model.IceCreamDTOobj;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class IceCreamsService {
    private final IceCreamRepository iceCreamRepository;
    private final ModelMapper mapper;

    public IceCreamsService(IceCreamRepository iceCreamRepository, ModelMapper mapper) {
        this.iceCreamRepository = iceCreamRepository;
        this.mapper = mapper;
    }

    public List<IceCreamDTOobj> getIceCreams(Float price) {
        var iceCreamEntities = price != null ? iceCreamRepository.findByPrice(price) : iceCreamRepository.findAll();
        return iceCreamEntities.stream()
                .map(iceCreamEntity -> mapper.map(iceCreamEntity, IceCreamDTOobj.class))
                .toList();
    }

    public IceCreamDTOobj getIceCream(int id) {
        var iceCreamEntity = iceCreamRepository.findById(id).orElseThrow(() -> new IceCreamNotFoundException(id));
        return mapper.map(iceCreamEntity, IceCreamDTOobj.class);
    }

    public Integer addIceCream(IceCreamDTOid iceCream) {
        var iceCreamEntity = mapper.map(iceCream, IceCreamEntity.class);
        return saveIceCream(iceCreamEntity).getId();
    }

    public Optional<Integer> updateCreateIceCream(IceCreamDTOid iceCream) {
        var iceCreamEntity = mapper.map(iceCream, IceCreamEntity.class);
        var updatedCreatedIceCream = saveIceCream(iceCreamEntity);
        // Si l'ID de la glace n'a pas changé, il s'agit d'une mise à jour issue d'un PUT
        return updatedCreatedIceCream.getId() == iceCreamEntity.getId() ? Optional.empty() : Optional.of(updatedCreatedIceCream.getId());
    }

    public void deleteIceCream(int id) {
        var iceCreamEntity = iceCreamRepository.findById(id).orElseThrow(() -> new IceCreamNotFoundException(id));
        iceCreamRepository.deleteById(iceCreamEntity.getId());
    }

    private IceCreamEntity saveIceCream(IceCreamEntity iceCreamEntity) {
        IceCreamEntity savedIceCream;
        try {
            savedIceCream = iceCreamRepository.save(iceCreamEntity);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Cannot save entity", e);
        }
        return savedIceCream;
    }
}
