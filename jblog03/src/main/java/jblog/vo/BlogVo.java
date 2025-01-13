package jblog.vo;

public class BlogVo {
	private String blog_id;
	private String title;
	private String profile;
	
	public String getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(String blog_id) {
		this.blog_id = blog_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	@Override
	public String toString() {
		return "BlogVo [blog_id=" + blog_id + ", title=" + title + ", profile=" + profile + "]";
	}	
}
