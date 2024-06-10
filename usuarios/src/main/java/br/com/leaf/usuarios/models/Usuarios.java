package br.com.leaf.usuarios.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_usuarios", schema = "comprafacil")
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Length(min = 2, max = 50)
    @Column(name = "tx_nome", nullable = false, length = 50)
    private String nome;

    @Length(min = 2, max = 50)
    @Column(name = "tx_email", nullable = false, length = 50)
    private String email;

    @Column(name = "tx_senha", nullable = false)
    private String senha;

}
