import java.util.ArrayList;
/**
 * �ȶ� ����˵����߰���Դ����ļ��Ĳ��� 
 * @author wq
 *
 */
public class Main_Words {
	
	/**
	 * ֱ����������ʽ������Ҫ��
	 * ����ֱ��дһ�����ˡ�
	 * 
	 * ��Ҫ�����������: '-
	 * ��Ҫ�ָܻ�ԭ��
	 * 
	 * @param txt
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<WordEn> splitStr(String txt) throws Exception{
		if(txt.length()==0){
			return null;
		}
		
		boolean isWord = chkChar(txt.charAt(0));
		int idx = 0;
		
		ArrayList<WordEn> al = new ArrayList<WordEn>();
		for(int i=1; i<txt.length(); i++){
			char c = txt.charAt(i);
			if(isWord != chkChar(c)){
				String origin = txt.substring(idx, i);
				WordEn word = new WordEn();
				word.setWord(isWord);
				word.setOrigin(origin);
				al.add(word);
				idx = i;
				isWord = !isWord;
			}
		}
		
		if(idx<txt.length()){
			String origin = txt.substring(idx);
			WordEn word = new WordEn();
			word.setWord(isWord);
			word.setOrigin(origin);
			al.add(word);
		}
		
		return al;
	}
	
	/*
	 * �Ƿ񵥴ʵ���ĸ�� A-Za-z'-
	 */
	public static boolean chkChar(char c){
		if( (c>='A' && c <='Z') || (c>='a' && c <='z') 
				){
			return true;
		}
		return false;
	}
	
	/**
	 * do translate
	 * 
	 * @throws Exception
	 */
	public static void doTranslate(String docfn) throws Exception {
		
		String fn = docfn;
		
		String txt = FileUtil.getStr(fn);
		
		ArrayList<WordEn> al = splitStr(txt);
		
		//���ʣ�������
		String newFn3 = fn + ".nwd";
		String newWords3 = WordEn.showWords3();
		System.out.println( "newFn3:" + newFn3 );
		FileUtil.writeStr(newFn3, newWords3);
		
		//output  .html file
		StringBuffer sb = new StringBuffer();
		sb.append("<pre>");
		for(int i=0; i<al.size(); i++){
			WordEn word = al.get(i);
			if(word.isNew()){
				sb.append( word.origin + "<font color='blue'>(" + word.getCn() + ")</font>"  );
			}else{
				sb.append( al.get(i).getOrigin() );
			}
		}
		sb.append( "</pre>" );
		
		String outFn = fn + ".html";
		FileUtil.writeStr(outFn, sb.toString());
	}
	
	public static void preTranslate() throws Exception {
		String fn = "text/voa/1112/01.txt";
		doTranslate(fn);
	}
	
	public static void main(String[] args) throws Exception {
		if(args != null){
			System.out.println(args.length);
			if(args.length>0){
				for(int i=0; i<args.length; i++){
					System.out.println(args[i]);
				}
				doTranslate(args[0]);
			}
		}
		preTranslate();
	}

}
