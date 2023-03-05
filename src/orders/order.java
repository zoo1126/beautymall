package orders;

public class order {

	private String o_pid;
	private String o_uid;
	private String oid;
	private float price;
	private int pcount;
	private float discount;
	private String est_time;
	private String fin_time;
	private String ret_time;
	private String state;
	public String getO_pid() {
		return o_pid;
	}
	public void setO_pid(String o_pid) {
		this.o_pid = o_pid;
	}
	public String getO_uid() {
		return o_uid;
	}
	public void setO_uid(String o_uid) {
		this.o_uid = o_uid;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getPcount() {
		return pcount;
	}
	public void setPcount(int pcount) {
		this.pcount = pcount;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public String getEst_time() {
		return est_time;
	}
	public void setEst_time(String est_time) {
		this.est_time = est_time;
	}
	public String getFin_time() {
		return fin_time;
	}
	public void setFin_time(String fin_time) {
		this.fin_time = fin_time;
	}
	public String getRet_time() {
		return ret_time;
	}
	public void setRet_time(String ret_time) {
		this.ret_time = ret_time;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
