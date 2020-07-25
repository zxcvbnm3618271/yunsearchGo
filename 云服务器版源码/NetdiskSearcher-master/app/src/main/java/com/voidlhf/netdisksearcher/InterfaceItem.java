package com.voidlhf.netdisksearcher;

public class InterfaceItem
{
	private String name;
	private String rule;
	private String iconName;

	public InterfaceItem(String name, String rule, String iconName)
	{
		this.name = name;
		this.rule = rule;
		if(iconName.replace(" ","").length()>0) {
			this.iconName = iconName;
		} else {
			this.iconName = "site_default";
		}
		
	}
	
	public InterfaceItem(String name, String rule)
	{
		this.name = name;
		this.rule = rule;
		this.iconName = "site_default";
	}
	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setRule(String rule)
	{
		this.rule = rule;
	}

	public String getRule()
	{
		return rule;
	}

	public void setIconName(String iconName)
	{
		this.iconName = iconName;
	}

	public String getIconName()
	{
		return iconName;
	}
	
	
}
