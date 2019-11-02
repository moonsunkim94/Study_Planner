package vo;

public class GgraphVO {

	private String seq;
	private String graID;
	private String exam_name;
	private String exam_date ;
	private String exam_subject ;
	private String exam_grade;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getGraID() {
		return graID;
	}
	public void setGraID(String graID) {
		this.graID = graID;
	}
	public String getExam_name() {
		return exam_name;
	}
	public void setExam_name(String exam_name) {
		this.exam_name = exam_name;
	}
	public String getExam_date() {
		return exam_date;
	}
	public void setExam_date(String exam_date) {
		this.exam_date = exam_date;
	}
	public String getExam_subject() {
		return exam_subject;
	}
	public void setExam_subject(String exam_subject) {
		this.exam_subject = exam_subject;
	}
	public String getExam_grade() {
		return exam_grade;
	}
	public void setExam_grade(String exam_grade) {
		this.exam_grade = exam_grade;
	}
	@Override
	public String toString() {
		return "GgraphVO [seq=" + seq + ", graID=" + graID + ", exam_name=" + exam_name + ", exam_date=" + exam_date
				+ ", exam_subject=" + exam_subject + ", exam_grade=" + exam_grade + "]";
	}
	
	
}
