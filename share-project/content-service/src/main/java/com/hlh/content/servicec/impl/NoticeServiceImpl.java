package com.hlh.content.servicec.impl;

import com.hlh.content.domain.entity.Notice;
import com.hlh.content.repository.NoticeRepository;
import com.hlh.content.servicec.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;


    @Override
    public Notice getLatestNotice() {
        Sort sort = Sort.by("createTime").descending();
        return noticeRepository.findByShowFlag(true,sort).get(0);
    }
}
