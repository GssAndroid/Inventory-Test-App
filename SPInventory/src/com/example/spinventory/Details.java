package com.example.spinventory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Details extends Activity {
	
	
	final static String NAMESPACE = "urn:sap-com:document:sap:soap:functions:mc-style"; // Name space from WSDL
	final static String METHOD_NAME = "ZGssmwfmHndlEvntrqst00"; //Operation Name form WSDL
	final static String SOAP_ACTION = "http://75.99.152.10:8050/sap/bc/srt/wsdl/bndg_E0A8AEE275F3AEF1AE7900188B47B426/wsdl11/allinone/ws_policy/document?sap-client=110";
	final static String URL ="http://gsswd.globalsoftsolutions.net:7500/sap/bc/srt/rfc/sap/z_gssmwfm_hndl_evntrqst00/110/z_gssmwfm_hndl_evntrqst00/z_gssmwfm_hndl_evntrqst00";

	String line4 = "MD_T_MATNR[.]";
	String line5 = "DPC2";
	String line7 = "MD_T_MATNR[.]";
	String line6 = "DPC2-T01";
	
	String result,searchContent,rephrase,firstrow,selected,sel;
 	String[] strArray,stockArr,heading,len;
 	String[][] resArr;
 	TableLayout ll;
 	Set<String> set;
 	
	ArrayList <String> selectedStrings  = new ArrayList<String>();
	 ArrayList<String> top = new ArrayList<String>();
	 String[] top1;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_main);
        
      
		//RETRIEVING SELECTED ITEMS AS STRING INSTEAD OF ARRAYLIST(SET IN SP)
	/*	Intent i = getIntent();

		 selected = i.getStringExtra("CheckedVal"); 
		 
		 sel = selected.substring(1, selected.length()-1).replaceAll(",", "");
         System.out.println("String: " + sel);
		 
		 
		System.out.println("Selected for last screen:" + selected); */
		
		Context context = getApplicationContext();
		
		  SharedPreferences preferences = context.getSharedPreferences("MYPREFS", context.MODE_PRIVATE);
		  
		  set = preferences.getStringSet("Checked", null);
			
			System.out.println("SELECTED: "+ set);
			
			

		
		
		    
        try {
			  
			  Thread thread = new Thread(new Runnable() {

				    @Override
				    public void run() {
				        try  {
				         
				        	 SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
				        	 SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					   		            SoapEnvelope.VER11);
				        	 
				        	 	
//				        	 System.out.println("CONTENT:" +content);
				      		    Device d[];
				        	    d = new Device[10];
				        	    
//				        	    System.out.println("length is: " + d.length);
					           
				        	    for(int i=0; i<d.length; i++){
				        	    	d[i] = new Device();
				        	    }
				      				        	    
				        	    d[0].Cdata = "DEVICE-ID:100000000000000:DEVICE-TYPE:ANDROID:APPLICATION-ID:SALESPRO";
				        	    d[1].Cdata = "NOTATION:ZML:VERSION:0:DELIMITER:[.]";
				        	    d[2].Cdata = "EVENT[.]MATERIAL-STOCK-GET[.]VERSION[.]0[.]RESPONSE-TYPE[.]FULL-SETS";
				        	    //d[3].Cdata = line4+line5+line7+line6;
				        	    //d[3].Cdata = line4+sel;
				        	    //d[4].Cdata = line4+line6;
				        	    
				        	    Iterator itr = set.iterator();
				        	    
				        	 /*   for (String s : set) {
				        	        System.out.println(s);
				        	        System.out.println("ITERATOR VALUE AS STRING:" + s);
				        	        d[3].Cdata = line4+s;
				        	    } */
				        	    
				        	    String[] movieArray = set.toArray(new String[set.size()]);
				        	    for (int i = 0; i < movieArray.length; i++) {
				        	      System.out.println(movieArray[i]);
				        	      System.out.println("ITERATOR VALUE AS STRING:" + movieArray[i]);
				        	      d[i+3].Cdata = line4+movieArray[i];
				        	    }

							 /*   while (itr.hasNext()) {
							        System.out.println(itr.next());
							        System.out.println("ITERATOR VALUE:" + itr.toString());
							        d[3].Cdata = line4+itr;
							    } */

				        	    
					            Vector listVect = new Vector();
					            for(int j=0; j<d.length; j++){
					            	listVect.addElement(d[j]);
					            }
					            request.addProperty("DpistInpt", listVect); 

				 /*           	String[] list = new String[listVect.size()];
				            	list = (String[]) listVect.toArray(list);
				            	for(String content : list)
				            	   // System.out.println("List:" +content); 

					            
					            request.addProperty ("DpistInpt", content); */ 
					       
					           envelope.setOutputSoapObject(request);
					            
					 /*           System.out.println("request is: " + request);
					            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
					            androidHttpTransport.debug = true;
					   		  androidHttpTransport.call(SOAP_ACTION, envelope);
					   		  
					   		 String xml = androidHttpTransport.requestDump;
				        	 System.out.println("Request XML:" + xml);
				        	 
				        	 String xml1 = androidHttpTransport.responseDump;
				        	 System.out.println("Response XML:" + xml1);
					   		  
					   		 SoapObject result1 = (SoapObject)envelope.bodyIn;
					   	
						 	     System.out.println("result is : " +result1.toString()); */    
							    
							     
							     try {                
							         HttpTransportSE  androidHttpTransport = new HttpTransportSE (URL);
							         androidHttpTransport.debug = true;
							            try{
							             androidHttpTransport.call(SOAP_ACTION, envelope);
							             
//							             String xml = androidHttpTransport.requestDump;
//							        	 System.out.println("Request XML:" + xml);
							             
							            }
							            catch(org.xmlpull.v1.XmlPullParserException ex2){
							             System.out.println("Data handling error : "+ex2);
//							             System.out.println(this, ex2.toString()); 
							            	//System.out.println("a");
							             envelope = null;
							                return;
							            }
							            catch(IOException oex){
							                final String extStr = oex.toString();
							                System.out.println("Network error : "+extStr);
							                envelope = null;
							                return;
							                
							            }
							            catch(Exception ex){
							             final String extStr = ex.toString();
						             System.out.println("Error in Sap Resp : "+ex.toString());
							               
							                envelope = null;
							                return;
							            }
							            
							            if(envelope != null){
							                try{
							                 String result = (envelope.getResponse()).toString();
							                 System.out.println("Results : "+result);
							                 //System.out.println("d");   
							                    SoapObject result1 = (SoapObject)envelope.bodyIn;
							                    final String response = result1.toString();
						                //    System.out.println("2nd API Results1 : "+response);
						                    
						               	 runOnUiThread(new Runnable() {
									            public void run() {
						                    
						                    strArray = response.split("");

 						                    
  						                   for(int i=0; i < strArray.length; i++){
 							                        System.out.print(strArray[i]);
 							                }
  						                   
	     						                  String pattern11 =  "MAKTX[.]";
	    							            	String pattern12 =  "LGORT[.]";
	    							            	String s1="";
	    							            	
	    							            	Pattern p1 = Pattern.compile(Pattern.quote(pattern11) + "(.*?)" + (Pattern.quote(pattern12)));
	    							            	Matcher m1 = p1.matcher(response);
	    							                	ArrayList<String> header = new ArrayList<String>();
	    							                	
	    								            	while (m1.find()) {
	    			
	    								            		System.out.println(m1.group(0));
	    			
	    								            		s1 =m1.group(0);
	    								            		
	    								            		 firstrow = null;
	    								            		 
	    								            		 
	    									            	    if (s1 != null && s1.length() > 1) {
	    									            	    	
	    									            	        firstrow = s1.substring(0, s1.length());
	    									            	        
	    									   //         	        System.out.println("FirstRow:      " + firstrow);
	    									            	        
	    									            	         
	    									            	    }
	    									            	//System.out.println("R:"+rephrase);
	    									            	header.add(firstrow);
	    									            	} 
	    								            	
	    								            	heading = new String[header.size()];
	    								            	heading = header.toArray(heading);
	    								            	for(String s2 : heading)
	    								            	{
	    								            	    System.out.println("Heading:" +s2); 
	    								            	    
	    								            	}
	    								                 	
	    								            	resArr = new String[heading.length][];
	    								                 // split the lines
	    								         	for ( int i = 0; i < heading.length; i++ )
	    								         		resArr[i] = heading[i].split("\\[.]+");
	    								         	
//	    								         	 colName = new String[resArr.length];
// 								         	     value = new String[resArr.length];
 								         	
 								         	     
 								                 // separate the array into arrays of first and second words
// 								         	for ( int i = 0; i < resArr.length; i++ )
// 								         	{
// 								         	    colName[i] = resArr[i][0];
// 								         	    value[i] = resArr[i][1];
// 								         	}   
// 								         	
// 								         	System.out.println("Column Name: " + Arrays.deepToString(colName)); 
	    								         	
	    								        		    								         	
	    								         	
	    								         	// System.out.println("ARRAY:" +Arrays.deepToString(resArr));
	    								         	 
	    								            

	    								          
	    								             
	    								         	 for(int i = 0; i < resArr.length; i++) {
	    								                top.addAll( Arrays.asList(resArr[i]) );
	    								            }
	    								         	 top1 = top.toArray(new String[0]);
	    								         	

	    								         	System.out.println("TOP: " + Arrays.toString(top1));
	    								         	
	    								         	//String sl = Arrays.toString(top1);
	    							            	  //sl = sl.substring(1, sl.length()-1).replaceAll(",", ""); 
	    								         	 
										         	   
			  								            

	    								         	 
	    								         	    
//	    							         	    System.out.println("Value: " + Arrays.deepToString(value));


  						                   
  						                  String pattern1 =  "item=anyType{Cdata=ZGSEMBST_MTRLSTCK13[.]";
 							            	String pattern2 =  ";";
 							            	String s="";
 							            	
 							            	Pattern p = Pattern.compile(Pattern.quote(pattern1) + "(.*?)" + (Pattern.quote(pattern2)));
 							            	Matcher m = p.matcher(response);
 							                	ArrayList<String> allValues = new ArrayList<String>();
 							                	
 								            	while (m.find()) {
 			
 								            		System.out.println(m.group(0));
 			
 								            		s =m.group(0);
 								            		
 								            		 rephrase = null;
 								            		 
 								            		 
 									            	    if (s != null && s.length() > 1) {
 									            	    	
 									            	        rephrase = s.substring(41, s.length() - 4);
 									            	        
 									                       
 									            	    }
 									            	//System.out.println("R:"+rephrase);
 									            	allValues.add(rephrase);
 									            	} 
 								            	
 								            	stockArr = new String[allValues.size()];
 								            	stockArr = allValues.toArray(stockArr);
 								            	for(String s2 : stockArr)
 								            	    System.out.println("S2:" +s2); 
 								            	
 								            	
 								            	    
 								            	resArr = new String[stockArr.length][];
 								                 // split the lines
 								         	for ( int i = 0; i < stockArr.length; i++ )
 								         		resArr[i] = stockArr[i].split("\\[.]+");
 								            	
 								            	
 								    //      System.out.println("ARRAY1:" +Arrays.deepToString(resArr));
 								          
 								         ArrayList<String> body = new ArrayList<String>();

 								          
 								             
 								         	 for(int i = 0; i < resArr.length; i++) {
 								                body.addAll( Arrays.asList(resArr[i]) );
 								            }
 								         	String[] body1 = body.toArray(new String[0]);
 								         	

 								         	System.out.println("BODY: " + Arrays.toString(body1)); 

 								         	 
 								         	    String op = Arrays.deepToString(resArr);
 								            	  op = op.substring(1, op.length()-1).replaceAll(",", "");
 								            	System.out.println("OUT:" +op);

 								            	//addHeader();
 								            	
 								            	//INSERTION OF HEADINGS
 								            	  {
 													// TODO Auto-generated method stub

 													 List<String> name = Arrays.asList(top1);
 													  
 													     // TableLayout table = (TableLayout) findViewById(R.id.tl);
 													   ll = (TableLayout) findViewById(R.id.tl);
 													      View v = new View(Details.this);
 													      View v1 = new View(Details.this);
 													      TableRow row1=new TableRow(Details.this);
 													     TableRow row2=new TableRow(Details.this);
 													      for(int i=0;i<top1.length;i++)
 													      {

 													         String name1 = name.get(i);
 													        
 													          
 													          TextView tvName=new TextView(Details.this);
 													          tvName.setText(""+name1);
 													         
 										      	          tvName.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
 													          tvName.setPadding(10, 10, 10, 10);
 													       

 													          v.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 1));
 													          v.setBackgroundColor(Color.rgb(51, 51, 51));
 													          
// 													          v1.setLayoutParams(new TableRow.LayoutParams(1, TableRow.LayoutParams.MATCH_PARENT));
// 													          v1.setBackgroundColor(Color.rgb(51, 51, 51));
 													          
 													          
 													          row1.addView(tvName);
 													        

 													      }
 													      ll.addView(row1);
 													      ll.addView(v);

 													
 												}
 								            	  
 								            	  //INSERTION OF VALUES IN ROWS
 								            	  
 								            	   List<String> value11  = Arrays.asList(body1);
 								     			 //     List<String> value12  = Arrays.asList(h4);
 								     			      View v = new View(Details.this);
 								     			      System.out.println("Size of final array:" + value11.size());

 								     			      TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
 								     		           
 								     		        //    TableRow rowval = new TableRow(this);
 								     		          
 								     				   //for(int i=0;i<value11.size();i++) {
 								     			      for(int i=0;i<value11.size();i+=10) {
 								     			    	  TableRow rowval = new TableRow(Details.this);
 								     			    	  for(int j=i;j<i+10;j++)
 								     			    	  {			
 								     			    		//  TableRow rowval = new TableRow(this);
 								     			            rowval.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

 								     			               
 								     			            TextView textView = new TextView(Details.this);
 								     			            textView.setText(value11.get(j));
 								     			            textView.setPadding(10, 10, 10, 10);
 								     			            rowval.addView(textView);  
 								     			            
 								     			            v.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 1));
 								     				          v.setBackgroundColor(Color.rgb(51, 51, 51));
 								     				         // tableRow2=rowval;
 								     				          
 								     			    	  }
 								     				      ll.addView(rowval,layoutParams);

 								     			      }


									            } 
							                });
							                }
						                    
						                    catch(Exception dgg){
							                 System.out.println("Error in Envelope Null : "+dgg.toString());
							                	//System.out.println("f");
							                }
							            }else{
							            	System.out.println("Envelope is null");
							            }
							        }
							        catch (Throwable e) {
							         System.out.println("Error in Soap Conn : "+e.toString());
							        	//System.out.println("g");
							        } 

						     
				        } catch (Exception e) {
				        	System.out.println("Exception:" +e.toString());
				        	//e.printStackTrace();
				        }
				        }

									    
			  });
							     
							  	thread.start();
		  			 		     
			  		    } catch (Exception e) {
			  		    	System.out.println("Exception1:" +e.toString());
			  		    } 
	}	  
			      
        private void addHeader() {
			// TODO Auto-generated method stub

			 List<String> name = Arrays.asList(heading);
			  
			     // TableLayout table = (TableLayout) findViewById(R.id.tl);
			   ll = (TableLayout) findViewById(R.id.tl);
			      View v = new View(Details.this);
			      View v1 = new View(Details.this);
			      TableRow row1=new TableRow(Details.this);
			     TableRow row2=new TableRow(Details.this);
			      for(int i=0;i<heading.length;i++)
			      {

			         String name1 = name.get(i);
			        
			          
			          TextView tvName=new TextView(Details.this);
			          tvName.setText(""+name1);
			         
      	          tvName.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
			          tvName.setPadding(10, 10, 10, 10);
			       

			          v.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 1));
			          v.setBackgroundColor(Color.rgb(51, 51, 51));
			          
//			          v1.setLayoutParams(new TableRow.LayoutParams(1, TableRow.LayoutParams.MATCH_PARENT));
//			          v1.setBackgroundColor(Color.rgb(51, 51, 51));
			          
			          
			          row1.addView(tvName);
			        

			      }
			      ll.addView(row1);
			      ll.addView(v);

			
		

			  	

			  	

			      
			  	
			  	
			  	
			            

	}	        
				    }


