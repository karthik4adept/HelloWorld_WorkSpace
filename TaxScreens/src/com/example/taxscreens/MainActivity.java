package com.example.taxscreens;

import java.util.ArrayList;
import java.util.zip.Inflater;

import android.R.color;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.transition.Visibility;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	
	LinearLayout linearLayout;
	Button btn_addNew, btn_applytoStore, btn_add_back, btn_add_save, btn_edit_back, btn_edit_Delete, btn_edit_Update;
	TextView tv_addNew, tv_add_desc, tv_add_value, view_id, tv_unfilled_tax, tv_unfilled_value;
	EditText et_add_desc, et_add_value, et_edit_desc, et_edit_value;
	RelativeLayout relativeHome, relativeDesc, relativeEdit, relativeX;
	View view12;
	int x;
    Animation anim1, anim2, anim3, anim4;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		view_id=(TextView)findViewById(R.id.view_id);
		linearLayout=(LinearLayout) findViewById(R.id.layout_mainTax);
		relativeHome=(RelativeLayout) findViewById(R.id.relative_home);
		relativeDesc=(RelativeLayout) findViewById(R.id.relative_AddNew);
		relativeEdit=(RelativeLayout) findViewById(R.id.relative_Edit);
		relativeX=(RelativeLayout) findViewById(R.id.relative_x);
		
		btn_addNew=(Button) findViewById(R.id.button_NewTax);
		btn_applytoStore=(Button) findViewById(R.id.button_ApplyToStore);
		btn_add_back=(Button) findViewById(R.id.button_desc_back);
		btn_add_save=(Button) findViewById(R.id.button_desc_save);
		btn_edit_back=(Button) findViewById(R.id.button_back_edit);
		btn_edit_Delete=(Button) findViewById(R.id.button_edit_delete);
		btn_edit_Update=(Button) findViewById(R.id.button_edit_update);

		et_add_desc=(EditText) findViewById(R.id.editText_description);
		et_add_value=(EditText) findViewById(R.id.editText_value);
		et_edit_desc=(EditText) findViewById(R.id.editText_edit_desc);
		et_edit_value=(EditText) findViewById(R.id.editText_edit_value);
		
		btn_add_save.setOnClickListener(this);
		btn_add_back.setOnClickListener(this);
		btn_addNew.setOnClickListener(this);
		btn_applytoStore.setOnClickListener(this);
		btn_edit_back.setOnClickListener(this);
		
		tv_unfilled_tax=(TextView) findViewById(R.id.textView_add_unfilled_Tax);
		tv_unfilled_value=(TextView) findViewById(R.id.textView_add_unfilled_Value);

		anim1=AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_enter);
		anim2=AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_exit);
		anim3=AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_negative);
		anim4=AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_leftright);
		
	}
		
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK){

			Builder dialog=new AlertDialog.Builder(this);
			dialog.setMessage("Sure you want to exit?");
			dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					finish();
				}
			});
			dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.cancel();
				}
			});
			dialog.setCancelable(false);
			dialog.show();
			
		
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.button_NewTax:
			relativeDesc.startAnimation(anim1);
			relativeHome.startAnimation(anim2);
			
			relativeHome.setVisibility(View.GONE);
			relativeEdit.setVisibility(View.GONE);
			relativeDesc.setVisibility(View.VISIBLE);
			break;
		
		case R.id.button_desc_back:
			
			relativeDesc.startAnimation(anim4);
			relativeHome.startAnimation(anim3);
			
			relativeHome.setVisibility(View.VISIBLE);
			relativeEdit.setVisibility(View.GONE);
			relativeDesc.setVisibility(View.GONE);
			
			et_add_desc.setText("");
			et_add_value.setText("");
			tv_unfilled_tax.setVisibility(View.GONE);
			tv_unfilled_value.setVisibility(View.GONE);
			break;
			
		case R.id.button_desc_save:

			String s1=et_add_desc.getText().toString();
			String s2=et_add_value.getText().toString();		
			
			if(!s1.matches("")&&!s2.matches("")){
				
			relativeDesc.startAnimation(anim4);
			relativeHome.startAnimation(anim3);
			
			relativeHome.setVisibility(View.VISIBLE);
			relativeEdit.setVisibility(View.GONE);
			relativeDesc.setVisibility(View.GONE);

			final View view=getLayoutInflater().inflate(R.layout.layout_row, null, false);
			view.setId(x);
			x++;
			
			final TextView tv_rowDesc=(TextView)view.findViewById(R.id.textView_row_desc);
			final TextView tv_rowValue=(TextView)view.findViewById(R.id.textView_row_value);
			CheckBox cb=(CheckBox) view.findViewById(R.id.checkBox_row);
			final Button btn_row=(Button) view.findViewById(R.id.button_row);
			
			tv_rowDesc.setText(s1);
			tv_rowValue.setText(s2);
			
			et_add_desc.getText().clear();
			et_add_value.getText().clear();			
						
			//btn_row.setEnabled(false);
			cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					if(isChecked)
					{
					btn_row.setVisibility(View.VISIBLE);
					//btn_row.setEnabled(true);
					int count=linearLayout.getChildCount();
					int selected_id=view.getId();
					
					for(int i=0;i<count;i++){
						
						View v_cb=linearLayout.getChildAt(i);
						CheckBox cb1=(CheckBox) v_cb.findViewById(R.id.checkBox_row);
						Button v_b1=(Button)v_cb.findViewById(R.id.button_row);
						
						if(selected_id==v_cb.getId())
						{
							
						}else
						{
							if(cb1.isChecked()){
							cb1.setChecked(false);
							v_b1.setVisibility(View.GONE);
							}
						}
						
					}
					
					}
					else{
						btn_row.setVisibility(View.GONE);
						view.setBackgroundColor(getResources().getColor(android.R.color.background_light));
						//btn_row.setEnabled(false);
					}
					
					/*int count=linearLayout.getChildCount();
					int selected_id=view.getId();
					for(int i=0;i<count;i++){
						
						View v_cb=linearLayout.getChildAt(i);
						CheckBox cb1=(CheckBox) v_cb.findViewById(R.id.checkBox_row);
						Button v_b1=(Button)v_cb.findViewById(R.id.button_row);
						
						if(selected_id==v_cb.getId())
						{
							
						}else
						{
							if(cb1.isChecked()){
							cb1.setChecked(false);
							v_b1.setVisibility(View.GONE);
							}
						}
						
					}
					*/
				}
			});
			
			btn_row.setBackgroundResource(R.drawable.ic_action_edit);
			btn_row.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					relativeHome.startAnimation(anim2);
					relativeEdit.startAnimation(anim1);
					
					relativeHome.setVisibility(View.GONE);
					relativeDesc.setVisibility(View.GONE);
					relativeEdit.setVisibility(View.VISIBLE);
			
					String name1=tv_rowDesc.getText().toString();
					String name2=tv_rowValue.getText().toString();
					view_id.setText(view.getId()+"");
					et_edit_desc.setText(name1);
					et_edit_value.setText(name2);
				}
			});
			
			btn_edit_Update.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					relativeHome.startAnimation(anim3);
					relativeEdit.startAnimation(anim4);
					
					relativeHome.setVisibility(View.VISIBLE);
					relativeDesc.setVisibility(View.GONE);
					relativeEdit.setVisibility(View.GONE);
					View dub_view=findViewById(Integer.parseInt(view_id.getText().toString()));
					
					String s1=et_edit_desc.getText().toString();
					String s2=et_edit_value.getText().toString();
					TextView textView1=(TextView) dub_view.findViewById(R.id.textView_row_desc);
					TextView textView2=(TextView) dub_view.findViewById(R.id.textView_row_value);
					textView1.setText(s1);
					textView2.setText(s2);
					//tv_rowDesc.setText(s1);
					//tv_rowValue.setText(s2);
					
					
					CheckBox cb=(CheckBox) dub_view.findViewById(R.id.checkBox_row);
					if(cb.isChecked()){
						cb.setChecked(false);
					}
					
				}
			});
			
			btn_edit_Delete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					relativeHome.setVisibility(View.VISIBLE);
					relativeDesc.setVisibility(View.GONE);
					relativeEdit.setVisibility(View.GONE);
					Toast.makeText(getApplicationContext(), "pressed", Toast.LENGTH_LONG).show();
					View dub_view=findViewById(Integer.parseInt(view_id.getText().toString()));
					linearLayout.removeView(dub_view);
				}
			});
		
			
