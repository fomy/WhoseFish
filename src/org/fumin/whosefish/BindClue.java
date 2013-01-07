package org.fumin.whosefish;

import java.util.Iterator;

public class BindClue extends Clue{

	Attribute attr1;
	Attribute attr2;
	@Override
	public boolean match(Solution solution) {
		// TODO Auto-generated method stub
		Iterator<House> iter = solution.iterator();
		/*遍历五个房子，看是否存在房子同时具有属性attr1和attr2*/
		while(iter.hasNext()){
			House house = iter.next();
			if(attr1==(house.get(attr1.getType().getId()-1)) && 
			attr2==(house.get(attr2.getType().getId()-1))){
				//System.out.printf("%s 和 %s 绑定，线索匹配成功。\n", attr1.getValue(), attr2.getValue());
				return true;
			}
		}
		return false;
	}

	public BindClue(Attribute attr1, Attribute attr2){
		this.attr1 = attr1;
		this.attr2 = attr2;
	}
}
