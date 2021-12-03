package sales;

public class SalesJavaBean 
{
	
	private String branch_ID,branch_Name,date,item_ID,item_Name,item_Unit,extra_item;
	
	float available_Stock,item_Price_Per_Liter,sales_Quantity,sales_Amount;
	
	
	public String getBranch_ID() {
		return branch_ID;
	}

	public void setBranch_ID(String branch_ID) {
		this.branch_ID = branch_ID;
	}

	public String getBranch_Name() {
		return branch_Name;
	}

	public void setBranch_Name(String branch_Name) {
		this.branch_Name = branch_Name;
	}

	public float getAvailable_Stock() {
		return available_Stock;
	}

	public void setAvailable_Stock(float available_Stock) {
		this.available_Stock = available_Stock;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getItem_ID() {
		return item_ID;
	}

	public void setItem_ID(String item_ID) {
		this.item_ID = item_ID;
	}

	public String getItem_Name() {
		return item_Name;
	}

	public void setItem_Name(String item_Name) {
		this.item_Name = item_Name;
	}

	public String getItem_Unit() {
		return item_Unit;
	}

	public void setItem_Unit(String item_Unit) {
		this.item_Unit = item_Unit;
	}

	public float getItem_Price_Per_Liter() {
		return item_Price_Per_Liter;
	}

	public void setItem_Price_Per_Liter(float item_Price_Per_Liter) {
		this.item_Price_Per_Liter = item_Price_Per_Liter;
	}

	public float getSales_Quantity() {
		return sales_Quantity;
	}

	public void setSales_Quantity(float sales_Quantity) {
		this.sales_Quantity = sales_Quantity;
	}

	public float getSales_Amount() {
		return sales_Amount;
	}

	public void setSales_Amount(float sales_Amount) {
		this.sales_Amount = sales_Amount;
	}

	public void setExtraItem(String extraItem)
	{
		this.extra_item=extraItem;
	}
	public String getExtraItem() {
		return extra_item;
	}
	
}