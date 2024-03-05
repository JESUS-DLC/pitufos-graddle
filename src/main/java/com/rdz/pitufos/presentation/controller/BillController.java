package com.rdz.pitufos.presentation.controller;


import com.rdz.pitufos.business.facade.IBillFacade;
import com.rdz.pitufos.domain.dto.request.BillRequestDto;
import com.rdz.pitufos.domain.dto.response.BillResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bill")
public class BillController {

    private final IBillFacade iBillFacade;

    @GetMapping("/{id}")
    ResponseEntity<BillResponseDto> findById(@PathVariable("id") long id){
        return ResponseEntity.status(HttpStatus.OK).body(iBillFacade.findById(id));
    }

    @GetMapping("/active")
    ResponseEntity<List<BillResponseDto>> findAllActive(){
        return ResponseEntity.status(HttpStatus.OK).body(iBillFacade.findAllActive());
    }

    @GetMapping
    ResponseEntity<List<BillResponseDto>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(iBillFacade.findAll());
    }


    @PostMapping
    ResponseEntity<BillResponseDto> save(@RequestBody BillRequestDto billRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(iBillFacade.save(billRequestDto));
    }

    @GetMapping("/pay/{id}")
    ResponseEntity<Boolean> pay(@PathVariable("id") long id){
        return ResponseEntity.status(HttpStatus.OK).body(iBillFacade.pay(id));
    }

    @PatchMapping("/{id}")
    ResponseEntity<BillResponseDto> update(@PathVariable(name = "id")long id, @RequestBody BillRequestDto billRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(iBillFacade.update(id,billRequestDto));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> delete(@PathVariable("id") long id){
        return ResponseEntity.status(HttpStatus.OK).body(iBillFacade.delete(id));
    }



}
