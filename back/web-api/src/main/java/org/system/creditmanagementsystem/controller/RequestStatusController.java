package org.system.creditmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.system.creditmanagementsystem.dto.RequestStatusDto;
import org.system.creditmanagementsystem.service.RequestStatusService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/request_statuses")
@CrossOrigin("http://localhost:3000/")
public class RequestStatusController {
    private final RequestStatusService requestStatusService;

    @Autowired
    public RequestStatusController(RequestStatusService requestStatusService) {
        this.requestStatusService = requestStatusService;
    }

    @GetMapping
    public List<RequestStatusDto> getAllRequestStatuses() {
        return requestStatusService.getAllRequestStatuses();
    }

    @GetMapping("/{id}")
    public RequestStatusDto getRequestStatusById(@PathVariable UUID id) {
        return requestStatusService.getRequestStatusById(id);
    }

    @PostMapping("/add")
    public RequestStatusDto addRequestStatus(@RequestBody RequestStatusDto requestStatus) {
        return requestStatusService.addRequestStatus(requestStatus);
    }

    @PutMapping("/update/{id}")
    public RequestStatusDto updateRequestStatus(@RequestBody RequestStatusDto requestStatus, @PathVariable UUID id) {
        return requestStatusService.updateRequestStatus(requestStatus, id);
    }

    @DeleteMapping("/delete/{id}")
    public void removeRequestStatusById(@PathVariable UUID id) {
        requestStatusService.removeRequestStatusById(id);
    }
}
