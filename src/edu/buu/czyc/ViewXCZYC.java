package edu.buu.czyc;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class ViewXCZYC extends View  
{
	public ViewWelcome myViewWelcome;
	public ViewMain myViewMain;
	public ViewCar myViewCar;
	public ViewCallCar myViewCallCar;
	public ViewSetting myViewSetting;
	
	public static boolean screenSize = false;
	public static int screenW = 0;
	public static int screenH = 0;
	//声明一个Resources实例便于加载图片
	private Resources res = this.getResources();
	
	public static int selectcar = 1;
	public static int selectstyle = 1;
	//  1 叫车   2跟踪
	public static int TYPE_CALL_CAR = 1;
	public static int TYPE_FOLLOW = 2;
	public static int callcartype = 1;
	
	public static int station_start = 1;
	public static int station_end = 2;

  	public Context context;
  	
  	/**只有在主界面的时候才可以退出*/
  	public boolean can_quit()
  	{
  		if (myViewMain.get_is_display())
  			return true;
  		else
  			return false;
  	}
  	
	public ViewXCZYC(Context context) {
		super(context);
	}
	public ViewXCZYC(Context context, AttributeSet attrs) 
	 {
       super(context, attrs); 
       this.context = context;
	}
	
	@Override
	protected void onDraw(Canvas canvas) 
	{
		if (screenSize)
		{
	        //创建一个画笔的实例
			Paint paint = new Paint();
	  		myViewWelcome.draw(canvas, paint);
	  		myViewMain.draw(canvas, paint);
	  		myViewCar.draw(canvas, paint);
	  		myViewCallCar.draw(canvas, paint);
	  		myViewSetting.draw(canvas, paint);
		}
		super.onDraw(canvas);
	}
	
	@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) 
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if (!screenSize) 
		{
			screenSize = true;
			return;
		}
        if (screenW==0)
        {
        	screenH=this.getMeasuredHeight(); 
        	screenW=this.getMeasuredWidth();
        	
//        	Toast.makeText(context,screenH+","+screenW, Toast.LENGTH_SHORT).show();
        	
        	myViewWelcome = new ViewWelcome(res, screenW, screenH);
        	myViewWelcome.set_is_display(true);
        	myViewMain = new ViewMain(ViewXCZYC.this,res, screenW, screenH);
        	myViewMain.set_is_display(false);
        	myViewCar = new ViewCar(ViewXCZYC.this,res, screenW, screenH);
        	myViewCar.set_is_display(false);
        	myViewCallCar = new ViewCallCar(ViewXCZYC.this,res, screenW, screenH);
        	myViewCallCar.set_is_display(false);
        	myViewSetting = new ViewSetting(ViewXCZYC.this,res, screenW, screenH);
        	myViewSetting.set_is_display(false);
//        	
        	new Handler().postDelayed(new Runnable()
        	{    
        	    public void run() 
        	    {
        	    	myViewWelcome.set_is_display(false);
        	    	myViewMain.set_is_display(true);
        	    	//重绘画布
        			invalidate();
        	    }    
        	 }, 3000); 
        }
        
    }
	@Override
	public boolean onTouchEvent(MotionEvent event) 
	{
		boolean f = false;
		if (myViewWelcome.get_is_display())
			return true;
  		if (myViewMain.get_is_display())
  			f = myViewMain.onTouchEvent(event);
  		if (myViewCar.get_is_display())
  			f = myViewCar.onTouchEvent(event);
  		if (myViewSetting.get_is_display())
  			f = myViewSetting.onTouchEvent(event);
  		if (myViewCallCar.get_is_display())
  			f = myViewCallCar.onTouchEvent(event);
  		
		invalidate();
		if (f)
		{
			return true;
		}
		return super.onTouchEvent(event);
	}
}
