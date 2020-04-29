package com.ydj.logback.service.impl;

import com.ydj.logback.service.InnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("innerService")
@Slf4j
public class InnerServiceImpl implements InnerService {


    @Override
    public List<LocalDateTime> inner(Long id, String name) {
        log.info(">>>> inner service start <<<<");

        List<LocalDateTime> list = new ArrayList<>();
        list.add(LocalDateTime.now());
        list.add(LocalDateTime.now());
        list.add(LocalDateTime.now());
        list.add(LocalDateTime.now());
        list.add(LocalDateTime.now());

        log.info(">>>> inner service end <<<<");
        return list;
    }
}
