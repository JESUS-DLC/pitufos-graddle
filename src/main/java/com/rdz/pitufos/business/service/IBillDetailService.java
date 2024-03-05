package com.rdz.pitufos.business.service;



import com.rdz.pitufos.domain.entity.BillDetailEntity;

import java.util.List;
import java.util.Optional;

public interface IBillDetailService {


    Optional<BillDetailEntity> findById(long id, long billId);

    boolean existByIdAndBillId(long id, long billId);

    boolean existById(long id);

    boolean existByProductIdAndBillId(long productId, long billId);

    Optional<BillDetailEntity> findByProductIdAndBillId(long productId, long billId);

    List<BillDetailEntity> findAll(long id);
    BillDetailEntity save(BillDetailEntity billDetailEntity);
    void delete(long id);
}
