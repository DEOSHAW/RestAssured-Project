package pojo;

import java.util.List;

public class DBAPI {
	
		List<Posts> posts;
		List<Comments> comments;
		Profile profile;
		public List<Posts> getPosts() {
			return posts;
		}
		public void setPosts(List<Posts> posts) {
			this.posts = posts;
		}
		public List<Comments> getComments() {
			return comments;
		}
		public void setComments(List<Comments> comments) {
			this.comments = comments;
		}
		public Profile getProfile() {
			return profile;
		}
		public void setProfile(Profile profile) {
			this.profile = profile;
		}
		

}
