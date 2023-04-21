package com.jiao.testproject.testproject.controller;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import com.jiao.testproject.testproject.dao.ICustomerRepository;;
import com.jiao.testproject.testproject.dao.impl.CustomerCrudRepository;
import com.jiao.testproject.testproject.dto.AjaxResult;
import com.jiao.testproject.testproject.dto.FileDto;
import com.jiao.testproject.testproject.dto.UserDto;
import com.jiao.testproject.testproject.dto.UserDtoVo;
import com.jiao.testproject.testproject.entity.*;
import com.jiao.testproject.testproject.services.IFileService;
import com.jiao.testproject.testproject.services.IRegisterService;
import com.jiao.testproject.testproject.services.IUserService;
import com.jiao.testproject.testproject.utils.*;
import org.apache.commons.lang3.mutable.MutableBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;

import java.util.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/common")
public class CommonController {

    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IRegisterService registerService;


    @Autowired
    private  IFileService fileService;

    @Resource
    private  CustomerCrudRepository customerCrudRepository;

    @Resource
    private ICustomerRepository customerRepositoryImpl;

    private static final HashMap<String,Object> dataStore=new HashMap<>();

    @PersistenceContext
    private EntityManager em;

    @GetMapping("/doLogin")
    public AjaxResult doLogin(@RequestParam String username, @RequestParam String password, @RequestParam(required = false) Integer userid){
        if (username == null || password ==null){
            return AjaxResult.error("用户名 | 密码 | userid 为 null");
        }else{
            UserDto userDto = new UserDto();
            userDto.setUserName(username);
            userDto.setPassword(password);
            userDto.setUuid(userid);
            //UserEntity userEntity = userService.selectUserById(userDto);
            int overloadFlag=1;
            UserEntity userEntity = userService.selectUserById(userDto, overloadFlag);
            if (userEntity!=null
               && userDto.getUserName().equals(userEntity.getUser_name())
               && userDto.getPassword().equals(userEntity.getUser_password())){
                if (userEntity.getRole().equals(1) ){
                    dataStore.put("userId",userEntity.getUser_id());
                    UserDto userDto_1=new UserDto();
                    userDto_1.setStatus(Integer.valueOf(0));
                    userService.logOutUser(userDto_1);
                    return AjaxResult.success("1",userEntity);
                }else if (!userEntity.getRole().equals(1)) {
                    //0 :common
                    UserDto userDto_1=new UserDto();
                    userDto_1.setStatus(Integer.valueOf(0));
                    userService.logOutUser(userDto_1);
                    return AjaxResult.success("0",userEntity);
                }
                return AjaxResult.success("登陆失败");
            }
        }
    return AjaxResult.error("NG");
    }

