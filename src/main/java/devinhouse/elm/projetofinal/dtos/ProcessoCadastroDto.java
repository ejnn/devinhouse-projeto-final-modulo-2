package devinhouse.elm.projetofinal.dtos;

import lombok.*;

import devinhouse.elm.projetofinal.model.Assunto;
import devinhouse.elm.projetofinal.model.Interessado;


@Getter
@Setter
public class ProcessoCadastroDto {
    private String sigla;
    private String descricao;
    private Assunto assunto;
    private Interessado interessado;
}
