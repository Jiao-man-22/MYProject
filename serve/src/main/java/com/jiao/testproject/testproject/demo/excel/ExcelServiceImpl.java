package com.jiao.testproject.testproject.demo.excel;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiao.testproject.testproject.dao.FileEntityMapper;
import com.jiao.testproject.testproject.entity.FileEntity;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;


@Log4j2
@Service
@Transactional
public class ExcelServiceImpl implements IExcelService {

    @Autowired
    private FileEntityMapper fileEntityMapper;

    @Transactional
    @Override
    public OutputStream exportBigExcel(HttpServletResponse response) {
        ExecutorService executorService = ThreadUtil.newFixedExecutor(3,"fixed",false );
        List<Future<?>> futures = new ArrayList<>();
        AtomicInteger atoInt = new AtomicInteger(0);
        AtomicInteger atoCount  = new AtomicInteger(0);
        //存放 返回的 excel 数据  多线程集合
        final List<FileEntity> mergerList = Collections.synchronizedList(new ArrayList<>());

        Callable<List<FileEntity>> callable_1 = new Callable<List<FileEntity>>() {

            @Override
            public List<FileEntity> call() throws Exception {

                return null;
            }
        };

        Callable<List<FileEntity>> callable_2 = new Callable<List<FileEntity>>() {

            @Override
            public List<FileEntity> call() throws Exception {
                return null;
            }
        };
        Callable<List<FileEntity>> callable_3 = new Callable<List<FileEntity>>() {

            @Override
            public List<FileEntity> call() throws Exception {
                return null;
            }
        };

        IntBinaryOperator add = (c,u)-> c + u ;
        for (int i = 0; i < 3; i++) {
            final int startIndex = i * 10000;

            log.info( " \n 打印 第 " + i + " 次 查询 "  + " startIndex 的 value " + startIndex);
            futures.add(executorService.submit(new FutureTask<>(new Callable<List<FileEntity>>() {
                @Override
                public List<FileEntity> call() throws Exception {
                    //
                    Page<FileEntity> fileEntityPage = fileEntityMapper
                            .selectPage(new Page<>(startIndex, 10000),
                                    new QueryWrapper<FileEntity>());
                    List<FileEntity> records = fileEntityPage.getRecords();
                    mergerList.addAll(records);
                    log.info("print 代码执行 ============================== " + startIndex);
                    return mergerList;
                }
            })));
        }
        executorService.shutdown();
        this.waitFinish(futures);
        log.info("=========合计 的 总数据量 ============="+ mergerList.size() + "=========================================="+ "加上 ato的值 " + atoInt);
        OutputStream excelStream = getExcelStream(mergerList, response);
        return excelStream;
    }

    private OutputStream getExcelStream(List<FileEntity> mergerList ,HttpServletResponse response) {
        ServletOutputStream outputStream = null;
        BigExcelWriter bigWriter = ExcelUtil.getBigWriter();
        bigWriter.addHeaderAlias("fileId" ,"文件主键");
        bigWriter.addHeaderAlias("fileName" , "文件名称");
        bigWriter.addHeaderAlias("uploadTime", "上传时间");
        bigWriter.addHeaderAlias("createTime", "创建时间");
        bigWriter.merge(4,"文件上传列表");
        bigWriter.write(mergerList,true);
        String filename = new String("精品书籍列表");
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-disposition", "attachment;filename=" + filename);
            outputStream = response.getOutputStream();
            bigWriter.flush(outputStream,true);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            bigWriter.close();
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return outputStream ;
    }

    /**
     * 线程池内线程是否已全部执行结束
     *
     * @param futures 异步线程Future集合
     */
    @SneakyThrows
    private void waitFinish(List<Future<?>> futures) {
        for (Future<?> future : futures) {
            future.get();
        }
    }

}
