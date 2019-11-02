package vo;

public class StudentVO {
	private int seq;			//占쏙옙占싱븝옙 占쏙옙占쏙옙 占쏙옙占쏙옙 primarykey
	private String id;			//占쏙옙占쏙옙占� id
	private String password;	//占쏙옙占쏙옙占� 占쏙옙橘占싫�
	private String name;		//占쏙옙占쏙옙占� 占싱몌옙
	private String lev;			//占쏙옙占쏙옙占� 占쏙옙占쏙옙 占쏙옙치(占쌩곤옙占�,占쏙옙占싻삼옙,占쏙옙占시삼옙)
	
	//수정!
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLev() {
		return lev;
	}
	public void setLev(String lev) {
		this.lev = lev;
	}
	@Override
	public String toString() {
		return "StudentVO [seq=" + seq + ", id=" + id + ", password=" + password + ", name=" + name
				+ ", lev=" + lev + "]";
	}
}
