package org.fumin.whosefish;

public class FixedClue extends Clue{

	/*固定的房间编号*/
	int seat;
	Attribute attr;
	
	@Override
	public boolean match(Solution solution) {
		// TODO Auto-generated method stub
		/*获取此Solution第seat个房子*/
		House house = solution.get(seat-1);
		/*获取该房子和attr同类型的属性，并比较*/
		if(attr==(house.get(attr.getType().getId()-1))){
			//System.out.printf("第%d个房子 和 %s 绑定，线索匹配成功。\n", seat, attr.getValue());
			return true;
		}
		return false;
	}

	public FixedClue(int seat, Attribute attr){
		this.seat = seat;
		this.attr = attr;
	}
}
