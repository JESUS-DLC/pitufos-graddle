package com.rdz.pitufos.business.facade;

import com.rdz.pitufos.domain.dto.request.BillDetailRequestDto;
import com.rdz.pitufos.domain.dto.response.BillDetailResponseDto;

import java.util.List;

public interface IBillDetailFacade {

    BillDetailResponseDto findById(long billId, long billDetailId);
    
    List<BillDetailResponseDto> findAll(long id);
    //BillDetailResponseDto save(long billId, BillDetailRequestDto billDetailRequestDto);
    boolean save(long billId, List<BillDetailRequestDto> billDetailRequestDto);
    
    BillDetailResponseDto update(long billId,long billDetailId, BillDetailRequestDto billDetailRequestDto);

    boolean delete(long billId, long billDetailId);
}
