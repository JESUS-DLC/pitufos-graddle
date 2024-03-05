package com.rdz.pitufos.business.service.impl;


import com.rdz.pitufos.business.service.IBillDetailService;
import com.rdz.pitufos.domain.entity.BillDetailEntity;
import com.rdz.pitufos.repository.IBillDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BillDetailServiceImpl implements IBillDetailService {

    private final IBillDetailRepository iBillDetailRepository;

    @Override
    public Optional<BillDetailEntity> findById(long id, long billId) {
        return iBillDetailRepository.findByIdAndBillId(id,billId);
    }

    @Override
    public boolean existByIdAndBillId(long id,long billId){
        return iBillDetailRepository.existsByIdAndBillId(id,billId);
    }

    @Override
    public boolean existById(long id) {
        return iBillDetailRepository.existsById(id);
    }

    @Override
    public boolean existByProductIdAndBillId(long productId, long billId){
        return iBillDetailRepository.existsByProductIdAndBillId(productId, billId);
    }

    @Override
    public Optional<BillDetailEntity> findByProductIdAndBillId(long productId, long billId){
        return iBillDetailRepository.findByProductIdAndBillId(productId,billId);
    }

    @Override
    public List<BillDetailEntity> findAll(long id) {
        return iBillDetailRepository.findByBillId(id);
    }

    @Override
    public BillDetailEntity save(BillDetailEntity billDetailEntity) {
        return iBillDetailRepository.save(billDetailEntity);
    }

    @Override
    public void delete(long id) {
        iBillDetailRepository.deleteById(id);
    }
}
