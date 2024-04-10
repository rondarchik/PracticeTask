package org.system.creditmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.system.creditmanagementsystem.dto.CreditDto;
import org.system.creditmanagementsystem.entity.Credit;
import org.system.creditmanagementsystem.entity.CreditType;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CreditMapper {
    CreditDto toDto(Credit credit);
    Credit fromDto(CreditDto creditDto);

    default Credit mapCredit(UUID id) {
        return null;
    }

    default CreditType map(String creditType) {
        return null;
    }

    default String map(CreditType creditType) {
        return creditType.toString();
    }
}
