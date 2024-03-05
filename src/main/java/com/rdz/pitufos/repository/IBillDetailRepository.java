package com.rdz.pitufos.repository;

import com.rdz.pitufos.domain.entity.BillDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IBillDetailRepository extends JpaRepository<BillDetailEntity,Long> {

    Optional<BillDetailEntity> findByIdAndBillId(long id, long billId);

    boolean existsByIdAndBillId(long id, long billId);

    List<BillDetailEntity> findByBillId(long id);

    boolean existsByProductIdAndBillId(long productId,long billId);
    
    Optional<BillDetailEntity> findByProductIdAndBillId(long productId,long billId);
}
