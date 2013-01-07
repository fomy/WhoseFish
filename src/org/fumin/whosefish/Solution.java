package org.fumin.whosefish;

import java.util.ArrayList;
import java.util.Iterator;

import org.fumin.ga.God;
import org.fumin.ga.Individual;

public class Solution extends ArrayList<House>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6085788760293150735L;
	
	//public static ArrayList<Clue> clues=new ArrayList<Clue>();
	int matches=0;
	String codes;
	public static GivenClues clues=new GivenClues();
	
	public Solution(Individual rat){
		this.codes=rat.toString();
		int[] chromos = rat.getAllChromosome();
		int[] perms = Coder.decode(chromos);
		int p0,p1,p2,p3,p4;
		for(int j=0; j<5; ++j){
			House house = new House();
			p0 = (int) (perms[0]*Math.pow(10, j)/10000);
			p1 = (int) (perms[1]*Math.pow(10, j)/10000);
			p2 = (int) (perms[2]*Math.pow(10, j)/10000);
			p3 = (int) (perms[3]*Math.pow(10, j)/10000);
			p4 = (int) (perms[4]*Math.pow(10, j)/10000);
				
			perms[0] -= p0*10000/Math.pow(10, j);
			perms[1] -= p1*10000/Math.pow(10, j);
			perms[2] -= p2*10000/Math.pow(10, j);
			perms[3] -= p3*10000/Math.pow(10, j);
			perms[4] -= p4*10000/Math.pow(10, j);
				
			house.setAttr(p0, p1, p2, p3, p4);
			add(house);	
		}
	}
	
	/*
	 * 测试用，标准答案
	 * */
	public Solution(){
		House house = new House();			
		house.setAttr(2, 2, 1, 1, 1);
		add(house);	
		
		house = new House();			
		house.setAttr(3, 5, 5, 4, 2);
		add(house);	
		
		house = new House();			
		house.setAttr(1, 1, 4, 5, 3);
		add(house);	
		
		house = new House();			
		house.setAttr(4, 4, 2, 3, 4);
		add(house);	
		
		house = new House();			
		house.setAttr(5, 3, 3, 2, 5);
		add(house);	
	}
	
	public int getMatches(){
		matches=0;
		Iterator<Clue> iter = clues.iterator();
		while(iter.hasNext()){
			Clue clue = iter.next();
			if(clue.match(this))
				matches+=clue.points;
		}
		//System.out.print(this);
		
		if(God.Ones.size()==0||matches>God.Ones.get(0).matches){
			//System.out.printf("第%d代得到更大匹配度 %d\n",God.generation,matches);
			//System.out.print(this);
			God.Ones.clear();
			God.Ones.add((Solution)this.clone());
			//System.out.print(this);
		}else if(matches==God.Ones.get(0).matches){
			if(!God.Ones.get(0).equals(this)){
				God.Ones.clear();
				God.Ones.add((Solution)this.clone());
				//System.out.print(this);
			}
		}
		
		return matches;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String ret="第"+God.generation+"代：\n";
		//ret=ret.concat("匹配数").concat(""+matches).concat(":\n");
		Iterator<House> iter = iterator();
		while(iter.hasNext()){
			House house = iter.next();
			ret=ret.concat(house.toString());
		}
		ret=ret.concat(codes);
		ret=ret.concat("不匹配的线索：");
		for(int i=0; i<clues.size();++i){
			if(!clues.get(i).match(this))
				ret=ret.concat(i+1+" ");
		}
		ret=ret.concat("\n");
		return ret;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Solution other = (Solution) obj;
		if (codes == null) {
			if (other.codes != null)
				return false;
		} else if (!codes.equals(other.codes))
			return false;
		if (matches != other.matches)
			return false;
		return true;
	}

}
