package product;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="productbean")
@SessionScoped
public class productbean {

	private ArrayList<product> pro=new ArrayList<product>();
	public void add(ArrayList<product> p)
	{
		pro=p;
	}
	public void clearpro()
	{
		pro.clear();
	}
	public ArrayList<product> getpros()
	{
		return pro;
	}
	public int get()
	{
		return 1;
	}
	private String pid;
	private String pname;
	private String psort;
	private String brand;
	private float price;
	private int liked;
	private float discount;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPsort() {
		return psort;
	}
	public void setPsort(String psort) {
		this.psort = psort;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getLiked() {
		return liked;
	}
	public void setLiked(int liked) {
		this.liked = liked;
	}
	public void addliked()
	{
		liked++;
	}
	public void disliked()
	{
		liked--;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	
	
}
