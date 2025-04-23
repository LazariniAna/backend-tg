package tg.schoolapi.model.dto.users;

import tg.schoolapi.model.dto.AddressDTO;

public class PasswordDTO {
    private Long id;
    private String cpf;
    private String senha;

    public PasswordDTO() {
    }

    public PasswordDTO(Long id, String nome, String cpf, String email, String senha, String telefone, AddressDTO endereco, boolean admin) {
        this.id = id;
        this.cpf = cpf;
        this.senha = senha;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}