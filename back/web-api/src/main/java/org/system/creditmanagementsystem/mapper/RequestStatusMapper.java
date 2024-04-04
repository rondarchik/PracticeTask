package org.system.creditmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.system.creditmanagementsystem.dto.RequestStatusDto;
import org.system.creditmanagementsystem.entity.RequestStatus;

@Mapper(componentModel = "spring")
public interface RequestStatusMapper {
    RequestStatusDto toDto(RequestStatus requestStatus);
    RequestStatus fromDto(RequestStatusDto requestStatusDto);
}
