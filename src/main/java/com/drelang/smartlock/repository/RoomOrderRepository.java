package com.drelang.smartlock.repository;

import com.drelang.smartlock.domain.RoomOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomOrderRepository extends JpaRepository<RoomOrder, Long> {
//    @Query("select  NEW  com.drelang.smartlock.dto.RoomOrderResult(t1.id) " +
//            "from room_order t1 left join room_info t2 on (t1.room_id = t2.id)" +
//            "where t1.user_id = ?1")
//    List<RoomOrderResult> getResultsByUserId(Long userId);

    /**
     * 根据 userId 来获取特定用户的订单信息
     * @param userId  用户id
     * @return List<RoomOrder>
     */
    List<RoomOrder> getAllByUserId(Long userId);

    /**
     * 获取特定管理员范围内的用户房间申请
     * @param auditorId  管理员id
     * @return List<RoomOrder>
     */
    List<RoomOrder> getAllByAuditorId(Long auditorId);
}
