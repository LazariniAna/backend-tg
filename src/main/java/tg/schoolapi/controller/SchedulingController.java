package tg.schoolapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tg.schoolapi.model.dto.ContentDTO;
import tg.schoolapi.model.entity.SchedulingDTO;
import tg.schoolapi.service.ContentService;
import tg.schoolapi.service.SchedulingService;

import java.util.List;

@RestController
@RequestMapping({"/scheduling"})
public class SchedulingController {
    @Autowired
    SchedulingService service;

    public SchedulingController() {
    }

    @PostMapping
    public SchedulingDTO insert(@RequestBody SchedulingDTO schedulingDTO) {
        return this.service.insert(schedulingDTO);
    }

    @GetMapping
    public List<SchedulingDTO> consultaTodos() {
        return this.service.consultaTodos();
    }

    @GetMapping("/{id}")
    public SchedulingDTO consultaPorId(@PathVariable Long id) {
        return this.service.searchForId(id);
    }

    @PatchMapping("/{id}")
    public SchedulingDTO update(@PathVariable Long id, @RequestBody SchedulingDTO schedulingDTO){
        SchedulingDTO result = service.update(id, schedulingDTO);
        return result;
    }

    @DeleteMapping("/{id}")
    public String update(@PathVariable Long id){
        String result = service.deleteById(id);
        return result;
    }

}