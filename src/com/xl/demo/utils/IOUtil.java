package com.xl.demo.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;

public class IOUtil {

	// private static String TAG = "IOUtil";
	/** 图片缓存文件夹 */
	public static String imageDir_ = "/.baichuan/image/";
	public static String customHeadDir = "/.baichuan/head/";
	/** 下载文件夹 */
	public static String DOWNLOAD = "/baichuan_download/";
	/** 文件没有下载完成地址 **/
	public static String DEFDIR = "/.baichuan/download/";
	/** 外部储存目录,文件名 */
	public static String sdDir = Environment.getExternalStorageDirectory().getPath();

	/**
	 * 对InputStream对象进行缓存，转成byte对象
	 */
	public static byte[] inputStreamToByte(InputStream is) throws IOException {
		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
		byte[]  buffer = new byte[8 * 1024];
		int size = -1;
		while ((size = is.read(buffer)) != -1) {
			bytestream.write(buffer,0,size);
		}
		bytestream.flush();
		byte imgdata[] = bytestream.toByteArray();
		bytestream.close();
		is.close();
		bytestream = null;
		return imgdata;
	}

	public static String inputStreamToString(InputStream is) throws IOException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));   //实例化输入流，并获取网页代码
		String s;                                         //依次循环，至到读的值为空
		StringBuilder sb = new StringBuilder();
		while ((s = reader.readLine()) != null) {
			sb.append(s);     
		}
		reader.close();
		is.close();
		return sb.toString();
		
	}
	
	
	
	/**
	 * byte --> InputStream
	 * 
	 * @param data
	 * @return
	 */
	public static InputStream ByteToInputStream(byte[] data) {
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		return bais;
	}

	public static Bitmap readImage(String fileName, String path) {

		// BitmapFactory.Options options = new BitmapFactory.Options();
		Bitmap bitmap = null;
		bitmap = BitmapFactory.decodeFile(sdDir + path + "/" + fileName, null);
		return bitmap;
	}

	public static void saveFile(InputStream in, String fileName) {
		File testDir = new File(sdDir + imageDir_);

		if (!testDir.exists()) {
			testDir.mkdirs();
		}

		if (testDir.canWrite()) {
			File file = new File(sdDir + imageDir_ + fileName);
			FileOutputStream fos = null;
			try {
				if (file.exists())
					return;
				file.createNewFile();
				fos = new FileOutputStream(file);
				byte[] buf = new byte[2048];
				int c = 0;
				while ((c = in.read(buf)) != -1) {
					fos.write(buf, 0, c);
					fos.flush();
				}
				fos.close();
				fos = null;
				buf = null;
				in.close();
				in = null;
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (fos != null) {
						fos.close();
						fos = null;
					}

					if (in != null) {
						in.close();
						in = null;
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	
	
	public static void saveFile(Bitmap bitmap, String fileName) {
		File testDir = new File(sdDir + imageDir_);

		if (!testDir.exists()) {
			testDir.mkdirs();
		}

		if (testDir.canWrite()) {
			File file = new File(sdDir + imageDir_ + fileName);
			FileOutputStream fos = null;
			try {
				if (file.exists())
					return;
				file.createNewFile();
				fos = new FileOutputStream(file);  

			    bitmap.compress(CompressFormat.PNG, 50, fos);  

				fos.flush();  

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (fos != null) {
						fos.close();
						fos = null;
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	
	

	// 将bitmap保存到file
	public static void saveMyDrawable(Drawable dr, String bitName) {
		Bitmap bm = null;
		File f = new File(sdDir + imageDir_ + bitName);
		FileOutputStream fOut = null;
		try {
			if (f.exists())
				return;
			bm = drawableToBitmap(dr);
			f.createNewFile();

			fOut = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.PNG, 100, fOut);
			fOut.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				if (fOut != null) {
					fOut.flush();
					fOut.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 将drawable转化成bitmap
	public static Bitmap drawableToBitmap(Drawable drawable) {

		Bitmap bitmap = Bitmap
				.createBitmap(
						drawable.getIntrinsicWidth(),
						drawable.getIntrinsicHeight(),
						drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
								: Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
				drawable.getIntrinsicHeight());
		drawable.draw(canvas);
		return bitmap;
	}

	/**
	 * 保存手机本软件目录文件
	 * 
	 * @param fileName
	 *            文件名
	 * @param content
	 *            文件内容
	 * @throws Exception
	 */
	public static void saveData(String fileName, byte[] content, String path)
			throws Exception {
		// 获得本软件data目录下的位置
		File testDir = new File(Environment.getDataDirectory().getPath() + path);
		if (!testDir.exists()) {
			testDir.mkdirs();
		}

		if (testDir.canWrite()) {
			File file = new File(testDir.getAbsolutePath() + "/" + fileName);
			try {
				file.createNewFile();
			} catch (IOException e) {
				// Log.e(TAG, "error creating file", e);
			}
			if (file.exists() && file.canWrite()) {
				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(file);
					fos.write(content);
					fos.flush();
				} catch (FileNotFoundException e) {
					// Log.e(TAG, "ERROR", e);
				} catch (IOException e) {
					// Log.e(TAG, "ERROR", e);
				} finally {
					if (fos != null) {
						try {
							fos.flush();
							fos.close();
						} catch (IOException e) {

						}
					}
				}
			} else {
				// Log.e(TAG, "error writing to file");
			}

		} else {
			// Log.e(TAG, "ERROR, unable to write to " + path);
		}
		closeByte(content);
	}

	/**
	 * 读取文件内容
	 * 
	 * @param fileName
	 *            文件名
	 * 
	 * @return 文件内容
	 * 
	 * @throws Exception
	 */
	public static Bitmap readDataImage(String fileName, String path) {

		Bitmap bitmap = null;
		bitmap = BitmapFactory.decodeFile(Environment.getDataDirectory()
				.getPath() + path + "/" + fileName, null);
		return bitmap;
	}

	/**
	 * 读取文件内容
	 * 
	 * @param fileName
	 *            文件名
	 * 
	 * @return 文件内容
	 * 
	 * @throws Exception
	 */
	public static byte[] read(String fileName, String path) throws Exception {
		File readFile = new File(sdDir + path + "/" + fileName);
		if (readFile.exists() && readFile.canRead()) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(readFile);
				int available = fis.available();
//				if(available > 1.5 * 1024 * 1024){
//					return null;
//				}
				byte[] reader = new byte[available];
				while (fis.read(reader) != -1) {
				}
				// Log.w(TAG, "readName = " + fileName);
				return reader;
			} catch (OutOfMemoryError e){
				return null;
			} catch (IOException e) {
				// Log.e(TAG, e.getMessage(), e);
				return null;
			} finally {
				if (fis != null) {
					fis.close();
					// Log.w(TAG, "fis.close");
				}
			}
		} else {
			File baoruanDir = new File(path);
			baoruanDir.mkdirs();
			return null;
		}
	}

	/**
	 * 保存文件内容
	 * 
	 * @param fileName
	 *            文件名
	 * 
	 * @return content文件内容
	 * @return path 文件路径
	 * @throws Exception
	 */
	public static void save(String fileName, byte[] content, String path)
			throws Exception {
		File testDir = new File(sdDir + path);
		if (!testDir.exists()) {
			testDir.mkdirs();
		}
		if (testDir.canWrite()) {
			File file = new File(testDir.getAbsolutePath() + "/" + fileName);
			try {
				file.createNewFile();
			} catch (IOException e) {
				// Log.e(TAG, "error creating file", e);
			}
			if (file.exists() && file.canWrite()) {
				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(file);
					fos.write(content);
					fos.flush();
				} catch (FileNotFoundException e) {
					// Log.e(TAG, "ERROR", e);
				} catch (IOException e) {
					// Log.e(TAG, "ERROR", e);
				} finally {
					if (fos != null) {
						try {
							fos.flush();
							fos.close();
						} catch (IOException e) {
						}
					}
				}
			} else {
				// Log.e(TAG, "error writing to file");
			}

		} else {
			// Log.e(TAG, "ERROR, unable to write to " + path);
		}
		closeByte(content);
	}

	public void SaveIntoFile(final String dir, final String fileName,
			final int[] content) {
		File file = new File(dir, fileName);
		FileOutputStream output = null;
		file.deleteOnExit();
		try {
			if (!file.createNewFile())
				return;
			output = new FileOutputStream(file);
			for (int i = 0; i < content.length; ++i) {
				output.write(content[i]);
			}
			output.flush();
			output.close();
			output = null;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (output != null) {
					output.close();
					output = null;
				}
			} catch (Exception e) {

			}
		}

	}

	public static void saveMyBitmap(File dir, String bitName, Bitmap mBitmap)
			throws IOException {
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File f = new File(dir, bitName);
		f.createNewFile();
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
		try {
			fOut.flush();
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解压zip包
	 * 
	 * @param zipFileName
	 *            解压zip包路径
	 * @param outputDirectory
	 *            输出目录
	 * @throws Exception
	 */
	public static void unzip(String zipFileName, String outputDirectory)
			throws Exception {
		ZipInputStream in = null;
		try {
			if (!".zip".endsWith(zipFileName)) {
				throw new Exception("只支持zip包");
			}
			in = new ZipInputStream(new FileInputStream(zipFileName));
			ZipEntry z = in.getNextEntry();
			while (z != null) {
				// System.out.println("unziping " + z.getName());
				// 创建以zip包文件名为目录名的根目录
				File f = new File(outputDirectory);
				f.mkdir();
				if (z.isDirectory()) {
					String name = z.getName();
					name = name.substring(0, name.length() - 1);
					// System.out.println("name " + name);
					f = new File(outputDirectory + File.separator + name);
					f.mkdir();
					// System.out.println("mkdir " + outputDirectory +
					// File.separator + name);
				} else {
					f = new File(outputDirectory + File.separator + z.getName());
					f.createNewFile();
					FileOutputStream out = new FileOutputStream(f);
					int b;
					while ((b = in.read()) != -1) {
						out.write(b);
					}
					out.close();
				}
				// 读取下一个ZipEntry
				z = in.getNextEntry();
			}
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.toString());
		} finally {
			if (null != in) {
				in.close();
			}
		}
	}

	static final int BUFFER = 2048;

	public static void unZip(String fileName, String unZipDir) {

		try {
			// 先判断目标文件夹是否存在，如果不存在则新建，如果父目录不存在也新建
			File f = new File(unZipDir);
			if (!f.exists()) {
				f.mkdirs();
			}

			BufferedOutputStream dest = null;
			BufferedInputStream is = null;
			ZipEntry entry;
			ZipFile zipfile = new ZipFile(fileName);
			Enumeration<?> e = zipfile.entries();
			while (e.hasMoreElements()) {
				entry = (ZipEntry) e.nextElement();
				//System.out.println("Extracting: " + entry);
				is = new BufferedInputStream(zipfile.getInputStream(entry));
				int count;
				byte data[] = new byte[BUFFER];
				FileOutputStream fos = new FileOutputStream(unZipDir + "/"
						+ entry.getName());
				//System.out.println("entry.getName(): " + entry.getName());
				dest = new BufferedOutputStream(fos, BUFFER);
				while ((count = is.read(data, 0, BUFFER)) != -1) {
					dest.write(data, 0, count);
				}
				dest.flush();
				dest.close();
				is.close();
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	public static boolean makeDir(String unZipDir) {
		boolean b = false;
		try {
			File f = new File(unZipDir);
			if (!f.exists()) {
				b = f.mkdirs();
			}
		} catch (Exception e) {
			// e.printStackTrace();
			return b;
		}
		return b;
	}

	/**
	 * 扫描SD卡目录下某一文件夹下的某一类型的文件
	 * 
	 * @param dirpath
	 *            要扫描的SD卡路径
	 * @param type
	 *            文件类型（后缀名） null 取得所有
	 * @return
	 */
	public static List<File> getFileBytypr(String dirpath, String type) {
		if ("".equals(dirpath) || null == dirpath) {
			return null;
		}
		ArrayList<File> list = new ArrayList<File>();
		File dirfile = new File(dirpath);
		File[] filelist = dirfile.listFiles();
		if (filelist == null)
			return null;
		for (File f : filelist) {
			if (f.isDirectory())
				continue;
			String filename = f.getName();
			if (type != null && !filename.endsWith(type))
				continue;
			list.add(f);
		}
		return list;
	}

	/**
	 * 删除文件，可以是单个文件或文件夹
	 * 
	 * @param fileName
	 *            待删除的文件名
	 * @return 文件删除成功返回true,否则返回false
	 */
	public static boolean delete(String fileName) {
		File file = new File(sdDir + fileName);
		if (!file.exists()) {
			//System.out.println("删除文件失败：" + fileName + "文件不存在");
			return false;
		} else {
			if (file.isFile()) {
				return deleteFile(fileName);
			} else {
				return deleteDirectory(fileName);
			}
		}
	}

	/**
	 * 判断本地文件是否存在
	 * 
	 * @param Dir
	 *            文件目录
	 * @param imageName
	 *            文件名称
	 * @return 有文件返回true，没有文件返回false
	 */
	public static boolean checkExist(String dir, String imageName) {
		// Log.w("hao", "dir = " + dir + "  imageName = " +
		// imageName+" path="+sdDir + dir + imageName);
		java.io.File file = new java.io.File(sdDir + dir + imageName);
		if (file.exists() && !file.isDirectory()) {
			// Log.w("hao", "true");
			return true;
		} else {
			// Log.w("hao", "false");
			return false;
		}

	}

	/**
	 * @param fileName
	 *            文件名
	 * @param path
	 *            文件路径
	 * @return InputStream
	 * @throws Exception
	 */
	public static InputStream readInputStream(String path, String fileName) {
		File readFile = new File(sdDir + path + "/" + fileName);

		// Log.w("syn", "readInputStream************"+readFile.getName());
		if (readFile.exists() && readFile.canRead()) {
			// Log.w("syn", "readInputStream2========="+readFile.getName());
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(readFile);
				return fis;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * @param fileName
	 *            文件名
	 * @param path
	 *            文件路径
	 * @return InputStream
	 * @throws Exception
	 */
	public static InputStream readInputStream(String fileName) {
		File readFile = new File(fileName);
		if (readFile.exists() && readFile.canRead()) {
			/* Log.w("hao", path + "/" + fileName); */
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(readFile);
				return fis;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 删除单个文件
	 * 
	 * @param fileName
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true,否则返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.isFile() && file.exists()) {
			file.delete();
			// System.out.println("删除单个文件" + fileName + "成功！");
			return true;
		} else {
			// System.out.println("删除单个文件" + fileName + "失败！");
			return false;
		}
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param dir
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true,否则返回false
	 */
	public static boolean deleteDirectory(String dir) {
		// 如果dir不以文件分隔符结尾，自动添加文件分隔符
		if (!dir.endsWith(File.separator)) {
			// 如果不以sdDir开头，才添加sdDir目录
			if (dir.startsWith(sdDir)) {
				dir = dir + File.separator;
			} else {
				dir = sdDir + dir + File.separator;
			}
		}
		File dirFile = new File(dir);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			// System.out.println("删除目录失败" + dir + "目录不存在！");
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				// System.out.println(files[i].getAbsolutePath());
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
			// 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
		}

		if (!flag) {
			// System.out.println("删除目录失败");
			return false;
		}

		// 删除当前目录
		if (dirFile.delete()) {
			// System.out.println("删除目录" + dir + "成功！");
			return true;
		} else {
			// System.out.println("删除目录" + dir + "失败！");
			return false;
		}
	}

	/**
	 * 图片压缩方法
	 */
	public static Bitmap getBitmapCompress(InputStream in) {
		BitmapFactory.Options newopts = new BitmapFactory.Options();
		newopts.inSampleSize = 4;
		newopts.inJustDecodeBounds = false;
		Bitmap destBm = BitmapFactory.decodeStream(in, null, newopts);
		return destBm;
	}

	/**
	 * Bitmap转换成Byte数组：
	 */
	static byte[] bitmapToBytes(Bitmap bitmap) {
		int size = bitmap.getWidth() * bitmap.getHeight() * 4;
		ByteArrayOutputStream out = new ByteArrayOutputStream(size);
		try {
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
			out.flush();
			out.close();
			return out.toByteArray();
		} catch (IOException e) {
			return null;
		}
	}


	// 复制文件
	public static void copyFile(File sourceFile, File targetFile)
			throws IOException {
		// 新建文件输入流并对它进行缓冲
		FileInputStream input = new FileInputStream(sourceFile);
		BufferedInputStream inBuff = new BufferedInputStream(input);

		// 新建文件输出流并对它进行缓冲
		FileOutputStream output = new FileOutputStream(targetFile);
		BufferedOutputStream outBuff = new BufferedOutputStream(output);

		// 缓冲数组
		byte[] b = new byte[1024 * 32];
		int len;
		while ((len = inBuff.read(b)) != -1) {
			outBuff.write(b, 0, len);
		}
		// 刷新此缓冲的输出流
		outBuff.flush();
		closeByte(b);
		// 关闭流
		inBuff.close();
		outBuff.close();
		output.close();
		input.close();
		if (sourceFile.exists()) {
			sourceFile.delete();
		}
	}

	public static void closeByte(byte[] b) {
		if (b != null) {
			b = null;
		}
	}

	public static void getFile4type(List<File> filelist, String path,
			String type) {
		if (filelist == null || path == null)
			return;
		File file = new File(path);
		File[] arrfile = file.listFiles();
		if (arrfile == null)
			return;
		for (File f : arrfile) {
			if (f.isFile()) {
				if (f.getPath().endsWith(type)) {
					filelist.add(f);
				}
			} else {
				getFile4type(filelist, f.getAbsolutePath(), type);
			}
		}
	}

	/**
	 * 
	 * @param filelist
	 * @param path
	 * @param excpath
	 * @param type
	 */
	public static void getFile4type(List<File> filelist, String path,
			String excpath, String type) {
		if (null == path || "".equals(path)) {
			return;
		}
		if (path.startsWith(excpath)) {
			return;
		}
		File file = new File(path);
		File[] arrfile = file.listFiles();
		if (arrfile == null)
			return;
		for (File f : arrfile) {
			if (f.isFile()) {
				if (f.getPath().endsWith(type)) {
					filelist.add(f);
				}
			} else {
				getFile4type(filelist, f.getAbsolutePath(), type);
			}
		}
	}

	/**
	 * 获取资源包中指定名称的图片
	 */

	public static List<Drawable> getDrawables(Context context,
			String packagename) {
		Context c = null;
		ArrayList<Drawable> previews = new ArrayList<Drawable>();
		try {
			c = context.createPackageContext(packagename,
					Context.CONTEXT_INCLUDE_CODE
							| Context.CONTEXT_IGNORE_SECURITY);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			return previews;
		}
		final String name = "preview_";
		boolean end = false;
		int no = 1;
		while (!end) {
			int resID = c.getResources().getIdentifier(name + no, "drawable",
					packagename);
			if (resID != 0) {
				previews.add(c.getResources().getDrawable(resID));
				no++;
			} else {
				end = true;
			}
		}
		return previews;
	}

	/**
	 * 获取资源包中指定名称的图片
	 */

	public static Drawable getDrawable(Context context, String packagename) {
		Context c = null;
		try {
			c = context.createPackageContext(packagename,
					Context.CONTEXT_INCLUDE_CODE
							| Context.CONTEXT_IGNORE_SECURITY);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		}
		final String name = "preview_1";
		int resID = c.getResources().getIdentifier(name, "drawable",
				packagename);
		if (resID != 0) {
			InputStream input = c.getResources().openRawResource(resID);
			BitmapFactory.Options opt = new BitmapFactory.Options();
			opt.inPreferredConfig = Bitmap.Config.RGB_565;
			opt.inPurgeable = true;
			opt.inInputShareable = true;
			Bitmap bmp = BitmapFactory.decodeStream(input, null, opt);
			Drawable dra = new BitmapDrawable(bmp);
			try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return dra;
		} else {
			return null;
		}
	}

	/**
	 * 获取资源包中指定名称的图片
	 */

	public static List<Bitmap> getbitmap(Context context, String packagename) {
		Context c = null;
		ArrayList<Bitmap> previews = new ArrayList<Bitmap>();
		try {
			c = context.createPackageContext(packagename,
					Context.CONTEXT_INCLUDE_CODE
							| Context.CONTEXT_IGNORE_SECURITY);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			return previews;
		}
		final String name = "preview_";
		boolean end = false;
		int no = 1;
		while (!end) {
			int resID = c.getResources().getIdentifier(name + no, "drawable",
					packagename);
			if (resID != 0) {
				InputStream dra = c.getResources().openRawResource(resID);

				BitmapFactory.Options opt = new BitmapFactory.Options();
				opt.inPreferredConfig = Bitmap.Config.RGB_565;
				opt.inPurgeable = true;
				opt.inInputShareable = true;
				// 获取资源图片

				previews.add(BitmapFactory.decodeStream(dra, null, opt));
				try {
					dra.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				no++;
			} else {
				end = true;
			}
		}
		return previews;
	}

	public static Bitmap Diminish(Bitmap bmp, float scale) {
		int bmpWidth = bmp.getWidth();
		int bmpHeight = bmp.getHeight();
		// 设置缩小比例
		// 产生resize后的Bitmap对象
		Matrix matrix = new Matrix();
		matrix.postScale(0.25f, 0.25f);
		Bitmap resizeBmp = Bitmap.createBitmap(bmp, 0, 0, bmpWidth, bmpHeight,
				matrix, true);
		return resizeBmp;
	}

	public static byte[] readcatch(File file) throws IOException {
		byte[] by = null;
		InputStream in = null;
		in = new FileInputStream(file);
		by = inputStreamToByte(in);
		if (in != null)
			in.close();
		return by;
	}

	public static void writecatch(File file, byte[] by) throws IOException {
		FileOutputStream out = null;
		if (file.exists())
			file.delete();
		file.createNewFile();
		out = new FileOutputStream(file);
		out.write(by);
		out.flush();
		if (out != null)
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	/**
	 * 修改文件权限
	 * 
	 * @param args
	 * @return
	 */
	public static String exec(String[] args) {

		String result = "";
		ProcessBuilder processBuilder = new ProcessBuilder(args);
		Process process = null;
		InputStream errIs = null;
		InputStream inIs = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int read = -1;
			process = processBuilder.start();
			errIs = process.getErrorStream();
			while ((read = errIs.read()) != -1) {
				baos.write(read);
			}
			baos.write('\n');
			inIs = process.getInputStream();
			while ((read = inIs.read()) != -1) {
				baos.write(read);
			}
			byte[] data = baos.toByteArray();
			result = new String(data);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (errIs != null) {
					errIs.close();
				}
				if (inIs != null) {
					inIs.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (process != null) {
				process.destroy();
			}
		}
		return result;

	}
	
	/**
	 *  Function:
	 *     获取文件名
	 *  @author
	 */
	public  static String getNewFileName(String packname,String fileUrl){
		if(fileUrl!=null && !"".equals(fileUrl.trim())){
				if(packname!=null && !"".equals(packname)){
				int lastIndexOf = fileUrl.lastIndexOf(".");
				String lx =  fileUrl.substring(lastIndexOf, fileUrl.length());
				return packname+lx;
			}
		}
		return null;
	}
	/**
	 *  Function:
	 *     获取文件名
	 *  @author
	 */
	public  static String getUrlIconFileName(String fileURL){
		if(fileURL!=null && !"".equals(fileURL.trim())){
			int lastIndexOf = fileURL.lastIndexOf("/");
			return fileURL.substring(lastIndexOf+1, fileURL.length());
		}
		return null;
	}
	
	/**
	 *  Function:
	 *     获取图片文件名
	*  @author
	*/
	public  static String getimgName(String fileURL){
		if(fileURL!=null && !"".equals(fileURL.trim())){
			int start = fileURL.lastIndexOf("/");
			int end = fileURL.lastIndexOf(".");
			return fileURL.substring(start+1, end);
		}
		return null;
	}
	
	/**
	 * 		过滤特殊字符后设置软件下载的名字
	 * @param name		书名
	 * @param url		url地址
	 * @return			软件名+url.后面的格式
	 */
	public static String getLocalName ( String bookName, String downLoadURL ){
		String n = null;
		StringBuffer sb = new StringBuffer();
		for ( int i = 0; i < bookName.length(); i++ ){
			char c = bookName.charAt(i);
			if ( c == ':' || c == '?'  || c == '*'  || c == '<'  || c == '>' || c == '|' || c == '%' || c == '&' || c == '/' ){
				break;
			}
			sb.append(c);
		}
		String b = sb.toString();
		String a = downLoadURL.substring(downLoadURL.lastIndexOf("."), downLoadURL.length());
		n = b + a;
		sb = null;
		return n;
	}
	
	/**
	 *  Function:
	 *     保存图片文件流到sdcard指定的文件名 返回boolean
	*  @author Xiang  DateTime 2011-12-26 上午11:14:21
	*/
	public static synchronized boolean savePicInStream2sdCard(String dirName,String fileName,Bitmap bitmap){
		boolean result=false;
		if(!"".equals(dirName) && !"".equals(fileName) && bitmap!=null){
			OutputStream bufferedOutputStream=null;
			try {
				//文件夹
				File dirFile = new File(dirName);
				if(!dirFile.exists()){
					dirFile.mkdirs();
				}
				//文件
				File file=new File(dirFile,fileName);
				file.createNewFile();
				bufferedOutputStream=new BufferedOutputStream(new FileOutputStream(file));
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, bufferedOutputStream);
				result=true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeOutStream(bufferedOutputStream);
			}
		}
		return result;
	}
	
	/**
	 *  Function:
	 *     字节输出流的安全关闭
	 *  @author Xiang  DateTime 2011-12-26 上午11:24:45
	 */
	public static void closeOutStream(OutputStream outputStream){
		if(outputStream!=null){
			try {
				outputStream.close();
				outputStream=null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 文件是否存在
	 */
	public static boolean isExistOfFile(String dirName,String fileName){
		if(dirName!=null && fileName!=null && !"".equals(dirName.trim()) && !"".equals(fileName.trim())){
			File file=new File(dirName,fileName);
			return file.exists();
		}
		return false;
	}
	
	/**拷贝Aesset中的图片文件到 SD卡中*/
	public static void copyFile2(InputStream in,OutputStream out) throws IOException{
		BufferedInputStream inBuff = new BufferedInputStream(in);
		BufferedOutputStream outBuff = new BufferedOutputStream(out);
		byte [] buf = new byte [1024];
		int len = -1;
		while((len =inBuff.read(buf))!=-1){
			outBuff.write(buf, 0, len);
		}
		outBuff.flush();
		inBuff.close();
		outBuff.close();
	}
}
