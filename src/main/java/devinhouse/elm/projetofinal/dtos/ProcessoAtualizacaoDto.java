package devinhouse.elm.projetofinal.dtos;

import lombok.*;

import java.time.Year;

import devinhouse.elm.projetofinal.model.Assunto;
import devinhouse.elm.projetofinal.model.Interessado;

@Getter
@Setter
public class ProcessoAtualizacaoDto {
    private String descricao;
    private Assunto assunto;
    private Interessado interessado;
}
