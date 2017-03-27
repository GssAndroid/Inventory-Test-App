
package com.example.spinventory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListActivityText extends Activity implements OnItemClickListener {

	
	String[] value,id,colName;
	Button submit,click;
	
	boolean[] cb = new boolean[100];
 	ListView inventory;
 	ArrayList <String> checkedValue  = new ArrayList<String>();
 	private ArrayList <String> selectedStrings  = new ArrayList<String>();
 	ArrayList <String> uncheckedValue = new ArrayList<String>();
 	TextView tv1;
	
	
	 String content,name;
	 ListAdapter adapter;
	// ArrayAdapter<String> adapter;
 	
 	final String NAMESPACE = "urn:sap-com:document:sap:soap:functions:mc-style"; // Name space from WSDL
 	final String METHOD_NAME = "ZGssmwfmHndlEvntrqst00"; //Operation Name form WSDL
 	final String SOAP_ACTION = "http://75.99.152.10:8050/sap/bc/srt/wsdl/bndg_E0A8AEE275F3AEF1AE7900188B47B426/wsdl11/allinone/ws_policy/document?sap-client=110";
 	final String URL ="http://gsswd.globalsoftsolutions.net:7500/sap/bc/srt/rfc/sap/z_gssmwfm_hndl_evntrqst00/110/z_gssmwfm_hndl_evntrqst00/z_gssmwfm_hndl_evntrqst00";
 	
 	String result,searchContent,rephrase,firstrow;
 	String[] strArray,stockArr,heading,len;
 	String[][] resArr;
 	String line4 = "MD_T_MATNR[.]";
 	String line5 = "MD_T_MATNR[.]";
   
 	
 	@Override
 	protected void onCreate(Bundle savedInstanceState) {
 	super.onCreate(savedInstanceState);
 	setContentView(R.layout.listview_main);
 	
 	click = (Button) findViewById(R.id.click);
 	inventory = (ListView) findViewById(R.id.list);
 	final CheckBox cb1 = (CheckBox) findViewById(R.id.cb);
 	
 	/* Context context = getApplicationContext();
 	
	  SharedPreferences preferences = context.getSharedPreferences("MYPREFS", context.MODE_PRIVATE);
	// SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
	String name = preferences.getString("Checked","");
	
	System.out.println("SELECTED: "+ name); */
 	/* String value1 = null;
 	Bundle b = getIntent().getExtras();
 	if (b!=null) { // Here we could assume i.e. we get 2 return params of typ int with this name. They were put in the called Activity like we made in above code, so, `intent.put...`
 	     value1  = b.getString("Checked");
 	   
 	}
 	System.out.println("String Value: "+ value1); */

 /*	Intent i = getIntent();
	String content = i.getStringExtra("Checked"); 
	System.out.println("Selected:" + content); */ 
 	
	//System.out.println("1");
 	
 	final List<RowItem> rowItems;
 	
 	 rowItems = new ArrayList<RowItem>();
 	 
 	System.out.println("2");
 	
 	 Intent intent = getIntent();
 	System.out.println("a");
		value = intent.getStringArrayExtra("Value");
		System.out.println("Value Length :" +value.length);
		System.out.println("b");
		id = intent.getStringArrayExtra("Id");
		System.out.println("ID Length :" +id.length);
		System.out.println("c");
//		cb = in.getBooleanArrayExtra("cb");
     
//     value = getResources().getStringArray(R.array.value);
     
//     id = getResources().getStringArray(R.array.id);

		
 for (int j = 0; j< value.length; j++) {
//		for (int j = 0; j< 20; j++) {
//	 System.out.println("d"); 
	 RowItem item = new RowItem(value[j], id[j], cb[j]);
	// System.out.println("e");
      rowItems.add(item);
      //System.out.println("f");
     }

 //System.out.println("3");
 
 	Listadapter Adapter = new Listadapter(this,rowItems);//, packageManager);
 	//System.out.println("4");
 	
 	
	
 	
 	 	
// 	if(Adapter.getCount()==1)
// 	{
 		
// 		 		System.out.println("only one");
// 		inventory.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
 		
 		/*inventory.post(new Runnable() {

 	        @Override
 	        public void run() {
 	           inventory.setItemChecked(0,true);          
 	           }
 	        }); */
 		
 		

// 		inventory.setItemChecked(0,true);
// 		inventory.performItemClick(inventory.getSelectedView(), 0, 0);

// 		inventory.setAdapter(Adapter);
 	 	//inventory.setOnItemClickListener(this);  

 		
/* 		System.out.println("only one one");
 		inventory.setItemChecked(0,true);
 		System.out.println("only one one one ");
 	//	inventory.getChildAt(1).setBackgroundColor(Color.GREEN);     
 		ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListActivityText.this, android.R.layout.simple_list_item_single_choice, value);
 		System.out.println("only one one one one");
        inventory.setAdapter(adapter);
        System.out.println("only one one one one one"); */
// 	}
 	
// 	else
 	{
 	
 	inventory.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
 	//System.out.println("5");
     	inventory.setAdapter(Adapter);
     //	System.out.println("6");
 	inventory.setOnItemClickListener(this);
 	//System.out.println("7");
 	}
 	
 	
 /*	inventory.setOnItemClickListener(new OnItemClickListener() {
 	
 		public void onItemClick(AdapterView<?> parent, View v, int position,
 				long id) {
 			// TODO Auto-generated method stub
 			
 			System.out.println("0");
 			CheckBox cb = (CheckBox) v.findViewById(R.id.cb);
 		 	TextView tv = (TextView) v.findViewById(R.id.val);
 		 //	TextView tv1 = (TextView) v.findViewById(R.id.id);
 		 	cb.performClick();
 		 	System.out.println("00");
 		 	if (cb.isChecked()){
 		 		System.out.println("000");
 		 	checkedValue.add(tv.getText().toString());
 		 	}
 		 
 		 	else if (!cb.isChecked()){ 
 		 	checkedValue.remove(tv.getText().toString());
 		 	}
 		
 		}
 	}); */
 		
  	//System.out.println("9"); 	
 	click.setOnClickListener(new View.OnClickListener() {
	     public void onClick(View v) {
	         // Do something in response to button click
	    	 Context context = getApplicationContext();
	 	 	
			  SharedPreferences preferences = context.getSharedPreferences("MYPREFS", context.MODE_PRIVATE);
			  
			// SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
			  
			  //Passing id as string instead of arraylist(set in shared preferences)
			/*  name = preferences.getString("Checked","");
			  System.out.println("SELECTED: "+ name); */

			  Set<String> set = preferences.getStringSet("Checked",null);
			
			System.out.println("SELECTED: "+ set); 
			
	    	 	 
	     
	    	 Intent i = new Intent(ListActivityText.this, Details.class);
	    	// i.putString("CheckedVal", name);
	    	 startActivity(i);
	     }
	 });
 	}


	/*@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	} */

	@Override
 	public boolean onCreateOptionsMenu(Menu menu) {
 	// Inflate the menu; this adds items to the action bar if it is present.
 	getMenuInflater().inflate(R.menu.main, menu);
 	return true;
 	}
	
   
	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position,
			long arg3) {
		// TODO Auto-generated method stub
	/*	System.out.println("0");
			CheckBox cb1 = (CheckBox) findViewById(R.id.cb);
		 	TextView tv1 = (TextView) findViewById(R.id.val);
		 //	TextView tv1 = (TextView) v.findViewById(R.id.id);
		 	
		 	 Toast.makeText(view.getContext(), tv1.getText().toString()+" "+isCheckedOrNot(cb1), Toast.LENGTH_LONG).show(); */
		 	
		 /*	cb1.performClick();
		 	System.out.println("00");
		 	if (cb1.isChecked()){
		 		System.out.println("000");
		 	checkedValue.add(tv1.getText().toString());
		 	
		 	}
		 
		 	else if (!cb1.isChecked()){ 
		 		System.out.println("0000");
		 	checkedValue.remove(tv1.getText().toString());
		 	} */ 
		
	} 
	
	//CODE TO CHECK/UNCHECK
	 private String isCheckedOrNot(CheckBox checkbox) {
	        if(checkbox.isChecked())
	        return "is checked";
	        else
	        return "is not checked";
	      
	 
	 
	
	
}
}
	
	/*private ArrayList<String> isCheckedOrNot(CheckBox checkbox) {
        if(checkbox.isChecked())
        //return "is checked";
        
        	checkedValue.add(tv1.getText().toString());
        	
        else
        //return "is not checked";
        	checkedValue.remove(tv1.getText().toString());
        
        System.out.println("CHECKED:" + checkedValue.toString());
        
        return checkedValue;
    } */


	






 


