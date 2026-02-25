package com.springcloud.ms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.ms.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: yaorp
 */
public interface UserMapper extends BaseMapper<User> {
//    @Select("select * FROM user")
    List<User> qryUsers();
    List<Map> qryEBike();

    List<Map> qryOrder();
    List<Map> qryStatus();

    void upateEBike(@Param("id") String id,
                    @Param("nameD") String nameD,
                    @Param("idCardD") String idCardD,
                    @Param("mobileD") String mobileD);

    void upateOrder(@Param("id") String id,
                    @Param("idCardD") String idCardD
    );

    void upateStatus(@Param("id") String id,
                    @Param("nameD") String nameD,
                    @Param("idCardD") String idCardD,
                    @Param("mobileD") String mobileD);

}
