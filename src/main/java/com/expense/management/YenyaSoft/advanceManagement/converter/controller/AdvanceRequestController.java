package com.expense.management.YenyaSoft.advanceManagement.converter.controller;

import com.expense.management.YenyaSoft.advanceManagement.converter.dto.AdvanceRequestDto;
import com.expense.management.YenyaSoft.advanceManagement.converter.service.AdvanceRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/advance-requests")
@RequiredArgsConstructor
public class AdvanceRequestController {
    private final AdvanceRequestService advanceRequestService;

    @PostMapping
    public ResponseEntity<AdvanceRequestDto> createAdvanceRequest(@RequestBody @Valid AdvanceRequestDto advanceRequestDto) {
        AdvanceRequestDto created = advanceRequestService.createAdvanceRequest(advanceRequestDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<AdvanceRequestDto> updateAdvanceRequest(@RequestBody AdvanceRequestDto advanceRequestDto) {
        AdvanceRequestDto updated = advanceRequestService.updateAdvanceRequest(advanceRequestDto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AdvanceRequestDto> findAdvanceRequestById(@PathVariable Long id) {
        AdvanceRequestDto requestDto = advanceRequestService.findAdvanceRequestById(id);
        return new ResponseEntity<>(requestDto, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdvanceRequest(@PathVariable Long id) {
        advanceRequestService.deleteAdvanceRequest(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
