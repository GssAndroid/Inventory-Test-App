package com.example.spinventory;

import java.io.Serializable;
import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class Device implements KvmSerializable
{
    
	public String Cdata;
   
    public Device(){}
    
    

    public Device(String line1) {
        
    	Cdata = line1;    }


    public Object getProperty(int arg0) {
        
        switch(arg0)
        {
        case 0:
            return Cdata;        
        }
        
        return null;
    }

    public int getPropertyCount() {
        return 1;
    }

    public void getPropertyInfo(int index, Hashtable arg1, PropertyInfo info) {
        switch(index)
        {
        case 0:
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "Cdata";
            break;
        default:break;
        }
    }

    public void setProperty(int index, Object value) {
        switch(index)
        {
        case 0:
        	Cdata = value.toString();
            break;
        default:
            break;
        }
    }  
    
    
    /*public String Line1;
	public String Line2;
	public String Line3;
	public String Line4;
	    public Device(){}
	    
	    

	    public Device(String line1, String line2, String line3, String line4) {
	        
	    	Line1 = line1;
	    	Line2 = line2;
	    	Line3 = line3;
	    	Line4 = line4;
	    	}


	    public Object getProperty(int arg0) {
	        
	        switch(arg0)
	        {
	        case 0:
	            return Line1;
	        case 1:
	            return Line2;
	        case 2:
	            return Line2;
	        case 3:
	            return Line3;
	        }
	        
	        return null;
	    }

	    public int getPropertyCount() {
	        return 4;
	    }

	    public void getPropertyInfo(int index, Hashtable arg1, PropertyInfo info) {
	        switch(index)
	        {
	        case 0:
	            info.type = PropertyInfo.STRING_CLASS;
	            info.name = "Line1";
	            break;
	        case 1:
	            info.type = PropertyInfo.STRING_CLASS;
	            info.name = "Line2";
	            break;
	        case 2:
	            info.type = PropertyInfo.STRING_CLASS;
	            info.name = "Line3";
	            break;
	        case 3:
	            info.type = PropertyInfo.STRING_CLASS;
	            info.name = "Line4";
	            break;
	        default:break;
	        }
	    }

	    public void setProperty(int index, Object value) {
	        switch(index)
	        {
	        case 0:
	        	Line1 = value.toString();
	            break;
	        case 1:
	        	Line2 = value.toString();
	            break;
	        case 2:
	        	Line3 = value.toString();
	            break;
	        case 3:
	        	Line4 = value.toString();
	            break;
	       
	        default:
	            break;
	        }
	    } */

    
    
}