    /**
     * 通用上传请求
     */
    @PostMapping("/upload")
    public AjaxResult uploadFile(MultipartFile file , UserDtoVo userdata) throws Exception
    {
        try
        {
            // 存放的文件路径
            String filePath = FileUtils.getImportPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = filePath + fileName;
            AjaxResult ajax = AjaxResult.success();
            FileDto fileDto = new FileDto();
            fileDto.setUserId(Integer.valueOf(userdata.getUserId()));
            fileDto.setFilePath(url);
            fileDto.setFileName(fileName);
            fileDto.setFileSize(String.valueOf(file.getSize()));
            fileDto.setUploadTime(DateUtils.getSysTime());
            Object userId_1 = dataStore.get("userId");
            if (userId_1 instanceof Integer){
                fileDto.setUserId((Integer) userId_1);
            }
            int i = fileService.insertFileById(fileDto);
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            return AjaxResult.success();
        }
        catch (Exception e)
        {
            log.error("通用上传请求  error");

            return AjaxResult.error();
        }
    }

    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     * @param delete 是否删除
     */
    @GetMapping("common/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response)
    {
        try
        {
            String realFileName=null;
            String filePath=null;
            List<FileEntity> fileEntities = fileService.selectFileByFileName(fileName);
            if (fileEntities == null || fileEntities.size() < 1){
                log.error("fileEntities null | size=0 ");
            }
            for (FileEntity f:fileEntities) {
                realFileName= f.getFileName();
                filePath = f.getFilePath() + File.separator+ f.getFileName();
                String s = StringUtils.excludeString(filePath);
                response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
                FileUtils.setAttachmentResponseHeader(response, realFileName);
                FileUtils.writeBytes(s, response.getOutputStream());
            }
            if (delete)
            {
                FileUtils.deleteFile(filePath);
            }
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

    @GetMapping("/test")
    @Transactional
    public void testJpaCrud() throws ParseException {
//        String sql="SELECT *  FROM filelist";
//         List list = customerCrudRepository.sqlObjectList(sql, null, FileEntity.class);
//
//        for (int i = 0; i < list.size(); i++) {
//            Object o = list.get(i);
//        }

        //customerRepositoryImpl

        TestTableEntity testTableEntity = new TestTableEntity();
        //发生这个原因是因为我们已经在实体类用JPA注解指定了主键的生成策略主键就不能设置了，一旦不为空或者0就被认为是已经保存到了数据库中，一旦调用persist()方法就会抛出上面的异常
        //testTableEntity.setId(3);
        testTableEntity.setName("rong");

        testTableEntity.setSalary(new BigDecimal(22.20));

        testTableEntity.setTime_1(new Date(System.currentTimeMillis()));

        //

        Date date = new Date();
        //SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //String format = dateFormat.format(date);
        //Date parse = dateFormat.parse(format);
        testTableEntity.setTime_2(new Date());
        //SQL查询从表中选择。JPQL从应用程序域模型的实体中选择。
//        List<UserEntity> l = em.createQuery("SELECT e FROM UserEntity e").getResultList();
//        for(UserEntity p:l){
//            System.out.println(p);
//        }
        //Example<TestTableEntity> example = Example.of(testTableEntity);
        // customerRepositoryImpl.findOne(example);
        // em.persist(testTableEntity);
        //通过entityManager将一个存在的实体“同步到”persistenceContext中。
        //em.merge(testTableEntity);
        //em.refresh(testTableEntity);
        //TestTableEntity testTableEntity1 = em.find(TestTableEntity.class, 1);

//        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
//        QFileEntity qfileEntity = QFileEntity.fileEntity;
//        QUserEntity quserEntity = QUserEntity.userEntity;
//        jpaQueryFactory.select();
        StringBuilder content = new StringBuilder(16);
        final MutableBoolean mutableBoolean = new MutableBoolean(true);
        Map<Integer,Integer> map = new HashMap<>(16);
        for (int i = 0; i <10 ; i++) {
            map.put(i+1,i+1);
        }
        map.forEach((k,v)->{
            if (v == 5) {
                System.out.println("55555555555");
                return;
              }
            System.out.println("vvvvvvvv" +v);
            if (v == 8) {
             System.out.println("ssssssssssss");
            }
        });
        System.out.println("way outer");
    }

    @RequestMapping("/testExcelImport")
    public void testExcelImport(HttpServletResponse httpServletResponse) throws UnsupportedEncodingException {

        List<UserEntity> userEntities = userService.exportExcel();
        ExcelWriter writer = ExcelUtil.getWriter();
        //自定义excel标题和列名
        writer.addHeaderAlias("user_id","用户ID");
        writer.addHeaderAlias("user_name","用户名");
        writer.addHeaderAlias("user_password","密码");
        writer.addHeaderAlias("role","邮箱");
        writer.addHeaderAlias("status","状态");
        //合并单元格后的标题行，使用默认标题样式
        writer.merge(5,"用户基本信息表");
        writer.renameSheet(0,"用户登录信息");
        writer.write(userEntities,true);
        //设置要导出到的sheet
        ExcelWriter writer2= writer.setSheet("表2");
        writer2.addHeaderAlias("姓名","姓名___");
        writer2.addHeaderAlias("年龄","年龄___");
        writer2.addHeaderAlias("成绩","成绩___");
        writer2.addHeaderAlias("考试日期","考试日期___");
        Map<String, Object> row1 = new LinkedHashMap<>();
        row1.put("姓名", "张三");
        row1.put("年龄", 23);
        row1.put("成绩", 88.32);
        row1.put("是否合格", true);
        row1.put("考试日期", DateUtil.date());

        Map<String, Object> row2 = new LinkedHashMap<>();
        row2.put("姓名", "李四");
        row2.put("年龄", 33);
        row2.put("成绩", 59.50);
        row2.put("是否合格", false);
        row2.put("考试日期", DateUtil.date());

        ArrayList<Map<String, Object>> rowList = CollUtil.newArrayList(row1, row2);
        writer2.merge(5, "一班成绩单");
        writer2.write(rowList,true);
        writer2.writeCellValue(5,3,"总分");
        writer2.writeCellValue(6,3,"12000");
        writer.setSheet("表3");
        httpServletResponse.setContentType("application/vnd.ms-excel;charset=utf-8");
//name是下载对话框的名称，不支持中文，想用中文名称需要进行utf8编码
        String excelName = "用户基本信息表";
//excelName = new String(excelName.getBytes(),"utf-8");
        excelName = URLEncoder.encode(excelName, "utf-8");
        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + excelName +".xls");
        //将excel文件信息写入输出流，返回给调用者
        ServletOutputStream excelOut = null;
        try {
            excelOut = httpServletResponse.getOutputStream();
            writer.flush(excelOut,true);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            writer.close();
        }
        IoUtil.close(excelOut);
    }

//    解析导入的execl
    @PostMapping("/importExcel")
    @Transactional(rollbackFor = Exception.class)
    public void importExcel(@RequestPart("file") MultipartFile file){
      //将文件打成io
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //指定输入流和sheet
        ExcelReader reader = ExcelUtil.getReader(inputStream, 0);
        // 读取第二行到最后一行数据
        List<List<Object>> read = reader.read(1, reader.getRowCount());
        List<UserEntity> userEntities = new ArrayList<>();
        for (List<Object> list: read) {
            UserEntity userEntity = new UserEntity();
            Integer o = Integer.parseInt(String.valueOf(list.get(0)));//读取某行第一列数据 顺着取到 N 列
            String o1 = (String) list.get(1);
            String o2 = (String) list.get(2);
            Integer o3 = Integer.parseInt(String.valueOf(list.get(3))) ;
            String o4 = list.get(4) +"";
            userEntity.setUser_id(o);
            userEntity.setUser_password((String)o1);
            userEntity.setUser_name((String) o2 );
            userEntity.setRole((Integer) o3 );
            userEntity.setCreate_time((String) o4 );
            userEntities.add(userEntity);
        }
        boolean b = registerService.saveBatch(userEntities);
        System.out.println(" 批量导入 "+ b );
    }
    }









