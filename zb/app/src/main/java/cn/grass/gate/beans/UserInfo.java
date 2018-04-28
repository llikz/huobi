package cn.grass.gate.beans;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.grass.gate.http.Api;


/**
 * Created by weedys on 16/7/22.
 * 个人信息
 */
public class UserInfo {
    public String token = "";//
    public String createTime = "";//创建时间
    public String birth = "";//出生日期
    public int sort;//序号
    public String imageUrl = "";//图片路径
    public String xb_dm = "";//
    public String password = "";//密码
    public String id = "";//
    public String landline = "";//座机
    public String username = "";//账号
    public int personnel;//人员状态
    public int head ;//部门主要负责人
    public String revTime = "";//停用时间起
    public String modifyTime = "";//修改时间
    public String idNumber = "";//身份证号码
    public String createUserId = "";//创建人id
    public int status;//状态：0已删除 1未删除
    public String nickname = "";//用户昵称
    public String cornet = "";//短号
    public int revOrStop;//启停标志
    public String imUserName = "";//环信账号
    public String stopTime = "";//停用时间止
    public String modifyUserId = "";//修改人id
    public String deptCode = "";//所属组织编码
    public String salt = "";//加密
    public String phone = "";//手机号码

//    public String signature = "";
//    public String avatar;
//
//    public String realName = "";
//    public String account = "";//登陆账户
//    public String pwd = "";//登陆密码
//    public int state = 0;//第三方登录0为未绑定，1为已绑定

    public UserInfo() {
    }



//    public String getSignature() {
//        if (!TextUtils.isEmpty(signature)) {
//            if (signature.length() >= 30) {
//                return signature.substring(0, 30);
//            }
//        }
//        return signature;
//    }

//    public String getUname() {
//        return uname;
//    }

    public String getAvatar() {
        File fs = new File(imageUrl);
        if (fs != null && fs.exists()) {
            return imageUrl;
        }

        boolean http=false;
        if (imageUrl!=null&&imageUrl.length()>0){
            http = getCompleteUrl(imageUrl);
        }
        if (http == true) {
            return imageUrl;
        } else {
            return Api.HOST + imageUrl;
        }
    }

    public static boolean getCompleteUrl(String text) {
        Pattern p = Pattern.compile("((http|ftp|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(text);
        if (matcher.find()) {
            return true;
        } else {
            return false;
        }


    }
}
