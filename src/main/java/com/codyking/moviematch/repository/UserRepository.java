package com.codyking.moviematch.repository;

import com.codyking.moviematch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// JPA Repository for User entities, providing standard CRUD operations.
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
