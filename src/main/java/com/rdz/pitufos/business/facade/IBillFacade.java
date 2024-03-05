package com.rdz.pitufos.business.facade;


import com.rdz.pitufos.domain.dto.request.BillRequestDto;
import com.rdz.pitufos.domain.dto.response.BillResponseDto;

import java.util.List;

public interface IBillFacade {

    BillResponseDto findById(long id);
    List<BillResponseDto> findAll();

    List<BillResponseDto> findAllActive();
    boolean pay(long billId);

    BillResponseDto save(BillRequestDto billRequestDto);
    BillResponseDto update(long id, BillRequestDto billRequestDto);
    boolean delete(long id);
}
