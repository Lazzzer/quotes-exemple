package ch.heig.icecreams.api.services;

import ch.heig.icecreams.api.repositories.IceCreamRepository;
import org.modelmapper.ModelMapper;
import org.openapitools.model.IceCreamDTOobj;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IceCreamsService {
    private final IceCreamRepository iceCreamRepository;

    private final ModelMapper mapper;

    public IceCreamsService(IceCreamRepository iceCreamRepository, ModelMapper mapper) {
        this.iceCreamRepository = iceCreamRepository;
        this.mapper = mapper;
    }

    public List<IceCreamDTOobj> getIceCreams() {
        var iceCreamsEntities= iceCreamRepository.findAll();
        List<IceCreamDTOobj> iceCreams = new ArrayList<>();
        iceCreamsEntities.forEach(iceCream -> iceCreams.add(mapper.map(iceCream, IceCreamDTOobj.class)));

        return iceCreams;
    }
}
