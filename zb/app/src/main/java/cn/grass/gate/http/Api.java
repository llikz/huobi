package cn.grass.gate.http;

/**
 * Created by weedys on 16/7/21.
 */
public class Api {
//    public static String HOST = "http://192.168.158.2:8080"; //陈服务器
//    public static String HOST = "http://192.168.1.105:8080"; //105服务器
    public static String HOST = "http://192.168.1.110:8080"; //110服务器
    public static String HOST2 = ""; //zb服务器
//    public static String HOST = "http://10.0.2.2:8080"; //模拟器
    public static String URL_LOGIN = HOST + "/hyems/loginForApp?";//登录
    public static String URL_GET_ARTICLE_LIST = HOST + "/api/column/getPageArticle?";//获取对应栏目（税务动态，工作指导等）
    public static String URL_GET_ARTICLE_DETAIL = HOST + "/api/column/getArticleDetails?";//文章详情
    public static String URL_FORGET_PWD = HOST + "/hyems/forgetPwd?";//忘记密码
    public static String URL_GET_TASK_LIST = HOST + "/hyems/wrk/targetplan/taskList?";//任务列表
    public static String URL_GET_LIKE_TASK_LIST = HOST + "/hyems/wrk/targetplan/likeTaskList?";//任务列表模糊查找
    public static String URL_GET_AREA_LIST = HOST + "/hyems/wrk/target/areaList?";//台区列表
    public static String URL_GET_LIKE_AREA_LIST = HOST + "/hyems/wrk/target/likeAreaList?";//台区列表模糊查找




    public static String URL_GET_HOME_LIST = HOST2 + "http://api.zb.com/data/v1/markets";//home列表
    public static String URL_GET_HANGQING = HOST2 + "http://api.zb.com/data/v1/ticker?";//行情
    public static String URL_GET_GATEIO_CNY = HOST2 + "http://18.216.158.43:8080/blog/zuiXing";//最新
    public static String URL_GET_GATEIO_USDTCNY = HOST2 + "http://data.gateio.io/api2/1/ticker/usdt_cny";//usdt_cny



}
