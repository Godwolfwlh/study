package com.common.upload;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 文件上传实体，返回文件基本信息
 * @author Administrator
 *
 */
public class FileEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	//完整目录
	private String path;
	
	//文件大小
	private long fileSize;
	//文件名称
	private String fileName;
	//文件保存目录
	private String savePath;
	//源文件名称
	private String oldFileName;
	//保存后文件名称
	private String HashSavePath;
	//文件扩展名
	private String fileExt;
	//上传日期
	private Timestamp uploadDate;
	
	//上传进度
	private int uploadPress;
	
	//上传百分比
	private int uploadPercentage;
	
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getUploadPercentage() {
		return uploadPercentage;
	}
	public void setUploadPercentage(int uploadPercentage) {
		this.uploadPercentage = uploadPercentage;
	}
	public int getUploadPress() {
		return uploadPress;
	}
	public void setUploadPress(int uploadPress) {
		this.uploadPress = uploadPress;
	}
	public Timestamp getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Timestamp uploadDate) {
		this.uploadDate = uploadDate;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getOldFileName() {
		return oldFileName;
	}
	public void setOldFileName(String oldFileName) {
		this.oldFileName = oldFileName;
	}
	public String getHashSavePath() {
		return HashSavePath;
	}
	public void setHashSavePath(String hashSavePath) {
		HashSavePath = hashSavePath;
	}
	public String getFileExt() {
		return fileExt;
	}
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
}
