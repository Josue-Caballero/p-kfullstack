
package com.kruger.kdevfull.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kruger.kdevfull.enums.State;
import com.kruger.kdevfull.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    
    boolean existsByUsername(String username);
    
    public Optional<User> findByUsername(String username);

    public List<User> findAllByCreatedByAndStateNot(String createdBy, State state);

    public Optional<User> findByIdAndCreatedBy(Long id, String createdBy);

}
