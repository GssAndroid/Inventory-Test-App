package com.example.spinventory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.example.ws3.WebService;




import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

//import com.example.ws3.*;



public class Search extends ActionBarActivity {

	SearchView searchView;
	String content;
	
	
	final static String NAMESPACE = "urn:sap-com:document:sap:soap:functions:mc-style"; // Name space from WSDL
	final static String METHOD_NAME = "ZGssmwfmHndlEvntrqst00"; //Operation Name form WSDL
	final static String SOAP_ACTION = "http://75.99.152.10:8050/sap/bc/srt/wsdl/bndg_E0A8AEE275F3AEF1AE7900188B47B426/wsdl11/allinone/ws_policy/document?sap-client=110";
	final static String URL ="http://75.99.152.10:7500/sap/bc/srt/rfc/sap/z_gssmwfm_hndl_evntrqst00/110/z_gssmwfm_hndl_evntrqst00/z_gssmwfm_hndl_evntrqst00";
	
	String result,searchContent,rephrase;
	String[] id,value,strArray,stockArr;
	String[][] resArr;
	String line4 = "ZGSEVDST_MTRLSRCH01[.]";
	
//	 DBController controller = new DBController(this);
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main);

     	System.out.println("Before start Response from ws lib ");
        WebService response = new WebService();
      
        String strRes = response.getResponse(); 
     	System.out.println("After start Response from ws lib ");

     	System.out.println("Response from ws lib "+strRes);
       
        
	searchView=(SearchView) findViewById(R.id.searchView);
    searchView.setQueryHint("Search for Products");

    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

        @Override
        public boolean onQueryTextSubmit(String query) {
          //  Toast.makeText(getBaseContext(), query, Toast.LENGTH_LONG).show();
//            System.out.println("Searched Text:" +query);
            content=query.substring(0,query.length());
//            System.out.println("Content:" +content);
            
            if(content.length()==1){
            	Toast.makeText(getBaseContext(), "Minimum of 2 characters must be entered", Toast.LENGTH_LONG).show();
            }
            else{
            
            try {
  			  
  			  Thread thread = new Thread(new Runnable() {

  				    @Override
  				    public void run() {
  				        try  {
  				         
  				        	 SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
  				        	 SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
  					   		            SoapEnvelope.VER11);
  				        	 
  				        	 	
//  				        	 System.out.println("CONTENT:" +content);
  				      		    Device d[];
  				        	    d = new Device[4];
  				        	    
//  				        	    System.out.println("length is: " + d.length);
  					           
  				        	    for(int i=0; i<d.length; i++){
  				        	    	d[i] = new Device();
  				        	    }
  				      				        	    
  				        	    d[0].Cdata = "DEVICE-ID:100000000000000:DEVICE-TYPE:ANDROID:APPLICATION-ID:SALESPRO";
  				        	    d[1].Cdata = "NOTATION:ZML:VERSION:0:DELIMITER:[.]";
  				        	    d[2].Cdata = "EVENT[.]PRODUCT-SEARCH[.]VERSION[.]0[.]RESPONSE-TYPE[.]FULL-SETS";
  				        	    d[3].Cdata = line4+content;
  				        	    
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
  							             
//  							             String xml = androidHttpTransport.requestDump;
//  							        	 System.out.println("Request XML:" + xml);
  							             
  							            }
  							            catch(org.xmlpull.v1.XmlPullParserException ex2){
  							             System.out.println("Data handling error : "+ex2);
//  							             System.out.println(this, ex2.toString()); 
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
  							                    String response = result1.toString();
  						                    System.out.println("Results1 : "+response);
  						                    
  						                   
  						                    
  						                    strArray = response.split("");
  								               
  							               
  							               
  							                //print elements of String array
  							                for(int i=0; i < strArray.length; i++){
  							                        System.out.print(strArray[i]);
  							                }
  							                 
  						                    String pattern1 =  "item=anyType{Cdata=ZGSEMMST_MTRL10[.]";
  							            	String pattern2 =  ";";
  							            	String s="";
  							            	
  							            	Pattern p = Pattern.compile(Pattern.quote(pattern1) + "(.*?)" + (Pattern.quote(pattern2)));
  							            	Matcher m = p.matcher(response);
  							                	ArrayList<String> allValues = new ArrayList<String>();
  							                	
  								            	while (m.find()) {
  			
  								            //		System.out.println(m.group(0));
  			
  								            		s =m.group(0);
  								            		
  								            		 rephrase = null;
  								            		 
  								            		 
  									            	    if (s != null && s.length() > 1) {
  									            	    	
  									            	        rephrase = s.substring(37, s.length() - 4);
  									            	        
//  									            	        System.out.println("Rephrase:      " + rephrase);
  									            	        
  									            	    /*	String strArray1[] = rephrase.split("");
  												               
  												             resArr = new String[strArray1.length][];
  												            	  
  												                 // split the lines
  												         	for ( int i = 0; i < strArray1.length; i++ )
  												         	{
  												         		
  												         		resArr [i] = strArray[i].split("\\[.]+");    
  												         	}*/
  												             
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
  								         	
  								         	
//  								         	 System.out.println("ARRAY:" +Arrays.deepToString(resArr));
  								         	     id = new String[resArr.length];
  								         	     value = new String[resArr.length];
  								         	
  								                 // separate the array into arrays of first and second words
  								         	for ( int i = 0; i < resArr.length; i++ )
  								         	    {
  								         	    id[i] = resArr[i][0];
  								         	    value[i] = resArr[i][1];
  								         	   
  								         	    } 
  								         	
//  								         	System.out.println("Id: " + Arrays.deepToString(id));
//  							         	    System.out.println("Value: " + Arrays.deepToString(value));

  							         	   
			
  							         	/* try{
  							         		controller = new DBController(getApplicationContext());
  					                        SQLiteDatabase db = controller.getWritableDatabase();
  					                        ContentValues cv = new ContentValues();
  					                      for(int i=0;i<id.length;i++){
  					                        cv.put("id",id[i]);
  					                        cv.put("value", value[i]);
  					                        db.insert("productInfo", null, cv);
  					                      }
  					                        db.close();
  					                        System.out.println("Products added Successfully"); 
  							         	 }
  							         	 catch(Exception e){
  							         		 System.out.println("Database Exception:" + e.toString());
  							         	 } */
  							         		
  							         	    
  								        	String s1 = Arrays.toString(id);
  							            	  s1 = s1.substring(1, s1.length()-1).replaceAll(",", "");
  							            	
  							            	  
  							            	  String s2 = Arrays.toString(value);
  							            	  s2 = s2.substring(1, s2.length()-1).replaceAll(",", "");
  							            	
  							            	  
//  								           	System.out.println("ID = "+s1);
//  								         	System.out.println("VALUE = "+s2);
  								         	
//  								         	ArrayList<String> val = new ArrayList<String>();
//  								         	val.add(s2);

  							            	Intent intent=new Intent(Search.this,ListActivityText.class);
  								        	intent.putExtra("Value", value);
  								        	intent.putExtra("Id", id);
  										     startActivity(intent);
  								            	
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

  					private String[] explodeStringUsingCoreJava(String stringToExplode,String separator) {
  		   // TODO Auto-generated method stub
  		   return  stringToExplode.split(separator);
  		}
  	
  				    
  				});
  			  
  			 	thread.start();
  			 		     
  		    } catch (Exception e) {
  		    	System.out.println("Exception1:" +e.toString());
  		    } 
  		  
      
  		
  	

  	

      
  	
  	
  	
            }
            
            return false;
            
        }
        
        
        @Override
        public boolean onQueryTextChange(String newText) {
           // Toast.makeText(getBaseContext(), newText, Toast.LENGTH_LONG).show();
            System.out.println("New Text:" +newText);
            return false;
        }
    });
    
	}
}
    
   /* Intent i = new Intent(getApplicationContext(), MainActivity.class);

    i.putExtra("Query",content);
    startActivity(i); */ 

	
