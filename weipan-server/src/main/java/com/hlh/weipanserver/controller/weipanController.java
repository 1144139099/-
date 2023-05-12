package com.hlh.weipanserver.controller;

import com.hlh.weipanserver.common.ResponseResult;
import com.hlh.weipanserver.general.domain.Weipan;
import com.hlh.weipanserver.general.service.WeipanService;
import com.hlh.weipanserver.utils.MinIoTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/weipan")
public class weipanController {
    @Resource
    private MinIoTemplate minIoTemplate;
    @Resource
    private WeipanService weipanService;

    @PostMapping("/upload/{id}")
    public ResponseResult upload(MultipartFile file, @PathVariable String id) throws Exception {
        minIoTemplate.makeBucket("weipan");
        String originalFileName = file.getOriginalFilename();
        minIoTemplate.putObject("weipan",originalFileName,file.getInputStream());
        Weipan weipan = new Weipan();
        weipan.setCreateTime(new Date());
        weipan.setLink("http://124.221.232.15:9090" + "/" + "weipan" + "/" + originalFileName);
        weipan.setName(originalFileName);
        weipan.setUserId(id);
        weipanService.insert(weipan);
        return ResponseResult.success("保存成功");
    }

    @DeleteMapping("/delete")
    public ResponseResult delete(@RequestParam String name) throws Exception {
        weipanService.delete(name);
        minIoTemplate.removeObject("weipan",name);
        return ResponseResult.success("删除成功");
    }

    @GetMapping("/select/{id}")
    public ResponseResult select(@PathVariable String id){
       List<Weipan> select = weipanService.select(id);
       return ResponseResult.success(select);
    }
}
