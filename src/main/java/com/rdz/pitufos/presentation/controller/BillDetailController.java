package com.rdz.pitufos.presentation.controller;


import com.rdz.pitufos.business.facade.IBillDetailFacade;
import com.rdz.pitufos.domain.dto.request.BillDetailRequestDto;
import com.rdz.pitufos.domain.dto.response.BillDetailResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BillDetailController {

    private final IBillDetailFacade iBillDetailFacade;

    @GetMapping("/bill/{billId}/detail/{id}")
    ResponseEntity<BillDetailResponseDto> findById(@PathVariable("billId") long billId, @PathVariable("id") long id){
        return ResponseEntity.status(HttpStatus.OK).body(iBillDetailFacade.findById(billId,id));
    }


    @GetMapping("/bill/{billId}/detail")
    ResponseEntity<List<BillDetailResponseDto>> findAll(@PathVariable("billId") long billId){
        return ResponseEntity.status(HttpStatus.OK).body(iBillDetailFacade.findAll(billId));
    }

   /* @PostMapping("/bill/{billId}/detail")
    ResponseEntity<BillDetailResponseDto> save(@PathVariable("billId") long billId ,@RequestBody BillDetailRequestDto billDetailRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(iBillDetailFacade.save(billId,billDetailRequestDto));
    }*/
    @PostMapping("/bill/{billId}/detail")
    ResponseEntity<Boolean> save(@PathVariable("billId") long billId , @RequestBody List<BillDetailRequestDto> list){
        return ResponseEntity.status(HttpStatus.OK).body(iBillDetailFacade.save(billId,list));
    }

    @PatchMapping("/bill/{billId}/detail/{id}")
    ResponseEntity<BillDetailResponseDto> update(@Valid @PathVariable("billId") long billId,@PathVariable("id") long id,@RequestBody BillDetailRequestDto billDetailRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(iBillDetailFacade.update(billId,id,billDetailRequestDto));
    }

    @DeleteMapping("/bill/{billId}/detail/{id}")
    ResponseEntity<Boolean> update(@PathVariable("billId") long billId,@PathVariable("id") long id){
        return ResponseEntity.status(HttpStatus.OK).body(iBillDetailFacade.delete(billId,id));
    }

}
