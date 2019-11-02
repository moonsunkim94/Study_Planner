package vo;

public class CalendarVO {

	private String caid;
	private String title;
	private String start_date;
	private String end_date;
	private String create_date;
	
	public String getCaid() {
		return caid;
	}
	public void setCaid(String caid) {
		this.caid = caid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	@Override
	public String toString() {
		return "CalendarVO [caid=" + caid + ", title=" + title + ", start_date=" + start_date + ", end_date=" + end_date
				+ ", create_date=" + create_date + "]";
	}
}
