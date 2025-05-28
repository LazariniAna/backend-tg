package tg.schoolapi.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import tg.schoolapi.model.dto.PasswordDTO;
import tg.schoolapi.model.dto.LoginDTO;
import tg.schoolapi.model.dto.users.UserDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import tg.schoolapi.model.dto.AddressDTO;
import tg.schoolapi.model.entity.UserEntity;
import tg.schoolapi.model.entity.AddressEntity;
import tg.schoolapi.model.repository.UserRepository;
import tg.schoolapi.model.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AddressService addressService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService() {
    }

    public UserDTO insert(UserDTO userDTO) {
        // Verifica se já existe usuário com mesmo CPF
        Optional<UserEntity> existingUser = userRepository.findByCpf(userDTO.getCpf());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Já existe um usuário com esse CPF: " + userDTO.getCpf());
        }

        // Continua processo normal
        AddressEntity addressEntity = addressService.insert(userDTO.getAddress());

        UserEntity userEntity = this.converteDTO(userDTO);
        String senhaCriptografada = passwordEncoder.encode(userDTO.getSenha());
        userEntity.setSenha(senhaCriptografada);
        userEntity.setAddress(addressEntity);

        userEntity = userRepository.save(userEntity);
        return this.converteEntity(userEntity);
    }


    public UserEntity converteDTO(UserDTO userDTO) {
        AddressEntity enderecoEntity = addressService.converteAddressDTO(userDTO.getAddress());
        UserEntity userEntity = new UserEntity();

        userEntity.setId(userDTO.getId());
        userEntity.setNome(userDTO.getNome());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setTelefone(userDTO.getTelefone());
        userEntity.setCpf(userDTO.getCpf());
        userEntity.setAdmin(userDTO.getAdmin());
        userEntity.setAddress(enderecoEntity);
        if (userDTO.getSenha() != null) {
            userEntity.setSenha(userDTO.getSenha());
        }
        return userEntity;
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
                addressDTO,
                userEntity.getAdmin()
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
        if (userDTO.getAdmin() != false) {
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


    public UserDTO atualizaUser(Long id, UserDTO userDTO) {
        UserEntity userEntityAtual = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        Optional<UserEntity> usuarioComMesmoCpf = userRepository.findByCpf(userDTO.getCpf());

        if (usuarioComMesmoCpf.isPresent() && !usuarioComMesmoCpf.get().getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CPF já cadastrado para outro usuário");
        }

        userDTO.setId(id);

        if (userDTO.getSenha() == null) {
            userDTO.setSenha(userEntityAtual.getSenha());
        } else{
            String senhaCriptografada = passwordEncoder.encode(userDTO.getSenha());
            userDTO.setSenha(senhaCriptografada);
        }

        UserEntity atualizado = userRepository.save(converteDTO(userDTO));
        return converteEntity(atualizado);
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

    public UserDTO login(LoginDTO loginDTO) {
        UserEntity userEntity = userRepository.findByNome(loginDTO.getCpf())
                .orElseThrow(() -> new RuntimeException("User não encontrado com o nome: " + loginDTO.getCpf()));
        if (passwordEncoder.matches(loginDTO.getSenha(), userEntity.getSenha())) {
            return converteEntity(userEntity);
        }
        return converteEntity(userEntity);
    }

    public Boolean atualizarSenha(PasswordDTO passwordDTO){
        UserEntity user = userRepository.findById(passwordDTO.userId)
                .orElseThrow(() -> new RuntimeException("User não encontrado com o ID: " + passwordDTO.userId));

        if (passwordDTO.senha != null && !passwordDTO.senha.isEmpty()) {
            user.setSenha(passwordDTO.senha);
        } else {
            throw new RuntimeException("Senha não pode ser vazia");
        }
        return true;
    }

}