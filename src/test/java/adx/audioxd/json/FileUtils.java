package adx.audioxd.json;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {
	public static String readFile(File file) {
		String content = null;
		FileReader reader = null;
		try {
			reader = new FileReader(file);
			char[] chars = new char[(int) file.length()];
			reader.read(chars);
			content = new String(chars);
			reader.close();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(reader != null) {
				try { reader.close(); } catch(IOException e) { e.printStackTrace(); }
			}
		}
		return content;
	}

	public static File getFileInJar(String path) {
		return getFileInJar(FileUtils.class, path);
	}

	public static File getFileInJar(Class classInJar, String path) {
		ClassLoader classLoader = classInJar.getClassLoader();
		return new File(classLoader.getResource(path).getFile());
	}
}
