package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Establishment;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, Long> {
    List<Establishment> findAllByCreatedByOrderByCreatedDateDesc(String login);
    List<Establishment> findAllByMyCreatedByOrderByCreatedDateDesc(Long id);
}
