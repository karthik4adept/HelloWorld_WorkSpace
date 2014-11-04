package com.example.taxscreens;

import java.util.Random;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.CheckBox;

public class Tax_main extends Fragment {
	
	Button btn_Vat, btn_Sales, btn_Service, btn_Educess, btn_newTax, btn_ApplyStore;
	CheckBox cb_vat, cb_sales, cb_service, cb_educess;
	int key;
	FragmentManager manager;
	
	public Tax_main(){
		this.setRetainInstance(true);
		Random random=new Random();
		key = random.nextInt();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	
		View view= inflater.inflate(R.layout.layout_tax, null, false);
		
		btn_Vat=(Button) view.findViewById(R.id.button_Vat);
		btn_Sales=(Button) view.findViewById(R.id.button_sales);
		btn_Service=(Button) view.findViewById(R.id.button_service);
		btn_Educess=(Button) view.findViewById(R.id.button_educess);
		btn_newTax=(Button) view.findViewById(R.id.button_NewTax);
		btn_ApplyStore=(Button) view.findViewById(R.id.button_ApplyToStore);
		
		/*btn_Vat.setOnClickListener(this);
		btn_Sales.setOnClickListener(this);
		btn_Service.setOnClickListener(this);
		btn_Educess.setOnClickListener(this);
		btn_newTax.setOnClickListener(this);
		btn_ApplyStore.setOnClickListener(this);*/
		
		manager=getFragmentManager();
		
		return view;
	}

	/*@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		
		case R.id.button_NewTax:
			
			Tax_main.this.getView().setVisibility(View.GONE);
			Tax_desc ta_desc= new Tax_desc();
			FragmentTransaction ft= manager.beginTransaction();
			ft.add(R.id.relative_home, ta_desc);
			
			ft.commit();
			
			break;

		default:
			break;
		}
	}

*/	

}
