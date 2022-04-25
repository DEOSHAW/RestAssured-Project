package pojo;

public class Markets {
	
	private String exchange_id;
	private String symbol;
	private String base_asset;
	private String quote_asset;
	private double price_unconverted;
	private double price;
	private double change_24h;
	private double spread;
	private double volume_24h;
	private String status;
	private String created_at;
	private String updated_at;
	
	
	public String getExchange_id() {
		return exchange_id;
	}
	public void setExchange_id(String exchange_id) {
		this.exchange_id = exchange_id;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getBase_asset() {
		return base_asset;
	}
	public void setBase_asset(String base_asset) {
		this.base_asset = base_asset;
	}
	public String getQuote_asset() {
		return quote_asset;
	}
	public void setQuote_asset(String quote_asset) {
		this.quote_asset = quote_asset;
	}
	public double getPrice_unconverted() {
		return price_unconverted;
	}
	public void setPrice_unconverted(double price_unconverted) {
		this.price_unconverted = price_unconverted;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getChange_24h() {
		return change_24h;
	}
	public void setChange_24h(double change_24h) {
		this.change_24h = change_24h;
	}
	public double getSpread() {
		return spread;
	}
	public void setSpread(double spread) {
		this.spread = spread;
	}
	public double getVolume_24h() {
		return volume_24h;
	}
	public void setVolume_24h(double volume_24h) {
		this.volume_24h = volume_24h;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	

}
