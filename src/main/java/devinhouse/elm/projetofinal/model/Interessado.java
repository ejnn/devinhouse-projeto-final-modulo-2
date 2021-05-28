package devinhouse.elm.projetofinal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Interessado {
    @Id @GeneratedValue(strategy = IDENTITY) private long id;
    @Column(nullable = false, name = "NMINTERESSADO", length = 250) private String nome;
    @Column(nullable = false, name = "NUIDENTIFICACAO", length = 50) private String identificacao;
    @Column(nullable = false, name = "DTNASCIMENTO") private LocalDate dataDeNascimento;
    @Column(nullable = false, name = "FLATIVO") private boolean ativo = true;
}
