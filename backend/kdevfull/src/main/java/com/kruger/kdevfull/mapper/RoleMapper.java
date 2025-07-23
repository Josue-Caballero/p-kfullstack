
package com.kruger.kdevfull.mapper;

import javax.management.relation.Role;

import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {

    Role map(Role role);

}
