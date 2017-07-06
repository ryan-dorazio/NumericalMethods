package Langrange;

import java.util.Arrays;

public class DividedDifference {
	 
	private static double dividedDiffHelp(double[] x, double[] y, int n, int i){
		//compute the nth divided difference
		if (n==0){
			return(y[i]);
		}
		else{
			return (dividedDiffHelp(x,y,n-1,i+1)-dividedDiffHelp(x,y,n-1,i))/(x[i+n]-x[i]);
		}
	}
	
	public static double[] dividedDiff(double[] x, double[] y){
		//compute divided diff needed for each coefficient
		double[] coef= new double[x.length];
		for(int i =0;i<x.length;i++){
			coef[i]= dividedDiffHelp(x,y,i,0);
		}
		return coef;
	}
	
	public static String dividedOut(double[] x,double[] y){
		String out = "";
		double[] coef=dividedDiff(x,y);
		for(int i =0;i<coef.length;i++){
			String temp="";
			if(i ==0){
				temp = ""+coef[0];
			}
			else{
				
				for(int j=0; j<i;j++){
					temp=temp+"(x-"+x[j]+")";
				}
				temp = coef[i]+temp;
			}
			if(i!=coef.length-1){
				temp = temp+"+";
			}
			out = out+temp;

		}
		return out;
	}
	
	public static String printTable(double[] x, double[] y){
		//print line by line
		// number of columns will be the number of points -1+2
		String out = "";
		for(int row =0;row<x.length;row++){
			out = out+row +"  "+x[row]+"\t"+y[row]+"\t";
			for(int col=1; col<=row;col++){
				out = out + dividedDiffHelp(x,y,col,row-col)+"\t";
			}
			//new row
			out=out+"\n";
		}
		return out;
	}
	
	public static double interpolate(double x, double[] coef, double[] data){
		double y=0;
		for(int i=0;i<coef.length;i++){
			double product=1;
			for(int j=0;j<i;j++){
				product = product*(x-data[j]);
			}
			y=y+product*coef[i];
		}
		return y;
	}
	public static void main(String[] args){
		//PROBLEM 8
		//test dividedDiff
		double[] x = {0,0.1,0.3,0.6,1.0,1.1};
		double[] y={-6,-5.89483,-5.65014,-5.17788,-4.28172,-3.99583};
		//System.out.println(dividedDiffHelp(x,y,4,0));
	//	double[] coef = dividedDiff(x,y);
		//System.out.println(Arrays.toString(coef));
		System.out.println(dividedOut(x,y));
		
		//PROBLEM 9
		double[] x9 = {0,0.4,0.7};
		double[] y9={1,3,6};
		System.out.println(dividedOut(x9,y9));
		System.out.println(printTable(x9,y9));
		System.out.println(interpolate(0,dividedDiff(x9,y9),x9));
	}
	
	
}
