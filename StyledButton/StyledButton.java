/*
* Author:Saswat Raj
*/
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

public class StyledButton extends FrameLayout {
	
	private Button button;
	private ImageButton imgBtn;

	public StyledButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater inflater = (LayoutInflater) context
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    inflater.inflate(R.layout.styledbutton, this, true);
		TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.StyledButton);
		String text = a.getString(R.styleable.StyledButton_btext);
		int resId = a.getResourceId(R.styleable.StyledButton_bimg, R.drawable.ic_launcher);
		FrameLayout fl = (FrameLayout) getChildAt(0);
		button = (Button)fl.getChildAt(0);
		imgBtn = (ImageButton) fl.getChildAt(1);
		button.setText(text);
		imgBtn.setImageResource(resId);
		a.recycle();
	}
	
	public StyledButton(Context context,AttributeSet attrs,String text,OnClickListener l,int resId){
		super(context,attrs);
		button.setText(text);
		imgBtn.setImageResource(resId);
		imgBtn.setOnClickListener(l);
	}
	
	public void setOnClickListener(OnClickListener l){
		button.setOnClickListener(l);
		imgBtn.setOnClickListener(l);
	}

}
