package org.system.creditmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.system.creditmanagementsystem.entity.RequestStatus;
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
    public List<RequestStatus> getAllRequestStatuses() {
        return requestStatusService.getAllRequestStatuses();
    }

    @GetMapping("/request_statuses/{id}")
    public RequestStatus getRequestStatusById(@PathVariable UUID id) {
        return requestStatusService.getRequestStatusById(id);
    }

    @PostMapping("/request_statuses/add")
    public RequestStatus addRequestStatus(@RequestBody RequestStatus requestStatus) {
        return requestStatusService.addRequestStatus(requestStatus);
    }

    @PutMapping("/request_statuses/update")
    public RequestStatus updateRequestStatus(@RequestBody RequestStatus requestStatus) {
        return requestStatusService.updateRequestStatus(requestStatus);
    }

    @DeleteMapping("/request_statuses/delete/{id}")
    public void removeRequestStatusById(@PathVariable UUID id) {
        requestStatusService.removeRequestStatusById(id);
    }
}
