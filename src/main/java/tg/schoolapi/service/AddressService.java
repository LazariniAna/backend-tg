package tg.schoolapi.service;

import tg.schoolapi.model.dto.AddressDTO;
import tg.schoolapi.model.entity.AddressEntity;
import tg.schoolapi.model.repository.AddressRepository;
import tg.schoolapi.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    public AddressService() {
    }

    public AddressEntity insert(AddressDTO addressDTO) {
        AddressEntity addressEntity = this.converteAddressDTO(addressDTO);
        return addressRepository.save(addressEntity);
    }

    public AddressDTO update(Long id, AddressDTO addressDTO) {
        if (userRepository.existsById(id)){
            addressDTO.setId(id);
            AddressEntity aux = addressRepository.save(converteAddressDTO(addressDTO));
            return converteAddressEntity(aux);
        }else {
            return null;
        }
    }

    public AddressEntity converteAddressDTO(AddressDTO enderecoDTO) {
        if (enderecoDTO == null) {
            return null;
        }
        return new AddressEntity(
                enderecoDTO.getId(),
                enderecoDTO.getRua(),
                enderecoDTO.getNumero(),
                enderecoDTO.getBairro(),
                enderecoDTO.getCidade(),
                enderecoDTO.getEstado(),
                enderecoDTO.getCep()
        );
    }

    public AddressDTO converteAddressEntity(AddressEntity enderecoEntity) {
        if (enderecoEntity == null) {
            return null;
        }
        return new AddressDTO(
                enderecoEntity.getId(),
                enderecoEntity.getRua(),
                enderecoEntity.getNumero(),
                enderecoEntity.getBairro(),
                enderecoEntity.getCidade(),
                enderecoEntity.getEstado(),
                enderecoEntity.getCep()
        );
    }

    public List<AddressDTO> consultaTodos() {
        List<AddressEntity> listaEntities = addressRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return listaEntities.stream().map(this::converteAddressEntity).collect(Collectors.toList());
    }

}