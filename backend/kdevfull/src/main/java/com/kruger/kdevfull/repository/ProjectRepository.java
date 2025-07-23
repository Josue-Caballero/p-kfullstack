package com.kruger.kdevfull.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kruger.kdevfull.enums.State;
import com.kruger.kdevfull.models.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    
    public List<Project> findAllByCreatedByAndStateNot(String createdBy, State state);

    public Optional<Project> findByIdAndCreatedBy(Long id, String createdBy);

}
