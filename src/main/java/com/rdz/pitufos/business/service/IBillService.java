package com.rdz.pitufos.business.service;


import com.rdz.pitufos.domain.entity.BillEntity;

import java.util.List;
import java.util.Optional;

public interface IBillService {

    Optional<BillEntity> findById(long id);

    List<BillEntity> findAllActive();

    boolean existById(long id);
    List<BillEntity> findAll();
    BillEntity save(BillEntity billEntity);
    void delete(long id);
}
