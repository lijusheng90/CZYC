package edu.buu.czyc;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import edu.buu.czyc.communication.ApplyManager;
import edu.buu.czyc.conf.ViewCallCarConfig;
import edu.buu.czyc.conf.ViewCarConfig;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.widget.Toast;

public class ViewCallCar {
	private Bitmap bitmap;
	private Rect bitmapSrcRect , bitmapDesRect;
	private int screenW ;
	private int screenH ;
	private ViewXCZYC myViewXCZYC;
	
	/**提示文字  上车车站     车辆信息*/
	private Bitmap bitmap_tip1_call,bitmap_tip1_follow;
	private Rect bitmapSrcRect_tip1 , bitmapDesRect_tip1;
	/**提示文字   下车车站    线路信息*/
	private Bitmap bitmap_tip2_call,bitmap_tip2_follow;
	private Rect bitmapSrcRect_tip2 , bitmapDesRect_tip2;
	/**黑色背景*/
	private Bitmap bitmap_tip_show;
	private Rect bitmapSrcRect_tipshow , bitmapDesRect_tip1show,bitmapDesRect_tip2show;
	/**地图范围*/
	private Rect mapRect;
	
	ViewCallCar_Call myViewCallCar_Call;
	ViewCallCar_Follow myViewCallCar_Follow;
	
	/**确定，返回*/
	private boolean bitmap_confirm_isPress = false;
	private Bitmap bitmap_confirm1,bitmap_confirm1_s,bitmap_back1,bitmap_back1_s;
	private Bitmap bitmap_confirm2,bitmap_confirm2_s,bitmap_back2,bitmap_back2_s;
	private Rect bitmapSrcRect_confirm,bitmapDesRect_confirm;
	/**确定，返回的范围*/
	private Rect view_bottom_button_rect;
	
	Resources res;
	
