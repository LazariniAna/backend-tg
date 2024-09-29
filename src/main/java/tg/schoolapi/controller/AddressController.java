package tg.schoolapi.controller;

import tg.schoolapi.model.dto.AddressDTO;
import tg.schoolapi.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/addresses"})
public class AddressController {
    @Autowired
    AddressService injecao;

    public AddressController() {
    }

    @GetMapping
    public List<AddressDTO> consultaTodos() {
        return this.injecao.consultaTodos();
    }


    @PatchMapping("/{id}") //passa o parametro
    public AddressDTO update(@PathVariable Long id, @RequestBody AddressDTO addressDTO){
        return injecao.update(id, addressDTO);
    }

}