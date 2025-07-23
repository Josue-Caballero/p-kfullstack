
package com.kruger.kdevfull.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kruger.kdevfull.dto.project.ProjectRequest;
import com.kruger.kdevfull.dto.project.ProjectResponse;
import com.kruger.kdevfull.models.Project;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mapping(target = "owner", ignore = true)
    Project toEntity(ProjectRequest dto);

    @Mapping(target = "owner", ignore = true)
    ProjectResponse toResponse(Project entity);

    List<ProjectResponse> toResponseList(List<Project> projects);

}
