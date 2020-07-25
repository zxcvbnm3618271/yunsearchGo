package com.voidlhf.netdisksearcher;

public class SelectListItem
{
	private String itemName;
	private String itemDescription;
	private boolean itemSelected;

	public SelectListItem(String itemName, String itemDescription, boolean itemSelected)
	{
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemSelected = itemSelected;
	}

	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}

	public String getItemName()
	{
		return itemName;
	}

	public void setItemDescription(String itemDescription)
	{
		this.itemDescription = itemDescription;
	}

	public String getItemDescription()
	{
		return itemDescription;
	}

	public void setItemSelected(boolean itemSelected)
	{
		this.itemSelected = itemSelected;
	}

	public boolean isItemSelected()
	{
		return itemSelected;
	}
	
	
}
