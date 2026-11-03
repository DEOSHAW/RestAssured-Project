package pojo;

public class FakeAPIComments 
{
	
	private int id;
	private String body;
	private int postId;
	private int likes;
	private FakeAPIUser user;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public FakeAPIUser getUser() {
		return user;
	}
	public void setUser(FakeAPIUser user) {
		this.user = user;
	}
	
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
}
