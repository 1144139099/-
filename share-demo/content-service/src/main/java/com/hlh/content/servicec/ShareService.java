package com.hlh.content.servicec;

import com.hlh.content.domain.dto.NoteDto;
import com.hlh.content.domain.dto.ShareAuditDto;
import com.hlh.content.domain.dto.ShareQueryDto;
import com.hlh.content.domain.dto.ShareVo;
import com.hlh.content.domain.entity.Share;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author hlh
 */
public interface ShareService {

    Share findById(Integer id);
    Share auditShare(ShareAuditDto shareAuditDto);
    Page<Share> findAll(Pageable pageable, ShareQueryDto shareQueryDto, Integer userId);
    Share exChange(Integer shareId,Integer userId);
    Share Submission(Share share);
    Page<Share> myPost(Integer pageNum,Integer pageSize, Integer userId);
//    List<Share> mySubmission(Integer userId);
Page<ShareVo> getExchangeRecord(Integer pageNum, Integer pageSize, Integer userId);
}
