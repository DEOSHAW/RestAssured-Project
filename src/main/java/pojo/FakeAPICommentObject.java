package pojo;

import java.util.List;

public class FakeAPICommentObject 
{
	private List<FakeAPIComments> comments;
	private int total;
	private int skip;
	private int limit;
	
	public List<FakeAPIComments> getComments() {
		return comments;
	}
	public void setComments(List<FakeAPIComments> comments) {
		this.comments = comments;
	}
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getSkip() {
		return skip;
	}
	public void setSkip(int skip) {
		this.skip = skip;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
}
