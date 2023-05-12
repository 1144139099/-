package com.hlh.repair.service.impl;

import com.hlh.repair.domain.entity.Repair;
import com.hlh.repair.repository.RepairRepository;
import com.hlh.repair.service.RepairService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hlh
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RepairserviceImpl implements RepairService {
    @Resource
    private RepairRepository repairRepository;
    @Override
    public Page<Repair> getPageShareByAudit(int pageNum, int pageSize, String status) {
        PageRequest pageRequest = PageRequest.of(pageNum,pageSize, Sort.by("createTime").descending());
        return repairRepository.findByStatus(status,pageRequest);
    }

    @Override
    public List<Repair> getSharesByUserId(Integer userId) {
        return repairRepository.findAllByUserId(userId,Sort.by("createTime").descending());
    }

    @Override
    public Repair addShare(Repair repair) {
        return repairRepository.saveAndFlush(repair);
    }

    @Override
    public Repair findById(Integer id) {
        return repairRepository.findById(id).orElse(null);
    }
}
