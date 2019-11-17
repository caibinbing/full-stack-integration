package main.java.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.alibaba.fastjson.JSONObject;

class ExcelBo{
	private int userId ;    //用户ID
	private int activityId ;    //活动ID
	private int giftId ;    //赠品ID
	private int status ;    //赠品的选择状态
	private String itemName ;    //赠品名称
	private String phone ;    //用户联系方式
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public int getGiftId() {
		return giftId;
	}
	public void setGiftId(int giftId) {
		this.giftId = giftId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public ExcelBo(int userId, int activityId, int giftId, int status, String itemName, String phone) {
		super();
		this.userId = userId;
		this.activityId = activityId;
		this.giftId = giftId;
		this.status = status;
		this.itemName = itemName;
		this.phone = phone;
	}
	
}

public class ExcelExportUtils {
	
	private final static Logger logger = LogManager.getLogger(ExcelExportUtils.class);

	/**
	 *	导出excel表
	 */
	public static void exportExcel() {
		//获取数据,可从数据库获取。
		List<ExcelBo> excelContent = new ArrayList<>() ;
		excelContent.add(new ExcelBo(1 , 10001 , 10001 , 1 , "赠品一" , "13800138000")) ;
		excelContent.add(new ExcelBo(2 , 10001 , 10002 , 1 , "赠品二" , "13800138000")) ;
		excelContent.add(new ExcelBo(3 , 10001 , 10003 , 2 , "赠品三" , "13800138000")) ;
		excelContent.add(new ExcelBo(4 , 10002 , 10004 , 1 , "赠品四" , "13800138000")) ;
		excelContent.add(new ExcelBo(5 , 10002 , 10004 , 1 , "赠品四" , "13800138000")) ;
		excelContent.add(new ExcelBo(6 , 10002 , 10005 , 2 , "赠品五" , "13800138000")) ;
		excelContent.add(new ExcelBo(7 , 10003 , 10006 , 1 , "赠品六" , "13800138000")) ;
		excelContent.add(new ExcelBo(8 , 10003 , 10006 , 1 , "赠品六" , "13800138000")) ;
		excelContent.add(new ExcelBo(9 , 10003 , 10007 , 2 , "赠品七" , "13800138000")) ;
		excelContent.add(new ExcelBo(10 , 10004 , 10008 , 1 , "赠品八" , "13800138000")) ;
		logger.info(JSONObject.toJSONString(excelContent)) ;
		//设置标题名
		String[] titles = {"用户ID","活动ID","赠品ID","赠品名称","领取状态","联系方式"} ;
		
		HSSFWorkbook workbook = new HSSFWorkbook() ;
		HSSFSheet sheet = workbook.createSheet("sheet1") ;
		//第一行标题
		HSSFRow row0 = sheet.createRow(0) ;
		for(int index = 0 ; index < titles.length ; ++index) {
			row0.createCell(index).setCellValue(titles[index]);
		}
		//Excel内容
		for(int i = 0 ; i < excelContent.size() ; ++i) {
			HSSFRow rows = sheet.createRow(i+1) ;
			rows.createCell(0).setCellValue(excelContent.get(i).getUserId());
			rows.createCell(1).setCellValue(excelContent.get(i).getActivityId());
			rows.createCell(2).setCellValue(excelContent.get(i).getGiftId());
			rows.createCell(3).setCellValue(excelContent.get(i).getItemName());
			int status = excelContent.get(i).getStatus() ;
			switch(status) {
			case 0 :
				rows.createCell(4).setCellValue("未预定");
				break ;
			case 1 :
				rows.createCell(4).setCellValue("已预定");
				break ;
			case 2 :
				rows.createCell(4).setCellValue("已领取");
				break ;
			default :
				rows.createCell(4).setCellValue("未知状态");
				break ;
			}
			rows.createCell(5).setCellValue(excelContent.get(i).getPhone());
		}
		
		//输出Excel文件
		OutputStream os = null;
		try {
			String fileName = "赠品预订情况统计表_" + LocalDate.now() ;
			//使用response获取outputStream输出excel表
			/*
			os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment;filename="+ new String(fileName.getBytes("UTF-8"),"ISO8859-1") + ".xls");
			response.setContentType("application/msexcel");
			*/
			
			//本地测试，使用fileOutputStream输出excel表
			os = new FileOutputStream(new File("F:\\"+ fileName + ".xls"), false) ;
			workbook.write(os);
			logger.info("Export Successfully!");
		} catch (IOException e) {
			
		}finally {
			if(null != os) {
				try {
					os.close();
				} catch (IOException e) {
					logger.error("Export Failed!");
				}
			}
		}
		
	}
	public static void main(String[] args) {
		exportExcel() ;
	}

}
