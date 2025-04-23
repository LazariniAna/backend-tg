package tg.schoolapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tg.schoolapi.model.dto.ContentDTO;
import tg.schoolapi.service.ContentService;

import java.util.List;

@RestController
@RequestMapping({"/contents"})
public class ContentController {
    @Autowired
    ContentService service;

    public ContentController() {
    }

    @PostMapping
    public ContentDTO insert(@RequestBody ContentDTO contentDTO) {
        return this.service.insert(contentDTO);
    }

    @GetMapping
    public List<ContentDTO> consultaTodos() {
        return this.service.consultaTodos();
    }
    @GetMapping("/{id}")
    public ContentDTO consultaTodos(@PathVariable Long id) {
        return this.service.searchForId(id);
    }

    @PatchMapping("/{id}")
    public ContentDTO update(@PathVariable Long id, @RequestBody ContentDTO contentDTO){
        ContentDTO result = service.update(id, contentDTO);
        return result;
    }

    @DeleteMapping("/{id}")
    public String update(@PathVariable Long id){
        String result = service.deleteById(id);
        return result;
    }}