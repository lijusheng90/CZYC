package edu.buu.czyc;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import edu.buu.czyc.communication.ApplyManager;
import edu.buu.czyc.conf.ViewCallCarConfig;
import edu.buu.czyc.conf.ViewCarConfig;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.widget.Toast;

public class ViewCallCar_Call {
	private int screenW ;
	private int screenH ;
	Resources res;
	
	private Bitmap bitmap_arrow_left,bitmap_arrow_right;
	private Rect bitmapSrcRect_arrow_left , bitmapSrcRect_arrow_right,bitmapDesRect_arrow_right1,bitmapDesRect_arrow_left1,bitmapDesRect_arrow_right2,bitmapDesRect_arrow_left2;
	
	Station view_on_car,view_off_car;
	private boolean bitmap_arrow_right1_isPress = false;
	private boolean bitmap_arrow_right2_isPress = false;
	private boolean bitmap_arrow_left1_isPress = false;
	private boolean bitmap_arrow_left2_isPress = false;
	
	/**红点，绿点*/
	private Bitmap bitmap_car_on,bitmap_car_off;
	private Rect bitmapSrcRect_car_on , bitmapDesRect_car_on,bitmapDesRect_car_off;

	public ViewCallCar_Call(Resources res, int screenW ,int screenH) 
	{
		this.res = res;
		
		this.screenW = screenW;
		this.screenH = screenH;
	}
	public void againstOOM(boolean ini_flag)
	{
		if (ini_flag)
		{
			bitmap_arrow_left = BitmapFactory.decodeResource(res, R.drawable.arrow_left);
			bitmap_arrow_right = BitmapFactory.decodeResource(res, R.drawable.arrow_right );
			bitmapSrcRect_arrow_left = new Rect(0, 0, bitmap_arrow_left.getWidth(), bitmap_arrow_left.getHeight()) ;
			bitmapSrcRect_arrow_right = new Rect(0, 0, bitmap_arrow_right.getWidth(), bitmap_arrow_right.getHeight()) ;
			
			bitmapDesRect_arrow_left1  =  calcRect(ViewCallCarConfig.view_tip1_show_select1_start_x ,
					ViewCallCarConfig.view_tip1_show_select1_start_y,
					ViewCallCarConfig.view_tip1_show_select1_end_x,
					ViewCallCarConfig.view_tip1_show_select1_end_y
							); 
			bitmapDesRect_arrow_right1 =  calcRect(ViewCallCarConfig.view_tip1_show_select2_start_x ,
					ViewCallCarConfig.view_tip1_show_select2_start_y,
					ViewCallCarConfig.view_tip1_show_select2_end_x,
					ViewCallCarConfig.view_tip1_show_select2_end_y
							); 
			 bitmapDesRect_arrow_left2 =  calcRect(ViewCallCarConfig.view_tip2_show_select1_start_x ,
					ViewCallCarConfig.view_tip2_show_select1_start_y,
					ViewCallCarConfig.view_tip2_show_select1_end_x,
					ViewCallCarConfig.view_tip2_show_select1_end_y
							); 
			 bitmapDesRect_arrow_right2 =  calcRect(ViewCallCarConfig.view_tip2_show_select2_start_x ,
					ViewCallCarConfig.view_tip2_show_select2_start_y,
					ViewCallCarConfig.view_tip2_show_select2_end_x,
					ViewCallCarConfig.view_tip2_show_select2_end_y
							); 
			 
			 
			 Bitmap bitmap1 = BitmapFactory.decodeResource(res, R.drawable.station_name1);
				Bitmap bitmap2 = BitmapFactory.decodeResource(res, R.drawable.station_name2);
				Bitmap bitmap3 = BitmapFactory.decodeResource(res, R.drawable.station_name3);
				Bitmap bitmap4 = BitmapFactory.decodeResource(res, R.drawable.station_name4);
				Bitmap bitmap5 = BitmapFactory.decodeResource(res, R.drawable.station_name5);
				Bitmap bitmap6 = BitmapFactory.decodeResource(res, R.drawable.station_name6);
				Bitmap bitmap7 = BitmapFactory.decodeResource(res, R.drawable.station_name7);
				
				List<Bitmap> templist1 =  new ArrayList(); 
				templist1.add(bitmap1);
				templist1.add(bitmap6);
				templist1.add(bitmap5);
				templist1.add(bitmap4);
				templist1.add(bitmap3);
				templist1.add(bitmap2);
				List<Bitmap> templist2 =  new ArrayList(); 
				templist2.add(bitmap6);
				templist2.add(bitmap5);
				templist2.add(bitmap4);
				templist2.add(bitmap3);
				templist2.add(bitmap2);
				templist2.add(bitmap7);
				
				view_on_car = new Station(
						new Rect(0, 0, bitmap1.getWidth(),bitmap1.getHeight()),
						 calcRect(ViewCallCarConfig.view_station1_start_x, 
								ViewCallCarConfig.view_station1_start_y,
								ViewCallCarConfig.view_station1_end_x, 
								ViewCallCarConfig.view_station1_end_y),
						templist1
						) ;
				view_off_car = new Station(
						new Rect(0, 0, bitmap1.getWidth(),bitmap1.getHeight()),
						 calcRect(ViewCallCarConfig.view_station2_start_x, 
								ViewCallCarConfig.view_station2_start_y,
								ViewCallCarConfig.view_station2_end_x, 
								ViewCallCarConfig.view_station2_end_y),
						templist2
						) ;
				
				bitmap_car_on = BitmapFactory.decodeResource(res, R.drawable.select_car_on);
				bitmap_car_off = BitmapFactory.decodeResource(res, R.drawable.select_car_off);
				bitmapSrcRect_car_on = new Rect(0, 0, bitmap_car_on.getWidth(), bitmap_car_on.getHeight()) ;
				
				bitmapDesRect_car_on  =  calcRect(ViewCallCarConfig.view_car_on_start_x ,
						ViewCallCarConfig.view_car_on_start_y,
						ViewCallCarConfig.view_car_on_end_x,
						ViewCallCarConfig.view_car_on_end_y
								); 
				bitmapDesRect_car_off  =  calcRect(ViewCallCarConfig.view_car_off_start_x ,
						ViewCallCarConfig.view_car_off_start_y,
						ViewCallCarConfig.view_car_off_end_x,
						ViewCallCarConfig.view_car_off_end_y
								); 
		}
		else
		{//释放
			if (bitmap_arrow_left!=null)
			{
				bitmap_arrow_left.recycle();
			}
			bitmap_arrow_left = null;
			if (bitmap_arrow_right!=null)
			{
				bitmap_arrow_right.recycle();
			}
			bitmap_arrow_right = null;
			
			if (bitmap_car_on!=null)
			{
				bitmap_car_on.recycle();
			}
			bitmap_car_on = null;
			if (bitmap_car_off!=null)
			{
				bitmap_car_off.recycle();
			}
			bitmap_car_off = null;
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
			/**4个箭头*/
			canvas.drawBitmap(bitmap_arrow_left, bitmapSrcRect_arrow_left, bitmapDesRect_arrow_left1, null);
			canvas.drawBitmap(bitmap_arrow_left, bitmapSrcRect_arrow_left, bitmapDesRect_arrow_left2, null);
			canvas.drawBitmap(bitmap_arrow_right, bitmapSrcRect_arrow_right, bitmapDesRect_arrow_right1, null);
			canvas.drawBitmap(bitmap_arrow_right, bitmapSrcRect_arrow_right, bitmapDesRect_arrow_right2, null);
			
			/**上下车站*/
			view_on_car.draw(canvas, paint);
			view_off_car.draw(canvas, paint);
			
			/**红点，绿点*/
			canvas.drawBitmap(bitmap_car_on, bitmapSrcRect_car_on, bitmapDesRect_car_on, null);
			canvas.drawBitmap(bitmap_car_off, bitmapSrcRect_car_on, bitmapDesRect_car_off, null);
		}
		
		//上车车站id
		public int get_on_car_station() 
		{
			return view_on_car.now_station_id();
		}
		//下车车站id
		public int get_off_car_station() 
		{
			return view_off_car.now_station_id()+1;
		}
		
		public boolean onTouchEvent(MotionEvent event) 
		{
			//获取用户当前触屏位置
			int pointX = (int) event.getX();
			int pointY = (int) event.getY();
			boolean contain = false;
			
			switch (event.getAction() )
			{
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_MOVE:
					if ( bitmapDesRect_arrow_right1.contains(pointX, pointY) ) 
					{
						bitmap_arrow_right1_isPress = true;
						contain =  true;
					}
					else
						bitmap_arrow_right1_isPress = false;
					if ( bitmapDesRect_arrow_left1.contains(pointX, pointY) ) 
					{
						bitmap_arrow_left1_isPress = true;
						contain = true;
					}
					else
						bitmap_arrow_left1_isPress = false;
					
					if ( bitmapDesRect_arrow_right2.contains(pointX, pointY) ) 
					{
						bitmap_arrow_right2_isPress = true;
						contain = true;
					}
					else
						bitmap_arrow_right2_isPress = false;
					if ( bitmapDesRect_arrow_left2.contains(pointX, pointY) ) 
					{
						bitmap_arrow_left2_isPress = true;
						contain = true;
					}
					else
						bitmap_arrow_left2_isPress = false;
					break;
					//当用户是抬起动作
				case MotionEvent.ACTION_UP:
					if (bitmap_arrow_right1_isPress && bitmapDesRect_arrow_right1.contains(pointX, pointY) ) 
					{
						view_on_car.next();
						contain = true;
					}
					if (bitmap_arrow_left1_isPress && bitmapDesRect_arrow_left1.contains(pointX, pointY) ) 
					{
						view_on_car.pre();
						contain = true;
					}
					if (bitmap_arrow_right2_isPress && bitmapDesRect_arrow_right2.contains(pointX, pointY) ) 
					{
						view_off_car.next();
						contain = true;
					}
					if (bitmap_arrow_left2_isPress && bitmapDesRect_arrow_left2.contains(pointX, pointY) ) 
					{
						view_off_car.pre();
						contain = true;
					}
					bitmap_arrow_right1_isPress = false;
					bitmap_arrow_right2_isPress = false;
					bitmap_arrow_left1_isPress = false;
					bitmap_arrow_left2_isPress = false;
			}
			if (contain)
				return true;
			else
				return false;
		}	
}