package org.system.creditmanagementsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.system.creditmanagementsystem.entity.RequestStatus;
import org.system.creditmanagementsystem.service.RequestStatusService;

import java.util.List;
import java.util.UUID;

@RestController
public class RequestStatusController {
    private final RequestStatusService requestStatusService;

    public RequestStatusController(RequestStatusService requestStatusService) {
        this.requestStatusService = requestStatusService;
    }

    @GetMapping("/request_statuses")
    public ResponseEntity<List<RequestStatus>> getRequestStatuses() {
        var requestStatuses = requestStatusService.getRequestStatuses();

        return new ResponseEntity<>(requestStatuses, HttpStatus.OK);
    }

    @GetMapping("/request_statuses/{id}")
    public ResponseEntity<RequestStatus> getRequestStatusById(@PathVariable UUID id) {
        var requestStatus = requestStatusService.getRequestStatusById(id);

        return new ResponseEntity<>(requestStatus, HttpStatus.OK);
    }

    @PostMapping("/request_statuses")
    public ResponseEntity<Object> addRequestStatus(RequestStatus requestStatus) {
        requestStatusService.addRequestStatus(requestStatus);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/request_statuses")
    public ResponseEntity<Object> updateRequestStatus(RequestStatus requestStatus) {
        requestStatusService.updateRequestStatus(requestStatus);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/request_statuses/{id}")
    public ResponseEntity<Object> removeRequestStatusById(@PathVariable UUID id) {
        requestStatusService.removeRequestStatusById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
