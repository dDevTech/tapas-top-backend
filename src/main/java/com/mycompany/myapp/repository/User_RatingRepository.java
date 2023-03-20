package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.User_Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User_RatingRepository extends JpaRepository<User_Rating, Long> {
}
