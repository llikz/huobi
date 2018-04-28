package cn.grass.gate.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by min on 2017/7/29.
 * 操作CSV的工具类
 */

public class CSVUtil {

    public File writeCsv(String path) {
//        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/csv/";//CSV文件路径
        File csv = new File(path/* + "KnowYourTeam.csv"*/);
        File pa = new File(path);
        if (!pa.exists()) {
            pa.mkdirs();
        }
        if (!csv.exists()) {
            try {
                csv.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                csv.delete();//这里写的如果文件存在会删除文件新建一个文件
                csv.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));
//            ArrayList<Person> list = new PersonDao(this).queryAll();//数据库取Person List
//            for (Person person : list) { //循环写入person数据(name,title,image)
//                String img = writeBase64(person.getPicPath()); //getPicPath()路径
//                bw.write(person.getName() + "," + person.getTitle() + "," + img);
//                bw.newLine();//换行，一行一组数据
//            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csv;
    }
}
