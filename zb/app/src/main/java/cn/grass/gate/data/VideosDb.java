//package cn.grass.palmTax.data;
//
//import android.content.ContentValues;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.text.TextUtils;
//
//import java.util.ArrayList;
//
///**
// * Created by weedys on 16/10/18.
// */
//public class VideosDb {
//    public static String TABLE_NAME = "video_lessons";
//    public static final String COLUMN_NAME__ID = "id";
//    public static final String COLUMN_NAME_VID = "vid";
//    public static final String COLUMN_NAME_TITLE = "title";
//    public static final String COLUMN_NAME_THUMB = "thumb";
//    public static final String COLUMN_NAME_LEN = "length";
//    public static final String COLUMN_NAME_URL = "url";
//    public static final String COLUMN_NAME_SORT = "sort";
//    public static final String COLUMN_NAME_STATE = "state";
//    public static final String COLUMN_NAME_LESSON_TITLE = "lesson_title";
//    public static final String COLUMN_NAME_LESSON_FACE = "lesson_avator";
//    public static final String COLUMN_NAME_LESSON_ID = "lesson_id";
//    public static final String LESSONS_TABLE_CREATE = "CREATE TABLE "
//            + TABLE_NAME + " ( "
//            + COLUMN_NAME__ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//            + COLUMN_NAME_VID + " TEXT , "
//            + COLUMN_NAME_TITLE + " TEXT , "
//            + COLUMN_NAME_THUMB + " TEXT ,"
//            + COLUMN_NAME_LEN + " TEXT , "
//            + COLUMN_NAME_URL + " TEXT , "
//            + COLUMN_NAME_SORT + " TEXT , "
//            + COLUMN_NAME_LESSON_ID + " TEXT , "
//            + COLUMN_NAME_LESSON_TITLE + " TEXT , "
//            + COLUMN_NAME_LESSON_FACE + " TEXT , "
//            + COLUMN_NAME_STATE + " INTEGER);";
//    public static final String LESSONS_TABLE_DROP = "DROP TABLE " + TABLE_NAME + " ";
//    public static void saveVideo(LessonsInfo u) {
//        SQLiteDatabase db = DatabaseManager.open();
//        if (!db.isOpen()) {
//            return;
//        }
//        if (TextUtils.isEmpty(u.lessonId)) {
//            return;
//        }
//        if (!hasLesson(u.lessonId)) {
//            db.insert(TABLE_NAME, null, getValues(u));
//        } else {
//            int id = db.update(TABLE_NAME, updateValues(u), COLUMN_NAME_VID + " =?", new String[]{u.lessonId});
//        }
//        db.close();
//    }
//    private static boolean hasLesson(String vid){
//        SQLiteDatabase db = DatabaseManager.open();
//        if (!db.isOpen()) {
//            return false;
//        }
//        String sql="select * from "+TABLE_NAME +" WHERE "+COLUMN_NAME_VID+" = "+vid;
//        Cursor c=db.rawQuery(sql,null);
//        if(c!=null && c.getCount()>0){
//            return true;
//        }
//        return false;
//
//    }
//
//
//    public static void saveLessonList(ArrayList<LessonsInfo> us) {
//        SQLiteDatabase db = DatabaseManager.open();
//        if (!db.isOpen()) {
//            return;
//        }
//        if (us == null || us.size() == 0) {
//            return;
//        }
//        for (int i = 0; i < us.size(); i++) {
//            LessonsInfo lessonsInfo = us.get(i);
//            if (hasLesson(lessonsInfo.lessonId)) {
//                String where = COLUMN_NAME_VID + " =?";
//                db.update(TABLE_NAME, updateValues(lessonsInfo), where, new String[]{lessonsInfo.lessonId});
//            } else {
//                db.insert(TABLE_NAME, null, getValues(lessonsInfo));
//            }
//        }
//        db.close();
//    }
//    public ArrayList<LessonsInfo> getLessons(String courseId){
//        SQLiteDatabase db = DatabaseManager.open();
//        if (!db.isOpen()) {
//            return null;
//        }
//        String sql = "select * from " + TABLE_NAME + " where " + COLUMN_NAME_LESSON_ID + " = '" + courseId + "'";
//        Cursor cs = db.rawQuery(sql, null);
//        ArrayList<LessonsInfo> lessonsInfos = null;
////        Cursor cs=db.query(TABLE_NAME,null,null,null,null,null,null);
//        if (cs != null && cs.getCount() > 0) {
//            lessonsInfos = new ArrayList<>();
//            cs.moveToFirst();
//            for(int i=0;i<cs.getCount();i++){
//                lessonsInfos.add(getLesson(cs));
//            }
//            cs.close();
//        }
//        return lessonsInfos;
//    }
//    public static LessonsInfo getLesson(String vid) {
//        SQLiteDatabase db = DatabaseManager.open();
//        if (!db.isOpen()) {
//            return null;
//        }
//        String sql = "select * from " + TABLE_NAME + " where " + COLUMN_NAME_VID + " = '" + vid + "'";
//        Cursor cs = db.rawQuery(sql, null);
//        LessonsInfo lessonsInfo = null;
//        if (cs != null && cs.getCount() > 0) {
//            lessonsInfo = getLesson(cs);
//
//            cs.close();
//        }
//        return lessonsInfo;
//    }
//    public static void setLessonStated(String vid){
//        LessonsInfo v= getLesson(vid);
//        if(v!=null){
//            v.state=LessonsInfo.STATE_FINISH;
//            saveVideo(v);
//        }
//
//    }
//    private static LessonsInfo getLesson(Cursor cs){
//        LessonsInfo lessonsInfo = new LessonsInfo();
//        lessonsInfo.lessonId = cs.getString(cs.getColumnIndex(COLUMN_NAME_VID));
//        lessonsInfo.title = cs.getString(cs.getColumnIndex(COLUMN_NAME_TITLE));
//        lessonsInfo.length = cs.getString(cs.getColumnIndex(COLUMN_NAME_LEN));
//        lessonsInfo.url = cs.getString(cs.getColumnIndex(COLUMN_NAME_URL));
//        lessonsInfo.state = cs.getInt(cs.getColumnIndex(COLUMN_NAME_STATE));
//        lessonsInfo.sort = cs.getString(cs.getColumnIndex(COLUMN_NAME_SORT));
//
//        lessonsInfo.courseTitle = cs.getString(cs.getColumnIndex(COLUMN_NAME_LESSON_TITLE));
//        lessonsInfo.courseId = cs.getString(cs.getColumnIndex(COLUMN_NAME_LESSON_ID));
//        lessonsInfo.courseAvator = cs.getString(cs.getColumnIndex(COLUMN_NAME_LESSON_FACE));
//        return lessonsInfo;
//    }
//    private static ContentValues getValues(LessonsInfo u) {
//        ContentValues values = new ContentValues();
//        if (u == null) {
//            return null;
//        }
//        values.put(COLUMN_NAME_VID, u.lessonId);
//        values.put(COLUMN_NAME_LEN, u.length);
//        values.put(COLUMN_NAME_SORT, u.sort);
//        values.put(COLUMN_NAME_STATE, u.state);
//        values.put(COLUMN_NAME_TITLE, u.title);
//        values.put(COLUMN_NAME_URL, u.url);
//        values.put(COLUMN_NAME_THUMB, "");
//        values.put(COLUMN_NAME_LESSON_ID, u.courseId);
//        values.put(COLUMN_NAME_LESSON_TITLE, u.courseTitle);
//        values.put(COLUMN_NAME_LESSON_FACE, u.courseAvator);
//        return values;
//    }
//    private static ContentValues updateValues(LessonsInfo u) {
//        ContentValues values = new ContentValues();
//        if (u == null) {
//            return null;
//        }
//        values.put(COLUMN_NAME_VID, u.lessonId);
//        values.put(COLUMN_NAME_LEN, u.length);
//        values.put(COLUMN_NAME_SORT, u.sort);
//        values.put(COLUMN_NAME_TITLE, u.title);
//        values.put(COLUMN_NAME_URL, u.url);
//        values.put(COLUMN_NAME_THUMB, "");
//        values.put(COLUMN_NAME_LESSON_ID, u.courseId);
//        values.put(COLUMN_NAME_LESSON_TITLE, u.courseTitle);
//        values.put(COLUMN_NAME_LESSON_FACE, u.courseAvator);
//        return values;
//    }
//}
