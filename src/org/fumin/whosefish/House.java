package org.fumin.whosefish;

import java.util.ArrayList;

public class House extends ArrayList<Attribute>{
	/*
	 * 协议：属性的顺序是 颜色、国籍、饮料、宠物、香烟
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 8124089089944365246L;

	public void setAttr(int a0,int a1, int a2,int a3,int a4){
		clear();
		add(Attribute.getAttr(a0, Type.Color));
		add(Attribute.getAttr(a1, Type.Nation));
		add(Attribute.getAttr(a2, Type.Drink));
		add(Attribute.getAttr(a3, Type.Pet));
		add(Attribute.getAttr(a4, Type.Cigarette));
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String ret = new String();
		for(int i=0; i<size(); ++i){
			ret=ret.concat(get(i).getValue());
			for(int j=10-get(i).getValue().length();j>0;--j){
				ret=ret.concat(" ");
			}
		}
		ret=ret.concat("\n");
		return ret;
	}
	
	
}