	public ViewCallCar(ViewXCZYC myViewXCZYC,Resources res,int screenW,int screenH) 
	{
		this.res = res;
		this.myViewXCZYC = myViewXCZYC;
		this.screenW = screenW;
		this.screenH = screenH;
		
		mapRect =  calcRect(ViewCallCarConfig.view_map_start_x ,
				ViewCallCarConfig.view_map_start_y,
				ViewCallCarConfig.view_map_end_x,
				ViewCallCarConfig.view_map_end_y
						); 
		myViewCallCar_Call = new ViewCallCar_Call(res,screenW,screenH);
		myViewCallCar_Follow = new ViewCallCar_Follow(res,screenW,screenH);
	}
	public void againstOOM(boolean ini_flag)
	{
		myViewCallCar_Call.againstOOM( ini_flag);
		myViewCallCar_Follow.againstOOM( ini_flag);
		if (ini_flag)
		{//分配
			this.bitmap = BitmapFactory.decodeResource(res, R.drawable.viewcallcar);
			int BitWidth = bitmap.getWidth();
	        int BitHeight = bitmap.getHeight();
			bitmapSrcRect = new Rect(0, 0, BitWidth, BitHeight);
			bitmapDesRect = new Rect(0, 0, screenW, screenH);
			
			this.bitmap_tip1_call = BitmapFactory.decodeResource(res, R.drawable.infor_station1 );
			this.bitmap_tip1_follow = BitmapFactory.decodeResource(res, R.drawable.infor_car);
			bitmapSrcRect_tip1 = new Rect(0, 0, bitmap_tip1_call.getWidth(), bitmap_tip1_call.getHeight()) ;
			bitmapDesRect_tip1 =  calcRect(ViewCallCarConfig.view_tip1_start_x ,
					ViewCallCarConfig.view_tip1_start_y,
					ViewCallCarConfig.view_tip1_end_x,
					ViewCallCarConfig.view_tip1_end_y); 
			
			this.bitmap_tip2_call = BitmapFactory.decodeResource(res, R.drawable.infor_station2);
			this.bitmap_tip2_follow = BitmapFactory.decodeResource(res, R.drawable.infor_road);
			bitmapSrcRect_tip2 = new Rect(0, 0, bitmap_tip2_call.getWidth(), bitmap_tip2_call.getHeight()) ;
			bitmapDesRect_tip2 =  calcRect(ViewCallCarConfig.view_tip2_start_x ,
					ViewCallCarConfig.view_tip2_start_y,
					ViewCallCarConfig.view_tip2_end_x,
					ViewCallCarConfig.view_tip2_end_y
							); 
			
			this.bitmap_tip_show = BitmapFactory.decodeResource(res, R.drawable.view_tipshow);
			bitmapSrcRect_tipshow = new Rect(0, 0, bitmap_tip_show.getWidth(), bitmap_tip_show.getHeight()) ;
			bitmapDesRect_tip1show =  calcRect(ViewCallCarConfig.view_tip1_show_start_x ,
					ViewCallCarConfig.view_tip1_show_start_y,
					ViewCallCarConfig.view_tip1_show_end_x,
					ViewCallCarConfig.view_tip1_show_end_y
							); 
			bitmapDesRect_tip2show =  calcRect(ViewCallCarConfig.view_tip2_show_start_x ,
					ViewCallCarConfig.view_tip2_show_start_y,
					ViewCallCarConfig.view_tip2_show_end_x,
					ViewCallCarConfig.view_tip2_show_end_y
							); 
			 
			 bitmap_confirm1 = BitmapFactory.decodeResource(res, R.drawable.viewcar1confirm);
			 bitmap_confirm1_s = BitmapFactory.decodeResource(res, R.drawable.viewcar1confirm_s);
			 bitmap_back1 = BitmapFactory.decodeResource(res, R.drawable.viewcar1back);
			 bitmap_back1_s = BitmapFactory.decodeResource(res, R.drawable.viewcar1back_s);
			 
			 bitmap_confirm2 = BitmapFactory.decodeResource(res, R.drawable.viewcar2back);
			 bitmap_confirm2_s = BitmapFactory.decodeResource(res, R.drawable.viewcar2back_s);
			 bitmap_back2 = BitmapFactory.decodeResource(res, R.drawable.viewcar2back);
			 bitmap_back2_s = BitmapFactory.decodeResource(res, R.drawable.viewcar2back_s);
			 
			  bitmapSrcRect_confirm = new Rect(0, 0, bitmap_confirm1.getWidth(), bitmap_confirm1.getHeight()) ;
			bitmapDesRect_confirm  =  calcRect(ViewCallCarConfig.view_bottom_button_start_x ,
					ViewCallCarConfig.view_bottom_button_start_y,
					ViewCallCarConfig.view_bottom_button_end_x,
					ViewCallCarConfig.view_bottom_button_end_y
							); 
			
			view_bottom_button_rect =  calcRect(ViewCallCarConfig.view_bottom_button_start_x_rect ,
					ViewCallCarConfig.view_bottom_button_start_y_rect,
					ViewCallCarConfig.view_bottom_button_end_x_rect,
					ViewCallCarConfig.view_bottom_button_end_y_rect
							); 
		}
		else
		{//释放
			if (bitmap!=null)
			{
				bitmap.recycle();
			}
			bitmap = null;
			
			if (bitmap_tip1_call!=null)
			{
				bitmap_tip1_call.recycle();
			}
			bitmap_tip1_call = null;
			
			if (bitmap_tip1_follow!=null)
			{
				bitmap_tip1_follow.recycle();
			}
			bitmap_tip1_follow = null;
			
			
			if (bitmap_tip2_call!=null)
			{
				bitmap_tip2_call.recycle();
			}
			bitmap_tip2_call = null;
			
			if (bitmap_tip2_follow!=null)
			{
				bitmap_tip2_follow.recycle();
			}
			bitmap_tip2_follow = null;
			
			if (bitmap_tip_show!=null)
			{
				bitmap_tip_show.recycle();
			}
			bitmap_tip_show = null;
			
			
			
			if (bitmap_back2!=null)
			{
				bitmap_back2.recycle();
			}
			bitmap_back2 = null;
			if (bitmap_back2_s!=null)
			{
				bitmap_back2_s.recycle();
			}
			bitmap_back2_s = null;
			
			if (bitmap_confirm2!=null)
			{
				bitmap_confirm2.recycle();
			}
			bitmap_confirm2 = null;
			if (bitmap_confirm2_s!=null)
			{
				bitmap_confirm2_s.recycle();
			}
			bitmap_confirm2_s = null;
			
			
			
			if (bitmap_confirm1!=null)
			{
				bitmap_confirm1.recycle();
			}
			bitmap_confirm1 = null;
			if (bitmap_confirm1_s!=null)
			{
				bitmap_confirm1_s.recycle();
			}
			bitmap_confirm1_s = null;
			if (bitmap_back1!=null)
			{
				bitmap_back1.recycle();
			}
			bitmap_back1 = null;
			if (bitmap_back1_s!=null)
			{
				bitmap_back1_s.recycle();
			}
			bitmap_back1_s = null;
			
		}
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
			if (bitmap==null)
			{
				againstOOM(true);
			}
			canvas.drawBitmap(bitmap, bitmapSrcRect, bitmapDesRect, null);
			canvas.drawBitmap(bitmap_tip_show, bitmapSrcRect_tipshow, bitmapDesRect_tip1show, null);
			canvas.drawBitmap(bitmap_tip_show, bitmapSrcRect_tipshow, bitmapDesRect_tip2show, null);
			
			if (myViewXCZYC.callcartype==1)
			{//叫车
				canvas.drawBitmap(bitmap_tip1_call, bitmapSrcRect_tip1, bitmapDesRect_tip1, null);
				canvas.drawBitmap(bitmap_tip2_call, bitmapSrcRect_tip2, bitmapDesRect_tip2, null);
					myViewCallCar_Call.draw( canvas,  paint);
				
				if (myViewXCZYC.selectcar==1)
				{
					if (bitmap_confirm_isPress)
					{
						canvas.drawBitmap(bitmap_confirm1_s, bitmapSrcRect_confirm, bitmapDesRect_confirm, null);
					}
					else
					{
						canvas.drawBitmap(bitmap_confirm1, bitmapSrcRect_confirm, bitmapDesRect_confirm, null);
					}
				}
				else
				{
					if (bitmap_confirm_isPress)
					{
						canvas.drawBitmap(bitmap_confirm2_s, bitmapSrcRect_confirm, bitmapDesRect_confirm, null);
					}
					else
					{
						canvas.drawBitmap(bitmap_confirm2, bitmapSrcRect_confirm, bitmapDesRect_confirm, null);
					}
				}
			}
			else
			{//跟踪
				canvas.drawBitmap(bitmap_tip1_follow, bitmapSrcRect_tip1, bitmapDesRect_tip1, null);
				canvas.drawBitmap(bitmap_tip2_follow, bitmapSrcRect_tip2, bitmapDesRect_tip2, null);
				
				myViewCallCar_Follow.draw(canvas, paint);
				
				if (myViewXCZYC.selectcar==1)
				{
					if (bitmap_confirm_isPress)
					{
						canvas.drawBitmap(bitmap_back1_s, bitmapSrcRect_confirm, bitmapDesRect_confirm, null);
					}
					else
					{
						canvas.drawBitmap(bitmap_back1, bitmapSrcRect_confirm, bitmapDesRect_confirm, null);
					}
				}
				else
				{
					if (bitmap_confirm_isPress)
					{
						canvas.drawBitmap(bitmap_back2_s, bitmapSrcRect_confirm, bitmapDesRect_confirm, null);
					}
					else
					{
						canvas.drawBitmap(bitmap_back2, bitmapSrcRect_confirm, bitmapDesRect_confirm, null);
					}
				}
			}
		}
		else
		{
			againstOOM(false);
		}
	}
	
	public boolean onTouchEvent(MotionEvent event) 
	{
		//获取用户当前触屏位置
		int pointX = (int) event.getX();
		int pointY = (int) event.getY();
		
		if (mapRect!=null)
		{
			if (mapRect.contains(pointX, pointY))
				return false;
		}
		if (myViewCallCar_Call.onTouchEvent(event))
			return true;
		
		switch (event.getAction() )
		{
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_MOVE:
				//判定用户是否点击了按钮
				if ( view_bottom_button_rect.contains(pointX, pointY) ) 
				{
					//还原Button状态为未按下状态
					bitmap_confirm_isPress = true;
				}
				else
				{
					bitmap_confirm_isPress = false;
				}
				break;
				//当用户是抬起动作
				
			case MotionEvent.ACTION_UP:
				if (bitmap_confirm_isPress)
				{
					if ( view_bottom_button_rect.contains(pointX, pointY) ) 
					{
						if (myViewXCZYC.callcartype == myViewXCZYC.TYPE_CALL_CAR)
						{//
							 ApplyManager myApplyManager = new ApplyManager();
								JSONObject jsonObj = null;
								try
								{
									int station_1 = myViewCallCar_Call.get_on_car_station();
									int station_2 = myViewCallCar_Call.get_off_car_station();
									
									if(station_1>=station_2)
									{
										Toast.makeText(myViewXCZYC.context,
												"上下车站有误！！！",Toast.LENGTH_SHORT).show();
										this.set_is_display(false);
										myViewXCZYC.myViewMain.set_is_display(true);
									}
									else
									{
										int selectcar =  myViewXCZYC. selectcar ;
										int selectstyle =  myViewXCZYC. selectstyle ;
										
										jsonObj = myApplyManager.do_apply(ViewCallCarConfig. DEVICE_ID+"",station_1,station_2,selectcar,selectstyle);
										if (jsonObj !=null)
										{
											if (jsonObj.getInt("ret") ==1 && jsonObj.getInt("cmd")==1)
											{
												myViewXCZYC.callcartype = myViewXCZYC.TYPE_FOLLOW;
												Toast.makeText(myViewXCZYC.context, "约车车辆成功！",Toast.LENGTH_SHORT).show();
											}
											else
											{
												Toast.makeText(myViewXCZYC.context, "无合适车辆！",Toast.LENGTH_SHORT).show();
											}
										}
										else
										{
											Toast.makeText(myViewXCZYC.context,"服务器忙或检查网络！", Toast.LENGTH_LONG).show();
										}
									}
								}
								catch (Exception e)
								{
									Toast.makeText(myViewXCZYC.context,"服务器忙或检查网络！！！", Toast.LENGTH_LONG).show();
								}
						}
						//还原Button状态为未按下状态
						this.set_is_display(false);
						myViewXCZYC.myViewMain.set_is_display(true);
					}
				}
				bitmap_confirm_isPress = false;
				
				break;
		}
		return true;
	}
	private boolean is_display; 
	public void set_is_display(boolean is_display)
	{
		this.is_display = is_display;
	}
	public boolean get_is_display()
	{
		return this.is_display;
	}
}
