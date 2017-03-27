package com.example.spinventory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;


public class Listadapter extends BaseAdapter {

		private static final int REQUEST_FOR_ACTIVITY_CODE = 0;
		String values;
		 View row;
		Context context;
	//Context context;
		List<RowItem> rowItems;
		 boolean checkAll_flag = false;
		    boolean checkItem_flag = false;
		 private ArrayList<String> selectedStrings = new ArrayList<String>();
		 boolean[] itemChecked;
//		public boolean[] checkedHolder;

		Listadapter(Context mContext, List<RowItem> rowItems) {
		super();
		this.context = mContext;
		 this.rowItems = rowItems;
		
		  itemChecked = new boolean[rowItems.size()];
		}
	    
		private class ViewHolder {
			 
			 TextView value;
			  TextView id;
			  
			 CheckBox cb1;
//			 CheckBox checkbox;
			 }
		
		
		@Override
		public int getCount() {
			return rowItems.size();
		}

		@Override
		public Object getItem(int position) {
			return rowItems.get(position);
		}

		@Override
		public long getItemId(int position) {
//		return rowItems.indexOf(getItem(position));
		return 0;
		}
		
	/*	ArrayList<String> getSelectedString(){
			  return selectedStrings;
			} */ 

		
		
			public View getView(final int position, View convertView, ViewGroup parent) {

				
			  final ViewHolder holder;
			  
			  
			 

			  LayoutInflater mInflater = (LayoutInflater)context
			    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE); 
			  
//			  LayoutInflater inflater =  context.getLayoutInflater();

			  //int position = -1;
			  
		 if (convertView == null) {
		//	  LayoutInflater inflater =  context.getLayoutInflater();
			   convertView = mInflater.inflate(R.layout.listview_item, null);
			   
			 			
			/*   convertView.setClickable(true);
			    convertView.setFocusable(true); */
			  
			   holder = new ViewHolder();
			   
			    
			  
			   
			 holder.value = (TextView) convertView
			     .findViewById(R.id.val);
			   holder.id = (TextView) convertView
					     .findViewById(R.id.id);
			   holder.cb1 = (CheckBox) convertView.findViewById(R.id.cb);
			   
			   
			   holder.cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
	                
	                @Override
	                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	                //    int getPosition = (Integer) buttonView.getTag();  // Here we get the position that we have set for the checkbox using setTag.
	                  //  rowItems.get(getPosition).setSelected(buttonView.isChecked());  // Set the value of checkbox to maintain its state.
	                    
	                    if (isChecked) {
	                        selectedStrings.add(holder.id.getText().toString());
	                    }else{
	                        selectedStrings.remove(holder.id.getText().toString());
	                    }
	                    
	                    values= selectedStrings.toString();
	                    
	                 
	                    
	                    System.out.println("\n Checked Values: " +values); 
	                    
	                   	              //      System.out.println("\n Checked Array: " +selectedStrings.toString());
	                  //  Toast.makeText(context,selectedStrings.toString()+" checked", Toast.LENGTH_SHORT).show(); 
	                    
	                   // return values;
	                    
	                    //PLACE SOMEWHERE
	                 /*   Intent i = new Intent(mContext, ListActivityText.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	     			   System.out.println("\n Checked Values123: " +values);
	     			   i.putExtra("CheckedVal", values);
	     			   System.out.println("\n Checked Values1: " +values);
	     			  // ((Activity) mContext).startActivityForResult(i,REQUEST_FOR_ACTIVITY_CODE);
	     			   mContext.startActivity(i);
	     			   System.out.println("\n Checked Values12: " +values); */      

	                  
	     			   
	                    SharedPreferences preferences = context.getSharedPreferences("MYPREFS", context.MODE_PRIVATE);
		       			   SharedPreferences.Editor editor = preferences.edit();
		       			   //SELECTED STRINGS VARIABLE TYPE IS ARRAYLIST, SO IT MUST BE SAVED AS HashSet IN ORDER FOR SHARED PREFERENCES
		       			Set<String> set = new HashSet<String>();
		       			set.addAll(selectedStrings);
		       			   editor.putStringSet("Checked",set);
		       			   editor.apply(); 
		      				
	                  



	                   	                }
	                
	                
			   
	                
	                
	            }); 
			   
			//   SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
			//   SharedPreferences preferences = mContext.getSharedPreferences("MYPREFS", 1); 
			  	  			 
			   //row=convertView;
			   
						/*   Intent i = new Intent(context, ListActivityText.class);
               i.putExtra("Checked", "values");
               context.startActivity(i); */ 
			   
			 
			     			   convertView.setTag(holder);
   			 
			}	   
			  else
			  {  
					   holder = (ViewHolder) convertView.getTag();
					  
			  }  
		 
