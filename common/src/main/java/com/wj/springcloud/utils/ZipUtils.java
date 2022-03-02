package com.wj.springcloud.utils;

import cn.hutool.extra.template.TemplateException;
import com.wj.springcloud.entity.ZipParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * @author actor
 */
public class ZipUtils {
	
	private static final int  BUFFER_SIZE = 2 * 1024;
	
	/**
	 * 压缩成ZIP 方法1
	 * @param srcDir 压缩文件夹路径 
	 * @param out    压缩文件输出流
	 * @param KeepDirStructure  是否保留原来的目录结构,true:保留目录结构; 
	 * 							false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
	 * @throws RuntimeException 压缩失败会抛出运行时异常
	 */
	public static void toZip(String srcDir, OutputStream out, boolean KeepDirStructure)
			throws RuntimeException{
		
		long start = System.currentTimeMillis();
		ZipOutputStream zos = null ;
		try {
			zos = new ZipOutputStream(out);
			File sourceFile = new File(srcDir);
			compress(sourceFile,zos,sourceFile.getName(),KeepDirStructure);
			long end = System.currentTimeMillis();
			System.out.println("压缩完成，耗时：" + (end - start) +" ms");
		} catch (Exception e) {
			throw new RuntimeException("zip error from ZipUtils",e);
		}finally{
			if(zos != null){
				try {
					zos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * 压缩成ZIP 方法2
	 * @param srcFiles 需要压缩的文件列表
	 * @param out 	        压缩文件输出流
	 * @throws RuntimeException 压缩失败会抛出运行时异常
	 */
	public static void toZip(List<File> srcFiles , OutputStream out)throws RuntimeException {
		long start = System.currentTimeMillis();
		ZipOutputStream zos = null ;
		try {
			zos = new ZipOutputStream(out);
			for (File srcFile : srcFiles) {
				byte[] buf = new byte[BUFFER_SIZE];
				zos.putNextEntry(new ZipEntry(srcFile.getName()));
				int len;
				FileInputStream in = new FileInputStream(srcFile);
				while ((len = in.read(buf)) != -1){
					zos.write(buf, 0, len);
				}
				zos.closeEntry();
				in.close();
			}
			long end = System.currentTimeMillis();
			System.out.println("压缩完成，耗时：" + (end - start) +" ms");
		} catch (Exception e) {
			throw new RuntimeException("zip error from ZipUtils",e);
		}finally{
			if(zos != null){
				try {
					zos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	 * 递归压缩方法
	 * @param sourceFile 源文件
	 * @param zos		 zip输出流
	 * @param name		 压缩后的名称
	 * @param KeepDirStructure  是否保留原来的目录结构,true:保留目录结构; 
	 * 							false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
	 * @throws Exception
	 */
	private static void compress(File sourceFile, ZipOutputStream zos, String name,
			boolean KeepDirStructure) throws Exception{
		byte[] buf = new byte[BUFFER_SIZE];
		if(sourceFile.isFile()){
			// 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
			zos.putNextEntry(new ZipEntry(name));
			// copy文件到zip输出流中
			int len;
			FileInputStream in = new FileInputStream(sourceFile);
			while ((len = in.read(buf)) != -1){
				zos.write(buf, 0, len);
			}
			// Complete the entry
			zos.closeEntry();
			in.close();
		} else {
			File[] listFiles = sourceFile.listFiles();
			if(listFiles == null || listFiles.length == 0){
				// 需要保留原来的文件结构时,需要对空文件夹进行处理
				if(KeepDirStructure){
					// 空文件夹的处理
					zos.putNextEntry(new ZipEntry(name + "/"));
					// 没有文件，不需要文件的copy
					zos.closeEntry();
				}
				
			}else {
				for (File file : listFiles) {
					// 判断是否需要保留原来的文件结构
					if (KeepDirStructure) {
						// 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
						// 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
						compress(file, zos, name + "/" + file.getName(),KeepDirStructure);
					} else {
						compress(file, zos, file.getName(),KeepDirStructure);
					}
					
				}
			}
		}
	}

	public static void  zipDownload(List<File> fileList,HttpServletRequest request,HttpServletResponse response) throws IOException, TemplateException {
		if(fileList.size() > 0){
			Map<String,Object> dataMap = new HashMap<>();
			/** 下面为下载zip压缩包相关流程 */
			FileWriter writer;
			/** 1.创建临时文件夹  */
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			File temDir = new File(rootPath + "/" + UUID.randomUUID().toString().replaceAll("-", ""));
			if(!temDir.exists()){
				temDir.mkdirs();
			}

			/** 2.生成需要下载的文件，存放在临时文件夹内 */
			// 这里我们直接来10个内容相同的文件为例，但这个10个文件名不可以相同

			/** 3.设置response的header */
			response.setContentType("application/zip");
			response.setHeader("Content-Disposition", "attachment; filename=excel.zip");

			/** 4.调用工具类，下载zip压缩包 */
			// 这里我们不需要保留目录结构
			ZipUtils.toZip(fileList, response.getOutputStream());

			/** 5.删除临时文件和文件夹 */
			// 这里我没写递归，直接就这样删除了
			File[] listFiles = temDir.listFiles();
			for (int i = 0; i < listFiles.length; i++) {
				listFiles[i].delete();
			}
			temDir.delete();
		}
	}


	/**
	 * 将多个流转成zip文件输出
	 * @param listStream 文件流实体类对象
	 * @param fileName zip包的名称
	 * @param response
	 * @return
	 */
	public static boolean listStreamToZipStream(List<ZipParam> listStream, String fileName, HttpServletRequest request, HttpServletResponse response) {
		byte[] buf = new byte[1024];
		// 获取输出流
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			response.reset(); // 重点突出
			fileName = getFileNameCode(fileName, request);
			// 不同类型的文件对应不同的MIME类型
			response.setContentType("application/x-msdownload");
			response.setCharacterEncoding("utf-8");
			response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".zip");
			// ZipOutputStream类：完成文件或文件夹的压缩
			ZipOutputStream out = new ZipOutputStream(bos);
			for (int i = 0; i < listStream.size(); i++) {
				InputStream in = listStream.get(i).getInputstream();
				// 给列表中的文件单独命名
				out.putNextEntry(new ZipEntry(listStream.get(i).getName()));
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				out.closeEntry();
				in.close();
			}
			out.close();
			bos.close();
			System.out.println("压缩完成.");
			return  true;
		} catch (Exception e) {
			e.printStackTrace();
			return  false;
		}

	}

    /**
	 *
	 * @param fileName
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static String getFileNameCode(String fileName, HttpServletRequest request) throws UnsupportedEncodingException {
		String userAgent = request.getHeader("User-Agent").toUpperCase();
		if (userAgent.contains("CHROME")) {
			// 谷歌
			fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
		} else if ((userAgent.contains("FIREFOX"))) {
			// firefox浏览器
			fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
		} else if (userAgent.contains("MSIE")) {
			// IE浏览器
			fileName = URLEncoder.encode(fileName, "UTF-8");
//                packageName = new String(packageName.getBytes("ISO8859-1"), "UTF-8");
		} else if (userAgent.contains("TRIDENT")) {
			// IE浏览器
			fileName = URLEncoder.encode(fileName, "UTF-8");
		}
		return fileName;
	}

}