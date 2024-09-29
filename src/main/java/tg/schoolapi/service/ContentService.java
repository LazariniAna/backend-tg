package tg.schoolapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tg.schoolapi.model.dto.AddressDTO;
import tg.schoolapi.model.dto.ContentDTO;
import tg.schoolapi.model.dto.UserDTO;
import tg.schoolapi.model.entity.*;
import tg.schoolapi.model.entity.SchedulingDTO;
import tg.schoolapi.model.repository.AddressRepository;
import tg.schoolapi.model.repository.ContentRepository;
import tg.schoolapi.model.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentService {
    @Autowired
    ContentRepository repository;


    public ContentService() {
    }

    public ContentDTO insert(ContentDTO contentDTO) {

        ContentEntity contentEntity = this.converteDTO(contentDTO);
        contentEntity = repository.save(contentEntity);

        return this.converteEntity(contentEntity);
    }

    public ContentEntity converteDTO(ContentDTO contentDTO) {
        return new ContentEntity(
                contentDTO.getId(),
                contentDTO.getTitle(),
                contentDTO.getSubtitle(),
                contentDTO.getImage(),
                contentDTO.getText(),
                contentDTO.getFooter()
        );
    }

    public ContentDTO converteEntity(ContentEntity contentEntity) {
        return new ContentDTO(
                contentEntity.getId(),
                contentEntity.getTitle(),
                contentEntity.getSubtitle(),
                contentEntity.getImage(),
                contentEntity.getText(),
                contentEntity.getFooter()
        );
    }

    public List<ContentDTO> consultaTodos() {
        List<ContentEntity> listaEntities = repository.findAll();
        return listaEntities.stream().map(this::converteEntity).collect(Collectors.toList());
    }

    public ContentDTO converteEntityComEndereco(ContentEntity contentEntity) {
        ContentDTO contentDTO = converteEntity(contentEntity);
        return contentDTO;
    }

    public List<ContentDTO> converteEntities(List<ContentEntity> listaEntities) {
        List<ContentDTO> listaDTOs = new ArrayList<>();
        for (ContentEntity objEntity : listaEntities) {
            listaDTOs.add(this.converteEntity(objEntity));
        }
        return listaDTOs;
    }

    public ContentDTO update(Long id, ContentDTO contentDTO){
        if (repository.existsById(id)){
            contentDTO.setId(id);
            ContentEntity aux = repository.save(converteDTO(contentDTO));
            return converteEntity(aux);
        }else {
            return null;
        }
    }

    public ContentDTO searchForId(Long id) {
        ContentEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User não encontrado com o ID: " + id));
        return converteEntity(entity);
    }

    public String deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "Item deletado com sucesso!";
        } else {
            throw new RuntimeException("Scheduling não encontrado com o ID: " + id);
        }
    }
}