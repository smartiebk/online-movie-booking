package com.ombp.cloud.app.ui.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class CommonFileUtil {

	private static String FILE_TYPE_AUDIO = "AUDIO";
	private static String FILE_TYPE_VIDEO = "VIDEO";
	private static String FILE_TYPE_DOC = "DOC";
	private static String FILE_TYPE_IMAGE = "IMAGE";
	private static String FILE_TYPE_OTHER = "OTHER";

	private static String[] allImageExtentions = { "jpg", "jpeg", "png", "webp", "gif", "bmp", "svg" };

	//Commented temporarily till good document viewer is found "pdf", "doc", "docx", "tiff", "ppt", "pptx", "xls",	"xlsx"  
	private static String[] allDocViewerFileExtentions = { };

	private static String[] allVideoExtentions = { "mp4", "webm", "ogg" };

	private static String[] allAudioExtentions = { "mp3", "wav", "ogg", "mpeg" };

	private static Map<String, String> htmlEmbeds = new HashMap<String, String>();

	static {
		htmlEmbeds.put(FILE_TYPE_AUDIO, "<audio controls><source src='FILE_URL' type='audio/FILE_EXT'></audio>");
		htmlEmbeds.put(FILE_TYPE_VIDEO,
				"<video width='100%' controls><source src='FILE_URL' type='video/FILE_EXT'></video>");
		htmlEmbeds.put(FILE_TYPE_DOC,
				"<iframe src='https://docs.google.com/viewer?url=FILE_URL&embedded=true' frameborder='0'></iframe>");
		htmlEmbeds.put(FILE_TYPE_IMAGE, "<img alt='FILE_NAME' width='100%' src='FILE_URL'>");
	}

	public static String FILE_UPLOAD_PATH = "";
	
	public static String SITE_URL = "";

	public static void setFileUploadPath(String path) {
		FILE_UPLOAD_PATH = path;
	}
	
	public static void setSiteURL(String siteURL) {
		SITE_URL = siteURL;
	}

	public static String getFileExtension(String fileName) {
		String extension = "";

		if (fileName != null && fileName.length() != 0 && fileName.contains(".")) {
			extension = fileName.substring(fileName.lastIndexOf("."));
		}
		if (extension.length() != extension.trim().length()) {
			extension = "";
		}
		return extension;
	}
	
	public static String getFileNameWithoutExtension(String fileName) {
		String fileNameWithoutExtention = "";

		if (fileName != null && fileName.length() != 0 && fileName.contains(".")) {
			fileNameWithoutExtention = fileName.substring(0, fileName.lastIndexOf("."));
		}
		
		return fileNameWithoutExtention;
	}
	

	public static File convertMultiPartToFile(MultipartFile file, String fileName) throws IOException
    {
        File convFile = new File(fileName);
        FileOutputStream fos = new FileOutputStream( convFile );
        fos.write( file.getBytes() );
        fos.close();
        return convFile;
    }

	public static Object getImageAsBase64Data(String headerImage) throws IOException {

		String encodedfile = null;
		
		if(headerImage!=null) 
		{
			encodedfile = Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(new File(headerImage)));
		}
		return encodedfile;
	}
	
	
	public static String getFileContentAsString(String fileName) {
		String data = "";
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		Resource resource = resourceLoader.getResource(fileName); 
		try (Reader reader = new InputStreamReader(resource.getInputStream(), "UTF-8")) {
			data = FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
		
		return data;
	}
}
