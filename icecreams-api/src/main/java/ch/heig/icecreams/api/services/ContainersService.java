package ch.heig.icecreams.api.services;

import ch.heig.icecreams.api.repositories.ContainerRepositoy;
import org.modelmapper.ModelMapper;
import org.openapitools.model.ContainerDTOobj;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContainersService {
    public final ContainerRepositoy containerRepositoy;
    private final ModelMapper mapper;

    public ContainersService(ContainerRepositoy containerRepositoy, ModelMapper mapper) {
        this.containerRepositoy = containerRepositoy;
        this.mapper = mapper;
    }

    public List<ContainerDTOobj> getContainers() {
        var containersEntities = containerRepositoy.findAll();
        return containersEntities.stream()
                .map(containerEntity -> mapper.map(containerEntity, ContainerDTOobj.class))
                .toList();
    }

}
