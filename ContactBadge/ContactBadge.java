/**
* Author: Saswat Raj
*/

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ContactBadge extends LinearLayout {
	
	//Also in android-tools check RoundedImageView
	private RoundedImageView rim;
	private TextView name;
	private TextView phnumber;
	private TextView icalls;
	private TextView ocalls;
	
	public ContactBadge(Context context,AttributeSet attr){
		super(context,attr);
		setOrientation(LinearLayout.HORIZONTAL);
		LayoutInflater inflater = (LayoutInflater) context
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    inflater.inflate(R.layout.contact_badge, this, true);
		LinearLayout ll = (LinearLayout)getChildAt(0);
		rim = (RoundedImageView)ll.getChildAt(0);
		LinearLayout ll1 = (LinearLayout)ll.getChildAt(1);
		LinearLayout ll2 = (LinearLayout)ll.getChildAt(2);
		name = (TextView) ll1.getChildAt(0);
		phnumber = (TextView)ll1.getChildAt(1);
		icalls = (TextView)ll2.getChildAt(0);
		ocalls = (TextView)ll2.getChildAt(1);
		//get the attribute values
		TypedArray a = context.obtainStyledAttributes(attr,R.styleable.ContactBadge);
		int resId = a.getResourceId(R.styleable.ContactBadge_contact_image, R.drawable.ic_default_picture);
		String cname = a.getString(R.styleable.ContactBadge_contact_name);
		String number = a.getString(R.styleable.ContactBadge_contact_number);
		String incom = a.getString(R.styleable.ContactBadge_incoming_calls);
		String outg = a.getString(R.styleable.ContactBadge_outgoing_calls);
		if(cname == null)
			cname = "Unknown";
		name.setText(cname);
		phnumber.setText(number);
		icalls.setText(incom);
		ocalls.setText(outg);
		rim.setImageResource(resId);
		a.recycle();
	}
	
	public ContactBadge(Context context,AttributeSet attr,String cname,
			String number,int incoming,int outgoing,String uriString)
	{
		this(context,attr);
		LayoutInflater inflater = (LayoutInflater) context
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    inflater.inflate(R.layout.contact_badge, this, true);
		if(uriString != null)
		rim.setImageURI(Uri.parse(uriString));
		else
		rim.setImageResource(R.drawable.ic_default_picture);
		if(cname == null)
			cname = "Unknown";
		name.setText(cname);
		phnumber.setText(number);
		icalls.setText(incoming+"");
		ocalls.setText(outgoing+"");
	}
}
