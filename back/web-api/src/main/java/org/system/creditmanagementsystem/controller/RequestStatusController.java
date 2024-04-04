package org.system.creditmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.system.creditmanagementsystem.dto.RequestStatusDto;
import org.system.creditmanagementsystem.service.RequestStatusService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class RequestStatusController {
    private final RequestStatusService requestStatusService;

    @Autowired
    public RequestStatusController(RequestStatusService requestStatusService) {
        this.requestStatusService = requestStatusService;
    }

    @GetMapping("/request_statuses")
    public List<RequestStatusDto> getAllRequestStatuses() {
        return requestStatusService.getAllRequestStatuses();
    }

    @GetMapping("/request_statuses/{id}")
    public RequestStatusDto getRequestStatusById(@PathVariable UUID id) {
        return requestStatusService.getRequestStatusById(id);
    }

    @PostMapping("/request_statuses/add")
    public RequestStatusDto addRequestStatus(@RequestBody RequestStatusDto requestStatus) {
        return requestStatusService.addRequestStatus(requestStatus);
    }

    @PutMapping("/request_statuses/update")
    public RequestStatusDto updateRequestStatus(@RequestBody RequestStatusDto requestStatus) {
        return requestStatusService.updateRequestStatus(requestStatus);
    }

    @DeleteMapping("/request_statuses/delete/{id}")
    public void removeRequestStatusById(@PathVariable UUID id) {
        requestStatusService.removeRequestStatusById(id);
    }
}
