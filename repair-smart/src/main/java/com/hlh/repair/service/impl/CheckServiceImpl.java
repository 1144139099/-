package com.hlh.repair.service.impl;

import com.hlh.repair.domain.entity.Checkin;
import com.hlh.repair.repository.CheckRepository;
import com.hlh.repair.service.CheckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CheckServiceImpl implements CheckService {
    @Resource
    private CheckRepository checkRepository;
    @Override
    public List<Checkin> getCheckByUserId(Integer userId) {
        return checkRepository.findAllByUserId(userId, Sort.by("createTime").descending());
    }

    @Override
    public Checkin addCheck(Checkin checkin) {
        return checkRepository.saveAndFlush(checkin);
    }

    @Override
    public Checkin findById(Integer id) {
        return checkRepository.findById(id).orElse(null);
    }
}
