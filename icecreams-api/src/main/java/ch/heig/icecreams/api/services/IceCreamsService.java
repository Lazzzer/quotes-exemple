package ch.heig.icecreams.api.services;

import ch.heig.icecreams.api.entities.ContainerEntity;
import ch.heig.icecreams.api.entities.IceCreamEntity;
import ch.heig.icecreams.api.entities.OriginEntity;
import ch.heig.icecreams.api.exceptions.IceCreamNotFoundException;
import ch.heig.icecreams.api.repositories.IceCreamRepository;
import org.modelmapper.ModelMapper;
import org.openapitools.model.IceCreamDTOid;
import org.openapitools.model.IceCreamDTOidOptional;
import org.openapitools.model.IceCreamDTOobj;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return iceCream.getId() != null && updatedCreatedIceCream.getId() == iceCreamEntity.getId()
                ? Optional.empty()
                : Optional.of(updatedCreatedIceCream.getId());
    }

    public void updateIceCream(IceCreamDTOidOptional iceCream) {
        var foundIceCreamEntity = iceCreamRepository.findById(iceCream.getId()).orElseThrow(() -> new IceCreamNotFoundException(iceCream.getId()));

        if (iceCream.getName() != null)
            foundIceCreamEntity.setName(iceCream.getName());
        if (iceCream.getPrice() != null)
            foundIceCreamEntity.setPrice(iceCream.getPrice());
        if (iceCream.getOriginId() != null) {
            var originEntity = new OriginEntity();
            originEntity.setId(iceCream.getOriginId());
            foundIceCreamEntity.setOrigin(originEntity);
        }
        if (iceCream.getContainerIds() != null) {
            var containerEntities = iceCream.getContainerIds().stream()
                    .map(containerId -> {
                        var containerEntity = new ContainerEntity();
                        containerEntity.setId(containerId);
                        return containerEntity;
                    })
                    .collect(Collectors.toList());
            foundIceCreamEntity.setContainers(containerEntities);
        }
        saveIceCream(foundIceCreamEntity);
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
