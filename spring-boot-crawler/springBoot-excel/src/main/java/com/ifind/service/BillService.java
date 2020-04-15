package com.ifind.service;

import com.ifind.entity.Bill;

import java.util.List;

public interface BillService {

    /**
     * 将所有记录查出来
     * @return
     */
    List<Bill> loadBillList();
}
