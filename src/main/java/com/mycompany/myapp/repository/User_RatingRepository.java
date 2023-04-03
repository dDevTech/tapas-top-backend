package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.User_Rating;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User_RatingRepository extends JpaRepository<User_Rating, Long> {
    List<User_Rating> findAllByTapaId(Long id);

    List<User_Rating> findAllByUserIdAndTapaId(Long user_id, Long tapa_id);
}
