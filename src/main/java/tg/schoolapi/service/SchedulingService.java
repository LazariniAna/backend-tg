package tg.schoolapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tg.schoolapi.model.entity.SchedulingDTO;
import tg.schoolapi.model.entity.SchedulingEntity;
import tg.schoolapi.model.entity.UserEntity;
import tg.schoolapi.model.repository.SchedulingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import tg.schoolapi.model.dto.users.UserDTO;

@Service
public class SchedulingService {

    @Autowired
    private SchedulingRepository repository;

    @Autowired
    private UserService userService;  // Injetando o UserService

    public SchedulingService() {
    }

    public SchedulingDTO insert(SchedulingDTO schedulingDTO) {
        SchedulingEntity schedulingEntity = this.converteDTO(schedulingDTO);
        schedulingEntity = repository.save(schedulingEntity);
        return this.converteEntity(schedulingEntity);
    }

    public SchedulingEntity converteDTO(SchedulingDTO schedulingDTO) {
        return new SchedulingEntity(
                schedulingDTO.getId(),
                schedulingDTO.getUsuario(),
                schedulingDTO.getData_hora(),
                schedulingDTO.getObs()
        );
    }

    public SchedulingDTO converteEntity(SchedulingEntity schedulingEntity) {
        UserDTO userDTO = userService.searchForId(schedulingEntity.getUsuario().getId());
        UserEntity userEntity = userService.converteDTO(userDTO);
        return new SchedulingDTO(
                schedulingEntity.getId(),
                userEntity,
                schedulingEntity.getData_hora(),
                schedulingEntity.getObs()
        );
    }

    public List<SchedulingDTO> consultaTodos() {
        List<SchedulingEntity> listaEntities = repository.findAll();
        return listaEntities.stream().map(this::converteEntity).collect(Collectors.toList());
    }

    public List<SchedulingDTO> converteEntities(List<SchedulingEntity> listaEntities) {
        List<SchedulingDTO> listaDTOs = new ArrayList<>();
        for (SchedulingEntity objEntity : listaEntities) {
            listaDTOs.add(this.converteEntity(objEntity));
        }
        return listaDTOs;
    }

    public SchedulingDTO update(Long id, SchedulingDTO schedulingDTO){
        if (repository.existsById(id)){
            schedulingDTO.setId(id);
            SchedulingEntity aux = repository.save(converteDTO(schedulingDTO));
            return converteEntity(aux);
        } else {
            return null;
        }
    }

    public String deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "Item deletado com sucesso!";
        } else {
            throw new RuntimeException("Scheduling não encontrado com o ID: " + id);
        }
    }

    public SchedulingDTO searchForId(Long id) {
        SchedulingEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User não encontrado com o ID: " + id));
        return converteEntity(entity);
    }
}
