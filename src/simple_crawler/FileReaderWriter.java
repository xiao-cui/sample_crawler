package simple_crawler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderWriter {
	
	public static boolean createNewFile(String filePath) {
		boolean isSuccess=true;
		//replace all '\\' with '/'
		String filePathTurn=filePath.replaceAll("\\\\", "/");
		//retrieve the folder where the file locates in
		//filePath="D:/document/temp.txt"
		//dir="D:/document"
		int index=filePathTurn.lastIndexOf("/");
		String dir=filePathTurn.substring(0, index);
		//create a new folder
		File fileDir=new File(dir);
		isSuccess=fileDir.mkdirs();
		//create a new file
		File file=new File(filePathTurn);
		try{
			isSuccess=file.createNewFile();
		}catch(IOException e){
			isSuccess=false;
			e.printStackTrace();
		}
		return isSuccess;
	}
	
	public static boolean writeIntoFile(String content, String filePath, boolean isAppend){
		boolean isSuccess=true;
		//只要filename之前的path(i.e. dir)
		int index=filePath.lastIndexOf("/");
		String dir=filePath.substring(0, index);
		//create a new folder
		File fileDir=new File(dir);
		fileDir.mkdirs();
		//create a new file
		File file=null;
		try{
			file=new File(filePath);
			isSuccess=file.createNewFile();
		}catch(IOException e){
			isSuccess=false;
			e.printStackTrace();
		}
		//writer
		FileWriter fileWriter=null;
		try{
			fileWriter=new FileWriter(file, isAppend);
			fileWriter.write(content);
			fileWriter.flush();
		}catch(IOException e){
			isSuccess=false;
			e.printStackTrace();
		}finally{
			try{
				if(fileWriter!=null){
					fileWriter.close();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return isSuccess;
	}
	
}
