package com.rdz.pitufos.business.facade.impl;


import com.rdz.pitufos.business.exception.ResourceNotFoundExcpetion;
import com.rdz.pitufos.business.facade.IBillFacade;
import com.rdz.pitufos.business.mapper.IBillMapper;
import com.rdz.pitufos.business.service.IBillService;
import com.rdz.pitufos.domain.dto.request.BillRequestDto;
import com.rdz.pitufos.domain.dto.response.BillResponseDto;
import com.rdz.pitufos.domain.entity.BillEntity;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillFacadeImpl implements IBillFacade {

    private final IBillService iBillService;
    private final IBillMapper iBillMapper;

    @Override
    public BillResponseDto findById(long id) {
        BillEntity billEntity = iBillService.findById(id).orElseThrow(ResourceNotFoundExcpetion::new);
        return iBillMapper.toBillResponseDto(billEntity);
    }

    @Override
    public List<BillResponseDto> findAll() {
        return iBillService.findAll().stream().map(iBillMapper::toBillResponseDto).toList();
    }

    @Override
    public List<BillResponseDto> findAllActive(){
        return iBillService.findAllActive().stream().map(iBillMapper::toBillResponseDto).toList();
    }

    @Override
    public BillResponseDto save(BillRequestDto billRequestDto) {
        BillEntity billEntity = iBillMapper.toBillEntity(billRequestDto);
        billEntity.setStatus(false);
        billEntity.setTotal(new BigDecimal(0.00));
        return iBillMapper.toBillResponseDto(iBillService.save(billEntity));
    }

    @Override
    public boolean pay(long billId){
        BillEntity billDetailEntity = iBillService.findById(billId).orElseThrow(ResourceNotFoundExcpetion::new);
        billDetailEntity.setStatus(true);
        iBillService.save(billDetailEntity);
        return true;
    }
    
    @Override
    public BillResponseDto update(long id, BillRequestDto billRequestDto) {
        BillEntity billEntity = iBillService.findById(id).orElseThrow(ResourceNotFoundExcpetion::new);
        billEntity.setStatus(billRequestDto.getStatus());
        return iBillMapper.toBillResponseDto(iBillService.save(billEntity));
    }

    @Override
    public boolean delete(long id) {
        if(!iBillService.existById(id)) throw new ResourceNotFoundExcpetion();
        iBillService.delete(id);
        return true;
    }

}
