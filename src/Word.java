
/**
 * word ������
 * @author wq
 *
 */
public class Word {
	protected String origin = "";     // ԭ��
	protected String lowwer = "";     // ת��Сд
	protected String actual = "";     // ʵ�ʵĵ���
	protected String cn = "";         // ����

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
