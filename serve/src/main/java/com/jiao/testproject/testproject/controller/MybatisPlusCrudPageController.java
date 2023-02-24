package com.jiao.testproject.testproject.controller;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jiao.testproject.testproject.dto.AjaxResult;
import com.jiao.testproject.testproject.dto.FileDto;
import com.jiao.testproject.testproject.entity.FileEntity;
import com.jiao.testproject.testproject.services.IFileService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Log4j
@RequestMapping("/mybatisplus")
@RestController
public class MybatisPlusCrudPageController {

    @Resource
    private IFileService fileServiceImpl;

    @PostMapping("/insertFunction")
    public void insertFunction(){
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName("色情激情小说");
        UUID uuid = UUID.fastUUID();
        //fileEntity.setFileId(Integer.valueOf(uuid.toString()));
        //boolean save(T entity); // 插入一条记录
        fileServiceImpl.save(fileEntity);
        //boolean saveBatch(Collection<T> entityList);// 插入（批量），默认分批大小为1000
        List<FileEntity> fileEntities = new ArrayList<>();
        for (int i = 0; i <20 ; i++) {
            FileEntity fileEntity1 = new FileEntity();
            fileEntity1.setUserId(i);
            fileEntity1.setFileName("色情激情小说 第 : " + i + " 部"  );
            fileEntities.add(fileEntity1);
        }
        fileServiceImpl.saveBatch(fileEntities);
        //boolean saveBatch(Collection<T> entityList, int batchSize);// 插入（批量）
    }

    @PostMapping("/updateByExample")
    public AjaxResult updateByExample(){



        UpdateWrapper<FileEntity> fileEntityUpdateWrapper = new UpdateWrapper<>();

        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        fileEntityUpdateWrapper.set("update_time",currentTime);
        fileEntityUpdateWrapper.set("updater_id","123");
        fileEntityUpdateWrapper.eq("file_id",1);
        boolean update = fileServiceImpl.update(fileEntityUpdateWrapper);
        log.info(" 更新成功了吗 "+update);
        return AjaxResult.success();
    }

    @GetMapping("/getPageDate")
    public AjaxResult getPageDate(){

        List<FileDto> fileList = fileServiceImpl.getPageFileData();

        int size = fileList.size();
        log.info("打印 分页dto 的 数量 " + size);

        return AjaxResult.success(fileList);
    }


    @GetMapping("/test")
    public AjaxResult test(){

        List<FileDto> fileList = fileServiceImpl.getPageFileData();

        int size = fileList.size();
        log.info("打印 分页dto 的 数量 " + size);

        return AjaxResult.success(fileList);
    }


    @GetMapping("/getMap")
    public AjaxResult getMap(){

        List<Map<String,Object>> fileList = fileServiceImpl.getMapList();

        int size = fileList.size();
        log.info("打印 分页dto 的 数量 " + size);

        return AjaxResult.success(fileList);
    }









}
