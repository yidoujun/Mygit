package com.ifind.dao;

import com.ifind.entity.Bill;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BillDao {

    /**
     * 将所有记录查出来
     * @return
     */
    List<Bill> getBillList();
}
