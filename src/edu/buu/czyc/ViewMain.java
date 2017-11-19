package edu.buu.czyc;

import edu.buu.czyc.conf.ViewMainConfig;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

public class ViewMain 
{
	private ViewXCZYC myViewXCZYC;
	private int screenW ;
	private int screenH ;
	
	private Bitmap bitmap;
	private Rect bitmapSrcRect , bitmapDesRect;
	
	private boolean bitmap_callcar_isPress = false;
	private Bitmap bitmap_callcar_s;
	private Rect  bitmapSrcRect_callcar_s, bitmapDesRect_callcar;
	
	private Bitmap bitmap_callcar_f,bitmap_callcar_f_s;
	
	
	private boolean bitmap_car_isPress = false;
	private Bitmap bitmap_car_s;
	private Rect bitmapSrcRect_car_s , bitmapDesRect_car_s;
	
	private boolean bitmap_setting_isPress = false;
	private Bitmap bitmap_setting_s;
	private Rect bitmapSrcRect_setting_s , bitmapDesRect_setting_s;
	
	
	private PositionCircle callcarPositionCircle;
	private PositionCircle carPositionCircle;
	private PositionCircle settingPositionCircle;
	
	private boolean temp1_isPress = false;
	private PositionCircle carPositionCircle_temp1;
	private boolean temp2_isPress = false;
	private PositionCircle settingPositionCircle_temp2;
	private boolean is_display; 
	public void set_is_display(boolean is_display)
	{
		this.is_display = is_display;
	}
	public boolean get_is_display()
	{
		return this.is_display;
	}

