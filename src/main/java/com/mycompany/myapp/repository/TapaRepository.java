package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Tapa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TapaRepository extends JpaRepository<Tapa, Long> {
    List<Tapa> findAllByCreatedByOrderByCreatedDateDesc(String id_usuario);
}
