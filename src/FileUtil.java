import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class FileUtil {
	public static String getStr(String fn){
		try {
			File f = new File(fn);
			//System.out.println(fn);
			System.out.println(f.getAbsolutePath());
			FileInputStream fis = new FileInputStream(f);
			byte[] b = new byte[1024*1024*8];
			int len = fis.read(b);
			fis.close();
			return new String(b, 0, len); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static void writeStr(String fn, String txt) {
		try {
			File f = new File(fn);
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(txt.getBytes());
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
