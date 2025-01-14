package devinhouse.elm.projetofinal.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import devinhouse.elm.projetofinal.model.Processo;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProcessosRepository extends JpaRepository<Processo, Integer> {
    int countDistinctBySiglaEquals(String sigla);
    boolean existsBySigla(String sigla);
    boolean existsByChave(String chave);
    List<Processo> findByChaveContains(String chave);
    List<Processo> findByAssuntoId(long id);
    List<Processo> findByInteressadoId(long id);
}
