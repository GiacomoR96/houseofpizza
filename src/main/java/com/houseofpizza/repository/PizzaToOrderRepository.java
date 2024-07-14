package com.houseofpizza.repository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.houseofpizza.model.PizzaToOrder;

@Component
@Transactional(readOnly = true)
public interface PizzaToOrderRepository extends BaseRepository<PizzaToOrder, Long> {

//    @Query(nativeQuery = true, value = "select * from pizza_to_order pto"
//        + " join order o on pto.id_order = o.id "
//        + " where pto.status = :status and o.lifecycle = :lifecycle")
//    List<PizzaToOrder> findByStatusEqualsAndOrderLifecycleEquals(@Param("status") String status,
//                                                                 @Param("lifecycle") String lifecycle);

}
