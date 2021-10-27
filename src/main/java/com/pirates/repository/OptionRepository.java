package com.pirates.repository;

import com.pirates.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {
    @Query("SELECT min(o.price) FROM Option o where o.product.id in :productIdList group by o.product.id")
    List<Long> getLowestPrice(@Param("productIdList") List<Long> productIdList);
}
