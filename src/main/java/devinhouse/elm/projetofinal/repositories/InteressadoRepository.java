package devinhouse.elm.projetofinal.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import devinhouse.elm.projetofinal.model.Interessado;

public interface InteressadoRepository extends JpaRepository<Interessado, Long> {
    boolean existsByIdentificacao(String identificacao);

    Optional<Interessado> findByIdentificacao(String identificacao);
}
