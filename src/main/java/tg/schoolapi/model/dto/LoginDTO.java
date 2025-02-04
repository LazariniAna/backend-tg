package tg.schoolapi.model.dto;

public class LoginDTO {
    private Long id;
    private String cpf;
    private String senha;

    public LoginDTO() {
    }

    public LoginDTO(Long id, String cpf, String senha) {
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