package edu.buu.czyc;

public class PositionCircle 
{
	double pointx, pointy, radius;
	public PositionCircle(double pointx,double pointy,double radius) 
	{
		this.pointx = pointx;
		this.pointy = pointy;
		this.radius = radius;
	}
	public boolean contains(double x,double y) 
	{
		double d = (pointx-x)*(pointx-x) + (pointy-y)*(pointy-y);
		double distance =  Math.sqrt(d);
		
		if (distance>radius)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
}
