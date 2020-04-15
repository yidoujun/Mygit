package com.ifind.service.impl;

import com.ifind.dao.BillDao;
import com.ifind.entity.Bill;
import com.ifind.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("billService")
public class BillServiceImpl implements BillService {

    @Autowired
    private BillDao dao;

    /**
     * 将所有记录查出来
     * @return
     */
    @Override
    public List<Bill> loadBillList() {
        List<Bill> list = dao.getBillList();
        return list;
    }
}
