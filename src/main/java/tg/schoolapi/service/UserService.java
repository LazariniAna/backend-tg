package tg.schoolapi.service;

import tg.schoolapi.model.dto.UserDTO;
import tg.schoolapi.model.dto.AddressDTO;
import tg.schoolapi.model.entity.UserEntity;
import tg.schoolapi.model.entity.AddressEntity;
import tg.schoolapi.model.repository.UserRepository;
import tg.schoolapi.model.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AddressService addressService;

    public UserService() {
    }

    public UserDTO insert(UserDTO userDTO) {
        AddressEntity addressEntity = addressService.insert(userDTO.getAddress());

        UserEntity userEntity = this.converteDTO(userDTO);
        userEntity.setAddress(addressEntity);
        userEntity = userRepository.save(userEntity);

        return this.converteEntity(userEntity);
    }

    public UserEntity converteDTO(UserDTO userDTO) {
        AddressEntity enderecoEntity = addressService.converteAddressDTO(userDTO.getAddress());
        return new UserEntity(
                userDTO.getId(),
                userDTO.getNome(),
                userDTO.getCpf(),
                userDTO.getEmail(),
                userDTO.getSenha(),
                userDTO.getTelefone(),
                enderecoEntity
        );
    }

    public UserDTO converteEntity(UserEntity userEntity) {
        AddressDTO addressDTO = addressService.converteAddressEntity(userEntity.getAddress());
        return new UserDTO(
                userEntity.getId(),
                userEntity.getNome(),
                userEntity.getCpf(),
                userEntity.getEmail(),
                userEntity.getSenha(),
                userEntity.getTelefone(),
                addressDTO
        );
    }

    public List<UserDTO> consultaTodos() {
        List<UserEntity> listaEntities = userRepository.findAll();
        return listaEntities.stream().map(this::converteEntityComEndereco).collect(Collectors.toList());
    }

    public UserDTO converteEntityComEndereco(UserEntity userEntity) {
        UserDTO userDTO = converteEntity(userEntity);
        AddressDTO addressDTO = addressService.converteAddressEntity(userEntity.getAddress());
        userDTO.setAddress(addressDTO);
        return userDTO;
    }

    public List<UserDTO> converteEntities(List<UserEntity> listaEntities) {
        List<UserDTO> listaDTOs = new ArrayList<>();
        for (UserEntity objEntity : listaEntities) {
            listaDTOs.add(this.converteEntity(objEntity));
        }
        return listaDTOs;
    }

    public List<UserDTO> alteraSenha(){
        //recupera todos os usuarios
        List<UserEntity> users = userRepository.findAll();
        for(UserEntity obj: users){
            //alterar o id do obj
            obj.setSenha("admin");
            //salva o obj alterado no banco de dados
            userRepository.save(obj);
        }
        //precisar converter as entidades em lista de DTO
        return converteEntities(users);
    }

    public UserDTO atualizarSenhaId(Long id, UserDTO userDTO){// atualiza os dados do usuario e do endereço relacionado ao usuario
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User não encontrado com o ID: " + id));
        if (userDTO.getNome() != null) {
            user.setNome(userDTO.getNome());
        }
        if (userDTO.getTelefone() != null) {
            user.setTelefone(userDTO.getTelefone());
        }
        if (userDTO.getCpf() != null) {
            user.setCpf(userDTO.getCpf());
        }
        if (userDTO.getEmail() != null) {
            user.setEmail(userDTO.getEmail());
        }
        if (userDTO.getSenha() != null) {
            user.setSenha(userDTO.getSenha());
        }
        if (userDTO.getAddress() != null) {
            AddressDTO addressDTO = userDTO.getAddress();
            Long addres_id = user.getAddress().getId();
            addressDTO.setId(addres_id);
            addressService.update(addres_id, addressDTO);
            AddressEntity endereco = addressService.converteAddressDTO(addressDTO);
            user.setAddress(endereco);
        }
        UserEntity userEntity = userRepository.save(user);
        UserDTO userDTO1 = this.converteEntity(userEntity);
        return userDTO1;

    }

    public UserDTO atualizaUser(Long id, UserDTO userDTO){
        if (userRepository.existsById(id)){
            userDTO.setId(id);
            UserEntity aux = userRepository.save(converteDTO(userDTO));
            return converteEntity(aux);
        }else {
            return null;
        }
    }

    public UserDTO searchForId(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User não encontrado com o ID: " + id));
        return converteEntity(userEntity);
    }

    public String deleteById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "Item deletado com sucesso!";
        } else {
            throw new RuntimeException("Scheduling não encontrado com o ID: " + id);
        }
    }
}