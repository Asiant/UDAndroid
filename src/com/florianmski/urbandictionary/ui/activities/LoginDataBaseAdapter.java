package com.florianmski.urbandictionary.ui.activities;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class LoginDataBaseAdapter 
{
		static final String DATABASE_NAME = "los.db";
		static final int DATABASE_VERSION = 1;
		public static final int NAME_COLUMN = 2;
		public static final String TABLE_NAME ="WORD";
		//public static final String TABLE_NAME1 ="LOS1";
		public static final String COL_EMPNO = "empno";
		public static int count=1,count1=1;
		// TODO: Create public field for each column in your table.
		// SQL Statement to create a new database.
		public static final String DATABASE_CREATE = "create table "+TABLE_NAME+
		                             "(ID text,NAME  text); ";
		/*
		public static final String DATABASE_CREATE1 = "create table "+TABLE_NAME1+
                "( ID1 integer,NAME1  text,EMAIL1 text,PROFESSION1 text,PHONENO1 text); ";*/

		// Variable to hold the database instance
		public  SQLiteDatabase db;
		// Context of the application using the database.
		private final Context context;
		// Database open/upgrade helper
		private DataBaseHelper dbHelper;
		public  LoginDataBaseAdapter(Context _context) 
		{
			context = _context;
			dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		public  LoginDataBaseAdapter open() throws SQLException 
		{
			db = dbHelper.getWritableDatabase();
			return this;
		}
		public void close() 
		{
			db.close();
		}

		public  SQLiteDatabase getDatabaseInstance()
		{
			return db;
		}

		public String insertEntry(String name)
		{
	       ContentValues newValues = new ContentValues();
			// Assign values for each row.
			newValues.put("NAME",name);
			
			newValues.put("ID",""+count);
			// Insert the row into your table
			db.insert("WORD", null, newValues);
			//db.insert("LOS1", null, newValues);
		
			//Toast.makeText(context,"your id is"+count,Toast.LENGTH_LONG).show();
			String c=""+count;
			count++;
			return c;
		}
		/*public String insertEntry1(String name,String email,String profession,String phoneno)
		{
	       ContentValues newValues = new ContentValues();
	       
	       Log.d("nikunj", "inserting valoes");
			// Assign values for each row.
			newValues.put("NAME1",name);
			newValues.put("EMAIL1",email);
			newValues.put("PROFESSION1",profession);
			newValues.put("PHONENO1",phoneno);
			newValues.put("ID1",count1);
			// Insert the row into your table
			//db.insert("LOS", null, newValues);
			Log.d("nikunj",name);
			Log.d("nikunj",email);
			
			db.insert("LOS1", null, newValues);
		
			//Toast.makeText(context,"your id is"+count1,Toast.LENGTH_LONG).show();
			String c=""+count1;
			count1++;
			return c;
		}*/
		public int deleteEntry(String id)
		{
			//int id=Integer.valueOf("ID");
		    String where="ID=?";
		    int numberOFEntriesDeleted= db.delete("WORD", where,new String[]{""+id});
	       // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
	        return numberOFEntriesDeleted;
		}	
		
	/*	public int deleteEntry1(String id)
		{
			//int id=Integer.valueOf("ID");
		    String where="ID1=?";
		    int numberOFEntriesDeleted= db.delete("LOS1", where,new String[]{""+id});
	       // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
	        return numberOFEntriesDeleted;
		}	*/
		
		public int getSinlgeEntry(String Name)
		{
			Cursor cursor=db.query("WORD", null, "NAME=?", new String[]{Name}, null, null, null);
	        if(cursor.getCount()<1) // UserName Not Exist
	        {
	        	cursor.close();
	        	return 0;
	        }
		    cursor.moveToFirst();
			int id= cursor.getInt(cursor.getColumnIndex("ID"));
			
			cursor.close();
			return id;	
			
		}
		
		public String getSinlgeEntry1(int id)
		{
			int sum=0;
			String name="";
			boolean flag=false;
			Cursor cursor=db.query("WORD", null, "ID=?", new String[]{""+id}, null, null, null);
	        /*if(cursor.getCount()<1) // UserName Not Exist
	        {
	        	cursor.close();
	        	return 0;
	        }*/
		    if(cursor.moveToFirst())
		    {
		    	do
		    	{
		    		name=cursor.getString(cursor.getColumnIndex("NAME"));
		    	/*	String email=cursor.getString(cursor.getColumnIndex("EMAIL1"));
		    		String profession=cursor.getString(cursor.getColumnIndex("PROFESSION1"));
		    		String phoneNo=cursor.getString(cursor.getColumnIndex("PHONENO1"));*/
		    		
		    	}
		    	while(cursor.moveToNext());
		    }
			
			cursor.close();
			return name;				
		}
		
		/*public void  updateEntry(String education,String pancard,String experience,String assets,String id)
		{
			// Define the updated row content.
			ContentValues updatedValues = new ContentValues();
			// Assign values for each row.
			updatedValues.put("EDUCATION",education);
			updatedValues.put("PANCARD",pancard);
			updatedValues.put("EXPERIENCE",experience);
			updatedValues.put("ASSETS",assets);
			updatedValues.put("AMOUNT",amount);
			updatedValues.put("LOAN",loan);
			
	        String where="ID=?";
		    db.update("LOS",updatedValues, where, new String[]{id});			   
		}
		*/
}

