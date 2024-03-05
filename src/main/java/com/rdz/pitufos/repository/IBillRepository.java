package com.rdz.pitufos.repository;


import com.rdz.pitufos.domain.entity.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface IBillRepository extends JpaRepository<BillEntity,Long> {

    boolean existsById(long id);
    List<BillEntity> findByDateBetween(Date inicio,Date fin);
    List<BillEntity> findByStatusIsFalse();
}
