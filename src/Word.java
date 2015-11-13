
/**
 * word 数据类
 * @author wq
 *
 */
public class Word {
	protected String origin = "";     // 原文
	protected String lowwer = "";     // 转成小写
	protected String actual = "";     // 实际的单词
	protected String cn = "";         // 中文

	public String getActual() {
		return actual;
	}

	public void setActual(String actual) {
		this.actual = actual;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getLowwer() {
		return lowwer;
	}
	
	public void setLowwer(String lowwer) {
		this.lowwer = lowwer;
	}

	public String toString() {
		return lowwer;
	}
}
