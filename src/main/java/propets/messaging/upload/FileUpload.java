package propets.messaging.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

public class FileUpload {
	
	private static final String SAVE_DIR = "D:";
	
	public static String loadFile(String pathInput) {
		LocalDateTime date = LocalDateTime.now();
		String fileName = "" + date.getYear() + date.getMonthValue() + date.getDayOfMonth() + date.getHour() + date.getMinute() + date.getSecond();
		String fileExtension = pathInput.split("\\.")[1];
		String newFile = SAVE_DIR + File.separator + fileName + "." + fileExtension;
		try(FileInputStream fin = new FileInputStream(pathInput); 
				FileOutputStream fout = new FileOutputStream(newFile)){
			int data = fin.read();
			while (data != -1) {
				fout.write(data);
				data = fin.read();
			}
			return newFile;
		}catch (FileNotFoundException e) {
			return "Wrong file name";
		}
		catch (IOException e) {
			return "Uncknow input-output error";
		}
	}
	

}
