package cn.grass.gate.data;

import android.database.sqlite.SQLiteDatabase;

import com.weedys.weedlibrary.utils.LogUtil;


public class DatabaseManager {

	private static final String TAG = "DatabaseManager";
	private static SQLiteDatabase db ;
	public static SQLiteDatabase open() {
		if(db==null||!db.isOpen())
			db = DbHelper.getInstance().getWritableDatabase();
		return db;
	}
	/**
	 * 退出程序的时候关闭即可
	 */
	public static void close() {
		LogUtil.show(TAG, "close()");
		if (db != null) {
			db.close();
			db = null;
		}
	}

}