/*			int count=linearLayout.getChildCount();
			
			for(int i=0;i<count;i++){
				
				View v_cb=linearLayout.getChildAt(i);
				CheckBox cb1=(CheckBox) v_cb.findViewById(R.id.checkBox_row);
				if(cb1.isChecked()){
					for(int j=1;j<count;j++){
						View v_cb2=linearLayout.getChildAt(j);
						CheckBox cb2=(CheckBox) v_cb2.findViewById(R.id.checkBox_row);
						cb2.setChecked(false);
					}
				}
			}
*/			
			linearLayout.addView(view);
			
			}else{
				Toast.makeText(getApplicationContext(), "Please complete all fields or go BACK ", Toast.LENGTH_SHORT).show();
				if(s1.equals("")&& s2.equals("")){

				tv_unfilled_tax.setVisibility(View.VISIBLE);	
				tv_unfilled_tax.setText("**");

				tv_unfilled_value.setVisibility(View.VISIBLE);
				tv_unfilled_value.setText("**");
				
				}else{
				
					if(s1.equals("")){
						tv_unfilled_tax.setVisibility(View.VISIBLE);	
						tv_unfilled_tax.setText("**");
					}
						tv_unfilled_value.setVisibility(View.VISIBLE);
						tv_unfilled_value.setText("**");
				}
			}
			
			/*final int count=linearLayout.getChildCount();
					
					for(int i=0;i<count;i++){					
						
						View view1=linearLayout.getChildAt(i);
						
						btn_row=(Button)view1.findViewById(R.id.button_row);
						btn_row.setTag(i);
						view1.setTag(i);
	
						cb=(CheckBox) view.findViewById(R.id.checkBox_row);
						btn_row.setEnabled(false);
						
						OnCheckedChangeListener checker= new OnCheckedChangeListener() {	@Override
							public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
								// TODO Auto-generated method stub
									if(cb.isChecked()){
										btn_row.setEnabled(true);
									}else if(btn_row.isEnabled()){
										btn_row.setEnabled(false);
									}
							}
						};
						cb.setOnCheckedChangeListener(checker);
						
						btn_row.setOnClickListener(new OnClickListener() {							
							@Override
							public void onClick(View v) {
								
								// TODO Auto-generated method stub
								relativeHome.setVisibility(View.GONE);
								relativeDesc.setVisibility(View.GONE);
								relativeEdit.setVisibility(View.VISIBLE);
						
								for( int i=0;i<linearLayout.getChildCount();i++)
								{
									final View v1=linearLayout.getChildAt(i);
									Log.d("T", v1.getTag()+", "+ v.getTag());
									
									if((Integer)v1.getTag()==(Integer)v.getTag())
									{
										tv_rowDesc=(TextView)v1.findViewById(R.id.textView_row_desc);
										tv_rowValue=(TextView)v1.findViewById(R.id.textView_row_value);
									
										Log.d("T", " DESC ::"+ tv_rowDesc.getText().toString()+" \n Value"+ tv_rowValue.getText().toString());	
										et_edit_desc.setText(tv_rowDesc.getText().toString());
										et_edit_value.setText(tv_rowValue.getText().toString());
										
										btn_edit_Update.setOnClickListener(new OnClickListener() {
											
											@Override
											public void onClick(View v) {
												// TODO Auto-generated method stub
												relativeHome.setVisibility(View.VISIBLE);
												relativeDesc.setVisibility(View.GONE);
												relativeEdit.setVisibility(View.GONE);
												
												tv_rowDesc.setText(et_edit_desc.getText().toString());
												tv_rowValue.setText(et_edit_value.getText().toString());
											}
										});
										
										btn_edit_Delete.setOnClickListener(new OnClickListener() {
											
											@Override
											public void onClick(View v) {
												// TODO Auto-generated method stub
												relativeHome.setVisibility(View.VISIBLE);
												relativeDesc.setVisibility(View.GONE);
												relativeEdit.setVisibility(View.GONE);
												
												linearLayout.removeView(v1);
												
											}
										});
										
										break;
									}
								}
								
							}
						});		
				}*/

			break;
		
		case R.id.button_back_edit:
			
			relativeHome.startAnimation(anim3);
			relativeEdit.startAnimation(anim4);

			relativeHome.setVisibility(View.VISIBLE);
			relativeEdit.setVisibility(View.GONE);
			relativeDesc.setVisibility(View.GONE);
			break;
			
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
