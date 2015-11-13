import java.util.HashMap;
import java.util.Iterator;

/**
 * English word
 * 添加了各种操作
 * @author wq
 *
 */
public class WordEn extends Word{
	private boolean isWord = false; // 是否单词。有可能只是各种标点符号，而不是单词，此时只要打印出来即可。
	private boolean isNew = false;  // 是否生词
	
	private static Dic dic = Dic.getAllDic();
	private static Dic dicOld = Dic.getMyDic();

	private static HashMap<String, Integer> hmCount = new HashMap<String, Integer>();
	private static HashMap<String, String> hmActual = new HashMap<String, String>();
	
	public static HashMap<String, Word> hmNewWord = new HashMap<String, Word>();
	
	public String getCn() {
		return hmNewWord.get(hmActual.get(lowwer)).cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
		setLowwer(origin.toLowerCase());
	}

	public boolean isWord() {
		return isWord;
	}

	public void setWord(boolean isWord) {
		this.isWord = isWord;
	}

	public String getLowwer() {
		return lowwer;
	}
	
	public void setLowwer(String _lowwer) {
		this.lowwer = _lowwer;
		if (isWord) {
			
			dicOld.getCn(this);
			if( cn !=null ){
				return;
			}
			isNew = true;
			
			String _actual = hmActual.get(lowwer);
			if(_actual==null){
				dic.getCn(this);
				if(actual==null || actual.equals("")){
					_actual = lowwer;
				}else{
					_actual = actual;
				}
			}
			hmActual.put(lowwer,_actual);
			
			Integer count = hmCount.get(_actual);
			if (count == null) {
				count = 1;
				dic.getCn(this);
				hmActual.put(lowwer, actual);
				hmNewWord.put(_actual, this);
			} else {
				count++;
			}
			hmCount.put(_actual, count);
		}
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public String toString() {
		return lowwer;
	}

	public String toString2() {
		return lowwer + "\t" + hmCount.get(lowwer);
	}

	public static void showWords() {
		Iterator<String> it = hmCount.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			System.out.println(key + "\t" + hmCount.get(key));
		}
	}

	/**
	 * 列出生词
	 * 按单词出现次数降序显示
	 */
	public static String showWords2() {
		StringBuffer sb = new StringBuffer();
		Iterator<String> it = hmCount.keySet().iterator();
		HashMap<Integer, String> hmSort = new HashMap<Integer, String>();
		int max = 0;
		while (it.hasNext()) {
			String key = it.next();
			int count = hmCount.get(key);
			if(count>max){
				max = count;
			}
			String str = hmSort.get(count);
			if(str==null){
				str = key;
			}else{
				str += "\r\n" + key;
			}
			hmSort.put(count, str);
		}
		
		for(int i=max; i>=0; i--){
			String str = hmSort.get(i);
			if(str!=null){
				sb.append("======" + i ).append("\r\n");
				sb.append(str).append("\r\n");
			}
		}
		return sb.toString();
	}
	
	/**
	 * 列出生词
	 * 按单词出现次数降序显示，附解释
	 */
	public static String showWords3() {
		StringBuffer sb = new StringBuffer();
		Iterator<String> it = hmCount.keySet().iterator();
		HashMap<Integer, String> hmSort = new HashMap<Integer, String>();
		int max = 0;
		while (it.hasNext()) {
			String key = it.next();
			sb.append(key + " ");
			int count = hmCount.get(key);
			if(count>max){
				max = count;
			}

			Word word = hmNewWord.get(key);

			String str = hmSort.get(count);

			if(str==null){
				str = word.actual + "|" + word.cn;
			}else{
				try {
					str += "\r\n" + word.actual + "|" + word.cn;
				} catch (Exception e) {
					System.out.println("keys:" + sb.toString());
					e.printStackTrace();
				}
			}
			hmSort.put(count, str);
		}
		
		sb = new StringBuffer();
		for(int i=max; i>=0; i--){
			String str = hmSort.get(i);
			if(str!=null){
				sb.append("======" + i ).append("\r\n");
				sb.append(str).append("\r\n");
			}
		}
		return sb.toString();
	}
}
