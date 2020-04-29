package com.ydj.logback.service;

import java.time.LocalDateTime;
import java.util.List;

public interface InnerService {

    List<LocalDateTime> inner(Long id, String name);
}
