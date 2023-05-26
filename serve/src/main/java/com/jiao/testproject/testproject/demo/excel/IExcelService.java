package com.jiao.testproject.testproject.demo.excel;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;


public interface IExcelService {

    OutputStream exportBigExcel(HttpServletResponse response);

}
