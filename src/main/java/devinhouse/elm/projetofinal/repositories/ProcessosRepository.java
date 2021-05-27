package devinhouse.elm.projetofinal.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import devinhouse.elm.projetofinal.model.Processo;

import java.util.List;


@Repository
public interface ProcessosRepository extends JpaRepository<Processo, Integer> {
    int countBySiglaEquals(String sigla);
    boolean existsByChave(String chave);
    List<Processo> findByChaveContains(String chave);
}
