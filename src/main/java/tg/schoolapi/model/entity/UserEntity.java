package tg.schoolapi.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(
        name = "users"
)
public class
UserEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "nome"
    )
    private String nome;

    @Column(
            name = "cpf"
    )
    private String cpf;

    @Column(
            name = "email"
    )
    private String email;

    @Column(
            name = "senha"
    )
    private String senha;

    @Column(
            name = "telefone"
    )
    private String telefone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    private AddressEntity endereco;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE) // Adicionado
    private List<SchedulingEntity> agendamentos;

    public UserEntity() {
    }

    public UserEntity(Long id, String nome, String cpf, String email, String senha, String telefone, AddressEntity endereco) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.endereco = endereco;
    }

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


    public AddressEntity getAddress() {
        return endereco;
    }

    public void setAddress(AddressEntity endereco) {
        this.endereco = endereco;
    }
}


