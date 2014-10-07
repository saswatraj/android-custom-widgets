package com.example.simplerank;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class MeterCircle extends View{
	
	private int bgColor;
	private int meterColor;
	private int meterPathColor;
	private int textColor;
	private int initValue;
	private Paint p1;
	private Paint p2;
	private Paint p3;
	private final int FRAME_RATE = 20;
	private Handler handler;
	private Runnable runnable;
	private int sweepAngle = 0;
	private int bboxSize;
	private int width;
	private int height;
	private float padding;
	private float strokeWidth;

	public MeterCircle(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public MeterCircle(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.MeterCircle);
		bgColor = a.getColor(R.styleable.MeterCircle_bgColor, Color.BLACK);
		meterColor = a.getColor(R.styleable.MeterCircle_meterColor, Color.WHITE);
		meterPathColor = a.getColor(R.styleable.MeterCircle_mpathColor, Color.GRAY);
		textColor = a.getColor(R.styleable.MeterCircle_textColor, Color.WHITE);
		initValue = a.getInteger(R.styleable.MeterCircle_initValue, 35);
		padding = a.getDimension(R.styleable.MeterCircle_ipadding, 0);
		strokeWidth = a.getDimension(R.styleable.MeterCircle_strokeWidth,60f);
		padding = padding + strokeWidth;
		//initialize the paints
		p1 = new Paint();
		p2 = new Paint();
		p3 = new Paint();
		p1.setColor(meterColor);
		p2.setColor(meterPathColor);
		p3.setColor(textColor);
		p1.setAntiAlias(true);
		p2.setAntiAlias(true);
		p3.setAntiAlias(true);
		p1.setStyle(Paint.Style.STROKE);
		p2.setStyle(Paint.Style.STROKE);
		p3.setStyle(Paint.Style.STROKE);
		p1.setStrokeWidth(strokeWidth);
		p2.setStrokeWidth(strokeWidth);
		p3.setStrokeWidth(4.5f);
		p3.setTextSize(45);
		//set handler and runnable
		handler = new Handler();
		runnable = new Runnable() {
			
			@Override
			public void run() {
				invalidate();	
				if(sweepAngle<3.6*initValue){
					sweepAngle++;
					handler.postDelayed(runnable, FRAME_RATE);
				}
			}
		};
		runnable.run();
		//recycle
		a.recycle();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(bgColor);
		if(height>width)
			bboxSize = width;
		else
			bboxSize = height;
		bboxSize = bboxSize - (int)padding;
		RectF rectF = new RectF(padding, padding, bboxSize, bboxSize);
		canvas.drawCircle(rectF.centerX(), rectF.centerY(), rectF.width()/2, p1);
		float textWidth = p3.measureText(initValue+"%");
		float xcor = (bboxSize+padding)/2 - textWidth/2 ;
		canvas.drawText(initValue+"%", xcor, (bboxSize+padding)/2, p3);
		canvas.drawOval(rectF, p3);
		canvas.drawArc (rectF, -90, sweepAngle, false, p2);
	}

	
	public void setDimensions(int width,int height){
		this.width = width;
		this.height = height;
	}
	
	public void start(){
		runnable.run();
	}
	

}
