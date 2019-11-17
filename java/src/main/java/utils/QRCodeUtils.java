package main.java.utils;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class QRCodeUtils {

	public static void main(String[] args) throws WriterException, IOException {
		
		/*************************Zxing Generator**************************/
		String text = "I Love You！";
		int width = 300;
		int height = 300;
		String format = "gif";
		
		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		
		BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
		// 生成二维码
		File outputFile = new File("F:" + File.separator + "new.gif");
		MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
		
		/*************************QRCode Generator**************************/
		
		
	}

}
