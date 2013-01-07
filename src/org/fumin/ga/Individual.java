package org.fumin.ga;

import java.util.ArrayList;


public class Individual extends ArrayList<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8552885142859511672L;
		
	protected double fitness;
	
	public Individual(){
		for(int i=0; i<God.CHROMOSOME_AMOUNT;++i){
			add(God.RAN.nextInt(God.CHROMOSOME_UPPERLIMIT));
			//System.out.printf("<%d>", MASK & get(size()-1));
		}
		fitness=0;
	}

	/*
	 * 测试用，标准答案
	 * */
	public Individual(int TEST){
		add(30);
		add(43);
		add(22);
		add(17);
		add(0);
		fitness=0;
	}
	
	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		//System.out.printf("适应度%2.0f%%  ",fitness*100);
		this.fitness = fitness;
	}
	
	public int[] getAllChromosome(){
		int[] chroms = new int[size()];
		
		for(int i=0; i<size(); ++i){
			chroms[i] = God.MASK & get(i);
		}
		
		return chroms;
	}
	
	public void mutate(){
		double pm = God.Pm;
		if(fitness<0.6)
			pm=1;
		for(int i=0;i<size();++i){
			int x;
			do{
				x=this.get(i);
				double point = God.RAN.nextDouble();
				if(point>God.Pm)
					continue;
				x=x^(1<<(int)(God.CHROMOSOME_LENGTH*point/pm));			
			}while(x>=God.CHROMOSOME_UPPERLIMIT);
			set(i, x);
		}	
	}
	
	/*
	 * 每个染色体都是单点交叉
	 * */
	public void recombine(Individual indi){
		//遍历个体的染色体
		for(int i=0;i<size();++i){
			int x,y;
			do{
				x=this.get(i);
				y=indi.get(i);
				//int point = God.RAN.nextInt((int)(God.CHROMOSOME_LENGTH/God.Pc));
				double point = God.RAN.nextDouble();
				if(point>God.Pc)
					continue;
	 
				int mask=(int)Math.pow(2, (int)((point/God.Pc)*God.CHROMOSOME_LENGTH)+1)-1;//00……0111……1

				x = (x-(x&mask))+(x&mask)^(y&mask);
				y = (y-(y&mask))+(x&mask)^(y&mask);
				x = (x-(x&mask))+(x&mask)^(y&mask);
			}while(x>God.CHROMOSOME_UPPERLIMIT||y>God.CHROMOSOME_UPPERLIMIT);
			this.set(i, x);
			indi.set(i, y);
		}
	}
	
	public String toString(){
		String code="codes:";
		code=code.concat(""+get(0));
		code=code.concat(" "+get(1));
		code=code.concat(" "+get(2));
		code=code.concat(" "+get(3));
		code=code.concat(" "+get(4));
		code=code.concat("\n");
		return code;
	}
}
