
package com.kruger.kdevfull.service;

import java.util.List;

import com.kruger.kdevfull.dto.project.ProjectRequest;
import com.kruger.kdevfull.dto.project.ProjectResponse;

public interface ProjectServiceI {

    ProjectResponse create(ProjectRequest request);

    List<ProjectResponse> findAll();

    ProjectResponse update(Long id, ProjectRequest request);

    ProjectResponse delete(Long id);

}
