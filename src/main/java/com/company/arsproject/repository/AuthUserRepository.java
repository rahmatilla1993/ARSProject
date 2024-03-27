package com.company.arsproject.repository;

import com.company.arsproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RepositoryRestResource(exported = false)
public interface AuthUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
