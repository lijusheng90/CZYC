package edu.buu.czyc;

import java.util.ArrayList;
import java.util.List;

import edu.buu.czyc.conf.CallCarInfor;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Bitmap_tip1 {
	//概念车
	Bitmap bitmap_tip1_1;
	//,距离
	Bitmap bitmap_tip1_2;
	//公里,预计
	Bitmap bitmap_tip1_3;
	//分钟到达
	Bitmap bitmap_tip1_4;
	
	
	Bitmap bitmap_num_1;
	Bitmap bitmap_num_2;
	Bitmap bitmap_num_3;
	Bitmap bitmap_num_4;
	Bitmap bitmap_num_5;
	Bitmap bitmap_num_6;
	Bitmap bitmap_num_7;
	Bitmap bitmap_num_8;
	Bitmap bitmap_num_9;
	Bitmap bitmap_num_0;
	Bitmap bitmap_num_dot;
	
	List<Bitmap> bitmap_num_list;
	
	
	public void recycle()
	{
		if (bitmap_tip1_1!=null)
		this.bitmap_tip1_1.recycle();
		bitmap_tip1_1 = null;
		
		if (bitmap_tip1_2!=null)
		this.bitmap_tip1_2.recycle();
		bitmap_tip1_2 = null;
		
		if (bitmap_tip1_3!=null)
		this.bitmap_tip1_3.recycle();
		bitmap_tip1_3 = null;
		
		if (bitmap_tip1_4!=null)
		this.bitmap_tip1_4.recycle();
		bitmap_tip1_4 = null;
				
		if (bitmap_num_1!=null)
		bitmap_num_1.recycle();
		bitmap_num_1 = null;
		
		if (bitmap_num_2!=null)
		bitmap_num_2.recycle();
		bitmap_num_2 = null;
		
		if (bitmap_num_3!=null)
		bitmap_num_3.recycle();
		bitmap_num_3 = null;
		
		if (bitmap_num_4!=null)
		bitmap_num_4.recycle();
		bitmap_num_4 = null;
		
		if (bitmap_num_5!=null)
		bitmap_num_5.recycle();
		bitmap_num_5 = null;
		
		if (bitmap_num_6!=null)
		bitmap_num_6.recycle();
		bitmap_num_6 = null;
		
		if (bitmap_num_7!=null)
		bitmap_num_7.recycle();
		bitmap_num_7 = null;
		
		if (bitmap_num_8!=null)
		bitmap_num_8.recycle();
		bitmap_num_8 = null;
		
		if (bitmap_num_9!=null)
		bitmap_num_9.recycle();
		bitmap_num_9 = null;
		
		if (bitmap_num_0!=null)
		bitmap_num_0.recycle();
		bitmap_num_0 = null;
		
		if (bitmap_num_dot!=null)
		bitmap_num_dot.recycle();
		bitmap_num_dot = null;
		
	}
	
	public Bitmap_tip1(Resources res) 
	{
		this.bitmap_tip1_1 = BitmapFactory.decodeResource(res, R.drawable.bitmap_tip1_1);
		this.bitmap_tip1_2 = BitmapFactory.decodeResource(res, R.drawable.bitmap_tip1_2);
		this.bitmap_tip1_3 = BitmapFactory.decodeResource(res, R.drawable.bitmap_tip1_3);
		this.bitmap_tip1_4 = BitmapFactory.decodeResource(res, R.drawable.bitmap_tip1_4);
		
				
		bitmap_num_1 = BitmapFactory.decodeResource(res, R.drawable.num1);
		bitmap_num_2 = BitmapFactory.decodeResource(res, R.drawable.num2);
		bitmap_num_3 = BitmapFactory.decodeResource(res, R.drawable.num3);
		bitmap_num_4 = BitmapFactory.decodeResource(res, R.drawable.num4);
		bitmap_num_5 = BitmapFactory.decodeResource(res, R.drawable.num5);
		bitmap_num_6 = BitmapFactory.decodeResource(res, R.drawable.num6);
		bitmap_num_7 = BitmapFactory.decodeResource(res, R.drawable.num7);
		bitmap_num_8 = BitmapFactory.decodeResource(res, R.drawable.num8);
		bitmap_num_9 = BitmapFactory.decodeResource(res, R.drawable.num9);
		bitmap_num_0 = BitmapFactory.decodeResource(res, R.drawable.num0);
		
		bitmap_num_dot = BitmapFactory.decodeResource(res, R.drawable.num_dot);
		
		bitmap_num_list = new ArrayList<Bitmap>();
		bitmap_num_list.add(bitmap_num_0 )	;	
		bitmap_num_list.add(bitmap_num_1 )	;	
		bitmap_num_list.add(bitmap_num_2 )	;	
		bitmap_num_list.add(bitmap_num_3 )	;	
		bitmap_num_list.add(bitmap_num_4 )	;	
		
		bitmap_num_list.add(bitmap_num_5 )	;	
		bitmap_num_list.add(bitmap_num_6 )	;	
		bitmap_num_list.add(bitmap_num_7 )	;	
		bitmap_num_list.add(bitmap_num_8 )	;	
		bitmap_num_list.add(bitmap_num_9 )	;	
	}
	//把一个整数，变数个位数的list   123 -> [1,2,3]
	public  List<Integer> int_to_list(int num)
	{
		int num_i = num;
		List<Integer> mylist = new  ArrayList(); 
		List<Integer> mylist_return = new  ArrayList(); 
		if (num_i==0)
		{
			mylist.add( 0 );
		}
		else
		{
			for (int i = 0;num_i>0;i++)
			{
				 mylist.add( num_i%10 );
				 num_i = num_i/10;
			}
		}
		
		for (int i = mylist.size()-1;i>-1;i--)
		{
			mylist_return.add( mylist.get(i) );
		}
		
		return mylist_return;
	}
	public Bitmap getBitmap( )
	{
		List<Integer> distance_integer_list =  int_to_list( CallCarInfor.distance_integer);
		
		
		List<Integer> distance_float_list =  int_to_list( CallCarInfor.distance_float);
		
		List<Integer> arrivetime_list =  int_to_list( CallCarInfor.arrivetime);
		
		
		List<Bitmap> bitmap_list =   new ArrayList(); 
		bitmap_list.add(bitmap_tip1_1 );
		if(CallCarInfor.car_num==1)
		{
			bitmap_list.add(bitmap_num_1 );
		}
		else
		{
			bitmap_list.add(bitmap_num_2 );
		}
		
		bitmap_list.add(bitmap_tip1_2 );
		
		for (  int i=0;i<distance_integer_list.size();i++)
		{
			bitmap_list.add( bitmap_num_list.get( distance_integer_list.get(i) ) );
		}
		bitmap_list.add(bitmap_num_dot );
		for (  int i=0;i<distance_float_list.size();i++)
		{
			bitmap_list.add( bitmap_num_list.get( distance_float_list.get(i) ) );
		}
		bitmap_list.add(bitmap_tip1_3 );
		for (  int i=0;i<arrivetime_list.size();i++)
		{
			bitmap_list.add( bitmap_num_list.get( arrivetime_list.get(i) ) );
		}
		bitmap_list.add(bitmap_tip1_4 );
		
		return  new_bitmap_horizontal(bitmap_list);
	}
	
	/**
     * 拼接图片
     * @return 返回拼接后的Bitmap
     */
    private Bitmap new_bitmap_horizontal(List<Bitmap> bitmaplist)
    {
    	if (bitmaplist.size()<1)
    		return null;
        int width = 0;
        int height = bitmaplist.get(0).getHeight();
        for (int i = 0;i<bitmaplist.size();i++)
        {
        	width += bitmaplist.get(i).getWidth();
        }
        //创建一个空的Bitmap(内存区域),宽度等于第一张图片的宽度，高度等于两张图片高度总和
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        
        //将bitmap放置到绘制区域,并将要拼接的图片绘制到指定内存区域
        Canvas canvas = new Canvas(bitmap);
        for (int i = 0;i<bitmaplist.size();i++)
        {
        	int w = 0;
        	int h = 0;
        	for ( int ii = 0;ii<i;ii++)
        	{
        		w += (bitmaplist.get(ii).getWidth());
        	}
        	
        	canvas.drawBitmap(bitmaplist.get(i), w, h, null);
        }
        return bitmap;
    }
}
