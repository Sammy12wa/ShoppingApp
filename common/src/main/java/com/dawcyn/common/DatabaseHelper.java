package com.dawcyn.common;

/*
 * Created by DAWCYN on 11/23/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "african_dresses_db";

    //Instances for News database table creation
    public static final String TABLE_APPLOCK_NAME = "tblapplock";
    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String CONTACT = "CONTACT";
    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";
    public static final String CODE = "CODE";
    public static final String STATUS = "STATUS";

    //Instances for add to cart database table creation
    public static final String TABLE_CART_NAME = "tbl_add_to_cart";
    public static final String CART_ID = "ID";
    public static final String CART_ITEM_NAME = "PRODUCT_NAME";
    public static final String CART_PRICE_PER_ITEM = "PRICE";
    public static final String CART_QUANTITY = "QUANTITY";
    public static final String CART_COST = "COST";
    public static final String CART_IMAGE = "IMAGE";

    //Instance for saved database table creation
    public static final String TABLE_SAVED_TABLE = "tblsaved";
    public static final String SAVED_ID = "ID";
    public static final String SAVED_PRODUCT_CODE = "CODE";
    public static final String SAVED_ITEM_NAME = "PRODUCT_NAME";
    public static final String SAVED_PRICE_PER_ITEM = "PRICE";
    public static final String SAVED_QUANTITY = "QUANTITY";
    public static final String SAVED_COST = "COST";
    public static final String SAVED_IMAGE = "IMAGE";



    public static final String SQL_Applock = "CREATE TABLE " + TABLE_APPLOCK_NAME + "(" + ID + " TEXT,"
                                            + NAME + " TEXT," + CONTACT + " TEXT, " + EMAIL + " TEXT, "
                                            +PASSWORD+" TEXT, "+ CODE + " TEXT, " + STATUS + " TEXT"  + ")";

    public static final String SQL_Saved = "CREATE TABLE " + TABLE_SAVED_TABLE + "("
            + SAVED_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + SAVED_PRODUCT_CODE + " TEXT," + SAVED_ITEM_NAME + " TEXT,"
            + SAVED_PRICE_PER_ITEM + " TEXT,"+ SAVED_QUANTITY + " TEXT," + SAVED_COST + " TEXT,"
            + SAVED_IMAGE + " TEXT" + ")";

    public static final String SQL_Add_to_Cart = "CREATE TABLE " + TABLE_CART_NAME + "("
            + CART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + CART_ITEM_NAME + " TEXT,"
            + CART_PRICE_PER_ITEM + " TEXT,"+ CART_QUANTITY + " TEXT," + CART_COST + " TEXT,"
            + CART_IMAGE + " TEXT" + ")";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        // SQLiteDatabase  db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_Applock);
        sqLiteDatabase.execSQL(SQL_Add_to_Cart);
        sqLiteDatabase.execSQL(SQL_Saved);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+TABLE_APPLOCK_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+TABLE_CART_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+TABLE_SAVED_TABLE);
        onCreate(sqLiteDatabase);
    }


    public boolean register_appLock(String id,String name, String contact, String email,String pass, String code,
                                    String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(NAME, name);
        contentValues.put(CONTACT, contact);
        contentValues.put(EMAIL, email);
        contentValues.put(PASSWORD, pass);
        contentValues.put(CODE, code);
        contentValues.put(STATUS,status);

        long result2 =db.insert(TABLE_APPLOCK_NAME, null, contentValues);
        if (result2 == -1)
            return false;
        else
            return true;
    }

    public boolean addToCart(Cart cart){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CART_ITEM_NAME, cart.name); // Contact Name
        values.put(CART_PRICE_PER_ITEM,cart.price);
        values.put(CART_QUANTITY,cart.quantity);
        values.put(CART_COST,cart.cost);
        values.put(CART_IMAGE, cart.testImage); // Contact Phone

// Inserting Row
        // Log.d("Insert: ", "Inserting ..");
        long result = db.insert(TABLE_CART_NAME, null, values);
        db.close(); // Closing database connection
        return result != -1;

    }

    public boolean savedProduct(Cart cart){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SAVED_PRODUCT_CODE, cart.code);
        values.put(SAVED_ITEM_NAME, cart.name); // Contact Name
        values.put(SAVED_PRICE_PER_ITEM,cart.price);
        values.put(SAVED_QUANTITY,cart.quantity);
        values.put(SAVED_COST,cart.cost);
        values.put(SAVED_IMAGE, cart.testImage); // Contact Phone

// Inserting Row
        // Log.d("Insert: ", "Inserting ..");
        long result = db.insert(TABLE_SAVED_TABLE, null, values);
        db.close(); // Closing database connection
        return result != -1;

    }

    public Cursor getAddToCartCount (SQLiteDatabase db)
    {
        String[] projection = {DatabaseHelper.CART_ID,DatabaseHelper.CART_COST};
        return db.query(DatabaseHelper.TABLE_CART_NAME,projection,null,null,null,null,null);
    }

    public Cursor getCartInfo (SQLiteDatabase db)
    {
        String[] projection = {DatabaseHelper.CART_ITEM_NAME, DatabaseHelper.CART_PRICE_PER_ITEM,DatabaseHelper.CART_QUANTITY, DatabaseHelper.CART_COST, DatabaseHelper.CART_IMAGE};
        return db.query(DatabaseHelper.TABLE_CART_NAME,projection,null,null,null,null,null);
    }

    public Cursor getSavedInfo (SQLiteDatabase db)
    {
        String[] projection = {DatabaseHelper.SAVED_PRODUCT_CODE, DatabaseHelper.SAVED_ITEM_NAME,DatabaseHelper.SAVED_PRICE_PER_ITEM, DatabaseHelper.SAVED_IMAGE};
        return db.query(DatabaseHelper.TABLE_SAVED_TABLE,projection,null,null,null,null,null);
    }


    public Cursor getSavedCount (SQLiteDatabase db)
    {
        String[] projection = {DatabaseHelper.SAVED_ID};
        return db.query(DatabaseHelper.TABLE_SAVED_TABLE,projection,null,null,null,null,null);
    }

    public void deletedSavedProduct(String code)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_SAVED_TABLE+ " WHERE "+CODE+"='"+code+"'");
        db.close();
    }

    public void clearSavedProduct()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_SAVED_TABLE);
        db.close();
    }

    public void clearCartCollection()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CART_NAME);
        db.close();
    }

    public String searchProductCode(String id)
    {

        SQLiteDatabase  db = this.getReadableDatabase();
        String query = "select ID, CODE from "+TABLE_SAVED_TABLE +" where CODE='"+id+"'";
        Cursor cursor = db.rawQuery(query, null);
        String a,b;
        b ="";
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);
                b =cursor.getString(1);
                //c = cursor.getString(2);

                if(a.equals(id))
                {
                    b =cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }

        return b;


    }

    public String getEmail(String id)
    {

        SQLiteDatabase  db = this.getReadableDatabase();
        String query = "select ID, EMAIL from "+TABLE_APPLOCK_NAME +" where ID='"+id+"'";
        Cursor cursor = db.rawQuery(query, null);
        String a,b;
        b ="";
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);
                b =cursor.getString(1);
                //c = cursor.getString(2);

                if(a.equals(id))
                {
                    b =cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }

        return b;



    }

    public String getPassword(String id)
    {

        SQLiteDatabase  db = this.getReadableDatabase();
        String query = "select ID, PASSWORD from "+TABLE_APPLOCK_NAME +" where ID='"+id+"'";
        Cursor cursor = db.rawQuery(query, null);
        String a,b;
        b ="";
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);
                b =cursor.getString(1);
                //c = cursor.getString(2);

                if(a.equals(id))
                {
                    b =cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }

        return b;



    }

/*



    public List<Faculty> getFacultyDetails(String pass) {
        List<Faculty> contactList = new ArrayList<Faculty>();
// Select All Query
        String selectQuery = "SELECT F_NAME,F_IMAGES FROM " + TABLE_FACULTY_NAME+" WHERE PASSWORD='"+pass+"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Faculty contact = new Faculty();
                contact.setName(cursor.getString(0));
                //contact.setQualification(cursor.getString(1));
                contact.setImages(cursor.getBlob(1));
                //contact.setPos(cursor.getString(3));
// Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
// close inserting data from database
        db.close();
// return contact list
        return contactList;

    }

    public List<Students> getStudentPhotoDetails(String id) {
        List<Students> contactList = new ArrayList<Students>();
// Select All Query
        String selectQuery = "SELECT NAME, STUDENT_IMAGE FROM " + TABLE_STUDENT_NAME+" WHERE STUDENTID='"+id+"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Students contact = new Students();
                contact.setName(cursor.getString(0));
                //contact.setId(cursor.getString(1));
                contact.setPhoto(cursor.getBlob(1));
// Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
// close inserting data from database
        db.close();
// return contact list
        return contactList;

    }

    public List<StudentRecords> getStudentRecordDetails(String id, String year, String semester) {
        List<StudentRecords> contactList = new ArrayList<StudentRecords>();
// Select All Query
        String selectQuery = "SELECT COURSE_NAME, MARKS, GRADE FROM " + TABLE_SUBJECT_REGISTERED_NAME
                +" WHERE STUDENT_ID='"+id+"' AND SEMESTER='"+semester+"' AND ACADEMIC_YEAR='"+year+"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                StudentRecords records = new StudentRecords();
                records.setCourse_name(cursor.getString(0));
                records.setMarks(cursor.getString(1));
                records.setGrade(cursor.getString(2));
// Adding contact to list
                contactList.add(records);
            } while (cursor.moveToNext());
        }
// close inserting data from database
        db.close();
// return contact list
        return contactList;

    }


    public String faculty_searchPass(String user, String pass)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select USERNAME, PASSWORD from "+TABLE_FACULTY_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a,b;
        b ="Not Found!!!";
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);
                b =cursor.getString(1);

                if(a.equals(user) && b.equals(pass))
                {
                    b =cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }

        return b;


    }

    public String student_searchPass(String user, String ID)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select NAME, STUDENTID from "+TABLE_STUDENT_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a,b;
        b ="Not Found!!!";
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);
                b =cursor.getString(1);

                if(a.equals(user) && b.equals(ID))
                {
                    b =cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }

        return b;


    }

    public boolean updateAccountDetails(String year, String current, String type, String program,
                                        String tuition, String semester, String computer, String Sid,
                                        String status, String dusa, String exams, String penalty, String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ACC_ACADEMIC_YEAR, year);
        contentValues.put(CURRENT_FEE, current);
        contentValues.put(ACC_ADMISSION_TYPE, type);
        contentValues.put(ACC_PROGRAMME,program);
        contentValues.put(TUITION_FEE, tuition);
        contentValues.put(ACC_SEMESTER, semester);
        contentValues.put(COMPUTER_FEE, computer);
        contentValues.put(ACC_STUDENTID, Sid);
        contentValues.put(ACC_STATUS, status);
        contentValues.put(DUSA_DUES, dusa);
        contentValues.put(EXAMS_FEE,exams);
        contentValues.put(INSTALLMENT_PENALTY, penalty);
        String[] whereArgs={id};
        long count = sqLiteDatabase.update(TABLE_ACCOUNT_NAME,contentValues,ACC_ID +" =? ",whereArgs);
        return count != -1;
    }

    public String searchCurrentFee()
    {

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select CURRENT_FEE from "+TABLE_ACCOUNT_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a,b;
        b ="";
        if(cursor.moveToFirst())
        {
            do{
                //a = cursor.getString(0);
                b =cursor.getString(0);
                //c = cursor.getString(2);

                if(b.length()>0)
                {
                    b =cursor.getString(0);
                    break;
                }
            }
            while (cursor.moveToNext());
        }

        return b;


    }

    public String searchPenaltyFee()
    {

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select PENALTY from "+TABLE_ACCOUNT_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a,b;
        b ="";
        if(cursor.moveToFirst())
        {
            do{
                //a = cursor.getString(0);
                b =cursor.getString(0);
                //c = cursor.getString(2);

                if(b.length()>0)
                {
                    b =cursor.getString(0);
                    break;
                }
            }
            while (cursor.moveToNext());
        }

        return b;


    }*/



    /*

    public String searchID(String user, String v_id)
    {
        SQLiteDatabase  db = this.getReadableDatabase();
        String query = "select USERNAME, STUDENTID from "+TABLE_VOTER_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a,b;
        b ="Not Found!!!";
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);
                b =cursor.getString(1);

                if(a.equals(user) && b.equals(v_id))
                {
                    b =cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }

        return b;


    }

    public Cursor getCandidateInfo (String candidatepos, SQLiteDatabase db)
    {
        String[] projection = {DatabaseHelper.C_NAME, DatabaseHelper.C_STUDENT_ID,DatabaseHelper.IMAGES, DatabaseHelper.TOTAL_VOTE};
        String selection = DatabaseHelper.C_POSITION + " LIKE ?";
        String[] selector = {candidatepos};
        return db.query(DatabaseHelper.TABLE_CANDIDATE_NAME,projection,selection,selector,null,null,null);
    }

    public Cursor getCandidateInfo (SQLiteDatabase db)
    {
        String[] projection = {DatabaseHelper.C_NAME, DatabaseHelper.C_STUDENT_ID,DatabaseHelper.C_POSITION};
        return db.query(DatabaseHelper.TABLE_CANDIDATE_NAME,projection,null,null,null,null,C_POSITION);
    }

    public Cursor getVoteOutcome (SQLiteDatabase db)
    {
        String[] projection = {DatabaseHelper.C_STUDENT_ID, DatabaseHelper.C_POSITION,DatabaseHelper.TOTAL_VOTE};
        return db.query(DatabaseHelper.TABLE_CANDIDATE_NAME,projection,null,null,null,null,C_POSITION);
    }

    public Cursor getVotersInfo (SQLiteDatabase db)
    {
        String[] projection = {DatabaseHelper.NAME, DatabaseHelper.STUDENT_ID,DatabaseHelper.STATUS};
        return db.query(DatabaseHelper.TABLE_VOTER_NAME,projection,null,null,null,null,STATUS);
    }

    public List<Candidates> getAllCandidates(String title) {
        List<Candidates> contactList = new ArrayList<Candidates>();
// Select All Query
        String selectQuery = "SELECT CNAME,STUDENT_ID,IMAGES FROM " + TABLE_CANDIDATE_NAME+" WHERE POSITION='"+title+"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Candidates contact = new Candidates();
                contact.setName(cursor.getString(0));
                contact.setId(cursor.getString(1));
                contact.setImage(cursor.getBlob(2));
// Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
// close inserting data from database
        db.close();
// return contact list
        return contactList;

    }

    public List<Candidates> getWinningCandidates(String title) {
        List<Candidates> contactList = new ArrayList<Candidates>();
// Select All Query
        String selectQuery = "SELECT CNAME,STUDENT_ID,IMAGES, MAX(VOTE) FROM " + TABLE_CANDIDATE_NAME+" WHERE POSITION='"+title+"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Candidates contact = new Candidates();
                contact.setName(cursor.getString(0));
                contact.setId(cursor.getString(1));
                contact.setImage(cursor.getBlob(2));
// Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
// close inserting data from database
        db.close();
// return contact list
        return contactList;

    }

    public String searchIDs(String v_id)
    {

        SQLiteDatabase  db = this.getReadableDatabase();
        String query = "select USERNAME, STUDENTID from "+TABLE_VOTER_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a,b;
        b ="";
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);
                b =cursor.getString(1);
                //c = cursor.getString(2);

                if(b.equals(v_id))
                {
                    b =cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }

        return b;


    }


    public String sms_mail(String v_id, int id)
    {
        SQLiteDatabase  db = this.getReadableDatabase();
        String query = "select STUDENTID, PHONE, EMAIL  from "+TABLE_VOTER_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b ="Not Found!!!";
        if(cursor.moveToFirst())
        {
            do{
                //a = cursor.getString(0);
                a =cursor.getString(0);

                if(a.equals(v_id))
                {
                    if(id==1)
                        b =cursor.getString(1);
                    else
                        b =cursor.getString(2);
                    break;
                }
            }
            while (cursor.moveToNext());
        }

        return b;


    }

    public String searchCandidateVote(String v_id)
    {
        SQLiteDatabase  db = this.getReadableDatabase();
        String query = "select STUDENT_ID, VOTE  from "+TABLE_CANDIDATE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b ="Not Found!!!";
        if(cursor.moveToFirst())
        {
            do{
                //a = cursor.getString(0);
                a =cursor.getString(0);

                if(a.equals(v_id))
                {

                    b =cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }

        return b;


    }



    public String searchPass(String user, String pass)
    {
        SQLiteDatabase  db = this.getReadableDatabase();
        String query = "select USER, PASS from "+TABLE_ADMIN_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a,b;
        b ="Not Found!!!";
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);
                b =cursor.getString(1);

                if(a.equals(user) && b.equals(pass))
                {
                    b =cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }

        return b;


    }

    public String voterStatusVerifier(String pass)
    {
        SQLiteDatabase  db = this.getReadableDatabase();
        String query = "select STUDENTID, STATUS from "+TABLE_VOTER_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a,b;
        b ="Not Found!!!";
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);

                //b =cursor.getString(1);

                if(a.equals(pass) )
                {
                    b =cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }

        return b;


    }


    public Cursor getSelectedCandidatesRows(String pos){
        SQLiteDatabase  db = this.getReadableDatabase();
        String[] columns = {C_NAME,C_STUDENT_ID,C_POSITION};
        String selection = C_POSITION + " LIKE ?";
        String[] selector ={pos};
        return db.query(TABLE_CANDIDATE_NAME,columns,selection,selector,null,null,null);
    }

    public Cursor getAllCandidatesRows(){
        SQLiteDatabase  db = this.getReadableDatabase();
        String[] columns = {C_NAME,C_STUDENT_ID,C_POSITION};
        return db.query(TABLE_CANDIDATE_NAME,columns,null,null,null,null,null);
    }

    public boolean updateVoterStatus(int status, String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(STATUS, status);
        String[] whereArgs={id};
        long count = sqLiteDatabase.update(TABLE_VOTER_NAME,contentValues,STUDENT_ID+" =? ",whereArgs);
        return count != -1;
    }
    public Cursor getVoterStatus(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("select STATUS from "+TABLE_VOTER_NAME + " where STUDENTID='"+id+"'",null);
    }

    public Cursor getCandidateVote(String img){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("select VOTE from "+TABLE_CANDIDATE_NAME + " where IMAGES='"+img+"'",null);
    }

    public boolean updateCandidateVote(String vote, String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TOTAL_VOTE, vote);
        String[] whereArgs={id};
        long count = sqLiteDatabase.update(TABLE_CANDIDATE_NAME,contentValues,C_STUDENT_ID+" =? ",whereArgs);
        return count != -1;
    }*/
}