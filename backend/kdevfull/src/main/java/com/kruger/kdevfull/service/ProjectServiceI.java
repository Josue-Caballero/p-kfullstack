
package com.kruger.kdevfull.service;

import java.util.List;

import com.kruger.kdevfull.dto.project.ProjectRequest;
import com.kruger.kdevfull.dto.project.ProjectResponse;
import com.kruger.kdevfull.dto.project.ProjectUpdateRequest;

public interface ProjectServiceI {

    ProjectResponse create(ProjectRequest request);

    List<ProjectResponse> findAll();

    ProjectResponse update(
        Long id, ProjectUpdateRequest request);

    ProjectResponse delete(Long id);

}
