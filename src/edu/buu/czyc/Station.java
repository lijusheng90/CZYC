package edu.buu.czyc;

import java.util.List;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Station {
	private List<Bitmap>  bitmap_stationlist;

	private Rect bitmapSrcRect,bitmapDesRect;
	//站id
	private int show_page_index = 0;
	private int len = 0;
	
	public Station(Rect bitmapSrcRect,Rect bitmapDesRect,
			List<Bitmap> bitmap_stationlist 
			)
	{
		this.bitmap_stationlist = bitmap_stationlist;
		len = this.bitmap_stationlist.size();
		if (len>0)
			show_page_index = 0;
	
		this.bitmapSrcRect = bitmapSrcRect ;
		this.bitmapDesRect = bitmapDesRect ;
	}
	public int  now_station_id()
	{//从1开始计算
		return show_page_index+1;
	}
	public void next()
	{
		show_page_index += 1 ;
		show_page_index = show_page_index %len;
	}
	public void pre()
	{
		show_page_index += (len-1);
		show_page_index = show_page_index %len;
	}
//	public void set_index_after(int index)
//	{
//		show_page_index += 1 ;
//		show_page_index = show_page_index %len;
//	}
//	public void set_index_before(int index)
//	{
//		show_page_index += (len-1);
//		show_page_index = show_page_index %len;
//	}
	
	//绘制
	public void draw(Canvas canvas, Paint paint)
	{
		canvas.drawBitmap(bitmap_stationlist.get(show_page_index), bitmapSrcRect, bitmapDesRect, null);
	}
}
