package org.system.creditmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.system.creditmanagementsystem.dto.status.AddRequestStatusDto;
import org.system.creditmanagementsystem.dto.status.GetRequestStatusDto;
import org.system.creditmanagementsystem.dto.status.UpdateRequestStatusDto;
import org.system.creditmanagementsystem.entity.RequestStatus;
import org.system.creditmanagementsystem.exception.NotFoundException;
import org.system.creditmanagementsystem.mapper.RequestStatusMapper;
import org.system.creditmanagementsystem.repository.RequestStatusRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RequestStatusService {
    private final RequestStatusRepository requestStatusRepository;
    private final RequestStatusMapper requestStatusMapper;
    private static final String NOT_FOUND_MESSAGE = "Such request status not found!";

    @Autowired
    public RequestStatusService(RequestStatusRepository requestStatusRepository, RequestStatusMapper requestStatusMapper) {
        this.requestStatusRepository = requestStatusRepository;
        this.requestStatusMapper = requestStatusMapper;
    }

    public List<GetRequestStatusDto> getAllRequestStatuses() {
        return requestStatusRepository.findAll().stream().map(requestStatusMapper::toDto).toList();
    }

    public GetRequestStatusDto getRequestStatusById(UUID id) {
        RequestStatus status = requestStatusRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        return requestStatusMapper.toDto(status);
    }

    public GetRequestStatusDto addRequestStatus(AddRequestStatusDto requestStatusDto) {
        RequestStatus status = requestStatusMapper.fromDto(requestStatusDto);
        requestStatusRepository.save(status);
        return requestStatusMapper.toDto(status);
    }

    public GetRequestStatusDto updateRequestStatus(UpdateRequestStatusDto requestStatusDto, UUID id) {
        RequestStatus existingRequestStatus = requestStatusRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        if (requestStatusDto.getStatus() != null) {
            existingRequestStatus.setStatus(requestStatusDto.getStatus());
        }
        requestStatusRepository.save(existingRequestStatus);
        return requestStatusMapper.toDto(existingRequestStatus);
    }

    public void removeRequestStatusById(UUID id) {
        Optional<RequestStatus> existingRequestStatus = requestStatusRepository.findById(id);

        if (existingRequestStatus.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        requestStatusRepository.deleteById(id);
    }
}
