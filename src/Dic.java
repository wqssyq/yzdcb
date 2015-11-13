import java.util.HashMap;

/**
 * ×Öµä
 * dictionary
 * @author tiantian
 *
 */
public class Dic {

	public HashMap<String, String> hmWord = new HashMap<String, String>();

	public static Dic getDic(String fn) {
		Dic dic = new Dic();
		dic.init(fn);
		return dic;
	}

	//all dic
	public static Dic getAllDic() {
		String fn = "dic/all.txt";
		return getDic(fn);
	}
	
	//my dic
	public static Dic getMyDic() {
		String fn = "dic/myself.txt";
		return getDic(fn);
	}

	public void init(String _fn) {
		String txt = FileUtil.getStr(_fn);
		String[] datas = txt.split("\r\n");
		for (int i = 0; i < datas.length; i++) {
			String[] ss = datas[i].split("\\|");
			try {
				if (ss.length == 1) {
					hmWord.put(ss[0], "");
				} else {
					hmWord.put(ss[0], ss[1]);
				}
			} catch (Exception e) {
				System.err.println(datas[i]);
			}
		}
	}
	
	public void init2(String _fn) {
		String txt = FileUtil.getStr(_fn);
		String[] datas = txt.split("\r\n");
		for (int i = 0; i < datas.length; i++) {
			hmWord.put(datas[i], datas[i]);
		}
	}
	

	public String getCn(String en) {
		Word word = new Word();
		word.lowwer = en;
		getCn(word);
		return word.cn;
	}
	
	/**
	 * ²éµ¥´Ê
	 * @param word
	 */
	public void getCn(Word word) {
		String en = word.lowwer;

		while(en.startsWith("-") || en.startsWith("\'") ){
			en = en.substring(1);
		}
		while(en.endsWith("-") || en.endsWith("\'") ){
			en = en.substring(0, en.length()-1);
		}
		
		if(en.length()==0){
			word.cn = word.origin;
			return;
		}else if(en.length()==1){
			word.cn = word.origin;
			return;
		}
		
		String cn = hmWord.get(en);
		
		//Sunday
		if (cn == null) {
			cn = hmWord.get(word.origin);
			if(cn!=null){
				word.cn = cn;
				word.actual = word.origin;
				return;
			}
		}

		String key = "";
		if (cn == null) {
			if (en.endsWith("d")) {
				key = en.substring(0, en.length() - 1);
				cn = hmWord.get(key);
				if (cn == null) {
					if (en.endsWith("ed")) {
						key = en.substring(0, en.length() - 2);
						cn = hmWord.get(key);
						
						if (en.endsWith("ied")) {
							//specified => specify
							key = en.substring(0, en.length() - 3) + "y";
							cn = hmWord.get(key);
						}else if (cn == null && en.length()>4) {
							//occurred => occur
							if(en.charAt(en.length() - 3) == en.charAt(en.length() - 4)){
								key = en.substring(0, en.length() - 3);
								cn = hmWord.get(key);
							}
						}
					}
				}
			} else if (en.endsWith("s")) {
				key = en.substring(0, en.length() - 1);
				cn = hmWord.get(key);
				if (cn == null) {
					if (en.endsWith("'s")) {
						key = en.substring(0, en.length() - 2);
						cn = hmWord.get(key);
					}else if (en.endsWith("es")) {
						key = en.substring(0, en.length() - 2);
						cn = hmWord.get(key);
						if (cn == null) {
							//multiplies => multiply
							if (en.endsWith("ies")) {
								key = en.substring(0, en.length() - 3) + "y";
								cn = hmWord.get(key);
							}
						}
					}
				}
			} else if (en.endsWith("ing")) {
				key = en.substring(0, en.length() - 3);
				cn = hmWord.get(key);
				
				if(key.length()>2){
					//shopping => shop
					String key01 = key;
					if(key01.charAt(key01.length()-1) == key01.charAt(key01.length()-2)){
						key01 = key01.substring(0, key01.length() - 1);
						cn = hmWord.get(key01);
						if(cn!=null){
							key = key01;
						}
					}
				}
				
				if (cn == null) {
					//collating => collate
					key += "e";
					cn = hmWord.get(key);
				}
			}
		}
		
		word.cn = cn;
		if(key.length()>0 && cn!=null){
			word.actual = key;
		}else{
			word.actual = word.lowwer;
		}
	}
}
