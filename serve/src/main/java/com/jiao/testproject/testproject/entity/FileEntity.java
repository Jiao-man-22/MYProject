package com.jiao.testproject.testproject.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "filelist") //@Table来指定和哪个数据表对应;如果省略默认表名就是user；
@Data
@TableName(value = "filelist")
public class FileEntity implements Serializable {
    /** 
    *
    * DB Column Name: file_id
    */
    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    @Column(name = "file_id")
    private Integer fileId;

    /** 
    * 
    * DB Column Name: file_name
    */
    @Column(name = "file_name" ,columnDefinition = "VARCHAR(40)")
    private String fileName;

    /** 
    * 
    * DB Column Name: file_path
    */
    @Column(name = "file_path")
    private String filePath;

    /** 
    * 
    * DB Column Name: file_size
    */
    @Column(name = "file_size")
    private String fileSize;

    /** 
    * 
    * DB Column Name: upload_time
    */
    @Column(name = "upload_time")
    private String uploadTime;

    /**
     *
     * DB Column Name: is_share
     */
    @Column(name = "is_share")
    private Integer isShare;

    /**
     *
     * DB Column Name: is_delete
     */
    @Column(name = "is_delete")
    private Integer isDelete;
    /** 
    * 
    * DB Column Name: user_id
    */

    @Column(name = "user_id")
    private Integer userId;

    /** 
    * 
    * DB Column Name: creater_id
    */
    @Column(name = "creater_id")
    private String createrId;

    /** 
    * 
    * DB Column Name: create_time
    */
    @Column(name = "create_time")
    private String createTime;

    /** 
    * 
    * DB Column Name: updater_id
    */
    @Column(name = "updater_id")
    private String updaterId;

    /** 
    * 
    * DB Column Name: update_time
    */
    @Column(name = "update_time")
    private String updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table filelist
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column filelist.file_id
     *
     * @return the value of filelist.file_id
     *
     * @mbggenerated
     */
    public Integer getFileId() {
        return fileId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column filelist.file_id
     *
     * @param fileId the value for filelist.file_id
     *
     * @mbggenerated
     */
    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column filelist.file_name
     *
     * @return the value of filelist.file_name
     *
     * @mbggenerated
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column filelist.file_name
     *
     * @param fileName the value for filelist.file_name
     *
     * @mbggenerated
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column filelist.file_path
     *
     * @return the value of filelist.file_path
     *
     * @mbggenerated
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column filelist.file_path
     *
     * @param filePath the value for filelist.file_path
     *
     * @mbggenerated
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column filelist.file_size
     *
     * @return the value of filelist.file_size
     *
     * @mbggenerated
     */
    public String getFileSize() {
        return fileSize;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column filelist.file_size
     *
     * @param fileSize the value for filelist.file_size
     *
     * @mbggenerated
     */
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize == null ? null : fileSize.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column filelist.upload_time
     *
     * @return the value of filelist.upload_time
     *
     * @mbggenerated
     */
    public String getUploadTime() {
        return uploadTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column filelist.upload_time
     *
     * @param uploadTime the value for filelist.upload_time
     *
     * @mbggenerated
     */
    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime == null ? null : uploadTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column filelist.user_id
     *
     * @return the value of filelist.user_id
     *
     * @mbggenerated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column filelist.user_id
     *
     * @param userId the value for filelist.user_id
     *
     * @mbggenerated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column filelist.creater_id
     *
     * @return the value of filelist.creater_id
     *
     * @mbggenerated
     */
    public String getCreaterId() {
        return createrId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column filelist.creater_id
     *
     * @param createrId the value for filelist.creater_id
     *
     * @mbggenerated
     */
    public void setCreaterId(String createrId) {
        this.createrId = createrId == null ? null : createrId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column filelist.create_time
     *
     * @return the value of filelist.create_time
     *
     * @mbggenerated
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column filelist.create_time
     *
     * @param createTime the value for filelist.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column filelist.updater_id
     *
     * @return the value of filelist.updater_id
     *
     * @mbggenerated
     */
    public String getUpdaterId() {
        return updaterId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column filelist.updater_id
     *
     * @param updaterId the value for filelist.updater_id
     *
     * @mbggenerated
     */
    public void setUpdaterId(String updaterId) {
        this.updaterId = updaterId == null ? null : updaterId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column filelist.update_time
     *
     * @return the value of filelist.update_time
     *
     * @mbggenerated
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column filelist.update_time
     *
     * @param updateTime the value for filelist.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }
}