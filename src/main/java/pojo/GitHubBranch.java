package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GitHubBranch 
{
	private String name;
	private Commit commit;
	@JsonProperty("protected")
	private boolean protect;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Commit getCommit() {
		return commit;
	}
	public void setCommit(Commit commit) {
		this.commit = commit;
	}
	public boolean isProtect() {
		return protect;
	}
	public void setProtect(boolean protect) {
		this.protect = protect;
	}
}
