package com.github.gin.yunsearch.repository;

import com.github.gin.yunsearch.model.Item;
import com.github.gin.yunsearch.model.YunData;
import com.github.gin.yunsearch.model.referrer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * @author GinPonson
 */
public interface YunDataRepository extends JpaRepository<YunData,Long>{

    @Query(value = "select * from yun_data as d where to_days(d.updated_at) <= to_days(now())",nativeQuery = true)
    List<YunData> findByUpdateTimeGreaterThan(Date updateTime);

    @Query(value = "select new com.github.gin.yunsearch.model.Item(d.uuid,d.type,d.pid,d.updateAt,d.createAt,d.invalidCount,d.state,d.accessCode) from YunData as d where d.uuid = ?1")
   List<Item> findItemByUuid(String uuid);

}
