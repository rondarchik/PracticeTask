package org.system.creditmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.system.creditmanagementsystem.dto.RequestStatusDto;
import org.system.creditmanagementsystem.entity.RequestStatus;
import org.system.creditmanagementsystem.exception.AlreadyExistsException;
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
    private static final String CONFLICT = "Such record already exists in request status.";

    @Autowired
    public RequestStatusService(RequestStatusRepository requestStatusRepository, RequestStatusMapper requestStatusMapper) {
        this.requestStatusRepository = requestStatusRepository;
        this.requestStatusMapper = requestStatusMapper;
    }

    public List<RequestStatusDto> getAllRequestStatuses() {
        return requestStatusRepository.findAll().stream().map(requestStatusMapper::toDto).toList();
    }

    public RequestStatusDto getRequestStatusById(UUID id) {
        RequestStatus status = requestStatusRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
        return requestStatusMapper.toDto(status);
    }

    public RequestStatusDto addRequestStatus(RequestStatusDto requestStatusDto) {
        Optional<RequestStatus> optionalStatus = requestStatusRepository.findByStatus(requestStatusDto.getStatus());

        if (optionalStatus.isPresent()) {
            throw new AlreadyExistsException(CONFLICT);
        }

        RequestStatus status = requestStatusMapper.fromDto(requestStatusDto);
        requestStatusRepository.save(status);
        return requestStatusMapper.toDto(status);
    }

    public RequestStatusDto updateRequestStatus(RequestStatusDto requestStatusDto, UUID id) {
        RequestStatus status = requestStatusMapper.fromDto(requestStatusDto);
        Optional<RequestStatus> existingRequestStatus = requestStatusRepository.findById(id);

        if (existingRequestStatus.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        status.setStatus(requestStatusDto.getStatus());
        RequestStatus updatedStatus = requestStatusRepository.save(status);
        return requestStatusMapper.toDto(updatedStatus);
    }

    public void removeRequestStatusById(UUID id) {
        Optional<RequestStatus> existingRequestStatus = requestStatusRepository.findById(id);

        if (existingRequestStatus.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        requestStatusRepository.deleteById(id);
    }
}
