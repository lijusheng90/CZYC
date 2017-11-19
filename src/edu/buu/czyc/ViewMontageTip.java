package edu.buu.czyc;

import java.util.ArrayList;
import java.util.List;

import edu.buu.czyc.conf.ViewCarConfig;
import edu.buu.czyc.conf.ViewMontageTipConfig;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.widget.Toast;

public class ViewMontageTip
{
	class NumberPosition
	{
		public int start_x ;
		public int start_y ;

		public int w;
		public int h;
		public NumberPosition(int start_x ,int start_y,int w,int h) 
		{
			this.start_x = start_x;
			this.start_y = start_y;
			this.w = w;
			this.h = h;
		}
	}
	//1-0的10个数字
	Bitmap bitmap_numbers;
	List<NumberPosition> bitmap_numbers_position;
	
	//概念车
	Bitmap bitmap_tip1_1;
	//,距离
	Bitmap bitmap_tip1_2;
	//公里,预计
	Bitmap bitmap_tip1_3;
	//分钟到达
	Bitmap bitmap_tip1_4;
	
	//上车：站点
	Bitmap bitmap_tip2_1;
	//，下车：站点
	Bitmap bitmap_tip2_2;
	ViewXCZYC myViewXCZYC;
	
	//正常大小的提示文字的图片  (上下站的图片的大小)
	Bitmap bitmap_commonsize ;
	private Rect bitmapSrcRect_commonsize ,bitmapDesRect_commonsize1,bitmapDesRect_commonsize2;
	public ViewMontageTip(int  screenW,int screenH, ViewXCZYC myViewXCZYC ) 
	{
		
//		Bitmap bitmap_commonsize ,
//		Bitmap bitmap_numbers,
//		Bitmap bitmap_tip1_1,
//		Bitmap bitmap_tip1_2,
//		Bitmap bitmap_tip1_3,
//		Bitmap bitmap_tip1_4,
//		Bitmap bitmap_tip2_1,
//		Bitmap bitmap_tip2_2,
//		int  screenW,int screenH, ViewXCZYC myViewXCZYC
		
		
		this.myViewXCZYC = myViewXCZYC;
		this.screenW = screenW;
		this.screenH = screenH;
		
		this.bitmap_commonsize = bitmap_commonsize;
		this.bitmap_numbers = bitmap_numbers;
		this.bitmap_tip1_1 = bitmap_tip1_1;
		this.bitmap_tip1_2 = bitmap_tip1_2;
		this.bitmap_tip1_3 = bitmap_tip1_3;
		this.bitmap_tip1_4 = bitmap_tip1_4;
		this.bitmap_tip2_1 = bitmap_tip2_1;
		this.bitmap_tip2_2 = bitmap_tip2_2;
	
		bitmapDesRect_commonsize1 = calcRect(ViewMontageTipConfig.view_tip1_start_x, 
				ViewMontageTipConfig.view_tip1_start_y,
				ViewMontageTipConfig.view_tip1_end_x, 
				ViewMontageTipConfig.view_tip1_end_y);
		
		bitmapDesRect_commonsize2 =  calcRect(ViewMontageTipConfig.view_tip2_start_x, 
				ViewMontageTipConfig.view_tip2_start_y,
				ViewMontageTipConfig.view_tip2_end_x, 
				ViewMontageTipConfig.view_tip2_end_y);
		
		bitmapSrcRect_commonsize = new Rect(0,0,
				bitmap_commonsize.getWidth(),bitmap_commonsize.getHeight()
				);
		bitmap_commonsize.recycle();
		bitmap_commonsize = null;
		
		bitmap_numbers_position  =  new ArrayList(); 
		bitmap_numbers_position.add(new NumberPosition(ViewMontageTipConfig.number_x_0,0,
				ViewMontageTipConfig.number_x_9,bitmap_numbers.getHeight()));
		
		bitmap_numbers_position.add(new NumberPosition(ViewMontageTipConfig.number_x_1,0,
				ViewMontageTipConfig.number_x_2,bitmap_numbers.getHeight()));
		
		bitmap_numbers_position.add(new NumberPosition(ViewMontageTipConfig.number_x_2,0,
				ViewMontageTipConfig.number_x_3,bitmap_numbers.getHeight()));
		
		bitmap_numbers_position.add(new NumberPosition(ViewMontageTipConfig.number_x_3,0,
				ViewMontageTipConfig.number_x_4,bitmap_numbers.getHeight()));
		
		bitmap_numbers_position.add(new NumberPosition(ViewMontageTipConfig.number_x_4,0,
				ViewMontageTipConfig.number_x_5,bitmap_numbers.getHeight()));
		
		bitmap_numbers_position.add(new NumberPosition(ViewMontageTipConfig.number_x_5,0,
				ViewMontageTipConfig.number_x_6,bitmap_numbers.getHeight()));
		
		bitmap_numbers_position.add(new NumberPosition(ViewMontageTipConfig.number_x_6,0,
				ViewMontageTipConfig.number_x_7,bitmap_numbers.getHeight()));
		
		bitmap_numbers_position.add(new NumberPosition(ViewMontageTipConfig.number_x_7,0,
				ViewMontageTipConfig.number_x_8,bitmap_numbers.getHeight()));
		
		bitmap_numbers_position.add(new NumberPosition(ViewMontageTipConfig.number_x_8,0,
				ViewMontageTipConfig.number_x_9,bitmap_numbers.getHeight()));
		
		bitmap_numbers_position.add(new NumberPosition(ViewMontageTipConfig.number_x_9,0,
				ViewMontageTipConfig.number_x_0,bitmap_numbers.getHeight()));
	}
	public Bitmap get_num(int num)
	{
		if (num<0)
			return null;
		if (num>9)
			return null;
		Bitmap bitmap_num = null;
		
//创建一个空的Bitmap(内存区域),宽度等于第一张图片的宽度，高度等于两张图片高度总和
//        Bitmap bitmap = Bitmap.createBitmap(bitmap_numbers_position.get(num).w, 
//        		bitmap_numbers_position.get(num).h, Bitmap.Config.ARGB_8888);
        
        bitmap_num = Bitmap.createBitmap(bitmap_numbers,
        		bitmap_numbers_position.get(num).start_x, 
        		bitmap_numbers_position.get(num).start_y,
        		bitmap_numbers_position.get(num).start_x+bitmap_numbers_position.get(num).w, 
        		bitmap_numbers_position.get(num).start_y+bitmap_numbers_position.get(num).h);
       return bitmap_num;
	}
	
