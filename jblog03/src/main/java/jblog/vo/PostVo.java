package jblog.vo;

public class PostVo {
	private Long id;
	private Long categoryId;
	private String title;
	private String contents;
	private String reg_date;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	@Override
	public String toString() {
		return "PostVo [id=" + id + ", categoryId=" + categoryId + ", title=" + title + ", contents=" + contents
				+ ", reg_date=" + reg_date + "]";
	}
	
	
}
