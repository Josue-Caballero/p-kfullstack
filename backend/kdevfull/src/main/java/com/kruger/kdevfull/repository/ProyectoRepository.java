package com.kruger.kdevfull.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kruger.kdevfull.models.Project;

public interface ProyectoRepository extends JpaRepository<Project, Long> {
    
    public List<Project> findAllByCreatedBy(String createdBy);

    public Optional<Project> findByIdAndCreatedBy(Long id, String createdBy);

}
