package com.kruger.kdevfull.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kruger.kdevfull.enums.State;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "projects")
public class Project {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedBy
    private String updatedBy;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private State state = State.ACTIVE;
    
    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonIgnoreProperties({"projects", "tasks", "password", "createdBy", "updatedBy", "state", "createdAt"})
    private User owner;
    
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"project", "assignedTo", "createdBy", "updatedBy", "state", "createdAt"})
    private List<Task> tasks;

    @PrePersist
    void auditInsert() {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        this.state = State.ACTIVE;
        this.createdAt = LocalDateTime.now();
        this.createdBy = auth.getName();

    }

    @PreUpdate
    void auditUpdate() {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        this.updatedBy = auth.getName();

    }

}
