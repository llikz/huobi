//package cn.grass.palmTax.data;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import cn.grass.palmTax.R;
//
///**
// * Created by weedys on 2016/12/13.
// */
//
//public class MenuHelper {
//    public static MenuHelper helper;
//    public MenuHelper(){
//        helper = this;
//        if(menuDb==null){
//            menuDb=new MenuDb();
//        }
//    }
//    public static MenuHelper getHelper(){
//        if(helper==null){
//            helper = new MenuHelper();
//        }
//
//        return helper;
//    }
//    MenuDb menuDb;
//    public boolean isReady()
//    {
//        if(MenuDb.getMenusList(0)!=null)return true;
//      //  initData();
//        return false;
//    }
//    private String[] menuTitles={"作业","通知","我要请假","我的课程表","我的表现","班级相簿","活动","学习资料","在线视频","学校官网","成绩查询"};
//    private int[] menuRes={R.mipmap.icon_index_class_task,R.mipmap.icon_index_class_notice,R.mipmap.icon_leave,R.mipmap.icon_index_class_course
//            ,R.mipmap.icon_index_class_award,R.mipmap.icon_index_class_ablum
//            ,R.mipmap.icon_index_class_activity,R.mipmap.icon_index_class_study,R.mipmap.icon_index_class_video
//            ,R.mipmap.icon_office_site,R.mipmap.icon_student_result
//    };
//    private String[] menuContents={"我的作业","各类信息通知","便捷请假","学生的课程表","奖励学生表现","学生班级相簿"
//            ,"学生活动集锦","共享学习资源","在线视频","学校资讯网站","一键查询成绩"};
//    private String[] menuId={"task","notice","leave","courseList","award","album"
//            ,"activity","study","video","office","stu_result"};
//    private String[] menuActions={"cn.grasscloud.jiaxiaoteacher.activitys.TeaClassTaskActivity",
//            "cn.grasscloud.jiaxiao.activitys.ClassNoticeActivity",
//            "cn.grasscloud.jiaxiao.activitys.LeaveActivity",//改变家长请假的跳转
//            "cn.grasscloud.jiaxiao.activitys.CourseDetailActivity",
//            "cn.grasscloud.jiaxiao.activitys.AwardListActivity",
//            "cn.grasscloud.jiaxiao.activitys.ClassAlbumListActivity" ,
//            "cn.grasscloud.jiaxiao.activitys.ActivitiesListActivity",
//            "cn.grasscloud.jiaxiao.activitys.InformationActivity",
//            "cn.grasscloud.jiaxiao.activitys.VideoCourseListActivity",
////            "cn.grasscloud.jiaxiao.activitys.GrassWebView#"+ GrassWebView.URL+"="+ Api.URL_SCHOOL_HOME+"#"+GrassWebView.TITLE+"=学校微网",
//            "cn.grasscloud.jiaxiao.activitys.SchoolWebViewActivity",
//            "cn.grasscloud.jiaxiao.activitys.ScoreListActivity"
//    };
//    private HashMap<String,Integer> resMap=new HashMap<>();
//    public void initData(){
//        ArrayList<MenuBean> all =new ArrayList<>();
//                all = MenuHelper.getHelper().getAllMenus();
//        if (all==null||all.size()==0){
//            MenuDb.clearDb();
//            for(int i=0;i<menuTitles.length;i++){
//                MenuBean b=new MenuBean();
//                b.title = menuTitles[i];
//                b.content = menuContents[i];
//                b.classStr = menuActions[i];
//                b.res = menuRes[i];
//                b.status=0;
//                if(i<5){
//                    b.status=1;
//                }
//                b.unread=0;
//                b.code = i;
//                b.menuId=menuId[i];
//                b.from = 0;
//                MenuDb.saveMenu(b);
//                // all.add(b);
//            }
//        }
//    }
//    public int getRes(String key){
//
//        if(menuId==null){
//            return 0;
//        }
//        if(resMap==null || resMap.size()!=menuId.length){
//            if(resMap!=null){
//                resMap.clear();
//            }else{
//                resMap=new HashMap<>();
//            }
//            for(int i=0;i<menuId.length;i++){
//                resMap.put(menuId[i],menuRes[i]);
//            }
//        }
//        int value=0;
//        if(resMap.containsKey(key)) {
//            value = resMap.get(key);
//        }else{
//            if(key.equals("more")){
//                value = R.mipmap.icon_menu_all;
//            }
//        }
//        return value;
//
//    }
//
//    public ArrayList<MenuBean> getAvailableMenus(){
//
//        ArrayList<MenuBean> ms = MenuDb.getMenusList(1);
//        return  ms;
//    }
//    public ArrayList<MenuBean> getOverMenus(){
//
//        ArrayList<MenuBean> ms = MenuDb.getMenusList(0);
//        return  ms;
//    }
//
//    public ArrayList<MenuBean> getAllMenus(){
//
//        ArrayList<MenuBean> ms = MenuDb.getAllMenusList();
//        return  ms;
//    }
//
//}
