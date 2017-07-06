package CubicSpline;

import java.util.Arrays;

public class NaturalCubicSpline {
	//this class file will compute the natural cubic spline
	//we are interpolating over data array x with values y
	
	public static double[][] fitSpline(double[]x,double[]y){
		//compute hi=xi+1-xi
		double[] h = new double[x.length-1];
		for(int i = 0;i<h.length;i++ ){
			h[i]=x[i+1]-x[i];
		}
		double[] a = new double[h.length];
		//a[0] left empty
		for(int i = 1;i<a.length;i++){
			a[i]=3/h[i]*(y[i+1]-y[i])-3/h[i-1]*(y[i]-y[i-1]);
		}
		//create extra arrays
		double[] l = new double[x.length];
		double[] u = new double[x.length];
		double[] z = new double[x.length];
		l[0]=0;
		z[0]=0;
		for(int i = 1;i<x.length-1;i++){
			l[i]=2*(x[i+1]-x[i-1])-h[i-1]*u[i-1];
			u[i]=h[i]/l[i];
			z[i]=(a[i]-h[i-1]*z[i-1])/l[i];
		}
		l[l.length-1]=1;
		z[z.length-1]=0;
		double[] c = new double[x.length];
		c[c.length-1]=0;
		
		double[] b = new double[x.length-1];
		double[] d = new double[x.length-1];
		
		for(int j = x.length-2;j>=0;j--){
			c[j]=z[j]-u[j]*c[j+1];
			b[j]=(y[j+1]-y[j])/h[j]-h[j]*(c[j+1]+2*c[j])/3;
			d[j]=(c[j+1]-c[j])/3/h[j];
		}
		
		double[][] coef = new double[x.length-1][4];
		for(int j = 0;j<x.length-1;j++){
			coef[j][0]=y[j];
			coef[j][1]=b[j];
			coef[j][2]=c[j];
			coef[j][3]=d[j];
		}
		
		return coef;
				
	}
	
	public static double interpolate(double x, double[] coef,double xdat){
		double val=0;
		for(int i = 0;i<coef.length;i++){
			val = val+coef[i]*Math.pow((x-xdat),i);
		}
		return val;
	}
	
	public static void main(String[] args){
		double[] x = {1,2,3};
		double[] y = {2,3,5};
		double[][] coef = fitSpline(x,y);
		System.out.println(Arrays.toString(coef[1]));
		System.out.println(interpolate((double)2,coef[0],x[0]));
		
		//Assignment numerical 7
		double[] years={1950,1960,1970,1980,1990,2000};
		double[] pop={151326,179323,203302,226542,249633,281422};
		double[][] coef2 = fitSpline(years,pop);
		System.out.println(Arrays.toString(coef2[0]));
		System.out.println(interpolate(1940.0,coef2[0],years[0]));
		System.out.println(Arrays.toString(coef2[2]));
		System.out.println(interpolate(1975,coef2[2],years[2]));
		System.out.println(Arrays.toString(coef2[4]));
		System.out.println(interpolate(2020,coef2[4],years[4]));
	}
	
	
}
