package com.jiao.testproject.testproject.services.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiao.testproject.testproject.dao.FileEntityMapper;
import com.jiao.testproject.testproject.dao.FileEntityRepository;
import com.jiao.testproject.testproject.dao.UserRepository;
import com.jiao.testproject.testproject.dto.FileDto;
import com.jiao.testproject.testproject.dto.FileViewVo;
import com.jiao.testproject.testproject.dto.FolderDto;
import com.jiao.testproject.testproject.dto.SideDtoVo;
import com.jiao.testproject.testproject.entity.*;
import com.jiao.testproject.testproject.entity.QFileEntity;
import com.jiao.testproject.testproject.services.IFileService;
import com.jiao.testproject.testproject.services.IUserService;
import com.jiao.testproject.testproject.utils.DateUtils;
import com.jiao.testproject.testproject.utils.FieldTranfUtils;
import com.jiao.testproject.testproject.utils.StringUtils;
import com.jiao.testproject.testproject.utils.TreeDataUtils;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sun.istack.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Service
public class  FileServiceImpl extends ServiceImpl<FileEntityMapper , FileEntity> implements IFileService {


    private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);

     //????????????????????????
    private static final String base_path = "E://VirtaulDisk";

    //??????????????????
    private static final String share_stroen="E://VirtaulDisk//shareArea";

    //????????????????????????
    private static final String virture_base_path="E://VirtaulDisk";

    private static final String suffix_name="-VirtaulDisk";

    //?????????????????????????????????
    private static final String defaultUpload="E://VirtaulDisk//";

    //????????????List
    private ArrayList<File> fileList = new ArrayList<>();

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    //??????????????????????????????

    private static final String DEFAULTGEN = "-VirtaulDisk";

    private static final String DEFAULTSHAREFLODER = "shareFolder";

    private static final String DEFAULTUPLOAD = "defaultUpload";

    //??????size ??????
    private final static short BASE_AMOUNT= 10;

    private final static byte FILE_SIZE_B = 1;

    private final static int FILE_SIZE_KB = FILE_SIZE_B << BASE_AMOUNT;
    //????????? ????????????
    private final static long FILE_SIZE_MB = FILE_SIZE_B << BASE_AMOUNT << BASE_AMOUNT;

    //?????? ?????? ?????????
    private final static Integer FILE_SIZE=0;

    @PersistenceContext
    private EntityManager entityManager;

    //??????????????????
    private JPAQueryFactory queryFactory;


    //???????????????????????????????????????????????????JPAQueryFactory
    @PostConstruct
    public void initFactory()
    {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    @Resource
    FileEntityMapper FileEntityMapper;
    
    //??????jpa
    @Resource
    UserRepository userRepository;

    @Resource
    FileEntityRepository fileEntityRepository;

    @Autowired
    private IUserService userService;

    @Transactional
    @Override
    public int insertFileById(FileDto fileDto) {
        FileEntity fileEntity = new FileEntity();
        int inert_flag=0;
        // ????????????
        try{
            StringBuffer subFileName=new StringBuffer(fileDto.getFileName());
            int subIndex = subFileName.lastIndexOf("/");
            String fileName = StringUtils.substring(subFileName.toString(), subIndex+1);
            fileDto.setFileName(fileName);
            //??????filelist ???????????????path
            // ????????????????????????
            //?????????????????????
            StringBuffer subPathName=new StringBuffer(fileDto.getFilePath());
            int pathSubIndex = subPathName.lastIndexOf("/");
            String subPath = StringUtils.substring(subPathName.toString(), 0, pathSubIndex);
            fileDto.setFilePath(subPath);
            FieldTranfUtils.reflectField(fileDto,fileEntity);
            //inert_flag = FileEntityMapper.insert(fileEntity);
            FileEntity save_result = fileEntityRepository.save(fileEntity);
            inert_flag=save_result.getFileId();
        }catch (Exception e){
            log.error("FileServiceImpl ???????????????error | inser???????????? =====");
        }
        return inert_flag;
    }

    @Override
    public List<FileDto> selectFiles() {
        ArrayList<FileDto> fileDtos = new ArrayList<>();
        QFileEntity fileEntity = QFileEntity.fileEntity;
       // List<Tuple> fetch = queryFactory.select().from(fileEntity).fetch();

        List<FileEntity> fileEntities = FileEntityMapper.selectByExample(new FileEntityExample());
        if (fileEntities == null || fileEntities.size()< 1 ){
            return null;
        }
        for (FileEntity fe:fileEntities) {
            this.setSuffix(fe.getFileSize(),fe);
            FileDto fileDto = new FileDto();
            try{
                FieldTranfUtils.reflectField(fe,fileDto);
            }catch (Exception e){
                log.error("FieldTranfUtils ??????"+e);
            }
            fileDtos.add(fileDto);
        }
        return fileDtos;
    }

    @Override
    public List<FileEntity> selectFileByUserId(Integer userId) {
        FileEntityExample fileEntityExample = new FileEntityExample();
        //??????where??????
        FileEntityExample.Criteria criteria = fileEntityExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        fileEntityExample.setOrderByClause("upload_time asc");
        List<FileEntity> fileEntities = FileEntityMapper.selectByExample(fileEntityExample);
        for (FileEntity file:fileEntities) {
            String fileSize = file.getFileSize();
            this.setSuffix(fileSize,file);
        }
        return fileEntities;
    }

    @Override
    public List<FileEntity> selectFileByFileName(String fileName) {
        FileEntityExample fileEntityExample = new FileEntityExample();
        FileEntityExample.Criteria criteria = fileEntityExample.createCriteria();
        criteria.andFileNameEqualTo(fileName);
        List<FileEntity> fileEntities = FileEntityMapper.selectByExample(fileEntityExample);
        return fileEntities;
    }

    @Override
    public int deleteFile(Integer fileId) {
        return 0;
    }

    @Override
    public int deleteFileByUserId(Integer userid) {
        return 0;
    }

    @Override
    public int delFileByFileId(Integer fileid) {

        FileEntity fileEntity = FileEntityMapper.selectByPrimaryKey(fileid);

        File file = new File(virture_base_path);

        if (!fileList.isEmpty()){
            fileList.clear();
        }
        ArrayList<File> postFileList = IteraFolder(file);
        for (File f: postFileList) {
            if (f.getName() !=null && f.getName().contains(fileEntity.getFileName())){
               f.delete();
            }
        }
        int i = FileEntityMapper.deleteByPrimaryKey(fileid);
        return i;
    }

    @Transactional
    @Override
    public int delFileByFileName(String filename) {
        FileEntityExample fileEntityExample = new FileEntityExample();
        FileEntityExample.Criteria criteria = fileEntityExample.createCriteria();
        criteria.andFileNameEqualTo(filename);
        //SqlSession sqlSession = MybatisUtils.getSqlSession();
        int i = FileEntityMapper.deleteByExample(fileEntityExample);
        //sqlSession.commit();
        if (i==0){
            log.info("delete ??????????????????" + i);
        }
        return i;
    }

    /*
    * ???????????????
    * */
    @Override
    public ArrayList<String> createFolders(String path ,String filename,@Nullable String flag) {
        ArrayList<String> folderList = new ArrayList<>();
        File file;
        StringBuilder mappingPath;
        if(!path.contains(filename)){
             mappingPath = new StringBuilder(path + File.separator + filename + new Random().nextInt(1000) );
        }else{
            mappingPath = new StringBuilder(path + new Random().nextInt(1000) );
        }
        //????????????????????????
        if (mappingPath.toString().contains("E:") && flag == null){
           file = new File(mappingPath.toString());
        }else if(mappingPath.toString().contains("E:") && flag != null){
            String childFileName="???????????????_"+DateUtils.getSysTimeDoId();
            file = new File(mappingPath.substring(0,mappingPath.length()-3),childFileName);
        }else{
            file=new File(virture_base_path+File.separator+mappingPath);
        }
        String fullName = file.getName();
        if (file.exists()){
            folderList.add(file.getAbsolutePath());
            return folderList;
        }else if (!file.exists()){
            // -1
            int indexOf = fullName.lastIndexOf(".");
            if (indexOf != -1){
                folderList.add(file.getAbsolutePath());
            }else{
               try{
                   boolean flag_temp=file.mkdirs();
                   if(flag_temp){
                       folderList.add(file.getAbsolutePath());
                   }
               }catch (Exception e){
                   log.error("?????????????????????" +e);
               }
            }
        }
        return folderList;
    }

    /*???????????????*/
    @Override
    public File retrievalFileName(String fileName,@Nullable String path){
        String filepath="E://VirtaulDisk//ww_3-VirtaulDisk";
        try{
            File file =new File(filepath);
            ArrayList<File> files = IteraFolder(file);
            for (File f: files) {
                if(f.getName().equals(fileName)){
                    return f;
                }
            }
        }catch (Exception e){
            log.error(e+"====?????????????????????=====");
            log.error(e.toString());
        }
        return null;
    }

    //???????????????
    @Override
    public void copyFile(String sourcePath,@Nullable String targetPath,String filename)  {
        //??????????????????
        String absolutPath=sourcePath;
        String targetPathName;
        if (targetPath == null || "".equals(targetPath)){
            targetPathName=share_stroen;
        }else {
             targetPathName=targetPath;
        }
        if (targetPath!=null && !"".equals(targetPath) && !targetPath.contains(filename)){
            targetPathName =targetPath +File.separator+ filename;
        }else {
            targetPathName += File.separator + filename;
        }
        FileInputStream fis=null;
        FileOutputStream fos=null;
        try{
            File file = new File(absolutPath);
            if (file.isFile()){
                fis = new FileInputStream(file);
                File target =new File(targetPathName);
                fos = new FileOutputStream(target);
                byte[] buf = new byte[1024];
                int bytesRead;
                while ((bytesRead = fis.read(buf)) > 0) {
                    fos.write(buf, 0, bytesRead);
                }
            }else {
                log.info("???????????????" );
            }
        }catch (Exception e){
            log.error("?????? ??????NG " + e );
        }finally {
           try{
               fis.close();
               fos.close();
           } catch (IOException e){
               log.error("????????? ???????????? " +  e);
           }
        }
    }
    @Override
    public void copyFile(File source, File target) throws IOException {
        FileInputStream fis=null;
        FileOutputStream fos=null;
        if (!source.exists()){
            log.error("file not exist" + source);
        }
        try {
            fis = new FileInputStream(source);
            fos = new FileOutputStream(target);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buf)) > 0) {
                fos.write(buf, 0, bytesRead);
            }

        } catch (Exception e) {
            log.error("??????????????????" +e);
        }finally {
            try{
                fos.flush();
                fis.close();
                fos.close();
                source.delete();
            } catch (IOException e){
                log.error("????????? ???????????? " +  e);
            }
        }

    }

    /*
    *??????????????????
    * ?????????????????????error
    *
    * @prama  File ????????????
    * void    ????????????*/
    @Transactional
    @Override
    public synchronized String fileShare(String fileName, String absolutPath){
        try{
            File share_folder = new File(share_stroen);
            File file;
            if (share_folder.exists() && share_folder.isDirectory()){
                if (!absolutPath.contains(fileName)){
                     file=new File(absolutPath + File.separator +fileName);
                }else{
                     file=new File(absolutPath);
                }
                file.setReadable(true);
                int indexOf = absolutPath.lastIndexOf(":");
                String pathString = absolutPath.substring(indexOf + 1);
                //File file_target=new File(share_stroen +pathString);
                File file_target = new File(share_stroen+File.separator+ fileName);

                copyFile(absolutPath,fileName,file_target.getAbsolutePath());
                file_target.setReadable(true);
                file_target.setWritable(true);
                //this.copyFile(file,file_target);
                file_target.setWritable(false);
                return file_target.getAbsolutePath();
            }else if(!share_folder.exists()){
                share_folder.mkdirs();
                this.fileShare(fileName,absolutPath);
            }
        }catch (Exception e){
            log.error("???????????? err"+e);
        }
        return null;
    }

    /*????????????????????????*/
    @Override
    public boolean delFileByPath(String path) {
        File file = new File(path);
        log.info(" jpa ?????? "+file.getName());
        QFileEntity qfileEntity = QFileEntity.fileEntity;
        FileEntity fileEntity_result = queryFactory.select(qfileEntity)
                .from(qfileEntity)
                .where(qfileEntity.fileName.eq(file.getName()))
                .fetchOne();
        FileEntity fileEntity_param = new FileEntity();
        if (fileEntity_result == null || fileEntity_result.getFileId() == null || fileEntity_result.getFileName() == null){
            log.info("fileEntity_result : null " );
            return false;
        }
        fileEntity_param.setFileName(fileEntity_result.getFileName());
        fileEntity_param.setFileId(fileEntity_result.getFileId());
        fileEntityRepository.delete(fileEntity_param);
        if (file.exists() && file.isFile()){
            file.delete();
            return true;
        }else if (file.exists() && file.isDirectory()){
            delFolder(file);
            file.delete();
            return true;
        }
        return false;
    }

    /*?????????????????????????????????*/
    @Override
    public List<FolderDto> initUserVirtualDisk(String username, String userid) {
        List<FolderDto> folderDtos=null;
        FolderDto folderDto = new FolderDto();
        //???file ???????????????
        List<File> files = new ArrayList<>();
        try{
            //????????????????????????????????????????????????
            File file = new File(virture_base_path ,username + "_" + userid+suffix_name);
            File uploadFolder = new File(file.getAbsolutePath(),"defaultUpload");
            File shareFolder = new File(file.getAbsolutePath(),"shareFolder");
            //???????????? ??? ??????????????????
            if (file.exists() && uploadFolder.exists()){
                folderDtos = fileStructrue(file);
                return folderDtos;
            }else if(!file.exists()){
                file.mkdirs();
                uploadFolder.mkdir();
                shareFolder.mkdir();
                shareFolder.setReadOnly();
                files.add(file);
                files.add(uploadFolder);
                files.add(shareFolder);
                folderDtos = this.setFolder(files);
                return folderDtos;
            }
        }catch (Exception e){
            log.error("initUserVirtualDisk ?????? ???????????? " + e );
        }
        return null;
    }

    /*??????????????????*/
    private void delFolder(File folder){
        File[] files = folder.listFiles();
        try{
            for (File f:files) {
                if (f.isFile()){
                    f.delete();
                }else if (f.isDirectory()){
                    delFolder(f);
                    f.delete();
                }
            }
        }catch (Exception e){
            log.error("??????????????????"+ e);
        }
    }

    /*
     * ???????????????????????????
     * */
    @Override
    public List<FolderDto> fileStructrue(){
        ArrayList<FolderDto> files_folders = new ArrayList<>();
        File file = new File(virture_base_path);
        if (file.exists()){
            IterafileStructrue(file,files_folders);
            //?????????????????????
/*            FolderDto folderDto = new FolderDto();
            folderDto.setName(file.getName());
            folderDto.setParent_name(subFileName(file.getParent()));
            folderDto.setAbsolutePath(subFileName(file.getPath()));
            folderDto.setRole(0);*/
            //files_folders.add(folderDto);
        }else{
            File initFileStructrue = fileStructrueInit(virture_base_path);
            return files_folders;
        };
        return files_folders;
    }

    /*
    * ???????????????????????????*/
    public File fileStructrueInit(String virture_base_path){
        File file = new File(virture_base_path);
        boolean mkdirs = file.mkdirs();
        if (mkdirs){
            return file;
        }
        return null;
    }

    //?????? ????????????????????????
    public List<FolderDto> fileStructrue(File file){
        ArrayList<FolderDto> files_folders = new ArrayList<>();
            IterafileStructrue(file,files_folders);
            //?????????????????????
            FolderDto folderDto = new FolderDto();
            folderDto.setName(file.getName());
            folderDto.setParent_name(subFileName(file.getParent()));
            folderDto.setAbsolutePath(file.getPath());
            folderDto.setRole(0);
            files_folders.add(folderDto);
        return files_folders;
    }

    /*??????????????????*/
    private void IterafileStructrue(File file,ArrayList<FolderDto> files_folders){
        try{
            File[] files=  file.listFiles();
            for (File f: files) {
                if (f.isFile()) {
                    FolderDto folderDto = new FolderDto();
                    folderDto.setName(f.getName());
                    folderDto.setParent_name(subFileName(f.getParent()));
                    folderDto.setAbsolutePath(f.getAbsolutePath());
                    folderDto.setRole(1);
                    files_folders.add(folderDto);
                } else if (f.isDirectory() && f.list() == null) {
                    FolderDto folderDto = new FolderDto();
                    folderDto.setName(f.getName());
                    folderDto.setParent_name(subFileName(f.getParent()));
                    folderDto.setAbsolutePath(f.getAbsolutePath());
                    folderDto.setRole(0);
                    files_folders.add(folderDto);
                } else if (f.isDirectory() && f.list() != null) {
                    FolderDto folderDto = new FolderDto();
                    folderDto.setName(f.getName());
                    folderDto.setParent_name(subFileName(f.getParent()));
                    files_folders.add(folderDto);
                    folderDto.setAbsolutePath(f.getAbsolutePath());
                    folderDto.setRole(0);
                    IterafileStructrue(f, files_folders);
                }
            }
        }catch (Exception e){
            log.error("?????????????????? error" + e );
        }

    }

    //????????????
     ArrayList<File> IteraFolder(File file){
        try{
           if (file.isFile()){
               fileList.add(file);
           }else if(file.isDirectory()){
               File[] files = file.listFiles();
               if (files !=null && files.length>0){
                   for ( File f:files) {
                       IteraFolder(f);
                   }
               }
           }
       }catch (Exception e){
            log.error("????????? error=====");
            e.printStackTrace();
       }
        return fileList;
    }

    /**
     *
     * @param f File
     * @return List<File> ?????? list??????????????? ??????????????????????????????
     */
       private List<File> modifyIterator(File f){
           ArrayList<File> returnArrayList = new ArrayList<>();
           if (f == null || !f.exists()){
               log.info("?????????????????????null ???????????????"+f);
               return returnArrayList;
           }
           if (f.exists() && f.isFile()){
               //??????????????? returnArrayList.add(f);
               return returnArrayList;
           }else if (f.exists() && f.isDirectory()){
               File[] tempFileList = f.listFiles();
                   returnArrayList.add(f);
               for (File ff: tempFileList) {
                   returnArrayList.addAll(modifyIterator(ff));
               }
           }
           return returnArrayList;
       }

    //??????????????????
    @Transactional
    public void handleFileUpload(MultipartFile file, @Nullable String specificPath){
        String originalFilename = file.getOriginalFilename();
       // String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
        //UserDto user = SessionLocal.getUser();
        //??????????????????
        File outFile=null;
        Map<String, UserEntity> userMap = userService.getUserInfoMap();
        UserEntity userinfo = userMap.get("userinfo");
        if (specificPath !=null && !specificPath.equals("''")){
             outFile = new File(specificPath,file.getOriginalFilename());
        }else {
             outFile = new File(defaultUpload +userinfo.getUser_name() +"_"+userinfo.getUser_id().toString()+ "-VirtaulDisk"+File.separator+"defaultUpload" + File.separator +file.getOriginalFilename());
        }
        FileEntity insertFileEntity = new FileEntity();
        insertFileEntity.setUserId(userinfo.getUser_id());
        insertFileEntity.setFileName(file.getOriginalFilename());
        insertFileEntity.setFilePath(outFile.getPath());
        insertFileEntity.setCreaterId(String.valueOf(userinfo.getUser_id()));
        insertFileEntity.setFileSize(String.valueOf(file.getSize()));
        insertFileEntity.setUploadTime(DateUtils.getSysTime());
        insertFileEntity.setIsDelete(0);
        insertFileEntity.setIsShare(0);
        //????????????????????????
        FileEntityMapper.avoidRepeatInsert(insertFileEntity);
        try {
            file.transferTo(outFile);
        } catch (IOException e) {
            log.error("??????????????????"+e);
        }
    }

    /*????????????*/
    private String subFileName(String s){
       // int i = s.lastIndexOf("\\");OK
        int i = s.lastIndexOf(File.separator);
        return s.substring(i+1);
    }

    /*????????????*/
    private  void setSuffix(String fileSize,Object file){
        if (fileSize == null || fileSize.equals("") || !isNumeric(fileSize)){
            log.info("fileSize === null | ???????????????");
            return;
        }
        //?????? 1k ?????? ??????
        //?????? 1k ?????? 1m  ?????? kb
        //?????? 1m ?????? xxxMB
        Integer integer=null;
        // ??????????????? ??????????????? ?????????????????? 1 ????????? 0
        int powerNumberKb = Integer.toBinaryString(FILE_SIZE_KB).length() - 1 ;
        int powerNumberMb = Long.toBinaryString(FILE_SIZE_MB).length() - 1 ;
        if (file instanceof  FileEntity){
            FileEntity convertObj=(FileEntity) file;
            try{
                integer = Integer.valueOf(fileSize);
                if (integer < FILE_SIZE_KB){
                    convertObj.setFileSize(fileSize + "??????");
                }else if (integer > FILE_SIZE_KB && integer >> powerNumberMb <= 1){
                    int size_KB = integer >> powerNumberKb;
                    convertObj.setFileSize(size_KB + "KB");
                }else if(integer >> powerNumberMb > 1){
                    int size_MB = integer >> powerNumberMb;
                    convertObj.setFileSize(size_MB + "MB");
                }
            }catch(NumberFormatException e){
                log.error("setSuffix ?????? integer = " + integer + e);
            }
            return;
        }
        if (file instanceof  FileViewVo){
            FileViewVo convertObj=(FileViewVo) file;
            try{
                integer = Integer.valueOf(fileSize);
                if (integer < FILE_SIZE_KB){
                    convertObj.setFileSize(fileSize + "??????");
                }else if (integer > FILE_SIZE_KB && integer >> powerNumberMb <= 1){
                    int size_KB = integer >> powerNumberKb;
                    convertObj.setFileSize(size_KB + "KB");
                }else if(integer >> powerNumberMb > 1){
                    int size_MB = integer >> powerNumberMb;
                    convertObj.setFileSize(size_MB + "MB");
                }
            }catch(NumberFormatException e){
                log.error("setSuffix ?????? integer = " + integer + e);
            }
            return;

        }


    }

    //set FoderDto
    private List<FolderDto> setFolder(List<File> list){
        List<FolderDto> folderDtoList = new ArrayList<>();
        for (File file : list) {
            FolderDto folderDto = new FolderDto();
            folderDto.setName(file.getName());
            folderDto.setParent_name(subFileName(file.getParent()));
            folderDto.setAbsolutePath(file.getAbsolutePath());
            folderDto.setRole(file.isFile()?1:0);
            folderDtoList.add(folderDto);
        }
        return folderDtoList;
    }

    //????????????share???????????????
    public List<FolderDto>appendFolder(List<FolderDto> listA , @Nullable List<FolderDto> listB){
        if (listB == null || listB.size()<1){
            for (FolderDto fd: listA) {
                String fileName = fd.getName();
                if (fileName.contains("shareFolder")){
                    List<FolderDto> shareFolder = this.getShareFolder(fileName);
                    listB = shareFolder;
                    fileList.clear();
                    break;
                }
            }
        }
        for (FolderDto fd:listB) {
            //??????????????????
            listA.add(fd);
        }
        return listA;
    }

    @Override
    public List<FileViewVo> netDiskViewInit(String userName, String userId , @Nullable String absolutePath) {
        List<FileViewVo> resultFileViewList = new ArrayList<>();

        if (absolutePath == null || absolutePath.equals("''")){
            //???????????????????????????
            String fileName=userName+"_"+userId+"-VirtaulDisk";
            String filePath=base_path+File.separator+fileName;
            File userGenFolder = new File(filePath);
            if (!userGenFolder.exists() || userGenFolder.isFile()){
                log.info("???????????????????????????");
                return null;
            }
            fileList.clear();
            List<File> tempList = iteratorFolder(userGenFolder);
            //??????gen??????
            tempList.add(userGenFolder);
            //??????????????? dto?????????
            resultFileViewList = dtoToResultDto(tempList);
            fileList.clear();
            return resultFileViewList;
        }else if (absolutePath!=null && !absolutePath.equals("''")){
            File file = new File(absolutePath);
            if (file.exists()){
                File[] fileArray = file.listFiles();
                //Arrays????????????ArrayList???????????? AbstractList???add???remove??????
                ArrayList<File> fileList = new ArrayList<>(Arrays.asList(fileArray));
                //??????
                fileList.add(file);
                resultFileViewList = dtoToResultDto(fileList);
                //filter ?????????????????????
                List<FileViewVo> collect = resultFileViewList.stream().filter(x -> !x.getName().equals(file.getName())).collect(Collectors.toList());
                return collect;
            }
        }else {
            log.info("----netDiskViewInit----" + "????????????");
        }
        return resultFileViewList;
    }

    /**
     * <pre>
     *  ??????side?????? ????????????
     *
     * </pre>
     * @param userName
     * @param userId
     * @return List<SideDtoVo>
     */
    @Override
    public Map<String,List<SideDtoVo>> getSideDto(String userName, String userId) {
        //todo
        //db???????????????
        QFileEntity qfileEntity = QFileEntity.fileEntity;
        List<Tuple> collect = queryFactory.select(
                qfileEntity.fileName,
                qfileEntity.fileId,
                qfileEntity.isDelete,
                qfileEntity.isShare
                )
                .from(qfileEntity)
                .where(qfileEntity.userId.eq(Integer.valueOf(userId))).fetch();
        List<SideDtoVo> SideDtoList = collect.stream().map(tuple ->  {
            SideDtoVo sideDtoVo = new SideDtoVo();
            sideDtoVo.setId(tuple.get(qfileEntity.fileId));
            sideDtoVo.setName(tuple.get(qfileEntity.fileName));
            sideDtoVo.setIsDelete(tuple.get(qfileEntity.isDelete));
            sideDtoVo.setIsShare(tuple.get(qfileEntity.isShare));
            return sideDtoVo;
        }).collect(Collectors.toList());
        //??????????????????;
        List<SideDtoVo> myFileList = SideDtoList.stream().filter(x -> x.getIsDelete() != 1 && x.getIsShare() != 1).collect(Collectors.toList());
        //??????????????????
        List<SideDtoVo> myShareList = SideDtoList.stream().filter(x -> x.getIsShare() == 1 && x.getIsDelete() != 1).collect(Collectors.toList());
        //???????????????
        List<SideDtoVo> recycleBinList = SideDtoList.stream().filter(x -> x.getIsDelete() == 1).collect(Collectors.toList());

        Map<String, List<SideDtoVo>> sideDtoResultMap = new HashMap<>();

        sideDtoResultMap.put("????????????",myFileList);
        sideDtoResultMap.put("????????????",myShareList);
        sideDtoResultMap.put("???????????????",recycleBinList);

        return sideDtoResultMap;
    }

    /**
     * <pre>
     *     ?????????????????????path
     *     ?????????????????????
     *     ?????????????????????
     * </pre>
     *
     * @param path
     * @return int 0:OK 1:NG
     */
    @Override
    @Transactional
    public int delFileByMapping(String path) {

        try {
            File file = new File(path);
            if (file.exists() && file.isFile()){
                //????????????
                file.delete();
                //update database record;
                //todo
                FileEntity fileEntity = new FileEntity();
                fileEntity.setFileName(file.getName());
                QFileEntity qfileEntity = QFileEntity.fileEntity;
                // 1 : delete ; 0 :exist
                queryFactory.update(qfileEntity).set(qfileEntity.isDelete,1)
                            .where(qfileEntity.fileName.eq(file.getName())).execute();
                return 0;
            }else if (file.exists() && file.isDirectory()){
                //???????????????????????? ??? ?????? ?????? ???????????????
                List<File> delObjectList = delFolderReturnObject(file);
                file.delete();
                List<FileDto> collect = delObjectList.stream().map(x -> {
                    FileDto fileDto = new FileDto();
                    fileDto.setFileName(x.getName());
                    return fileDto;
                }).collect(Collectors.toList());

                if (collect == null || collect.size()<1){
                    log.info("????????????????????? ??? =    " + collect);
                    return 1;
                }
                //???????????????????????????
                int i=0;
                try {
                    i = FileEntityMapper.batchDelFileByFileName(collect);
                } catch (Exception e) {
                    log.info("?????????????????????===============" +i +'\t'+ e);
                }
                //fileEntityRepository.save()
                return 0;
            }
        } catch (Exception e) {
            log.error("delFileByMapping inner error"+e);
        }
        return 1;
    }

    private List<File> delFolderReturnObject(File file) {
        List<File> returnFileList=new ArrayList<>();
        File[] files = file.listFiles();
        for (File f:files) {
            if (f.exists() && f.isFile()){
                f.delete();
                returnFileList.add(f);
            }else if (f.exists() && f.isDirectory()){
                f.delete();
                returnFileList.addAll(delFolderReturnObject(f));
            }

        }
        return returnFileList;
    }

    /**
     *
     *
     * @return
     */
    @Override
    @Transactional
    public int updateFileShareByFileName(FileViewVo fvv){
        String sourcePath=fvv.getPath();
        String fileName = sourcePath.substring(sourcePath.lastIndexOf(File.separator) + 1);
        //?????????????????? ???????????????
        if (fvv.getShare() != null && fvv.getShare() == 1){
            try {
                this.copyFile(sourcePath,"",fileName);
            } catch (Exception e) {
                log.error("updateFileShareByFileName " +  e);
            }
        }else if (fvv.getShare() != null && fvv.getShare() == 0){
            //delete  local file
            File file = new File(fvv.getPath());
            file.exists();
            //file.delete();
        }else{
            log.info("FileViewVo??????  ---???????????????--- ????????????--- ");
        }

        QFileEntity qfileEntity = QFileEntity.fileEntity;
        long execute = queryFactory.update(qfileEntity).set(qfileEntity.isShare, fvv.getShare())
                .where(qfileEntity.fileName.eq(fvv.getName())).execute();
        return (int) execute;
    }

    @Override
    @Transactional
    public int deleteByMarkFile(Integer userId, Integer markStatus) {
        int i=0;
        FileEntityExample fileEntityExample = null;
        try {
            fileEntityExample = new FileEntityExample();
            FileEntityExample.Criteria criteria = fileEntityExample.createCriteria();
            criteria.andUserIdEqualTo(userId);
            criteria.andIsDeleteEqualTo(1);
            i = FileEntityMapper.deleteByExample(fileEntityExample);
        } catch (Exception e) {
            log.error("deleteByMarkFile" + e);
        }
        return i;
    }

    @Override
    public List<FileViewVo> getSideViewModify() {

        UserEntity userinfo = userService.getUserInfoMap().get("userinfo");

        File genPath = new File(defaultUpload
                +userinfo.getUser_name()
                +"_"+userinfo.getUser_id().toString()
                + "-VirtaulDisk");
        List<File> fileListShowSideView = modifyIterator(genPath);
        //????????????
        List<FileViewVo> returnCollect = fileListShowSideView.stream().map(f -> {
            FileViewVo fileViewVo = new FileViewVo();
            fileViewVo.setName(f.getName());
            fileViewVo.setRole(f.isFile() ? 1 : 0);
            fileViewVo.setParentName(subFileName(f.getParent() ));
            fileViewVo.setAbsolutePath(f.getAbsolutePath());
            return fileViewVo;
        }).collect(Collectors.toList());
        return returnCollect;
    }

    @Override
    public List<FileViewVo> setShareFileView() {
        //todo
        QFileEntity qfileEntity = QFileEntity.fileEntity;
        List<Tuple> collect = queryFactory.select(
                        qfileEntity.fileName,
                        qfileEntity.fileId,
                        qfileEntity.isDelete,
                        qfileEntity.isShare,
                        qfileEntity.userId,
                        qfileEntity.filePath
                )
                .from(qfileEntity)
                .where(qfileEntity.isShare.eq(Integer.valueOf(1)),qfileEntity.isDelete.eq(Integer.valueOf(0))).fetch();
        List<FileViewVo> SideDtoList = collect.stream().map(tuple ->  {
            FileViewVo fvv =new FileViewVo();
            fvv.setId(tuple.get(qfileEntity.fileId));
            fvv.setName(tuple.get(qfileEntity.fileName));
            fvv.setShare(tuple.get(qfileEntity.isShare));
            fvv.setIsDelete(tuple.get(qfileEntity.isDelete));
            fvv.setAbsolutePath(tuple.get(qfileEntity.filePath));
            return fvv;
        }).collect(Collectors.toList());

        //?????????????????? ???????????????
        File file = new File(share_stroen);
        //????????????????????????????????????
        FileViewVo shareFolderFileViewVo = new FileViewVo();
        //element-ui??????????????????
        shareFolderFileViewVo.setId(999);
        shareFolderFileViewVo.setName(file.getName());
        SideDtoList.stream().forEach(x->{
            x.setParentName(shareFolderFileViewVo.getName());
            x.setRole(1);

        });
        TreeDataUtils.addIdToTree(SideDtoList);
        shareFolderFileViewVo.setChildrenList(SideDtoList);

        ArrayList<FileViewVo> returnData = new ArrayList<>(SideDtoList.size() + 5);
        returnData.add(shareFolderFileViewVo);
        return returnData;
    }

    /**
     *
     * @param source
     * @return
     */
    private List dtoToResultDto(@NotNull Object source) {

        //el-table ?????????????????? gen id ?????? ??? 0
        Integer id=1;
        List<FileViewVo> resultList=new ArrayList<FileViewVo>();
        List<File> source1 = (List<File>) source;
        //???????????????
        File file = source1.get(source1.size() - 1);
        FileViewVo fileViewVo_1 = new FileViewVo();
        fileViewVo_1.setId(id);
        fileViewVo_1.setParentName(subFileName(file.getParent()));
        fileViewVo_1.setName(file.getName());
        if (file.isDirectory()){
            setSuffix(String.valueOf(calculateFolderLength(file)),fileViewVo_1);
        }else if (file.isFile()){
            setSuffix(String.valueOf(file.length()),fileViewVo_1);
        }
        fileViewVo_1.setUpload("2022-05-10:10:05");
        fileViewVo_1.setPath(file.getAbsolutePath());
        fileViewVo_1.setRole(file.isFile()?1:0);
        setExtensionName(fileViewVo_1);
        resultList.add(fileViewVo_1);
        try {
            source1.remove(source1.size()-1);
        } catch (Exception e) {
            log.info("??????");
            return null;
        }
        if (source instanceof List){
            for (File f : source1) {
                FileViewVo fileViewVo = new FileViewVo();
                fileViewVo.setId(++id);
                fileViewVo.setParentName(subFileName(f.getParent()));
                fileViewVo.setName(f.getName());
                if (f.isDirectory()){
                    setSuffix(String.valueOf(calculateFolderLength(f)),fileViewVo);
                }else if (f.isFile()){
                    setSuffix(String.valueOf(f.length()),fileViewVo);
                }
                fileViewVo.setUpload(f.isFile()?DateUtils.formatTime(f.lastModified()):"2022-05-10:10:05");
                fileViewVo.setPath(f.getAbsolutePath());
                fileViewVo.setRole(f.isFile()?1:0);
                setExtensionName(fileViewVo);
                resultList.add(fileViewVo);
            }
        }
        //??????????????????
        resultList.stream().forEach(x->{
            if(x.getName().contains(DEFAULTGEN)){
                x.setNotAllowOperator(true);
            }

            if(x.getName().equals(DEFAULTUPLOAD)){
                x.setNotAllowOperator(true);
            }

            if(x.getName().equals(DEFAULTSHAREFLODER)){
                x.setNotAllowOperator(true);
            }
        });
        //todo???????????????
        List<FileViewVo> fileViewVos = setShareByRecord(resultList);
        return fileViewVos;
    }

    //??????shareFolder????????????????????????
    public List<FolderDto> getShareFolder(String parentDir){
        ArrayList<FolderDto> folderDtoList = new ArrayList<>();
        File file = new File(share_stroen);
        ArrayList<File> fileList_1 = IteraFolder(file);
        for (File f: fileList_1) {
            FolderDto folderDto = new FolderDto();
            folderDto.setParent_name(parentDir);
            folderDto.setName(f.getName());
            folderDto.setRole(1);
            folderDto.setAbsolutePath(f.getAbsolutePath());
            folderDtoList.add(folderDto);
        }
        return folderDtoList;
    }

    /**
     * ???????????????????????????????????????????????????
     * @param str
     * @return
     */
    public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    /*
    *
    * */
    public List iteratorFolder(File file){
        for (File f : file.listFiles()) {
            if(f.exists() && f.isFile()){
                fileList.add(f);
            }else if(f.exists() && file.isDirectory()){
                fileList.add(f);
                iteratorFolder(f);
            }
        };
        List<File>  resultFile= new ArrayList<>();
        resultFile.addAll(fileList);
        return resultFile;
    }

    /**
     * <pre>
     *     ?????????????????????
     *     ????????? ??????????????????
     *     ?????? ????????????
     * </pre>
     * @param fvList
     */
    @Transactional
    public List<FileViewVo> setShareByRecord(List<FileViewVo> fvList){

        List<FileViewVo> rebuildCollect=null;

        //??????????????????????????????
        FileEntity fileEntity = new FileEntity();
        fileEntity.setUserId(userService.getUserInfoMap().get("userinfo").getUser_id());
        QFileEntity qfileEntity = QFileEntity.fileEntity;
        List<Tuple> fetch = queryFactory.select(qfileEntity.fileName, qfileEntity.fileSize, qfileEntity.isShare ,qfileEntity.uploadTime)
                .from(qfileEntity).where(qfileEntity.userId.eq(fileEntity.getUserId())).fetch();
        List<FileViewVo> collect = fetch.stream().map(tuple -> {
            FileViewVo fileViewVo = new FileViewVo();
            fileViewVo.setName(tuple.get(qfileEntity.fileName));
            fileViewVo.setShare(tuple.get(qfileEntity.isShare));
            fileViewVo.setFileSize(tuple.get(qfileEntity.fileSize));
            fileViewVo.setUpload(tuple.get(qfileEntity.uploadTime));
            return fileViewVo;
        }).collect(Collectors.toList());

        //????????????
        if (collect == null || collect.size()<1){

            return  fvList;
        }
        for (FileViewVo x : collect) {
            rebuildCollect = fvList.stream().map(y -> {
                if (y.getName().equals(x.getName())) {
                    y.setShare(x.getShare());
                    y.setUpload(x.getUpload());
                }
                return y;
            }).collect(Collectors.toList());
        }
        return rebuildCollect;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int cancelShare(FileViewVo fvv){
        long execute=0L;
        QFileEntity qfileEntity = QFileEntity.fileEntity;
        try {
             execute = queryFactory.update(qfileEntity).set(qfileEntity.isShare, fvv.getShare())
                    .where(qfileEntity.fileName.eq(fvv.getName())).execute();

        } catch (Exception e) {
            log.error("cancelShare---???????????????---" + execute);
            //???try catch ??????????????????????????????  ?????????????????????
            //???????????????????????????????????????????????????????????????????????????
            TransactionAspectSupport.currentTransactionStatus();
        }
        return (int)execute;

    }

    public Map getMapCollection(){
        QFileEntity qfileEntity = QFileEntity.fileEntity;
        Map<String,String > map = queryFactory.select(qfileEntity.fileName,qfileEntity.userId).from(qfileEntity)
                .fetch().stream().collect(
                        Collectors.toMap(
                                tuple->tuple.get(0, String.class),
                                tuple->tuple.get(1, String.class)
                        )
                );
        return map;

    }


    //???????????? ??????
    //????????????????????? length
    private <T> Long calculateFolderLength(T folder){
        Long fileSizeTotal=0L;
        if (folder instanceof  File){
            //listFiles ???????????????????????????????????????????????????????????????????????????????????????????????? I/O ?????????????????? null?????? ???????????????
            File[] files = ((File) folder).listFiles();
            if (files == null || files.length <1){
                return fileSizeTotal;
            }
            for (File f: files) {
                if (f.isFile()){
                    fileSizeTotal += f.length();
                }
                if (f.isDirectory()){
                    fileSizeTotal += calculateFolderLength(f);
                }
            }
            return fileSizeTotal;
        }
        return null;
    }

    private void setExtensionName(FileViewVo fvv){
        String[] extensionArray={"txt","doc","png","jpg","zip","docx"};
        List<String>  extensionList = Arrays.asList(extensionArray);

        String fullName = fvv.getName();
        if (fullName != null && !fullName.equals("''")){
            int indexOf = fullName.lastIndexOf(".");
            String extensionName = fullName.substring(indexOf + 1);
            if (extensionList.contains(extensionName)){
                fvv.setExtensionFlag(1);
            }else  if (fvv.getRole() == 0){
                fvv.setExtensionFlag(0);
            }else {
                fvv.setExtensionFlag(2);
            }
        }
    }


    private void editRedis(){
        //redisTemplate???????????????????????????
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        String s = redisTemplate.opsForValue().get("");
    }

    @Override
    public List<FileDto> getPageFileData() {
        IPage<FileEntity> fileDtoPage = new Page<>(1,10);
        List<FileDto> fileDtoList = new ArrayList<>();

        IPage<FileEntity> fileEntityIPage = FileEntityMapper.selectPageVo(fileDtoPage);
        List<FileEntity> records = fileEntityIPage.getRecords();
        Map<Integer, FileEntity> appleMap = records.stream().collect(Collectors.toMap(FileEntity::getFileId, a -> a,(k1,k2)->k1));
        appleMap.forEach((k,v) -> {
            FileDto fileDto = new FileDto();
            fileDto.setFileId(k);
            fileDto.setFileName(v.getFileName());
            fileDtoList.add(fileDto);
        });
        return fileDtoList;
    }

    @Override
    public List<Map<String, Object>> getMapList() {

        Map mapList = FileEntityMapper.getMapList();
        List<Map<String, Object>> objects = new ArrayList<>();

        return objects ;
    }

    ;



}
