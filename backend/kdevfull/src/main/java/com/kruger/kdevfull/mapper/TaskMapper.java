
package com.kruger.kdevfull.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kruger.kdevfull.dto.task.TaskRequest;
import com.kruger.kdevfull.dto.task.TaskResponse;
import com.kruger.kdevfull.models.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "project", ignore = true)
    @Mapping(target = "assignedTo", ignore = true)
    Task toEntity(TaskRequest dto);

    TaskResponse toResponse(Task entity);

    List<TaskResponse> toResponseList(List<Task> entities);

}
