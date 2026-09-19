package com.policy.dto;

public class PdfResponseDto {
	
	private String message;
	private String fileName;
	private String filePath;
	
	public PdfResponseDto() {
		super();
	}

	public PdfResponseDto(String message, String fileName, String filePath) {
		super();
		this.message = message;
		this.fileName = fileName;
		this.filePath = filePath;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String toString() {
		return "PdfReponseDto [message=" + message + ", fileName=" + fileName + ", filePath=" + filePath + "]";
	}
	
	

}
