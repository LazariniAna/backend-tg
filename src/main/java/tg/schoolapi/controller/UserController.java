package tg.schoolapi.controller;

import tg.schoolapi.model.dto.PasswordDTO;
import tg.schoolapi.model.dto.users.UserDTO;
import tg.schoolapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/users"})
public class UserController {
    @Autowired
    UserService service;

    public UserController() {
    }

    @PostMapping
    public UserDTO insert(@RequestBody UserDTO userDTO) {
        return this.service.insert(userDTO);
    }

    @GetMapping
    public List<UserDTO> consultaTodos() {
        return this.service.consultaTodos();
    }

    @GetMapping("/{id}")
    public UserDTO searchForId(@PathVariable Long id) {
        return this.service.searchForId(id);
    }

    @PatchMapping("/password/{id}")
    public UserDTO atualizarSenhaId(@PathVariable Long id, @RequestBody UserDTO userDTO){
        UserDTO userDTO1 = service.atualizarSenhaId(id, userDTO);
        return userDTO1;
    }
    @PatchMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO){
        UserDTO userDTO1 = service.atualizaUser(id, userDTO);
        return userDTO1;
    }

    @PatchMapping("/password/change")
    public Boolean changePassword(@RequestBody PasswordDTO passwordDTO){
        Boolean changePass = service.atualizarSenha(passwordDTO);
        return changePass;
    }

    @DeleteMapping("/{id}")
    public String update(@PathVariable Long id){
        String result = service.deleteById(id);
        return result;
    }

}