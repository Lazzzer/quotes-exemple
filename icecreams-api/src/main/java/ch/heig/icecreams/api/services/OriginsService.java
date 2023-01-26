package ch.heig.icecreams.api.services;

import ch.heig.icecreams.api.repositories.OriginRepository;
import org.modelmapper.ModelMapper;
import org.openapitools.model.OriginDTOobj;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OriginsService {

    public final OriginRepository originRepository;
    private final ModelMapper mapper;

    public OriginsService(OriginRepository originRepository, ModelMapper mapper) {
        this.originRepository = originRepository;
        this.mapper = mapper;
    }

    public List<OriginDTOobj> getOrigins() {
        var originsEntities = originRepository.findAll();
        return originsEntities.stream()
                .map(originEntity -> mapper.map(originEntity, OriginDTOobj.class))
                .toList();
    }
}
