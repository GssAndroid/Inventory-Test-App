package com.example.spinventory;

public class RowItem {

	
	private String value ;
	 private String id ;
	 private boolean selected;
	 
	 public RowItem(String value,String id,boolean selected) 
	 {
	  
	  this.value = value;
	  this.id  = id;
      this.selected = selected;
	 }

	 public String getvalue() {
	  return value;
	 }

	 public void setvalue(String value) {
	  this.value = value;
	 }
	 
	 public String getid() {
     return id;
    }

	 public void setid(String id) {
	  this.id = id;
    }
	 
		 
	 public boolean isSelected() {
		  return selected;
		 }
		 public void setSelected(boolean selected) {
		  this.selected = selected;
		 }  
	
	
} 

/*public class Product {
    String value;
    String id;
      boolean cb;
      

      Product(String _value, String _id, boolean _cb) {
        value = _value;
        id = _id;
        cb = _cb;
      }
    } */