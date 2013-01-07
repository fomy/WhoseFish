package org.fumin.whosefish;


public class AdjacentClue extends Clue{

	Attribute attr1;
	Attribute attr2;
	int dir;//attr2相对attr1的位置，负数为左相邻，正数为右相邻，0为相邻
	@Override
	public boolean match(Solution solution) {
		// TODO Auto-generated method stub
		int seat1=0, seat2=0;
		/*获取该Solution具有属性attr1和attr2的房子的位置*/
		for(int i=0; i<solution.size(); ++i){		
			/*获取此Solution第seat个房子*/
			House house = solution.get(i);
			if(house.get(attr1.getType().getId()-1)==attr1)
				seat1=i+1;
			if(house.get(attr2.getType().getId()-1)==attr2)
				seat2=i+1;	
			if(seat1!=0&&seat2!=0)
				break;
		}
		/*比较两个位置的关系*/
		if(dir==0){
			if(seat1-seat2==1 ||seat1-seat2==-1){
				//System.out.printf("%s和%s相邻匹配成功。\n",attr1.getValue(),attr2.getValue());
				return true;
			}
		}else if(dir==seat2-seat1){
			//System.out.printf("%s和%s相邻匹配成功。\n",attr1.getValue(),attr2.getValue());
			return true;
		}
		return false;
	}

	public AdjacentClue(Attribute attr1, Attribute attr2, int dir){
		this.attr1 = attr1;
		this.attr2 = attr2;
		this.dir = dir;
		if(dir==0)
			points=2;
	}
}
