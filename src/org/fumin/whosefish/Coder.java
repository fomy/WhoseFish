package org.fumin.whosefish;

import org.fumin.ga.God;

/*
 * 颜色/国籍/饮料/香烟/宠物，每一种排列的情况5!=120,可用1个字节编码。
 * 五个字节能表示一个可能解。
 * 此类将问题空间映射到遗传空间
 * */
public class Coder {

	public static byte[] code(int[] perms){
		byte[] ret = new byte[perms.length];
		for(int i=0; i<perms.length; ++i){
			ret[i] = code(perms[i]);
		}
		return ret;
	}
	
	public static int[] decode(int[] chromos){
		int[] ret = new int[chromos.length];
		for(int i=0; i<chromos.length; ++i){
			if((God.MASK & chromos[i]) > God.CHROMOSOME_UPPERLIMIT)
				return null;
			ret[i] = decode((byte)chromos[i]);
		}
		return ret;
	}
	/*
	 * 功能：将12345的排列映射到整数域[0,119]
	 * 思想：
	 * 每一位数字的权值，是在它之后的数字个数的阶乘，即权值分别为24,6,2,1,0
	 * 每一位数字的值，是将以它为首的数字串以升序排序后，它所在的位置减一。
	 * 比如3254,排序后是2345，那么“3”的位置是2，它的值就是1。因此类推。
	 * code(32415)=2*24+1*6+1*2+0=56
	 * */
	static byte code(int permutation){
		int x1=0,x2=0,x3=0,x4=0;
		byte ret=0;
		x1 = permutation/10000;
		permutation -= 10000*x1;
		x2 = permutation/1000;
		permutation -= 1000*x2;
		x3 = permutation/100;
		permutation -= 100*x3;
		x4 = permutation/10;
		
		ret += 24*(x1-1);
		
		x2 = x2>x1?x2-1:x2;
		x3 = x3>x1?x3-1:x3;
		x4 = x4>x1?x4-1:x4;
		ret += 6*(x2-1);
		
		x3 = x3>x2?x3-1:x3;
		x4 = x4>x2?x4-1:x4;
		ret += 2*(x3-1);
		
		x4 = x4>x3?x4-1:x4;
		ret+= x4-1;
		
		//System.out.printf("%d, ",ret);
		return ret;
	}
	
	/*
	 * 功能：将整数域[0,119]映射回12345的排列
	 * 思想：
	 * 每一个整数都是根据y=(x1-1)*24+(x2-1)*6+(x3-1)*2+(x4-1)*1计算出来的
	 * 对y依次取模再除以2,3,4就可以得到x1，x2，x3，x4，x5，
	 * 它们分别代表了各自在排序子串的位置
	 * 
	 * */
	static int decode(byte gene){
		int ret=0;
		int x1=0,x2=0,x3=0,x4=0,x5=0;
		/*恢复在子串的位置*/
		/*gene=24*x1+6*x2+2*x3+1*x4+0*x5;*/
		if(gene%2==0){/*x4=0,1*/
			x4=0;
			gene/=2;	
		}else if(gene%2==1){
			x4=1;
			gene=(byte)((gene-1)/2);
		}
		
		/*gene=12*x1+3*x2+x3*/
		if(gene%3==0){/*x3=0,1,2*/
			x3=0;
			gene/=3;
		}else if(gene%3==1){
			x3=1;
			gene=(byte)((gene-1)/3);
		}else if(gene%3==2){
			x3=2;
			gene=(byte)((gene-2)/3);
		}
		
		/*gene=4*x1+x2;*/
		if(gene%4==0){/*x2=0,1,2,3*/
			x2=0;
			gene/=4;
		}else if(gene%4==1){
			x2=1;
			gene=(byte)((gene-1)/4);
		}else if(gene%4==2){
			x2=2;
			gene=(byte)((gene-2)/4);
		}else if(gene%4==3){
			x2=3;
			gene=(byte)((gene-3)/4);
		}
		
		/*gene=x1;*/
		x1=gene;
		x1++;x2++;x3++;x4++;
		/* 
		 * 已获得各自在子串的位置
		 * 需要求它们分别在完整串的位置
		 * x1不需要上升位置，它在子串的位置就等于其在完整串的位置
		 * b2，b3，b4分别记录x2、x3、x4各自需要上升几个位置
		 * 某个数的位置需要上升几位取决于它左边的数有几个比它小
		 * */
		int b2=0,b3=0,b4=0;
		
		b2+=x2+b2>=x1?1:0;
		
		b3+=x3+b3>=x2?1:0;
		b3+=x3+b3>=x1?1:0;
		
		b4+=x4+b4>=x3?1:0;
		b4+=x4+b4>=x2?1:0;
		b4+=x4+b4>=x1?1:0;
		
		x2+=b2;
		x3+=b3;
		x4+=b4;
		/*x5没有参与code，也就不参与decode*/
		x5=15-x1-x2-x3-x4;
		
		ret=x1*10000+x2*1000+x3*100+x4*10+x5;
		//System.out.printf("%d\n", ret);
		return ret;
	}
	
	public static void main(String[] args){
		for(int i=0;i<120;++i){
			System.out.print(i+" ");
			System.out.printf("%d ",decode((byte)i));
			System.out.printf("%d\n",code(decode((byte)i)));
		}
	}
	
}
