package com.rdz.pitufos.business.service.impl;


import com.rdz.pitufos.business.service.IBillService;
import com.rdz.pitufos.domain.entity.BillEntity;
import com.rdz.pitufos.repository.IBillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements IBillService {

    private final IBillRepository iBillRepository;

    @Override
    public Optional<BillEntity> findById(long id) {
        return iBillRepository.findById(id);
    }

    @Override
    public List<BillEntity> findAllActive(){
        return iBillRepository.findByStatusIsFalse();
    }

    @Override
    public boolean existById(long id) {
        return iBillRepository.existsById(id);
    }

    @Override
    public List<BillEntity> findAll() {
        return iBillRepository.findAll();
    }

    @Override
    public BillEntity save(BillEntity billEntity) {
        return iBillRepository.save(billEntity);
    }

    @Override
    public void delete(long id) {
        iBillRepository.deleteById(id);
    }
}
