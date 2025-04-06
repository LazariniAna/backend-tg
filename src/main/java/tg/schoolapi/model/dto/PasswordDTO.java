package tg.schoolapi.model.dto;

public class PasswordDTO {
    public String senha;
    public Long userId;

    public PasswordDTO() {
    }

    public PasswordDTO(String senha, Long userId) {
        this.senha = senha;
        this.userId = userId;
    }

    public String getSenha() {
        return senha;
    }
    public Long getUserId() {
        return userId;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

}