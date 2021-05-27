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

    @Id 
    @GeneratedValue(strategy = IDENTITY) 
    @JsonProperty(access = READ_ONLY) private long id;

    @Column(nullable=false, name = "DESCRICAO", length = 250) private String descricao;

    @Column(nullable=false, name = "DTCADASTRO") @JsonProperty(access = READ_ONLY) 
    private final LocalDate dataCadastro = LocalDate.now();

    @Column(nullable=false, name = "FLATIVO") private boolean ativo = true;
}
