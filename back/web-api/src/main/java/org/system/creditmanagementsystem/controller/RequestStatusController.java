package org.system.creditmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.system.creditmanagementsystem.dto.status.AddRequestStatusDto;
import org.system.creditmanagementsystem.dto.status.GetRequestStatusDto;
import org.system.creditmanagementsystem.dto.status.UpdateRequestStatusDto;
import org.system.creditmanagementsystem.service.RequestStatusService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/request_statuses")
public class RequestStatusController {
    private final RequestStatusService requestStatusService;

    @Autowired
    public RequestStatusController(RequestStatusService requestStatusService) {
        this.requestStatusService = requestStatusService;
    }

    @GetMapping
    public ResponseEntity<List<GetRequestStatusDto>> getAllRequestStatuses() {
        return new ResponseEntity<>(requestStatusService.getAllRequestStatuses(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetRequestStatusDto> getRequestStatusById(@PathVariable UUID id) {
        return new ResponseEntity<>(requestStatusService.getRequestStatusById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<GetRequestStatusDto> addRequestStatus(@RequestBody AddRequestStatusDto requestStatus) {
        return new ResponseEntity<>(requestStatusService.addRequestStatus(requestStatus), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GetRequestStatusDto> updateRequestStatus(@RequestBody UpdateRequestStatusDto requestStatus, @PathVariable UUID id) {
        return new ResponseEntity<>(requestStatusService.updateRequestStatus(requestStatus, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> removeRequestStatusById(@PathVariable UUID id) {
        requestStatusService.removeRequestStatusById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
