package tg.schoolapi.model.dto;

public class UserDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private String telefone;
    private AddressDTO endereco;

    public UserDTO() {
    }

    public UserDTO(Long id, String nome, String cpf, String email, String senha, String telefone, AddressDTO endereco) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public AddressDTO getAddress() {
        return endereco;
    }

    public void setAddress(AddressDTO endereco) {
        this.endereco = endereco;
    }
}