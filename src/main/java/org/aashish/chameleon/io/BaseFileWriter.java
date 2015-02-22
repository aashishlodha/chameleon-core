package org.aashish.chameleon.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.aashish.chameleon.constant.Error;
import org.apache.log4j.Logger;

public class BaseFileWriter{
	
	static Logger logger = Logger.getLogger(BaseFileWriter.class);

	public static String write(String fileName, String packageName, String projectName, String inputStr, String extension) {
		
		logger.info("Writing file to disk..");
		
		String currentDir = System.getProperty("user.dir");
		logger.info("Current directory is >> " + currentDir);
		
		Path currPath = Paths.get(currentDir);
		Path root = currPath.getRoot();
		logger.info("Root directory is >> " + root);
		StringBuilder finalPath = new StringBuilder();
		finalPath.append(root.toString() + "chameleon"+ File.separator + projectName + File.separator + "src" + File.separator);
		
		if(packageName != null){
			if(!packageName.isEmpty()){
				String filePath = packageName.replace(".", File.separator);
				finalPath.append(filePath + File.separator);
			}
		}
		
		finalPath.append(fileName + extension);
		
		logger.info("Final path >> " + finalPath);
		logger.info("Creating folders...");
		
		Path newFile = Paths.get(finalPath.toString());
		
		try {
			if(Files.exists(newFile)){
				logger.info("This file already exists.. Deleting the old file..");
				Files.deleteIfExists(newFile);
			}
			logger.info("Parent >> " + newFile.getParent());
			//Files.createDirectory(newFile.getParent());
			createParentDirectoriesForFile(newFile.getParent());
			newFile = Files.createFile(newFile);
		} catch (IOException e) {
			logger.error("Error creating file..", e);
			return Error.FILE_CREATE_ERROR;
		}
		
		try/*(BufferedWriter writer = Files.newBufferedWriter(newFile, Charset.defaultCharset()))*/{
			/*writer.append(inputStr);
			writer.flush();*/
			Files.write(newFile, inputStr.getBytes());
		}catch(IOException e){
			logger.error("Error writing to file", e);
			return Error.FILE_WRITE_ERROR;
		}
		
		logger.info("File written successfully..");
		return finalPath.toString();
	}
	
	public static void createParentDirectoriesForFile(Path parentFolder){
		
		try {
			if(parentFolder.getParent() == null){
				return;
			}
			createParentDirectoriesForFile(parentFolder.getParent());
			logger.info("Creating folder >> " + parentFolder);
			Files.createDirectories(parentFolder);
		} catch (IOException e) {
			logger.error("Error in creating parent folders!", e);
		}
	}

}