	public void againstOOM(boolean ini_flag)
	{
		if (ini_flag)
		{//分配
			this.bitmap = BitmapFactory.decodeResource(res, R.drawable.mainview);
			int BitWidth = bitmap.getWidth();
	        int BitHeight = bitmap.getHeight();
			bitmapSrcRect = new Rect(0, 0, BitWidth, BitHeight);
			bitmapDesRect = new Rect(0, 0, screenW, screenH);
			
			bitmap_callcar_f = BitmapFactory.decodeResource(res, R.drawable.main_view_callcar_f);
			bitmap_callcar_f_s = BitmapFactory.decodeResource(res, R.drawable.main_view_callcar_f_s);
			
			bitmap_callcar_s = BitmapFactory.decodeResource(res, R.drawable.main_view_callcar_s);
			bitmapSrcRect_callcar_s = new Rect(0, 0, bitmap_callcar_s.getWidth(), bitmap_callcar_s.getHeight()) ;
			bitmapDesRect_callcar = calcRect(ViewMainConfig.callcar_start_x ,
					ViewMainConfig.callcar_start_y,
					ViewMainConfig.callcar_end_x,
					ViewMainConfig.callcar_end_y
					); 
			
			bitmap_car_s = BitmapFactory.decodeResource(res, R.drawable.main_view_car_s);
			bitmapSrcRect_car_s = new Rect(0, 0, bitmap_car_s.getWidth(), bitmap_car_s.getHeight()) ;
			bitmapDesRect_car_s = calcRect(ViewMainConfig.car_start_x ,
					ViewMainConfig.car_start_y,
					ViewMainConfig.car_end_x,
					ViewMainConfig.car_end_y
					);
			
			
			bitmap_setting_s = BitmapFactory.decodeResource(res, R.drawable.main_view_setting_s);
			bitmapSrcRect_setting_s = new Rect(0, 0, bitmap_setting_s.getWidth(), bitmap_setting_s.getHeight()) ;
			bitmapDesRect_setting_s = calcRect(ViewMainConfig.setting_start_x ,
					ViewMainConfig.setting_start_y,
					ViewMainConfig.setting_end_x,
					ViewMainConfig.setting_end_y
					);
			
			callcarPositionCircle = new PositionCircle( calc_real_w(ViewMainConfig.callcar_start_x_circle) ,
					calc_real_h (ViewMainConfig.callcar_start_y_circle),calc_real_w(ViewMainConfig.callcar_start_y_circle_radius) );
			carPositionCircle = new PositionCircle( calc_real_w(ViewMainConfig.car_start_x_circle) ,
					calc_real_h (ViewMainConfig.car_start_y_circle),calc_real_w(ViewMainConfig.car_start_y_circle_radius) );
			settingPositionCircle = new PositionCircle( calc_real_w(ViewMainConfig.setting_start_x_circle) ,
					calc_real_h (ViewMainConfig.setting_start_y_circle),calc_real_w(ViewMainConfig.setting_start_y_circle_radius) );
			
			
			carPositionCircle_temp1 = new PositionCircle( calc_real_w(ViewMainConfig.car_start_x1_circle) ,
					calc_real_h (ViewMainConfig.car_start_y1_circle),calc_real_w(ViewMainConfig.car_start_y1_circle_radius) );
			settingPositionCircle_temp2 = new PositionCircle( calc_real_w(ViewMainConfig.setting_start_x1_circle) ,
					calc_real_h (ViewMainConfig.setting_start_y1_circle),calc_real_w(ViewMainConfig.setting_start_y1_circle_radius) );
		}
		else
		{//释放
			if (bitmap!=null)
			{
				bitmap.recycle();
			}
			bitmap = null;
			
			
		
			if (bitmap_callcar_f!=null)
			{
				bitmap_callcar_f.recycle();
			}
			bitmap_callcar_f = null;
			
			if (bitmap_callcar_f_s!=null)
			{
				bitmap_callcar_f_s.recycle();
			}
			bitmap_callcar_f_s = null;
			
			if (bitmap_callcar_s!=null)
			{
				bitmap_callcar_s.recycle();
			}
			bitmap_callcar_s = null;
			
			if (bitmap_car_s!=null)
			{
				bitmap_car_s.recycle();
			}
			bitmap_car_s = null;
			
			if (bitmap_setting_s!=null)
			{
				bitmap_setting_s.recycle();
			}
			bitmap_setting_s = null;
		}
	}
	Resources res;
	public ViewMain(ViewXCZYC myViewXCZYC,Resources res,int screenW,int screenH) 
	{
		this.res = res;
		this.myViewXCZYC = myViewXCZYC;
		this.screenW = screenW;
		this.screenH = screenH;
	}
	public Rect calcRect(int x1,int y1,int x2,int y2)
	{
		return new Rect((int)((float)x1/(float)ViewMainConfig.sign_w*(float)screenW),
				(int)((float)y1/(float)ViewMainConfig.sign_h*(float)screenH),
				(int)((float)x2/(float)ViewMainConfig.sign_w*(float)screenW),
				(int)((float)y2/(float)ViewMainConfig.sign_h*(float)screenH));
	}
	public int calc_real_w(int x1)
	{
		return (int)((float)x1/(float)ViewMainConfig.sign_w*(float)screenW);
	}
	public int calc_real_h(int y1)
	{
		return (int)((float)y1/(float)ViewMainConfig.sign_h*(float)screenH);
	}
	//绘制
	public void draw(Canvas canvas, Paint paint)
	{
		if (is_display)
		{
			if (bitmap==null)
			{
				againstOOM(true);
			}
			canvas.drawBitmap(bitmap, bitmapSrcRect, bitmapDesRect, null);
			
//			if (bitmap_callcar_isPress)
//			{
				//bitmap_callcar_f = BitmapFactory.decodeResource(res, R.drawable.main_view_callcar_f);
				//bitmap_callcar_f_s = BitmapFactory.decodeResource(res, R.drawable.main_view_callcar_f_s);
				if (myViewXCZYC.callcartype==1)
				{
					if (bitmap_callcar_isPress)
					{
						canvas.drawBitmap(bitmap_callcar_s, bitmapSrcRect_callcar_s, bitmapDesRect_callcar, null);
					}
					else
					{
						;
					}
				}
				else
				{
					if (bitmap_callcar_isPress)
					{
						canvas.drawBitmap(bitmap_callcar_f_s, bitmapSrcRect_callcar_s, bitmapDesRect_callcar, null);
					}
					else
					{
						canvas.drawBitmap(bitmap_callcar_f, bitmapSrcRect_callcar_s, bitmapDesRect_callcar, null);
					}
				}
				
//			}
			if (bitmap_car_isPress)
			{
				canvas.drawBitmap(bitmap_car_s, bitmapSrcRect_car_s, bitmapDesRect_car_s, null);
			}
			if (bitmap_setting_isPress)
			{
				canvas.drawBitmap(bitmap_setting_s, bitmapSrcRect_setting_s, bitmapDesRect_setting_s, null);
			}
		}
		else
		{
			againstOOM(false);
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
				if ( callcarPositionCircle.contains(pointX, pointY) ) 
				{
					bitmap_callcar_isPress = true;
				} 
				else 
				{
					bitmap_callcar_isPress = false;
				}
				if ( carPositionCircle.contains(pointX, pointY) ) 
				{
					bitmap_car_isPress = true;
				} 
				else 
				{
					bitmap_car_isPress = false;
				}
				if ( settingPositionCircle.contains(pointX, pointY) ) 
				{
					bitmap_setting_isPress = true;
				} 
				else 
				{
					bitmap_setting_isPress = false;
				}
				
				
				if ( carPositionCircle_temp1.contains(pointX, pointY) ) 
				{
					temp1_isPress = true;
				} 
				else 
				{
					temp1_isPress = false;
				}
				
				if ( settingPositionCircle_temp2.contains(pointX, pointY) ) 
				{
					temp2_isPress = true;
				} 
				else 
				{
					temp2_isPress = false;
				}
				
				break;
				//当用户是抬起动作
				
			case MotionEvent.ACTION_UP:
				if (bitmap_callcar_isPress)
				{
					if ( callcarPositionCircle.contains(pointX, pointY) ) 
					{
						this.set_is_display(false);
						myViewXCZYC.myViewCallCar.set_is_display(true);
					} 
				}
				if (bitmap_car_isPress)
				{
					if ( carPositionCircle.contains(pointX, pointY) ) 
					{
						this.set_is_display(false);
						myViewXCZYC.myViewCar.set_is_display(true);
					} 
				}
				if (bitmap_setting_isPress)
				{
					if ( settingPositionCircle.contains(pointX, pointY) ) 
					{
						this.set_is_display(false);
						myViewXCZYC.myViewSetting.set_is_display(true);
					} 
				}
				
				if (temp1_isPress)
				{
					Toast.makeText(myViewXCZYC.context, "预约 暂未开通",
						     Toast.LENGTH_SHORT).show();
				}
				if (temp2_isPress)
				{
					Toast.makeText(myViewXCZYC.context, "更多 暂未开通",
						     Toast.LENGTH_SHORT).show();
				}
				bitmap_callcar_isPress = false;
				bitmap_car_isPress = false;
				bitmap_setting_isPress = false;
				temp1_isPress = false;
				temp2_isPress = false;
				
				break;
		}
		return true;
	}
}
