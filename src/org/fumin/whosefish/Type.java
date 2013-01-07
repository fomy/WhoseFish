package org.fumin.whosefish;

/*
 * 属性的类型
 * 协议：属性的顺序是 颜色、国籍、饮料、宠物、香烟
 * */
public enum Type {

	Color(1),Nation(2),Drink(3),Pet(4),Cigarette(5);
	
	int id;
	Type(int id){
		this.id=id;
	}
	
	int getId(){
		return id;
	}
}
