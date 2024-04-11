package org.system.creditmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.system.creditmanagementsystem.dto.status.AddRequestStatusDto;
import org.system.creditmanagementsystem.dto.status.GetRequestStatusDto;
import org.system.creditmanagementsystem.entity.RequestStatus;

@Mapper(componentModel = "spring")
public interface RequestStatusMapper {
    GetRequestStatusDto toDto(RequestStatus requestStatus);
    RequestStatus fromDto(AddRequestStatusDto requestStatusDto);
}
