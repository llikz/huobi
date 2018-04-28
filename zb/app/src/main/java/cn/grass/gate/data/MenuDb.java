//package cn.grass.palmTax.data;
//
//import android.content.ContentValues;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.text.TextUtils;
//
//import java.util.ArrayList;
//
//
///**
// * Created by weedys on 2016/12/12.
// */
//
//public class MenuDb {
//    public static String TABLE_NAME = "menus";
//    public static final String COLUMN_NAME__ID = "id";
//    public static final String COLUMN_NAME_MID = "mid";
//    public static final String COLUMN_NAME_TITLE = "title";
//    public static final String COLUMN_NAME_THUMB = "thumb";
//    public static final String COLUMN_NAME_ACTION = "action";
//    public static final String COLUMN_NAME_CONTENT = "content";
//    public static final String COLUMN_NAME_STATUS = "status";
//    public static final String COLUMN_NAME_RES = "res";
//    public static final String COLUMN_NAME_CODE = "code";
//    public static final String MENU_TABLE_CREATE = "CREATE TABLE "
//            + TABLE_NAME + " ( "
//            + COLUMN_NAME__ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//            + COLUMN_NAME_MID + " TEXT , "
//            + COLUMN_NAME_TITLE + " TEXT , "
//            + COLUMN_NAME_THUMB + " TEXT ,"
//            + COLUMN_NAME_ACTION + " TEXT , "
//            + COLUMN_NAME_CONTENT + " TEXT ,"
//            +COLUMN_NAME_RES + " TEXT ,"
//             +COLUMN_NAME_CODE + " TEXT ,"
//            + COLUMN_NAME_STATUS + " TEXT );";
//    public static final String MENUS_TABLE_DROP = "DROP TABLE " + TABLE_NAME ;
//    public static void saveMenu(MenuBean u) {
//        SQLiteDatabase db = DatabaseManager.open();
//        if (!db.isOpen()) {
//            return;
//        }
//        if (TextUtils.isEmpty(u.title)) {
//            return;
//        }
//        if (!hasMenu(u.menuId)) {
//            db.insert(TABLE_NAME, null, getValues(u));
//        } else {
//            int id = db.update(TABLE_NAME, updateValues(u), COLUMN_NAME_MID + " =?", new String[]{u.menuId});
//        }
//        db.close();
//    }
//    public static void clearDb(){
//        SQLiteDatabase db = DatabaseManager.open();
//        if (!db.isOpen()) {
//            return;
//        }
//        db.delete(TABLE_NAME,null,null);
//        db.close();
//    }
//
//    /**
//     * 0是全部，1是选中，-1是没选
//     * @param status
//     */
//    public static ArrayList<MenuBean> getMenusList(int status) {
//        SQLiteDatabase db = DatabaseManager.open();
//        if (!db.isOpen()) {
//            return null;
//        }
//        String sql = "select * from " + TABLE_NAME + " where " + COLUMN_NAME_STATUS + " = " + status+" order by "+COLUMN_NAME_CODE+" ASC ";
//        Cursor cs = db.rawQuery(sql, null);
//        ArrayList<MenuBean> menus = new ArrayList<>();
////        Cursor cs=db.query(TABLE_NAME,null,null,null,null,null,null);
//        if (cs != null && cs.getCount() > 0) {
//            cs.moveToFirst();
//            for(int i=0;i<cs.getCount();i++){
//                menus.add(getMenus(cs));
//                cs.moveToNext();
//            }
//            cs.close();
//        }
//        db.close();
//        return menus;
//    }
//
//    /**
//     * 全部
//     */
//    public static ArrayList<MenuBean> getAllMenusList() {
//        SQLiteDatabase db = DatabaseManager.open();
//        if (!db.isOpen()) {
//            return null;
//        }
//        String sql = "select * from " + TABLE_NAME;
//        Cursor cs = db.rawQuery(sql, null);
//        ArrayList<MenuBean> menus = new ArrayList<>();
//        cs.moveToFirst();
//        if (cs != null && cs.getCount() > 0) {
//
//            for(int i=0;i<cs.getCount();i++){
//                menus.add(getMenus(cs));
//                cs.moveToNext();
//            }
//
//        }
//        cs.close();
//        db.close();
//        return menus;
//    }
//
//
//    private static boolean hasMenu(String vid){
//        SQLiteDatabase db = DatabaseManager.open();
//        if (!db.isOpen()) {
//            return false;
//        }
//        String sql="select * from "+TABLE_NAME +" WHERE "+COLUMN_NAME_MID+" = '"+vid+"'";
//        Cursor c=db.rawQuery(sql,null);
//        if(c!=null && c.getCount()>0){
//            return true;
//        }
//        c.close();
//        return false;
//
//    }
//    private static MenuBean getMenus(Cursor cs){
//        MenuBean info = new MenuBean();
//        info.menuId = cs.getString(cs.getColumnIndex(COLUMN_NAME_MID));
//        info.title = cs.getString(cs.getColumnIndex(COLUMN_NAME_TITLE));
//        info.classStr = cs.getString(cs.getColumnIndex(COLUMN_NAME_ACTION));
//        info.content =cs.getString(cs.getColumnIndex(COLUMN_NAME_CONTENT));
//        info.resDraw =cs.getString(cs.getColumnIndex(COLUMN_NAME_THUMB));
//        info.status =cs.getInt(cs.getColumnIndex(COLUMN_NAME_STATUS));
//        info.res =cs.getInt(cs.getColumnIndex(COLUMN_NAME_RES));
//        info.code =cs.getInt(cs.getColumnIndex(COLUMN_NAME_CODE));
//        info.from = 0;
//        return info;
//    }
//    private static ContentValues getValues(MenuBean u) {
//        ContentValues values = new ContentValues();
//        if (u == null) {
//            return null;
//        }
//        values.put(COLUMN_NAME_MID, u.menuId);
//        values.put(COLUMN_NAME_TITLE, u.title);
//        values.put(COLUMN_NAME_THUMB, u.resDraw);
//        values.put(COLUMN_NAME_CONTENT, u.content);
//        values.put(COLUMN_NAME_ACTION, u.classStr);
//        values.put(COLUMN_NAME_STATUS,u.status);
//        values.put(COLUMN_NAME_CODE,u.code);
//        values.put(COLUMN_NAME_RES,u.res);
//        return values;
//    }
//    private static ContentValues updateValues(MenuBean u) {
//        ContentValues values = new ContentValues();
//        if (u == null) {
//            return null;
//        }
//        values.put(COLUMN_NAME_MID, u.code);
//        values.put(COLUMN_NAME_TITLE, u.title);
//        values.put(COLUMN_NAME_CONTENT, u.content);
//        values.put(COLUMN_NAME_THUMB ,u.resDraw);
//        values.put(COLUMN_NAME_ACTION,u.classStr);
//        values.put(COLUMN_NAME_STATUS,u.status);
//        return values;
//    }
//}
