package org.fumin.ga;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Random;

import org.fumin.whosefish.Solution;

/*
 * genetic algorithm
 * 上帝建立了规则
 * */
public class God {

	/*
	 * 题目特性参数
	 * */
	public static int CHROMOSOME_AMOUNT=5;//个体具有的染色体数量
	public static int CHROMOSOME_LENGTH=7;//一条染色的基因数
	public static int CHROMOSOME_UPPERLIMIT=119;//一条染色体转换为整数的上限，0~119，inclusive

	/*
	 * 掩码，chromosome & mask得到的是有效基因序列 
	 * */
	public static int MASK=(1<<God.CHROMOSOME_LENGTH)-1;
	
	/*
	 * 遗传算法控制参数
	 * */
	public static int SIZE=50;//种群规模
	public static int TERMINATION=50000;//终止条at件
	public static double Pc=0.8;//一对染色体交叉概率
	public static double Pm=0.04;//一条染色体变异概率
	public static double NEAR_PERFECT=0.95;
	/*
	 * 变异的概率空间,控制变异的概率
	 * 有效基因数是5*7=35，共有CHROMOSOME_AMOUNT*32个基因
	 * 变异概率是35/SCOPE
	 * */

	public static int generation=1;
	/*上帝喜欢掷骰子*/
	
	public static Random RAN = new Random(Calendar.getInstance().getTimeInMillis());
	public static ArrayList<Solution> Ones=new ArrayList<Solution>();
	
	public static void play(){
		Population pop = new Population();
		while(/*true*/generation<=TERMINATION){
			//System.out.print("突变：");
			if(check(pop))
				return;
			pop.reproduce();
			//System.out.print("交叉：");
			if(check(pop))
				return;
			radiate(pop);
		}
		
		System.out.printf("未找到最优解，局部最优解：\n");
		Iterator<Solution> iter=Ones.iterator();
		while(iter.hasNext()){
			System.out.print(iter.next());
		}
		
	}
	/*
	 * 检查种群各个个体的适应度
	 * 寻找最优解
	 * 返回值：true，找到最优解;false，未找到
	 * */
	public static boolean check(Population population){
		Iterator<Individual> iter = population.iterator();
		while(iter.hasNext()){
			Individual rat = iter.next();
			rat.setFitness(fitnessFunc(rat));
			if(rat.getFitness()>=NEAR_PERFECT)
				return true;
		}
		//System.out.println();
		return false;
	}
	
	/*
	 * 上帝有多爱你
	 */
	public static double fitnessFunc(Individual rat){
		Solution solution = new Solution(rat/**/);
		int matches = solution.getMatches();
		double fitness = (double)matches/Solution.clues.pointsAmount;
		//System.out.print(solution);
		if(fitness>=NEAR_PERFECT){
			System.out.printf("\n运行了%d代，找到最优解:\n", generation);
			System.out.print(solution);
		}
		return fitness;
	}
	
	/*
	 * 刺激突变
	 * */
	public static void radiate(Population pop){
		Iterator<Individual> iter=pop.iterator();
		while(iter.hasNext()){
			iter.next().mutate();
		}
	}
}
