package edu.buu.czyc;


import edu.buu.czyc.conf.ViewCarConfig;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

public class ViewCar 
{
	private ViewXCZYC myViewXCZYC;
	private int screenW ;
	private int screenH ;
	
	private Bitmap bitmap_viewcar;
	private Rect bgSrcRect , bgDesRect;
	
	private boolean bitmap_car1_isPress = false;
	private Bitmap bitmap_car1_s;
	private Rect bitmapSrcRect_car1_s , bitmapDesRect_car1_s;
	
	private boolean bitmap_car2_isPress = false;
	private Bitmap bitmap_car2_s;
	private Rect bitmapSrcRect_car2_s , bitmapDesRect_car2_s;
	
	private boolean bitmap_confirm_isPress = false;
	private Bitmap bitmap_car1_confirm, bitmap_car1_confirm_s;
	private Bitmap bitmap_car2_confirm, bitmap_car2_confirm_s;
	private Rect bitmapSrcRect_confirm_s , bitmapDesRect_confirm_s;
	
	
	private Rect car1_rect ,car2_rect ;
	
	private boolean is_display; 
	
	public void set_is_display(boolean is_display)
	{
		this.is_display = is_display;
	}
	public boolean get_is_display()
	{
		return this.is_display;
	}
	
	public void against(boolean ini_flag)
	{
		if (ini_flag)
		{//分配
			this.bitmap_viewcar = BitmapFactory.decodeResource(res, R.drawable.viewcar);
			
			bgSrcRect = new Rect(0,0,bitmap_viewcar.getWidth(),bitmap_viewcar.getHeight() );
			bgDesRect = new Rect(0, 0, screenW, screenH);
			
			bitmap_car1_s = BitmapFactory.decodeResource(res, R.drawable.view_car1_s);
			bitmapSrcRect_car1_s = new Rect(0, 0, bitmap_car1_s.getWidth(), bitmap_car1_s.getHeight()) ;
			bitmapDesRect_car1_s = calcRect(ViewCarConfig.viewcar_car1_start_x ,
					ViewCarConfig.viewcar_car1_start_y,
					ViewCarConfig.viewcar_car1_end_x,
					ViewCarConfig.viewcar_car1_end_y
					); 
			
			bitmap_car2_s = BitmapFactory.decodeResource(res, R.drawable.view_car1_s);
			bitmapSrcRect_car2_s = new Rect(0, 0, bitmap_car2_s.getWidth(), bitmap_car2_s.getHeight()) ;
			bitmapDesRect_car2_s = calcRect(ViewCarConfig.viewcar_car2_start_x ,
					ViewCarConfig.viewcar_car2_start_y,
					ViewCarConfig.viewcar_car2_end_x,
					ViewCarConfig.viewcar_car2_end_y
					); 
			
			car1_rect  = calcRect(ViewCarConfig.car1_start_x_rect ,
					ViewCarConfig.car1_start_y_rect,
					ViewCarConfig.car1_end_x_rect,
					ViewCarConfig.car1_end_y_rect
					); 
			car2_rect  = calcRect(ViewCarConfig.car2_start_x_rect ,
					ViewCarConfig.car2_start_y_rect,
					ViewCarConfig.car2_end_x_rect,
					ViewCarConfig.car2_end_y_rect
					); 
			
			bitmap_car1_confirm_s = BitmapFactory.decodeResource(res, R.drawable.viewcar1confirm_s);
			bitmap_car1_confirm = BitmapFactory.decodeResource(res, R.drawable.viewcar1confirm);
			bitmap_car2_confirm_s = BitmapFactory.decodeResource(res, R.drawable.viewcar2confirm_s);
			bitmap_car2_confirm = BitmapFactory.decodeResource(res, R.drawable.viewcar2confirm);

			bitmapSrcRect_confirm_s = new Rect(0, 0, bitmap_car1_confirm_s.getWidth(), bitmap_car1_confirm_s.getHeight()) ;
			bitmapDesRect_confirm_s = calcRect(ViewCarConfig.viewcar_confirm_start_x ,
					ViewCarConfig.viewcar_confirm_start_y,
					ViewCarConfig.viewcar_confirm_end_x,
					ViewCarConfig.viewcar_confirm_end_y
					); 
		}
		else
		{//释放
			if (bitmap_viewcar!=null)
			{
				bitmap_viewcar.recycle();
			}
			bitmap_viewcar = null;
			
			if (bitmap_car1_s!=null)
			{
				bitmap_car1_s.recycle();
			}
			bitmap_car1_s = null;
			
			if (bitmap_car2_s!=null)
			{
				bitmap_car2_s.recycle();
			}
			bitmap_car2_s = null;
			
			if (bitmap_car1_confirm!=null)
			{
				bitmap_car1_confirm.recycle();
			}
			bitmap_car1_confirm = null;
			if (bitmap_car1_confirm_s!=null)
			{
				bitmap_car1_confirm_s.recycle();
			}
			bitmap_car1_confirm_s = null;
			if (bitmap_car2_confirm!=null)
			{
				bitmap_car2_confirm.recycle();
			}
			bitmap_car2_confirm = null;
			if (bitmap_car2_confirm_s!=null)
			{
				bitmap_car2_confirm_s.recycle();
			}
			bitmap_car2_confirm_s = null;
		}
	}
	
