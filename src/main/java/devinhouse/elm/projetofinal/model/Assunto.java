package devinhouse.elm.projetofinal.model;

import lombok.*;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

import com.fasterxml.jackson.annotation.JsonProperty;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

import java.time.LocalDate;


@Data
@Entity
public class Assunto {
    @Id @GeneratedValue(strategy = IDENTITY) private long id;
    @Column(nullable=false, name = "DESCRICAO", length = 250) private String descricao;
    @Column(nullable=false, name = "DTCADASTRO") private LocalDate dataCadastro;
    @Column(nullable=false, name = "FLATIVO") private boolean ativo = true;
}
