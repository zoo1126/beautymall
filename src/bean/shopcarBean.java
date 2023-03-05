package bean;


public class shopcarBean
{
	private String pid;
	private String uid;
	private String pname;
	private String pnum;
	public shopcarBean(){}
	public shopcarBean(String pid,String uid,String pname,String pnum) 
	{
		this.pid=pid;
		this.uid=uid;
		this.pname=pname;
		this.pnum=pnum;
	}
	public void setPid(String pid)
	{
		this.pid=pid;
	}
	public String getPid()
	{
		return pid;
	}
	public void setUid(String uid)
	{
		this.uid=uid;
	}
	public String getUid()
	{
		return uid;
	}
	public void setPname(String pname)
	{
		this.pname=pname;
	}
	public String getPname()
	{
		return pname;
	}
	public void setPnum(String pnum)
	{
		this.pnum=pnum;
	}
	public String getPnum()
	{
		return pnum;
	}
}
