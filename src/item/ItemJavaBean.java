package item;

public class ItemJavaBean 
{

	private float buy_price,sell_price,purchase_quantity,total_price,available_quantity,extra_charges;
	private String branch_id,branch_name,item_id,item_name,date,unit,month;
	
	public float getBuy_price() {
		return buy_price;
	}
	public void setBuy_price(float buy_price) {
		this.buy_price = buy_price;
	}
	
	
	public float getSell_price() {
		return sell_price;
	}	
	public void setSell_price(float sell_price) {
		this.sell_price = sell_price;
	}
	
	
	public float getPurchase_quantity() {
		return purchase_quantity;
	}
	public void setPurchase_quantity(float purchase_quantity) {
		this.purchase_quantity = purchase_quantity;
	}
	
	
	public float getTotal_price() {
		return total_price;
	}
	public void setTotal_price(float total_price) {
		this.total_price = total_price;
	}
	
	
	public float getAvailable_quantity() {
		return available_quantity;
	}
	public void setAvailable_quantity(float available_quantity) {
		this.available_quantity = available_quantity;
	}
	
	
	public float getExtra_charges() {
		return extra_charges;
	}
	public void setExtra_charges(float extra_charges) {
		this.extra_charges = extra_charges;
	}
	
	
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	
	
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	
	
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	
	
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}

}