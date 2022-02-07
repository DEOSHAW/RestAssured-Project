package pojo;

public class GraphQLUsers {
	
	
	/*{
	  "query": "{\n  online_users(order_by: {id: asc}) {\n    id\n  }\n}\n",
	  "variables": null
	}*/
	
	private String query;
	private String variables;
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getVariables() {
		return variables;
	}
	public void setVariables(String variables) {
		this.variables = variables;
	}
	

}