//  try {
//			  
//			  Thread thread = new Thread(new Runnable() {
//
//				    @Override
//				    public void run() {
//				        try  {
//				         
//				        	 SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
//				        	 SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
//					   		            SoapEnvelope.VER11);
//				        	 
//				        	 	
//				        	 System.out.println("CONTENT:" +content);
//				      		    Device d[];
//				        	    d = new Device[4];
//				        	    
//				        	    System.out.println("length is: " + d.length);
//					           
//				        	    for(int i=0; i<d.length; i++){
//				        	    	d[i] = new Device();
//				        	    }
//				      				        	    
//				        	    d[0].Cdata = "DEVICE-ID:100000000000000:DEVICE-TYPE:ANDROID:APPLICATION-ID:SALESPRO";
//				        	    d[1].Cdata = "NOTATION:ZML:VERSION:0:DELIMITER:[.]";
//				        	    d[2].Cdata = "EVENT[.]PRODUCT-SEARCH[.]VERSION[.]0[.]RESPONSE-TYPE[.]FULL-SETS";
//				        	    d[3].Cdata = line4+content;
//				        	    
//					            Vector listVect = new Vector();
//					            for(int j=0; j<d.length; j++){
//					            	listVect.addElement(d[j]);
//					            }
//					            request.addProperty("DpistInpt", listVect); 
//
//				 /*           	String[] list = new String[listVect.size()];
//				            	list = (String[]) listVect.toArray(list);
//				            	for(String content : list)
//				            	   // System.out.println("List:" +content); 
//
//					            
//					            request.addProperty ("DpistInpt", content); */ 
//					       
//					           envelope.setOutputSoapObject(request);
//					            
//					 /*           System.out.println("request is: " + request);
//					            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
//					            androidHttpTransport.debug = true;
//					   		  androidHttpTransport.call(SOAP_ACTION, envelope);
//					   		  
//					   		 String xml = androidHttpTransport.requestDump;
//				        	 System.out.println("Request XML:" + xml);
//				        	 
//				        	 String xml1 = androidHttpTransport.responseDump;
//				        	 System.out.println("Response XML:" + xml1);
//					   		  
//					   		 SoapObject result1 = (SoapObject)envelope.bodyIn;
//					   	
//						 	     System.out.println("result is : " +result1.toString()); */    
//							    
//							     
//							     try {                
//							         HttpTransportSE  androidHttpTransport = new HttpTransportSE (URL);
//							         androidHttpTransport.debug = true;
//							            try{
//							             androidHttpTransport.call(SOAP_ACTION, envelope);
//							             
//							             String xml = androidHttpTransport.requestDump;
//							        	 System.out.println("Request XML:" + xml);
//							             
//							            }
//							            catch(org.xmlpull.v1.XmlPullParserException ex2){
//							             System.out.println("Data handling error : "+ex2);
////							             System.out.println(this, ex2.toString()); 
//							            	//System.out.println("a");
//							             envelope = null;
//							                return;
//							            }
//							            catch(IOException oex){
//							                final String extStr = oex.toString();
//							                System.out.println("Network error : "+extStr);
//							                envelope = null;
//							                return;
//							                
//							            }
//							            catch(Exception ex){
//							             final String extStr = ex.toString();
//						             System.out.println("Error in Sap Resp : "+ex.toString());
//							               
//							                envelope = null;
//							                return;
//							            }
//							            
//							            if(envelope != null){
//							                try{
//							                 String result = (envelope.getResponse()).toString();
//							                 System.out.println("Results : "+result);
//							                 //System.out.println("d");   
//							                    SoapObject result1 = (SoapObject)envelope.bodyIn;
//							                    String response = result1.toString();
//						                    System.out.println("Results1 : "+response);
//						                    
//						                    strArray = response.split("");
//								               
//							               
//							               
//							                //print elements of String array
//							                for(int i=0; i < strArray.length; i++){
//							                        System.out.print(strArray[i]);
//							                }
//							                 
//						                    String pattern1 =  "item=anyType{Cdata=ZGSEMMST_MTRL10[.]";
//							            	String pattern2 =  ";";
//							            	String s="";
//							            	
//							            	Pattern p = Pattern.compile(Pattern.quote(pattern1) + "(.*?)" + (Pattern.quote(pattern2)));
//							            	Matcher m = p.matcher(response);
//							                	ArrayList<String> allValues = new ArrayList<String>();
//							                	
//								            	while (m.find()) {
//			
//								            		System.out.println(m.group(0));
//			
//								            		s =m.group(0);
//								            		
//								            		 rephrase = null;
//								            		 
//								            		 
//									            	    if (s != null && s.length() > 1) {
//									            	    	
//									            	        rephrase = s.substring(37, s.length() - 4);
//									            	        
//									            	        System.out.println("Rephrase:      " + rephrase);
//									            	        
//									            	    /*	String strArray1[] = rephrase.split("");
//												               
//												             resArr = new String[strArray1.length][];
//												            	  
//												                 // split the lines
//												         	for ( int i = 0; i < strArray1.length; i++ )
//												         	{
//												         		
//												         		resArr [i] = strArray[i].split("\\[.]+");    
//												         	}*/
//												             
//									            	    }
//									            	//System.out.println("R:"+rephrase);
//									            	allValues.add(rephrase);
//									            	} 
//								            	
//								            	stockArr = new String[allValues.size()];
//								            	stockArr = allValues.toArray(stockArr);
//								            	for(String s2 : stockArr)
//								            	    System.out.println("S2:" +s2); 
//								            	
//								            	
//								            	    
//								            	resArr = new String[stockArr.length][];
//								                 // split the lines
//								         	for ( int i = 0; i < stockArr.length; i++ )
//								         		resArr[i] = stockArr[i].split("\\[.]+");
//								         	
//								         	
//								         	 System.out.println("ARRAY:" +Arrays.deepToString(resArr));
//								         	     id = new String[resArr.length];
//								         	     value = new String[resArr.length];
//								         	
//								                 // separate the array into arrays of first and second words
//								         	for ( int i = 0; i < resArr.length; i++ )
//								         	    {
//								         	    id[i] = resArr[i][0];
//								         	    value[i] = resArr[i][1];
//								         	   
//								         	    } 
//								         	
//								         	System.out.println("Id: " + Arrays.deepToString(id));
//							         	    System.out.println("Value: " + Arrays.deepToString(value));
//								          		
//								        	String s1 = Arrays.toString(id);
//							            	  s1 = s1.substring(1, s1.length()-1).replaceAll(",", "");
//							            	
//							            	  
//							            	  String s2 = Arrays.toString(value);
//							            	  s2 = s2.substring(1, s2.length()-1).replaceAll(",", "");
//							            	
//							            	  
////								           	System.out.println("ID = "+s1);
////								         	System.out.println("VALUE = "+s2);
//								         	
////								         	ArrayList<String> val = new ArrayList<String>();
////								         	val.add(s2);
//							            	  
//							            	
//								         	Intent intent=new Intent(Search.this,ListActivity.class);
//								        	intent.putExtra("Value", value);
//								        	intent.putExtra("Id", id);
//										     startActivity(intent);
//								            	
//								            	}
//							                
//							                
//								            	
//						                    
//							                
//							                catch(Exception dgg){
//							                 System.out.println("Error in Envelope Null : "+dgg.toString());
//							                	//System.out.println("f");
//							                }
//							            }else{
//							            	System.out.println("Envelope is null");
//							            }
//							        }
//							        catch (Throwable e) {
//							         System.out.println("Error in Soap Conn : "+e.toString());
//							        	//System.out.println("g");
//							        } 
//
//
//						     
//						  /*   runOnUiThread(new Runnable() {
//						            public void run() {
//						            //  TextView tv = (TextView)findViewById(R.id.tv);
//						            //  tv.setText(result);
//						            }
//						          }); */
//
//						     
//				        } catch (Exception e) {
//				        	System.out.println("Exception:" +e.toString());
//				        	//e.printStackTrace();
//				        }
//				        
//				    }
//
//					private String[] explodeStringUsingCoreJava(String stringToExplode,String separator) {
//		   // TODO Auto-generated method stub
//		   return  stringToExplode.split(separator);
//		}
//	
//				    
//				});
//			  
//			 	thread.start();
//			 		     
//		    } catch (Exception e) {
//		    	System.out.println("Exception1:" +e.toString());
//		    } 
//		  
//    
//		
//	}
//
//	
//
//    
//	}
//	
//	
