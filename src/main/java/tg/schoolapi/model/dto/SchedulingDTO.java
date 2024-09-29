package tg.schoolapi.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

public class SchedulingDTO {
    private Long id;

    private UserEntity usuario;

    private LocalDateTime data_hora;

    private String obs;

    public SchedulingDTO() {
    }

    public SchedulingDTO(Long id, UserEntity usuario, LocalDateTime data_hora, String obs) {
        this.id = id;
        this.usuario = usuario;
        this.data_hora = data_hora;
        this.obs = obs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UserEntity usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getData_hora() {
        return data_hora;
    }

    public void setData_hora(LocalDateTime data_hora) {
        this.data_hora = data_hora;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
