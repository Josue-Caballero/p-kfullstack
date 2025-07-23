
package com.kruger.kdevfull.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.kruger.kdevfull.enums.State;
import com.kruger.kdevfull.enums.TaskStatus;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private LocalDate dueDate;

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
    @JoinColumn(name = "assigned_to_id")
    private User assignedTo;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

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