/*		   row.setOnClickListener(new OnClickListener() {
	    				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 Intent intent= new Intent(row.getContext(), ListActivityText.class);
					 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	                   intent.putExtra("Checked",values);
	                   row.getContext().startActivity(intent);
					
				}
	         }); */

		  	        holder.cb1.setTag(position); // This line is important.
				  RowItem row_pos = (RowItem) getItem(position);
	       
					   holder.value.setText(row_pos.getvalue().toString());
				   holder.id.setText(row_pos.getid().toString());
				   
				 				   // holder.cb1.setChecked(false);
			
				  
			   holder.cb1.setChecked(rowItems.get(position).isSelected());
			   
			 
			   //AUTOSELECT FIRST ELEMENT OF LIST VIEW, IRRESPECTIVE OF LIST SIZE
			/*   if(position==0)
				{
				    System.out.println("ONEEEEEEEEEEEE");
					
				    holder.cb1.post(new Runnable() {
			            @Override
			            public void run() {
			                holder.cb1.setChecked(true);
			            }
			        });
				    
					//holder.cb1.setChecked(true);
				} */
				
			   
			/*   if (itemChecked[position])
				   holder.cb1.setChecked(true);
			   else
				   holder.cb1.setChecked(false);
			  
			   System.out.println("ADAPTER");
			   
			   
			   
			  
			   holder.cb1.setOnClickListener(new View.OnClickListener() {
				   @Override
			   public void onClick(View view){
				
					   if (view instanceof CheckBox) {
					        boolean checked = ((CheckBox) view).isChecked();
					       // int score = view.getTag();
					        // do the rest
					        if(checked==true)
					        System.out.println("Item is checked:" + checked);
					        else
					        	System.out.println("Item is unchecked:" + checked);
					    }
					   
					   
					/*   if(((CompoundButton) v).isChecked())
				    {itemChecked[position] = true;
				    	
				    
				    	System.out.println("Item is checked:" + v.toString());
				    }
				   else
				   {
					   itemChecked[position] = false; 
				   System.out.println("Item is unchecked:" + v.toString());
				   }  
			   } 
			   }
			  );  */
			   
			   
			 /*  if(position == 0){
                   // This is the first item, you need to select this
                   convertView.setSelected(true);

               } */
			   
						   
			   
			 /*   holder.value.setOnClickListener(new OnClickListener() {

		        @Override
		        public void onClick(View v) {
		          
		            Intent intent = new Intent(mContext, ListActivityText.class);
		            System.out.println("\n Checked Values1: " +values);
		            intent.putExtra("CheckedVal", values);
		            System.out.println("\n Checked Values2: " +values);
		            ((Activity) mContext).startActivityForResult(intent,REQUEST_FOR_ACTIVITY_CODE);
		            //mContext.startActivity(intent);
		            System.out.println("\n Checked Values3: " +values);

		        }
		    }); */   

			  		 
						   
			  		   		  return convertView;
			 		   		  
			 		   		  
			   }
			
			
            
					
}