	Resources res;
	//爆炸效果的构造函数
	public ViewCar(ViewXCZYC myViewXCZYC,Resources res,int screenW,int screenH) 
	{
		this.myViewXCZYC = myViewXCZYC;
		this.screenW = screenW;
		this.screenH = screenH;
		this.res = res;
		
	}
	public Rect calcRect(int x1,int y1,int x2,int y2)
	{
		return new Rect((int)((float)x1/(float)ViewCarConfig.sign_w*(float)screenW),
				(int)((float)y1/(float)ViewCarConfig.sign_h*(float)screenH),
				(int)((float)x2/(float)ViewCarConfig.sign_w*(float)screenW),
				(int)((float)y2/(float)ViewCarConfig.sign_h*(float)screenH));
	}
	//绘制
	public void draw(Canvas canvas, Paint paint)
	{
		if (is_display)
		{
			if (bitmap_viewcar==null)
			{
				against(true);
			}
			canvas.drawBitmap(bitmap_viewcar, bgSrcRect, bgDesRect, null);
			if (myViewXCZYC.selectcar==1)
			{
				canvas.drawBitmap(bitmap_car1_s, bitmapSrcRect_car1_s, bitmapDesRect_car1_s, null);
			}
			else
			{
				canvas.drawBitmap(bitmap_car2_s, bitmapSrcRect_car2_s, bitmapDesRect_car2_s, null);
			}
			
			if (bitmap_confirm_isPress)
			{
				if (myViewXCZYC.selectcar==1)
					canvas.drawBitmap(bitmap_car1_confirm_s, bitmapSrcRect_confirm_s, bitmapDesRect_confirm_s, null);
				else
					canvas.drawBitmap(bitmap_car2_confirm_s, bitmapSrcRect_confirm_s, bitmapDesRect_confirm_s, null);
			}
			else
			{
				if (myViewXCZYC.selectcar==1)
					canvas.drawBitmap(bitmap_car1_confirm, bitmapSrcRect_confirm_s, bitmapDesRect_confirm_s, null);
				else
					canvas.drawBitmap(bitmap_car2_confirm, bitmapSrcRect_confirm_s, bitmapDesRect_confirm_s, null);
			}
		}
		else
		{
			against(false);
		}
	}
	
	//菜单触屏事件函数，主要用于处理按钮事件
	public boolean onTouchEvent(MotionEvent event) 
	{
		//获取用户当前触屏位置
		int pointX = (int) event.getX();
		int pointY = (int) event.getY();
		
		switch (event.getAction() )
		{
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_MOVE:
				//判定用户是否点击了按钮
				if ( bitmapDesRect_confirm_s.contains(pointX, pointY) ) 
				{
					bitmap_confirm_isPress = true;
				} 
				else 
				{
					bitmap_confirm_isPress = false;
				}
				
				if ( car1_rect.contains(pointX, pointY) ) 
				{
					bitmap_car1_isPress = true;
					
				} 
				else 
				{
					bitmap_car1_isPress = false;
				}
				
				if ( car2_rect.contains(pointX, pointY) ) 
				{
					bitmap_car2_isPress = true;
				} 
				else 
				{
					bitmap_car2_isPress = false;
				}
				break;
				//当用户是抬起动作
				
			case MotionEvent.ACTION_UP:
				if (bitmap_confirm_isPress)
				{
					if ( bitmapDesRect_confirm_s.contains(pointX, pointY) ) 
					{
						//还原Button状态为未按下状态
						this.set_is_display(false);
						myViewXCZYC.myViewMain.set_is_display(true);
					}
				}
				
				//抬起判断是否点击按钮，防止用户移动到别处
				if (bitmap_car1_isPress)
				{
					if ( car1_rect.contains(pointX, pointY) ) 
					{
						//还原Button状态为未按下状态
						myViewXCZYC.selectcar = 1;
					}
				}
				if (bitmap_car2_isPress)
				{
					if ( car2_rect.contains(pointX, pointY) ) 
					{
						//还原Button状态为未按下状态
						myViewXCZYC.selectcar = 2;
					}
				}
				bitmap_confirm_isPress = false;
				bitmap_car2_isPress = false;
				bitmap_car1_isPress = false;
				break;
		}
		return true;
	}
}