	public String draw(Canvas canvas, Paint paint,int carnum,int dis ,int min,int station_1,int station_2)
	{
		String s = "";
		
		Bitmap bitmap_tip1  = draw_tip_1(carnum,dis,min);
		Bitmap bitmap_tip2  = draw_tip_2(station_1,station_2);
		
		Rect bitmapSrcRect_tip1_temp = new Rect( 0,0,bitmap_tip1.getWidth(),bitmap_tip1.getHeight() );
		Rect bitmapSrcRect_tip2_temp = new Rect( 0,0,bitmap_tip2.getWidth(),bitmap_tip2.getHeight() );
		
		Rect bitmapDesRect_tip1_real = calcRect_real(  bitmapDesRect_commonsize1,
				bitmapSrcRect_commonsize.right- bitmapSrcRect_commonsize.left, 
				bitmapSrcRect_tip1_temp.right - bitmapSrcRect_tip1_temp.left );
		Rect bitmapDesRect_tip2_real = calcRect_real(  bitmapDesRect_commonsize2,  
				bitmapSrcRect_commonsize.right- bitmapSrcRect_commonsize.left, 
				bitmapSrcRect_tip2_temp.right - bitmapSrcRect_tip2_temp.left );
		
//		Toast.makeText(myViewXCZYC.context,
//				ViewMontageTipConfig.view_tip1_start_y+",,,,"+ViewMontageTipConfig.view_tip1_end_y+"   "+
//				bitmapDesRect_commonsize1.top+",****,"+bitmapDesRect_commonsize1.bottom, Toast.LENGTH_LONG).show();
//		
		canvas.drawBitmap(bitmap_tip1, bitmapSrcRect_tip1_temp , bitmapDesRect_commonsize1, null);
		canvas.drawBitmap(bitmap_tip2, bitmapSrcRect_tip2_temp , bitmapDesRect_tip2_real, null);
		
		return s;
	}
	
	public Bitmap draw_tip_1(int carnum,int distance,int time)
	{
		List<Bitmap> bitmap_list =   new ArrayList(); 
		
		bitmap_list.add(bitmap_tip1_1 );
		bitmap_list.add(get_num(carnum) );
		bitmap_list.add(bitmap_tip1_2 );
		
		List<Integer> distancelist =  int_to_list(distance);
		for (int i = 0;i<distancelist.size();i++)
		{
			bitmap_list.add(get_num(distancelist.get(i)) );
		} 
		
		bitmap_list.add(bitmap_tip1_3 );
		
		List<Integer> timelist =  int_to_list(time);
		for (int i = 0;i<timelist.size();i++)
		{
			bitmap_list.add(get_num(timelist.get(i)) );
		} 
		
		bitmap_list.add(bitmap_tip1_4 );
		
		return  new_bitmap_horizontal(bitmap_list);
	}
	private int screenW ;
	private int screenH ;
	public Rect calcRect(int x1,int y1,int x2,int y2)
	{
		int s_x = (int)((float)x1/(float)ViewCarConfig.sign_w*(float)screenW);
		
		return new Rect(s_x,
				(int)((float)y1/(float)ViewCarConfig.sign_h*(float)screenH),
				(int)((float)x2/(float)ViewCarConfig.sign_w*(float)screenW),
				(int)((float)y2/(float)ViewCarConfig.sign_h*(float)screenH));
	}
	//rectDes  原来要打印到的位置（正常大小）      原来要打印的图片的宽度，   现在图片的宽度
	public Rect calcRect_real( Rect rectDes, int raw_w,int real_w)
	{
		int des_w = rectDes.right-rectDes.left;
		
		des_w = (int) ( ((float)real_w*(float)des_w)/(float)raw_w );
		
		return new Rect(rectDes.left,
				rectDes.top,
				rectDes.left+des_w,
				rectDes.bottom
				);
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
		
	//计算得到
	public Bitmap draw_tip_2(int station_up,int station_down)
	{
		List<Bitmap> bitmap_list =   new ArrayList(); 
		
		bitmap_list.add(bitmap_tip2_1 );
		
		List<Integer> distancelist =  int_to_list(station_up);
		for (int i = 0;i<distancelist.size();i++)
		{
			bitmap_list.add(get_num(distancelist.get(i)) );
		} 
		
		bitmap_list.add(bitmap_tip2_2 );
		
		List<Integer> distancelist2 =  int_to_list(station_down);
		for (int i = 0;i<distancelist2.size();i++)
		{
			bitmap_list.add(get_num(distancelist2.get(i)) );
		} 
		
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
        	height += bitmaplist.get(i).getHeight();
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
