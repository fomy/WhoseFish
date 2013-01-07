package org.fumin.ga;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * 种群
 * */
public class Population extends ArrayList<Individual>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3522092605090982358L;
	double fitnessAmount;
	
	public Population(){
		for(int i=0; i<God.SIZE; ++i){
			//System.out.printf("个体%d ", i+1);
			add(new Individual());
			//System.out.print("\n");
		}
	}
	
	/*
	 * 繁衍
	 * 顺序，选择-交叉-变异
	 * 一定要保证恢复到种群规模
	 * */
	public void reproduce(){
		God.generation++;
	
		/*选择*/	
		select();
		/*交叉*/
		crossover();
	}
	
	/*
	 * 轮盘赌选择个体进行交叉
	 * 单独测试能反映优胜劣汰
	 * */
	public void select(){
		Iterator<Individual> iter = this.iterator();
		//计算种群适应度总和
		fitnessAmount=0;
		while(iter.hasNext()){
			Individual indi = iter.next();
			fitnessAmount+=indi.fitness;
		}
		//不断选择个体进入交配池
		int number = this.size();
		while(this.size()<number+God.SIZE){
			double points;
			Individual winner=null;
			points = God.RAN.nextDouble()*fitnessAmount;
			double amount=0;
			for(int i=0;i<number;++i){
				Individual indi=get(i);
				amount+=indi.fitness;
				if(amount>=points){
					winner=(Individual)indi.clone();
					break;
				}
			}
			this.add(winner);
		}
		this.removeRange(0,number);
	}
	
	public void select_compete(){
		
	}
	
	/*交叉*/
	public void crossover(){
		for(int i=0; i+1<size();i=i+2){
			Individual father=get(i);
			Individual mother=get(i+1);
			father.recombine(mother);
		}
	}
	
}
