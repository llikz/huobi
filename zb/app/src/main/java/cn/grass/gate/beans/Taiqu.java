package cn.grass.gate.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by min on 2017/8/29.
 */

public class Taiqu {


    /**
     * data : {"endRow":10,"first":1,"hasNext":true,"hasPrevious":false,"isFirst":true,"isLast":false,"last":8,"list":[{"anZhuangRiQi":"2016-01-30 12:00:00","banNianHuDianLiang":"1206","bianYaQiBianHao":"1111111111427822","bianYaQiEDingRongLiang":"800","bianYaQiMingCheng":"惠州市中港城实业有限公司(公变)","biaoXiangCaiZhi":0,"chanPinId":"c6e47907-2c94-4f7d-b3ea-103b3f50a0f1","chaoBiaoQuDuanBianHao":"中港城1号公变","chaoBiaoQuDuanShuLiang":0,"chaoBiaoXuHao":"138","chaoBiaoYuan":"李春杨","chaoBiaoZhouQi":4,"chuChangRiQi":"2014-10-16 12:00:00","createdAt":"2017-08-14 09:59:46","gongDianJuId":"34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17","gongDianSuoId":"21bc1159-93a5-130f-1ba1-c247ea5fe840","id":"e41218db-5c2d-4b29-af59-607b04e931d8","jiChaoGongZuoLeiXing":2,"jiLiangDianBianHao":"1111111112894839","jiLiangDianChaoBiaoFangShi":5,"jiZhongQiZiChanHao":"","kanChaNanDu":1,"kanChaYongHuZhuangKuang":0,"kcGengHuanDianBiao":0,"kuiXianId":"6f9e786d-d82d-a7fa-bd6f-bb96134cfd53","liHuRiQi":"2016-02-02 12:00:00","lianXiDianHua":"13528024465","piCiHao":"201411252025","sheBeiLeiXing":1,"shiFouGengHuanBiaoXiang":0,"shiFouGengHuanDianBiao":2,"shiFouJiChaoFuGai":1,"shiFouZhengTiGengHuan":0,"shiFouZhengTiQianYi":0,"status":0,"taiQuBianHao":"0313040002464901","taiQuId":"23f69837-d2c8-4c12-385c-cbf0c7721a1b","taiQuLeiXing":1,"tongXunDiZhi":"","txtBiaoXiangCaiZhi":"","txtChaoBiaoZhouQi":"双月","txtCreatedAt":"2017-08-14 09:59:46","txtGongDianJu":"博罗供电局","txtGongDianSuo":"福田供电所","txtJiChaoGongZuoLeiXing":"集抄改造用户","txtJiLiangDianChaoBiaoFangShi":"远程低压遥抄","txtKanChaNanDu":"正常勘查","txtKanChaYongHuZhuangKuang":"","txtKuiXianMingCheng":"10kV山吓线F20","txtSheBeiLeiXing":"RS-485接口单相电子式电能表","txtSheBeiXingHao":"DDSF284","txtShengChanChangJia":"华立仪表集团股份有限公司","txtShiFouGengHuanBiaoXiang":"","txtShiFouGengHuanDianBiao":"否","txtShiFouJiChaoFuGai":"是","txtShiFouZhengTiGengHuan":"","txtShiFouZhengTiQianYi":"","txtTaiQuLeiXing":"小区","txtTaiQuMingCheng":"惠州市中港城实业有限公司(公变)#1","txtUpdatedAt":"","txtUserAddName":"余思敏","txtUserModifyName":"","txtYiChangQingKuang":"台区户号一对多","txtYingXiaoYongHuZhuangKuang":"运行","txtYongDianLeiBie":"居民生活","txtYongDianLiangQingKuang":"正常","txtYongHuLeiBie":"公变客户","txtYxGengHuanDianBiao":"","txtZuiJinChaoBiaoFangShi":"远程低压遥抄","userAddId":"62d7f995-fbe2-405a-936f-cb1d67215fdc","userModifyId":"0","version":0,"yiChangQingKuang":4,"yiNianHuDianLiang":"1930","yingXiaoYongHuZhuangKuang":1,"yongDianDiZhi":"广东省惠州市博罗县福田镇中港城1栋401","yongDianLeiBie":4,"yongDianLiangQingKuang":1,"yongHuBianHao":"0313040011284202","yongHuHeTongRongLiang":"6","yongHuLeiBie":1,"yongHuMingCheng":"高殿龙","yxGengHuanDianBiao":2,"ziChanBianHao":"1310701035985","zongHeBeiLv":"1","zuiJinChaoBiaoFangShi":5,"zuiJinChaoBiaoShiJian":"2017-06-01 12:00:00"},{"anZhuangRiQi":"2016-01-30 12:00:00","banNianHuDianLiang":"0","bianYaQiBianHao":"1111111111427822","bianYaQiEDingRongLiang":"800","bianYaQiMingCheng":"惠州市中港城实业有限公司(公变)","biaoXiangCaiZhi":0,"chanPinId":"c6e47907-2c94-4f7d-b3ea-103b3f50a0f1","chaoBiaoQuDuanBianHao":"中港城1号公变","chaoBiaoQuDuanShuLiang":0,"chaoBiaoXuHao":"133","chaoBiaoYuan":"李春杨","chaoBiaoZhouQi":4,"chuChangRiQi":"2014-10-16 12:00:00","createdAt":"2017-08-14 09:59:47","gongDianJuId":"34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17","gongDianSuoId":"21bc1159-93a5-130f-1ba1-c247ea5fe840","id":"1bbc4340-bb4a-4c4b-83be-978b7de73c9c","jiChaoGongZuoLeiXing":2,"jiLiangDianBianHao":"1111111112894834","jiLiangDianChaoBiaoFangShi":5,"jiZhongQiZiChanHao":"","kanChaNanDu":2,"kanChaYongHuZhuangKuang":0,"kcGengHuanDianBiao":0,"kuiXianId":"6f9e786d-d82d-a7fa-bd6f-bb96134cfd53","liHuRiQi":"2016-02-02 12:00:00","lianXiDianHua":"13662428351","piCiHao":"201411252025","sheBeiLeiXing":1,"shiFouGengHuanBiaoXiang":0,"shiFouGengHuanDianBiao":2,"shiFouJiChaoFuGai":1,"shiFouZhengTiGengHuan":0,"shiFouZhengTiQianYi":0,"status":0,"taiQuBianHao":"0313040002464901","taiQuId":"23f69837-d2c8-4c12-385c-cbf0c7721a1b","taiQuLeiXing":1,"tongXunDiZhi":"","txtBiaoXiangCaiZhi":"","txtChaoBiaoZhouQi":"双月","txtCreatedAt":"2017-08-14 09:59:47","txtGongDianJu":"博罗供电局","txtGongDianSuo":"福田供电所","txtJiChaoGongZuoLeiXing":"集抄改造用户","txtJiLiangDianChaoBiaoFangShi":"远程低压遥抄","txtKanChaNanDu":"重点勘查","txtKanChaYongHuZhuangKuang":"","txtKuiXianMingCheng":"10kV山吓线F20","txtSheBeiLeiXing":"RS-485接口单相电子式电能表","txtSheBeiXingHao":"DDSF284","txtShengChanChangJia":"华立仪表集团股份有限公司","txtShiFouGengHuanBiaoXiang":"","txtShiFouGengHuanDianBiao":"否","txtShiFouJiChaoFuGai":"是","txtShiFouZhengTiGengHuan":"","txtShiFouZhengTiQianYi":"","txtTaiQuLeiXing":"小区","txtTaiQuMingCheng":"惠州市中港城实业有限公司(公变)#1","txtUpdatedAt":"","txtUserAddName":"余思敏","txtUserModifyName":"","txtYiChangQingKuang":"台区户号一对多","txtYingXiaoYongHuZhuangKuang":"运行","txtYongDianLeiBie":"居民生活","txtYongDianLiangQingKuang":"一年及以上无电量","txtYongHuLeiBie":"公变客户","txtYxGengHuanDianBiao":"","txtZuiJinChaoBiaoFangShi":"远程低压遥抄","userAddId":"62d7f995-fbe2-405a-936f-cb1d67215fdc","userModifyId":"0","version":0,"yiChangQingKuang":4,"yiNianHuDianLiang":"0","yingXiaoYongHuZhuangKuang":1,"yongDianDiZhi":"广东省惠州市博罗县福田镇中港城1栋204","yongDianLeiBie":4,"yongDianLiangQingKuang":3,"yongHuBianHao":"0313040011284156","yongHuHeTongRongLiang":"6","yongHuLeiBie":1,"yongHuMingCheng":"杨正恩","yxGengHuanDianBiao":2,"ziChanBianHao":"1310701035977","zongHeBeiLv":"1","zuiJinChaoBiaoFangShi":5,"zuiJinChaoBiaoShiJian":"2017-06-01 12:00:00"},{"anZhuangRiQi":"2016-01-30 12:00:00","banNianHuDianLiang":"0","bianYaQiBianHao":"1111111111427822","bianYaQiEDingRongLiang":"800","bianYaQiMingCheng":"惠州市中港城实业有限公司(公变)","biaoXiangCaiZhi":0,"chanPinId":"c6e47907-2c94-4f7d-b3ea-103b3f50a0f1","chaoBiaoQuDuanBianHao":"中港城1号公变","chaoBiaoQuDuanShuLiang":0,"chaoBiaoXuHao":"141","chaoBiaoYuan":"李春杨","chaoBiaoZhouQi":4,"chuChangRiQi":"2014-10-16 12:00:00","createdAt":"2017-08-14 09:59:47","gongDianJuId":"34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17","gongDianSuoId":"21bc1159-93a5-130f-1ba1-c247ea5fe840","id":"c15e2d9a-10eb-4ccd-a3e7-53fb75928f41","jiChaoGongZuoLeiXing":2,"jiLiangDianBianHao":"1111111112894842","jiLiangDianChaoBiaoFangShi":5,"jiZhongQiZiChanHao":"","kanChaNanDu":2,"kanChaYongHuZhuangKuang":0,"kcGengHuanDianBiao":0,"kuiXianId":"6f9e786d-d82d-a7fa-bd6f-bb96134cfd53","liHuRiQi":"2016-02-02 12:00:00","lianXiDianHua":"13922796905","piCiHao":"201411252025","sheBeiLeiXing":1,"shiFouGengHuanBiaoXiang":0,"shiFouGengHuanDianBiao":2,"shiFouJiChaoFuGai":1,"shiFouZhengTiGengHuan":0,"shiFouZhengTiQianYi":0,"status":0,"taiQuBianHao":"0313040002464901","taiQuId":"23f69837-d2c8-4c12-385c-cbf0c7721a1b","taiQuLeiXing":1,"tongXunDiZhi":"","txtBiaoXiangCaiZhi":"","txtChaoBiaoZhouQi":"双月","txtCreatedAt":"2017-08-14 09:59:47","txtGongDianJu":"博罗供电局","txtGongDianSuo":"福田供电所","txtJiChaoGongZuoLeiXing":"集抄改造用户","txtJiLiangDianChaoBiaoFangShi":"远程低压遥抄","txtKanChaNanDu":"重点勘查","txtKanChaYongHuZhuangKuang":"","txtKuiXianMingCheng":"10kV山吓线F20","txtSheBeiLeiXing":"RS-485接口单相电子式电能表","txtSheBeiXingHao":"DDSF284","txtShengChanChangJia":"华立仪表集团股份有限公司","txtShiFouGengHuanBiaoXiang":"","txtShiFouGengHuanDianBiao":"否","txtShiFouJiChaoFuGai":"是","txtShiFouZhengTiGengHuan":"","txtShiFouZhengTiQianYi":"","txtTaiQuLeiXing":"小区","txtTaiQuMingCheng":"惠州市中港城实业有限公司(公变)#1","txtUpdatedAt":"","txtUserAddName":"余思敏","txtUserModifyName":"","txtYiChangQingKuang":"台区户号一对多","txtYingXiaoYongHuZhuangKuang":"运行","txtYongDianLeiBie":"居民生活","txtYongDianLiangQingKuang":"一年及以上无电量","txtYongHuLeiBie":"公变客户","txtYxGengHuanDianBiao":"","txtZuiJinChaoBiaoFangShi":"远程低压遥抄","userAddId":"62d7f995-fbe2-405a-936f-cb1d67215fdc","userModifyId":"0","version":0,"yiChangQingKuang":4,"yiNianHuDianLiang":"0","yingXiaoYongHuZhuangKuang":1,"yongDianDiZhi":"广东省惠州市博罗县福田镇中港城1栋404","yongDianLeiBie":4,"yongDianLiangQingKuang":3,"yongHuBianHao":"0313040011284231","yongHuHeTongRongLiang":"6","yongHuLeiBie":1,"yongHuMingCheng":"袁柳","yxGengHuanDianBiao":2,"ziChanBianHao":"1310701035987","zongHeBeiLv":"1","zuiJinChaoBiaoFangShi":5,"zuiJinChaoBiaoShiJian":"2017-06-01 12:00:00"},{"anZhuangRiQi":"2016-01-30 12:00:00","banNianHuDianLiang":"0","bianYaQiBianHao":"1111111111427822","bianYaQiEDingRongLiang":"800","bianYaQiMingCheng":"惠州市中港城实业有限公司(公变)","biaoXiangCaiZhi":0,"chanPinId":"c6e47907-2c94-4f7d-b3ea-103b3f50a0f1","chaoBiaoQuDuanBianHao":"中港城1号公变","chaoBiaoQuDuanShuLiang":0,"chaoBiaoXuHao":"134","chaoBiaoYuan":"李春杨","chaoBiaoZhouQi":4,"chuChangRiQi":"2014-10-16 12:00:00","createdAt":"2017-08-14 09:59:48","gongDianJuId":"34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17","gongDianSuoId":"21bc1159-93a5-130f-1ba1-c247ea5fe840","id":"a310b770-811b-4737-9cd7-953c1cb66670","jiChaoGongZuoLeiXing":2,"jiLiangDianBianHao":"1111111112894835","jiLiangDianChaoBiaoFangShi":5,"jiZhongQiZiChanHao":"","kanChaNanDu":2,"kanChaYongHuZhuangKuang":0,"kcGengHuanDianBiao":0,"kuiXianId":"6f9e786d-d82d-a7fa-bd6f-bb96134cfd53","liHuRiQi":"2016-02-02 12:00:00","lianXiDianHua":"18817553108","piCiHao":"201411252025","sheBeiLeiXing":1,"shiFouGengHuanBiaoXiang":0,"shiFouGengHuanDianBiao":2,"shiFouJiChaoFuGai":1,"shiFouZhengTiGengHuan":0,"shiFouZhengTiQianYi":0,"status":0,"taiQuBianHao":"0313040002464901","taiQuId":"23f69837-d2c8-4c12-385c-cbf0c7721a1b","taiQuLeiXing":1,"tongXunDiZhi":"","txtBiaoXiangCaiZhi":"","txtChaoBiaoZhouQi":"双月","txtCreatedAt":"2017-08-14 09:59:48","txtGongDianJu":"博罗供电局","txtGongDianSuo":"福田供电所","txtJiChaoGongZuoLeiXing":"集抄改造用户","txtJiLiangDianChaoBiaoFangShi":"远程低压遥抄","txtKanChaNanDu":"重点勘查","txtKanChaYongHuZhuangKuang":"","txtKuiXianMingCheng":"10kV山吓线F20","txtSheBeiLeiXing":"RS-485接口单相电子式电能表","txtSheBeiXingHao":"DDSF284","txtShengChanChangJia":"华立仪表集团股份有限公司","txtShiFouGengHuanBiaoXiang":"","txtShiFouGengHuanDianBiao":"否","txtShiFouJiChaoFuGai":"是","txtShiFouZhengTiGengHuan":"","txtShiFouZhengTiQianYi":"","txtTaiQuLeiXing":"小区","txtTaiQuMingCheng":"惠州市中港城实业有限公司(公变)#1","txtUpdatedAt":"","txtUserAddName":"余思敏","txtUserModifyName":"","txtYiChangQingKuang":"台区户号一对多","txtYingXiaoYongHuZhuangKuang":"运行","txtYongDianLeiBie":"居民生活","txtYongDianLiangQingKuang":"一年及以上无电量","txtYongHuLeiBie":"公变客户","txtYxGengHuanDianBiao":"","txtZuiJinChaoBiaoFangShi":"远程低压遥抄","userAddId":"62d7f995-fbe2-405a-936f-cb1d67215fdc","userModifyId":"0","version":0,"yiChangQingKuang":4,"yiNianHuDianLiang":"0","yingXiaoYongHuZhuangKuang":1,"yongDianDiZhi":"广东省惠州市博罗县福田镇中港城1栋301","yongDianLeiBie":4,"yongDianLiangQingKuang":3,"yongHuBianHao":"0313040011284169","yongHuHeTongRongLiang":"6","yongHuLeiBie":1,"yongHuMingCheng":"肖金","yxGengHuanDianBiao":2,"ziChanBianHao":"1310701035980","zongHeBeiLv":"1","zuiJinChaoBiaoFangShi":5,"zuiJinChaoBiaoShiJian":"2017-06-01 12:00:00"},{"anZhuangRiQi":"2016-01-30 12:00:00","banNianHuDianLiang":"0","bianYaQiBianHao":"1111111111427822","bianYaQiEDingRongLiang":"800","bianYaQiMingCheng":"惠州市中港城实业有限公司(公变)","biaoXiangCaiZhi":0,"chanPinId":"c6e47907-2c94-4f7d-b3ea-103b3f50a0f1","chaoBiaoQuDuanBianHao":"中港城1号公变","chaoBiaoQuDuanShuLiang":0,"chaoBiaoXuHao":"130","chaoBiaoYuan":"李春杨","chaoBiaoZhouQi":4,"chuChangRiQi":"2014-10-16 12:00:00","createdAt":"2017-08-14 09:59:49","gongDianJuId":"34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17","gongDianSuoId":"21bc1159-93a5-130f-1ba1-c247ea5fe840","id":"8414908a-76ee-46bd-84a0-0659460f56f4","jiChaoGongZuoLeiXing":2,"jiLiangDianBianHao":"1111111112894830","jiLiangDianChaoBiaoFangShi":5,"jiZhongQiZiChanHao":"","kanChaNanDu":2,"kanChaYongHuZhuangKuang":0,"kcGengHuanDianBiao":0,"kuiXianId":"6f9e786d-d82d-a7fa-bd6f-bb96134cfd53","liHuRiQi":"2016-02-02 12:00:00","lianXiDianHua":"13544940089","piCiHao":"201411252025","sheBeiLeiXing":1,"shiFouGengHuanBiaoXiang":0,"shiFouGengHuanDianBiao":2,"shiFouJiChaoFuGai":1,"shiFouZhengTiGengHuan":0,"shiFouZhengTiQianYi":0,"status":0,"taiQuBianHao":"0313040002464901","taiQuId":"23f69837-d2c8-4c12-385c-cbf0c7721a1b","taiQuLeiXing":1,"tongXunDiZhi":"","txtBiaoXiangCaiZhi":"","txtChaoBiaoZhouQi":"双月","txtCreatedAt":"2017-08-14 09:59:49","txtGongDianJu":"博罗供电局","txtGongDianSuo":"福田供电所","txtJiChaoGongZuoLeiXing":"集抄改造用户","txtJiLiangDianChaoBiaoFangShi":"远程低压遥抄","txtKanChaNanDu":"重点勘查","txtKanChaYongHuZhuangKuang":"","txtKuiXianMingCheng":"10kV山吓线F20","txtSheBeiLeiXing":"RS-485接口单相电子式电能表","txtSheBeiXingHao":"DDSF284","txtShengChanChangJia":"华立仪表集团股份有限公司","txtShiFouGengHuanBiaoXiang":"","txtShiFouGengHuanDianBiao":"否","txtShiFouJiChaoFuGai":"是","txtShiFouZhengTiGengHuan":"","txtShiFouZhengTiQianYi":"","txtTaiQuLeiXing":"小区","txtTaiQuMingCheng":"惠州市中港城实业有限公司(公变)#1","txtUpdatedAt":"","txtUserAddName":"余思敏","txtUserModifyName":"","txtYiChangQingKuang":"台区户号一对多","txtYingXiaoYongHuZhuangKuang":"运行","txtYongDianLeiBie":"居民生活","txtYongDianLiangQingKuang":"一年及以上无电量","txtYongHuLeiBie":"公变客户","txtYxGengHuanDianBiao":"","txtZuiJinChaoBiaoFangShi":"远程低压遥抄","userAddId":"62d7f995-fbe2-405a-936f-cb1d67215fdc","userModifyId":"0","version":0,"yiChangQingKuang":4,"yiNianHuDianLiang":"0","yingXiaoYongHuZhuangKuang":1,"yongDianDiZhi":"广东省惠州市博罗县福田镇中港城1栋201","yongDianLeiBie":4,"yongDianLiangQingKuang":3,"yongHuBianHao":"0313040011284127","yongHuHeTongRongLiang":"6","yongHuLeiBie":1,"yongHuMingCheng":"郭万燕","yxGengHuanDianBiao":2,"ziChanBianHao":"1310701035978","zongHeBeiLv":"1","zuiJinChaoBiaoFangShi":5,"zuiJinChaoBiaoShiJian":"2017-06-01 12:00:00"},{"anZhuangRiQi":"2016-01-30 12:00:00","banNianHuDianLiang":"0","bianYaQiBianHao":"1111111111427822","bianYaQiEDingRongLiang":"800","bianYaQiMingCheng":"惠州市中港城实业有限公司(公变)","biaoXiangCaiZhi":0,"chanPinId":"c6e47907-2c94-4f7d-b3ea-103b3f50a0f1","chaoBiaoQuDuanBianHao":"中港城1号公变","chaoBiaoQuDuanShuLiang":0,"chaoBiaoXuHao":"135","chaoBiaoYuan":"李春杨","chaoBiaoZhouQi":4,"chuChangRiQi":"2014-10-23 12:00:00","createdAt":"2017-08-14 09:59:49","gongDianJuId":"34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17","gongDianSuoId":"21bc1159-93a5-130f-1ba1-c247ea5fe840","id":"e95634d1-a25f-40b6-9fdb-ceefc389cf66","jiChaoGongZuoLeiXing":2,"jiLiangDianBianHao":"1111111112894836","jiLiangDianChaoBiaoFangShi":5,"jiZhongQiZiChanHao":"","kanChaNanDu":2,"kanChaYongHuZhuangKuang":0,"kcGengHuanDianBiao":0,"kuiXianId":"6f9e786d-d82d-a7fa-bd6f-bb96134cfd53","liHuRiQi":"2016-02-02 12:00:00","lianXiDianHua":"13710458238","piCiHao":"201411252024","sheBeiLeiXing":1,"shiFouGengHuanBiaoXiang":0,"shiFouGengHuanDianBiao":2,"shiFouJiChaoFuGai":1,"shiFouZhengTiGengHuan":0,"shiFouZhengTiQianYi":0,"status":0,"taiQuBianHao":"0313040002464901","taiQuId":"23f69837-d2c8-4c12-385c-cbf0c7721a1b","taiQuLeiXing":1,"tongXunDiZhi":"","txtBiaoXiangCaiZhi":"","txtChaoBiaoZhouQi":"双月","txtCreatedAt":"2017-08-14 09:59:49","txtGongDianJu":"博罗供电局","txtGongDianSuo":"福田供电所","txtJiChaoGongZuoLeiXing":"集抄改造用户","txtJiLiangDianChaoBiaoFangShi":"远程低压遥抄","txtKanChaNanDu":"重点勘查","txtKanChaYongHuZhuangKuang":"","txtKuiXianMingCheng":"10kV山吓线F20","txtSheBeiLeiXing":"RS-485接口单相电子式电能表","txtSheBeiXingHao":"DDSF284","txtShengChanChangJia":"华立仪表集团股份有限公司","txtShiFouGengHuanBiaoXiang":"","txtShiFouGengHuanDianBiao":"否","txtShiFouJiChaoFuGai":"是","txtShiFouZhengTiGengHuan":"","txtShiFouZhengTiQianYi":"","txtTaiQuLeiXing":"小区","txtTaiQuMingCheng":"惠州市中港城实业有限公司(公变)#1","txtUpdatedAt":"","txtUserAddName":"余思敏","txtUserModifyName":"","txtYiChangQingKuang":"台区户号一对多","txtYingXiaoYongHuZhuangKuang":"运行","txtYongDianLeiBie":"居民生活","txtYongDianLiangQingKuang":"一年及以上无电量","txtYongHuLeiBie":"公变客户","txtYxGengHuanDianBiao":"","txtZuiJinChaoBiaoFangShi":"远程低压遥抄","userAddId":"62d7f995-fbe2-405a-936f-cb1d67215fdc","userModifyId":"0","version":0,"yiChangQingKuang":4,"yiNianHuDianLiang":"0","yingXiaoYongHuZhuangKuang":1,"yongDianDiZhi":"广东省惠州市博罗县福田镇中港城1栋302","yongDianLeiBie":4,"yongDianLiangQingKuang":3,"yongHuBianHao":"0313040011284172","yongHuHeTongRongLiang":"6","yongHuLeiBie":1,"yongHuMingCheng":"熊育光","yxGengHuanDianBiao":2,"ziChanBianHao":"1310701020671","zongHeBeiLv":"1","zuiJinChaoBiaoFangShi":5,"zuiJinChaoBiaoShiJian":"2017-06-01 12:00:00"},{"anZhuangRiQi":"2017-03-27 12:00:00","banNianHuDianLiang":"0","bianYaQiBianHao":"1111111111427822","bianYaQiEDingRongLiang":"800","bianYaQiMingCheng":"惠州市中港城实业有限公司(公变)","biaoXiangCaiZhi":0,"chanPinId":"28edc063-74cc-4a7f-b375-35321ce609ca","chaoBiaoQuDuanBianHao":"中港城1号公变","chaoBiaoQuDuanShuLiang":0,"chaoBiaoXuHao":"131","chaoBiaoYuan":"李春杨","chaoBiaoZhouQi":4,"chuChangRiQi":"2016-10-01 12:00:00","createdAt":"2017-08-14 09:59:50","gongDianJuId":"34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17","gongDianSuoId":"21bc1159-93a5-130f-1ba1-c247ea5fe840","id":"0fd91259-fa8d-46a3-a2c0-bc7c9905ddda","jiChaoGongZuoLeiXing":3,"jiLiangDianBianHao":"1111111112894831","jiLiangDianChaoBiaoFangShi":5,"jiZhongQiZiChanHao":"","kanChaNanDu":2,"kanChaYongHuZhuangKuang":0,"kcGengHuanDianBiao":0,"kuiXianId":"6f9e786d-d82d-a7fa-bd6f-bb96134cfd53","liHuRiQi":"2016-02-02 12:00:00","lianXiDianHua":"15920351855","piCiHao":"03201610170061","sheBeiLeiXing":2,"shiFouGengHuanBiaoXiang":0,"shiFouGengHuanDianBiao":2,"shiFouJiChaoFuGai":1,"shiFouZhengTiGengHuan":0,"shiFouZhengTiQianYi":0,"status":0,"taiQuBianHao":"0313040002464901","taiQuId":"23f69837-d2c8-4c12-385c-cbf0c7721a1b","taiQuLeiXing":1,"tongXunDiZhi":"","txtBiaoXiangCaiZhi":"","txtChaoBiaoZhouQi":"双月","txtCreatedAt":"2017-08-14 09:59:50","txtGongDianJu":"博罗供电局","txtGongDianSuo":"福田供电所","txtJiChaoGongZuoLeiXing":"集抄维护用户","txtJiLiangDianChaoBiaoFangShi":"远程低压遥抄","txtKanChaNanDu":"重点勘查","txtKanChaYongHuZhuangKuang":"","txtKuiXianMingCheng":"10kV山吓线F20","txtSheBeiLeiXing":"单相电子式电能表(费控)","txtSheBeiXingHao":"DDSK217-J","txtShengChanChangJia":"浙江晨泰科技股份有限公司","txtShiFouGengHuanBiaoXiang":"","txtShiFouGengHuanDianBiao":"否","txtShiFouJiChaoFuGai":"是","txtShiFouZhengTiGengHuan":"","txtShiFouZhengTiQianYi":"","txtTaiQuLeiXing":"小区","txtTaiQuMingCheng":"惠州市中港城实业有限公司(公变)#1","txtUpdatedAt":"","txtUserAddName":"余思敏","txtUserModifyName":"","txtYiChangQingKuang":"台区户号一对多","txtYingXiaoYongHuZhuangKuang":"运行","txtYongDianLeiBie":"居民生活","txtYongDianLiangQingKuang":"一年及以上无电量","txtYongHuLeiBie":"公变客户","txtYxGengHuanDianBiao":"","txtZuiJinChaoBiaoFangShi":"远程低压遥抄","userAddId":"62d7f995-fbe2-405a-936f-cb1d67215fdc","userModifyId":"0","version":0,"yiChangQingKuang":4,"yiNianHuDianLiang":"0","yingXiaoYongHuZhuangKuang":1,"yongDianDiZhi":"广东省惠州市博罗县福田镇中港城1栋202","yongDianLeiBie":4,"yongDianLiangQingKuang":3,"yongHuBianHao":"0313040011284130","yongHuHeTongRongLiang":"6","yongHuLeiBie":1,"yongHuMingCheng":"梁碧容","yxGengHuanDianBiao":2,"ziChanBianHao":"03131DY00000001601249243","zongHeBeiLv":"1","zuiJinChaoBiaoFangShi":5,"zuiJinChaoBiaoShiJian":"2017-06-01 12:00:00"},{"anZhuangRiQi":"2016-01-30 12:00:00","banNianHuDianLiang":"0","bianYaQiBianHao":"1111111111427822","bianYaQiEDingRongLiang":"800","bianYaQiMingCheng":"惠州市中港城实业有限公司(公变)","biaoXiangCaiZhi":0,"chanPinId":"c6e47907-2c94-4f7d-b3ea-103b3f50a0f1","chaoBiaoQuDuanBianHao":"中港城1号公变","chaoBiaoQuDuanShuLiang":0,"chaoBiaoXuHao":"142","chaoBiaoYuan":"李春杨","chaoBiaoZhouQi":4,"chuChangRiQi":"2014-10-23 12:00:00","createdAt":"2017-08-14 09:59:50","gongDianJuId":"34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17","gongDianSuoId":"21bc1159-93a5-130f-1ba1-c247ea5fe840","id":"c51cb4a1-8572-4d05-8f38-bf4c7bdfe0fd","jiChaoGongZuoLeiXing":2,"jiLiangDianBianHao":"1111111112894843","jiLiangDianChaoBiaoFangShi":5,"jiZhongQiZiChanHao":"","kanChaNanDu":2,"kanChaYongHuZhuangKuang":0,"kcGengHuanDianBiao":0,"kuiXianId":"6f9e786d-d82d-a7fa-bd6f-bb96134cfd53","liHuRiQi":"2016-02-02 12:00:00","lianXiDianHua":"15218880658","piCiHao":"201411252024","sheBeiLeiXing":1,"shiFouGengHuanBiaoXiang":0,"shiFouGengHuanDianBiao":2,"shiFouJiChaoFuGai":1,"shiFouZhengTiGengHuan":0,"shiFouZhengTiQianYi":0,"status":0,"taiQuBianHao":"0313040002464901","taiQuId":"23f69837-d2c8-4c12-385c-cbf0c7721a1b","taiQuLeiXing":1,"tongXunDiZhi":"","txtBiaoXiangCaiZhi":"","txtChaoBiaoZhouQi":"双月","txtCreatedAt":"2017-08-14 09:59:50","txtGongDianJu":"博罗供电局","txtGongDianSuo":"福田供电所","txtJiChaoGongZuoLeiXing":"集抄改造用户","txtJiLiangDianChaoBiaoFangShi":"远程低压遥抄","txtKanChaNanDu":"重点勘查","txtKanChaYongHuZhuangKuang":"","txtKuiXianMingCheng":"10kV山吓线F20","txtSheBeiLeiXing":"RS-485接口单相电子式电能表","txtSheBeiXingHao":"DDSF284","txtShengChanChangJia":"华立仪表集团股份有限公司","txtShiFouGengHuanBiaoXiang":"","txtShiFouGengHuanDianBiao":"否","txtShiFouJiChaoFuGai":"是","txtShiFouZhengTiGengHuan":"","txtShiFouZhengTiQianYi":"","txtTaiQuLeiXing":"小区","txtTaiQuMingCheng":"惠州市中港城实业有限公司(公变)#1","txtUpdatedAt":"","txtUserAddName":"余思敏","txtUserModifyName":"","txtYiChangQingKuang":"台区户号一对多","txtYingXiaoYongHuZhuangKuang":"运行","txtYongDianLeiBie":"居民生活","txtYongDianLiangQingKuang":"一年及以上无电量","txtYongHuLeiBie":"公变客户","txtYxGengHuanDianBiao":"","txtZuiJinChaoBiaoFangShi":"远程低压遥抄","userAddId":"62d7f995-fbe2-405a-936f-cb1d67215fdc","userModifyId":"0","version":0,"yiChangQingKuang":4,"yiNianHuDianLiang":"0","yingXiaoYongHuZhuangKuang":1,"yongDianDiZhi":"广东省惠州市博罗县福田镇中港城1栋501","yongDianLeiBie":4,"yongDianLiangQingKuang":3,"yongHuBianHao":"0313040011284244","yongHuHeTongRongLiang":"6","yongHuLeiBie":1,"yongHuMingCheng":"吴志强","yxGengHuanDianBiao":2,"ziChanBianHao":"1310701020668","zongHeBeiLv":"1","zuiJinChaoBiaoFangShi":5,"zuiJinChaoBiaoShiJian":"2017-06-01 12:00:00"},{"anZhuangRiQi":"2016-01-30 12:00:00","banNianHuDianLiang":"0","bianYaQiBianHao":"1111111111427822","bianYaQiEDingRongLiang":"800","bianYaQiMingCheng":"惠州市中港城实业有限公司(公变)","biaoXiangCaiZhi":0,"chanPinId":"c6e47907-2c94-4f7d-b3ea-103b3f50a0f1","chaoBiaoQuDuanBianHao":"中港城1号公变","chaoBiaoQuDuanShuLiang":0,"chaoBiaoXuHao":"132","chaoBiaoYuan":"李春杨","chaoBiaoZhouQi":4,"chuChangRiQi":"2014-10-23 12:00:00","createdAt":"2017-08-14 09:59:51","gongDianJuId":"34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17","gongDianSuoId":"21bc1159-93a5-130f-1ba1-c247ea5fe840","id":"6a47255b-79ec-440a-a4ec-53cf470a5129","jiChaoGongZuoLeiXing":2,"jiLiangDianBianHao":"1111111112894832","jiLiangDianChaoBiaoFangShi":5,"jiZhongQiZiChanHao":"","kanChaNanDu":2,"kanChaYongHuZhuangKuang":0,"kcGengHuanDianBiao":0,"kuiXianId":"6f9e786d-d82d-a7fa-bd6f-bb96134cfd53","liHuRiQi":"2016-02-02 12:00:00","lianXiDianHua":"","piCiHao":"201411252024","sheBeiLeiXing":1,"shiFouGengHuanBiaoXiang":0,"shiFouGengHuanDianBiao":2,"shiFouJiChaoFuGai":1,"shiFouZhengTiGengHuan":0,"shiFouZhengTiQianYi":0,"status":0,"taiQuBianHao":"0313040002464901","taiQuId":"23f69837-d2c8-4c12-385c-cbf0c7721a1b","taiQuLeiXing":1,"tongXunDiZhi":"","txtBiaoXiangCaiZhi":"","txtChaoBiaoZhouQi":"双月","txtCreatedAt":"2017-08-14 09:59:51","txtGongDianJu":"博罗供电局","txtGongDianSuo":"福田供电所","txtJiChaoGongZuoLeiXing":"集抄改造用户","txtJiLiangDianChaoBiaoFangShi":"远程低压遥抄","txtKanChaNanDu":"重点勘查","txtKanChaYongHuZhuangKuang":"","txtKuiXianMingCheng":"10kV山吓线F20","txtSheBeiLeiXing":"RS-485接口单相电子式电能表","txtSheBeiXingHao":"DDSF284","txtShengChanChangJia":"华立仪表集团股份有限公司","txtShiFouGengHuanBiaoXiang":"","txtShiFouGengHuanDianBiao":"否","txtShiFouJiChaoFuGai":"是","txtShiFouZhengTiGengHuan":"","txtShiFouZhengTiQianYi":"","txtTaiQuLeiXing":"小区","txtTaiQuMingCheng":"惠州市中港城实业有限公司(公变)#1","txtUpdatedAt":"","txtUserAddName":"余思敏","txtUserModifyName":"","txtYiChangQingKuang":"台区户号一对多","txtYingXiaoYongHuZhuangKuang":"运行","txtYongDianLeiBie":"居民生活","txtYongDianLiangQingKuang":"一年及以上无电量","txtYongHuLeiBie":"公变客户","txtYxGengHuanDianBiao":"","txtZuiJinChaoBiaoFangShi":"远程低压遥抄","userAddId":"62d7f995-fbe2-405a-936f-cb1d67215fdc","userModifyId":"0","version":0,"yiChangQingKuang":4,"yiNianHuDianLiang":"0","yingXiaoYongHuZhuangKuang":1,"yongDianDiZhi":"广东省惠州市博罗县福田镇中港城1栋203","yongDianLeiBie":4,"yongDianLiangQingKuang":3,"yongHuBianHao":"0313040011284143","yongHuHeTongRongLiang":"6","yongHuLeiBie":1,"yongHuMingCheng":"惠州市中港城实业有限公司","yxGengHuanDianBiao":2,"ziChanBianHao":"1310701020667","zongHeBeiLv":"1","zuiJinChaoBiaoFangShi":5,"zuiJinChaoBiaoShiJian":"2017-06-01 12:00:00"},{"anZhuangRiQi":"2016-01-30 12:00:00","banNianHuDianLiang":"0","bianYaQiBianHao":"1111111111427822","bianYaQiEDingRongLiang":"800","bianYaQiMingCheng":"惠州市中港城实业有限公司(公变)","biaoXiangCaiZhi":0,"chanPinId":"c6e47907-2c94-4f7d-b3ea-103b3f50a0f1","chaoBiaoQuDuanBianHao":"中港城1号公变","chaoBiaoQuDuanShuLiang":0,"chaoBiaoXuHao":"140","chaoBiaoYuan":"李春杨","chaoBiaoZhouQi":4,"chuChangRiQi":"2014-10-16 12:00:00","createdAt":"2017-08-14 09:59:51","gongDianJuId":"34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17","gongDianSuoId":"21bc1159-93a5-130f-1ba1-c247ea5fe840","id":"c58843fc-3cee-425e-9ce9-2ff7fbd71639","jiChaoGongZuoLeiXing":2,"jiLiangDianBianHao":"1111111112894841","jiLiangDianChaoBiaoFangShi":5,"jiZhongQiZiChanHao":"","kanChaNanDu":2,"kanChaYongHuZhuangKuang":0,"kcGengHuanDianBiao":0,"kuiXianId":"6f9e786d-d82d-a7fa-bd6f-bb96134cfd53","liHuRiQi":"2016-02-02 12:00:00","lianXiDianHua":"13824457268","piCiHao":"201411252025","sheBeiLeiXing":1,"shiFouGengHuanBiaoXiang":0,"shiFouGengHuanDianBiao":2,"shiFouJiChaoFuGai":1,"shiFouZhengTiGengHuan":0,"shiFouZhengTiQianYi":0,"status":0,"taiQuBianHao":"0313040002464901","taiQuId":"23f69837-d2c8-4c12-385c-cbf0c7721a1b","taiQuLeiXing":1,"tongXunDiZhi":"","txtBiaoXiangCaiZhi":"","txtChaoBiaoZhouQi":"双月","txtCreatedAt":"2017-08-14 09:59:51","txtGongDianJu":"博罗供电局","txtGongDianSuo":"福田供电所","txtJiChaoGongZuoLeiXing":"集抄改造用户","txtJiLiangDianChaoBiaoFangShi":"远程低压遥抄","txtKanChaNanDu":"重点勘查","txtKanChaYongHuZhuangKuang":"","txtKuiXianMingCheng":"10kV山吓线F20","txtSheBeiLeiXing":"RS-485接口单相电子式电能表","txtSheBeiXingHao":"DDSF284","txtShengChanChangJia":"华立仪表集团股份有限公司","txtShiFouGengHuanBiaoXiang":"","txtShiFouGengHuanDianBiao":"否","txtShiFouJiChaoFuGai":"是","txtShiFouZhengTiGengHuan":"","txtShiFouZhengTiQianYi":"","txtTaiQuLeiXing":"小区","txtTaiQuMingCheng":"惠州市中港城实业有限公司(公变)#1","txtUpdatedAt":"","txtUserAddName":"余思敏","txtUserModifyName":"","txtYiChangQingKuang":"台区户号一对多","txtYingXiaoYongHuZhuangKuang":"运行","txtYongDianLeiBie":"居民生活","txtYongDianLiangQingKuang":"一年及以上无电量","txtYongHuLeiBie":"公变客户","txtYxGengHuanDianBiao":"","txtZuiJinChaoBiaoFangShi":"远程低压遥抄","userAddId":"62d7f995-fbe2-405a-936f-cb1d67215fdc","userModifyId":"0","version":0,"yiChangQingKuang":4,"yiNianHuDianLiang":"0","yingXiaoYongHuZhuangKuang":1,"yongDianDiZhi":"广东省惠州市博罗县福田镇中港城1栋403","yongDianLeiBie":4,"yongDianLiangQingKuang":3,"yongHuBianHao":"0313040011284228","yongHuHeTongRongLiang":"6","yongHuLeiBie":1,"yongHuMingCheng":"邓奎","yxGengHuanDianBiao":2,"ziChanBianHao":"1310701035986","zongHeBeiLv":"1","zuiJinChaoBiaoFangShi":5,"zuiJinChaoBiaoShiJian":"2017-06-01 12:00:00"}],"navigatePages":8,"navigatepageNums":[1,2,3,4,5,6,7,8],"next":2,"pageIndex":1,"pageSize":10,"pages":20,"preview":0,"size":10,"startRow":1,"total":192}
     * message :
     * status : 1
     */

    private DataBean data;
    private String message;
    private int status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * endRow : 10
         * first : 1
         * hasNext : true
         * hasPrevious : false
         * isFirst : true
         * isLast : false
         * last : 8
         * list : [{"anZhuangRiQi":"2016-01-30 12:00:00","banNianHuDianLiang":"1206","bianYaQiBianHao":"1111111111427822","bianYaQiEDingRongLiang":"800","bianYaQiMingCheng":"惠州市中港城实业有限公司(公变)","biaoXiangCaiZhi":0,"chanPinId":"c6e47907-2c94-4f7d-b3ea-103b3f50a0f1","chaoBiaoQuDuanBianHao":"中港城1号公变","chaoBiaoQuDuanShuLiang":0,"chaoBiaoXuHao":"138","chaoBiaoYuan":"李春杨","chaoBiaoZhouQi":4,"chuChangRiQi":"2014-10-16 12:00:00","createdAt":"2017-08-14 09:59:46","gongDianJuId":"34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17","gongDianSuoId":"21bc1159-93a5-130f-1ba1-c247ea5fe840","id":"e41218db-5c2d-4b29-af59-607b04e931d8","jiChaoGongZuoLeiXing":2,"jiLiangDianBianHao":"1111111112894839","jiLiangDianChaoBiaoFangShi":5,"jiZhongQiZiChanHao":"","kanChaNanDu":1,"kanChaYongHuZhuangKuang":0,"kcGengHuanDianBiao":0,"kuiXianId":"6f9e786d-d82d-a7fa-bd6f-bb96134cfd53","liHuRiQi":"2016-02-02 12:00:00","lianXiDianHua":"13528024465","piCiHao":"201411252025","sheBeiLeiXing":1,"shiFouGengHuanBiaoXiang":0,"shiFouGengHuanDianBiao":2,"shiFouJiChaoFuGai":1,"shiFouZhengTiGengHuan":0,"shiFouZhengTiQianYi":0,"status":0,"taiQuBianHao":"0313040002464901","taiQuId":"23f69837-d2c8-4c12-385c-cbf0c7721a1b","taiQuLeiXing":1,"tongXunDiZhi":"","txtBiaoXiangCaiZhi":"","txtChaoBiaoZhouQi":"双月","txtCreatedAt":"2017-08-14 09:59:46","txtGongDianJu":"博罗供电局","txtGongDianSuo":"福田供电所","txtJiChaoGongZuoLeiXing":"集抄改造用户","txtJiLiangDianChaoBiaoFangShi":"远程低压遥抄","txtKanChaNanDu":"正常勘查","txtKanChaYongHuZhuangKuang":"","txtKuiXianMingCheng":"10kV山吓线F20","txtSheBeiLeiXing":"RS-485接口单相电子式电能表","txtSheBeiXingHao":"DDSF284","txtShengChanChangJia":"华立仪表集团股份有限公司","txtShiFouGengHuanBiaoXiang":"","txtShiFouGengHuanDianBiao":"否","txtShiFouJiChaoFuGai":"是","txtShiFouZhengTiGengHuan":"","txtShiFouZhengTiQianYi":"","txtTaiQuLeiXing":"小区","txtTaiQuMingCheng":"惠州市中港城实业有限公司(公变)#1","txtUpdatedAt":"","txtUserAddName":"余思敏","txtUserModifyName":"","txtYiChangQingKuang":"台区户号一对多","txtYingXiaoYongHuZhuangKuang":"运行","txtYongDianLeiBie":"居民生活","txtYongDianLiangQingKuang":"正常","txtYongHuLeiBie":"公变客户","txtYxGengHuanDianBiao":"","txtZuiJinChaoBiaoFangShi":"远程低压遥抄","userAddId":"62d7f995-fbe2-405a-936f-cb1d67215fdc","userModifyId":"0","version":0,"yiChangQingKuang":4,"yiNianHuDianLiang":"1930","yingXiaoYongHuZhuangKuang":1,"yongDianDiZhi":"广东省惠州市博罗县福田镇中港城1栋401","yongDianLeiBie":4,"yongDianLiangQingKuang":1,"yongHuBianHao":"0313040011284202","yongHuHeTongRongLiang":"6","yongHuLeiBie":1,"yongHuMingCheng":"高殿龙","yxGengHuanDianBiao":2,"ziChanBianHao":"1310701035985","zongHeBeiLv":"1","zuiJinChaoBiaoFangShi":5,"zuiJinChaoBiaoShiJian":"2017-06-01 12:00:00"},{"anZhuangRiQi":"2016-01-30 12:00:00","banNianHuDianLiang":"0","bianYaQiBianHao":"1111111111427822","bianYaQiEDingRongLiang":"800","bianYaQiMingCheng":"惠州市中港城实业有限公司(公变)","biaoXiangCaiZhi":0,"chanPinId":"c6e47907-2c94-4f7d-b3ea-103b3f50a0f1","chaoBiaoQuDuanBianHao":"中港城1号公变","chaoBiaoQuDuanShuLiang":0,"chaoBiaoXuHao":"133","chaoBiaoYuan":"李春杨","chaoBiaoZhouQi":4,"chuChangRiQi":"2014-10-16 12:00:00","createdAt":"2017-08-14 09:59:47","gongDianJuId":"34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17","gongDianSuoId":"21bc1159-93a5-130f-1ba1-c247ea5fe840","id":"1bbc4340-bb4a-4c4b-83be-978b7de73c9c","jiChaoGongZuoLeiXing":2,"jiLiangDianBianHao":"1111111112894834","jiLiangDianChaoBiaoFangShi":5,"jiZhongQiZiChanHao":"","kanChaNanDu":2,"kanChaYongHuZhuangKuang":0,"kcGengHuanDianBiao":0,"kuiXianId":"6f9e786d-d82d-a7fa-bd6f-bb96134cfd53","liHuRiQi":"2016-02-02 12:00:00","lianXiDianHua":"13662428351","piCiHao":"201411252025","sheBeiLeiXing":1,"shiFouGengHuanBiaoXiang":0,"shiFouGengHuanDianBiao":2,"shiFouJiChaoFuGai":1,"shiFouZhengTiGengHuan":0,"shiFouZhengTiQianYi":0,"status":0,"taiQuBianHao":"0313040002464901","taiQuId":"23f69837-d2c8-4c12-385c-cbf0c7721a1b","taiQuLeiXing":1,"tongXunDiZhi":"","txtBiaoXiangCaiZhi":"","txtChaoBiaoZhouQi":"双月","txtCreatedAt":"2017-08-14 09:59:47","txtGongDianJu":"博罗供电局","txtGongDianSuo":"福田供电所","txtJiChaoGongZuoLeiXing":"集抄改造用户","txtJiLiangDianChaoBiaoFangShi":"远程低压遥抄","txtKanChaNanDu":"重点勘查","txtKanChaYongHuZhuangKuang":"","txtKuiXianMingCheng":"10kV山吓线F20","txtSheBeiLeiXing":"RS-485接口单相电子式电能表","txtSheBeiXingHao":"DDSF284","txtShengChanChangJia":"华立仪表集团股份有限公司","txtShiFouGengHuanBiaoXiang":"","txtShiFouGengHuanDianBiao":"否","txtShiFouJiChaoFuGai":"是","txtShiFouZhengTiGengHuan":"","txtShiFouZhengTiQianYi":"","txtTaiQuLeiXing":"小区","txtTaiQuMingCheng":"惠州市中港城实业有限公司(公变)#1","txtUpdatedAt":"","txtUserAddName":"余思敏","txtUserModifyName":"","txtYiChangQingKuang":"台区户号一对多","txtYingXiaoYongHuZhuangKuang":"运行","txtYongDianLeiBie":"居民生活","txtYongDianLiangQingKuang":"一年及以上无电量","txtYongHuLeiBie":"公变客户","txtYxGengHuanDianBiao":"","txtZuiJinChaoBiaoFangShi":"远程低压遥抄","userAddId":"62d7f995-fbe2-405a-936f-cb1d67215fdc","userModifyId":"0","version":0,"yiChangQingKuang":4,"yiNianHuDianLiang":"0","yingXiaoYongHuZhuangKuang":1,"yongDianDiZhi":"广东省惠州市博罗县福田镇中港城1栋204","yongDianLeiBie":4,"yongDianLiangQingKuang":3,"yongHuBianHao":"0313040011284156","yongHuHeTongRongLiang":"6","yongHuLeiBie":1,"yongHuMingCheng":"杨正恩","yxGengHuanDianBiao":2,"ziChanBianHao":"1310701035977","zongHeBeiLv":"1","zuiJinChaoBiaoFangShi":5,"zuiJinChaoBiaoShiJian":"2017-06-01 12:00:00"},{"anZhuangRiQi":"2016-01-30 12:00:00","banNianHuDianLiang":"0","bianYaQiBianHao":"1111111111427822","bianYaQiEDingRongLiang":"800","bianYaQiMingCheng":"惠州市中港城实业有限公司(公变)","biaoXiangCaiZhi":0,"chanPinId":"c6e47907-2c94-4f7d-b3ea-103b3f50a0f1","chaoBiaoQuDuanBianHao":"中港城1号公变","chaoBiaoQuDuanShuLiang":0,"chaoBiaoXuHao":"141","chaoBiaoYuan":"李春杨","chaoBiaoZhouQi":4,"chuChangRiQi":"2014-10-16 12:00:00","createdAt":"2017-08-14 09:59:47","gongDianJuId":"34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17","gongDianSuoId":"21bc1159-93a5-130f-1ba1-c247ea5fe840","id":"c15e2d9a-10eb-4ccd-a3e7-53fb75928f41","jiChaoGongZuoLeiXing":2,"jiLiangDianBianHao":"1111111112894842","jiLiangDianChaoBiaoFangShi":5,"jiZhongQiZiChanHao":"","kanChaNanDu":2,"kanChaYongHuZhuangKuang":0,"kcGengHuanDianBiao":0,"kuiXianId":"6f9e786d-d82d-a7fa-bd6f-bb96134cfd53","liHuRiQi":"2016-02-02 12:00:00","lianXiDianHua":"13922796905","piCiHao":"201411252025","sheBeiLeiXing":1,"shiFouGengHuanBiaoXiang":0,"shiFouGengHuanDianBiao":2,"shiFouJiChaoFuGai":1,"shiFouZhengTiGengHuan":0,"shiFouZhengTiQianYi":0,"status":0,"taiQuBianHao":"0313040002464901","taiQuId":"23f69837-d2c8-4c12-385c-cbf0c7721a1b","taiQuLeiXing":1,"tongXunDiZhi":"","txtBiaoXiangCaiZhi":"","txtChaoBiaoZhouQi":"双月","txtCreatedAt":"2017-08-14 09:59:47","txtGongDianJu":"博罗供电局","txtGongDianSuo":"福田供电所","txtJiChaoGongZuoLeiXing":"集抄改造用户","txtJiLiangDianChaoBiaoFangShi":"远程低压遥抄","txtKanChaNanDu":"重点勘查","txtKanChaYongHuZhuangKuang":"","txtKuiXianMingCheng":"10kV山吓线F20","txtSheBeiLeiXing":"RS-485接口单相电子式电能表","txtSheBeiXingHao":"DDSF284","txtShengChanChangJia":"华立仪表集团股份有限公司","txtShiFouGengHuanBiaoXiang":"","txtShiFouGengHuanDianBiao":"否","txtShiFouJiChaoFuGai":"是","txtShiFouZhengTiGengHuan":"","txtShiFouZhengTiQianYi":"","txtTaiQuLeiXing":"小区","txtTaiQuMingCheng":"惠州市中港城实业有限公司(公变)#1","txtUpdatedAt":"","txtUserAddName":"余思敏","txtUserModifyName":"","txtYiChangQingKuang":"台区户号一对多","txtYingXiaoYongHuZhuangKuang":"运行","txtYongDianLeiBie":"居民生活","txtYongDianLiangQingKuang":"一年及以上无电量","txtYongHuLeiBie":"公变客户","txtYxGengHuanDianBiao":"","txtZuiJinChaoBiaoFangShi":"远程低压遥抄","userAddId":"62d7f995-fbe2-405a-936f-cb1d67215fdc","userModifyId":"0","version":0,"yiChangQingKuang":4,"yiNianHuDianLiang":"0","yingXiaoYongHuZhuangKuang":1,"yongDianDiZhi":"广东省惠州市博罗县福田镇中港城1栋404","yongDianLeiBie":4,"yongDianLiangQingKuang":3,"yongHuBianHao":"0313040011284231","yongHuHeTongRongLiang":"6","yongHuLeiBie":1,"yongHuMingCheng":"袁柳","yxGengHuanDianBiao":2,"ziChanBianHao":"1310701035987","zongHeBeiLv":"1","zuiJinChaoBiaoFangShi":5,"zuiJinChaoBiaoShiJian":"2017-06-01 12:00:00"},{"anZhuangRiQi":"2016-01-30 12:00:00","banNianHuDianLiang":"0","bianYaQiBianHao":"1111111111427822","bianYaQiEDingRongLiang":"800","bianYaQiMingCheng":"惠州市中港城实业有限公司(公变)","biaoXiangCaiZhi":0,"chanPinId":"c6e47907-2c94-4f7d-b3ea-103b3f50a0f1","chaoBiaoQuDuanBianHao":"中港城1号公变","chaoBiaoQuDuanShuLiang":0,"chaoBiaoXuHao":"134","chaoBiaoYuan":"李春杨","chaoBiaoZhouQi":4,"chuChangRiQi":"2014-10-16 12:00:00","createdAt":"2017-08-14 09:59:48","gongDianJuId":"34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17","gongDianSuoId":"21bc1159-93a5-130f-1ba1-c247ea5fe840","id":"a310b770-811b-4737-9cd7-953c1cb66670","jiChaoGongZuoLeiXing":2,"jiLiangDianBianHao":"1111111112894835","jiLiangDianChaoBiaoFangShi":5,"jiZhongQiZiChanHao":"","kanChaNanDu":2,"kanChaYongHuZhuangKuang":0,"kcGengHuanDianBiao":0,"kuiXianId":"6f9e786d-d82d-a7fa-bd6f-bb96134cfd53","liHuRiQi":"2016-02-02 12:00:00","lianXiDianHua":"18817553108","piCiHao":"201411252025","sheBeiLeiXing":1,"shiFouGengHuanBiaoXiang":0,"shiFouGengHuanDianBiao":2,"shiFouJiChaoFuGai":1,"shiFouZhengTiGengHuan":0,"shiFouZhengTiQianYi":0,"status":0,"taiQuBianHao":"0313040002464901","taiQuId":"23f69837-d2c8-4c12-385c-cbf0c7721a1b","taiQuLeiXing":1,"tongXunDiZhi":"","txtBiaoXiangCaiZhi":"","txtChaoBiaoZhouQi":"双月","txtCreatedAt":"2017-08-14 09:59:48","txtGongDianJu":"博罗供电局","txtGongDianSuo":"福田供电所","txtJiChaoGongZuoLeiXing":"集抄改造用户","txtJiLiangDianChaoBiaoFangShi":"远程低压遥抄","txtKanChaNanDu":"重点勘查","txtKanChaYongHuZhuangKuang":"","txtKuiXianMingCheng":"10kV山吓线F20","txtSheBeiLeiXing":"RS-485接口单相电子式电能表","txtSheBeiXingHao":"DDSF284","txtShengChanChangJia":"华立仪表集团股份有限公司","txtShiFouGengHuanBiaoXiang":"","txtShiFouGengHuanDianBiao":"否","txtShiFouJiChaoFuGai":"是","txtShiFouZhengTiGengHuan":"","txtShiFouZhengTiQianYi":"","txtTaiQuLeiXing":"小区","txtTaiQuMingCheng":"惠州市中港城实业有限公司(公变)#1","txtUpdatedAt":"","txtUserAddName":"余思敏","txtUserModifyName":"","txtYiChangQingKuang":"台区户号一对多","txtYingXiaoYongHuZhuangKuang":"运行","txtYongDianLeiBie":"居民生活","txtYongDianLiangQingKuang":"一年及以上无电量","txtYongHuLeiBie":"公变客户","txtYxGengHuanDianBiao":"","txtZuiJinChaoBiaoFangShi":"远程低压遥抄","userAddId":"62d7f995-fbe2-405a-936f-cb1d67215fdc","userModifyId":"0","version":0,"yiChangQingKuang":4,"yiNianHuDianLiang":"0","yingXiaoYongHuZhuangKuang":1,"yongDianDiZhi":"广东省惠州市博罗县福田镇中港城1栋301","yongDianLeiBie":4,"yongDianLiangQingKuang":3,"yongHuBianHao":"0313040011284169","yongHuHeTongRongLiang":"6","yongHuLeiBie":1,"yongHuMingCheng":"肖金","yxGengHuanDianBiao":2,"ziChanBianHao":"1310701035980","zongHeBeiLv":"1","zuiJinChaoBiaoFangShi":5,"zuiJinChaoBiaoShiJian":"2017-06-01 12:00:00"},{"anZhuangRiQi":"2016-01-30 12:00:00","banNianHuDianLiang":"0","bianYaQiBianHao":"1111111111427822","bianYaQiEDingRongLiang":"800","bianYaQiMingCheng":"惠州市中港城实业有限公司(公变)","biaoXiangCaiZhi":0,"chanPinId":"c6e47907-2c94-4f7d-b3ea-103b3f50a0f1","chaoBiaoQuDuanBianHao":"中港城1号公变","chaoBiaoQuDuanShuLiang":0,"chaoBiaoXuHao":"130","chaoBiaoYuan":"李春杨","chaoBiaoZhouQi":4,"chuChangRiQi":"2014-10-16 12:00:00","createdAt":"2017-08-14 09:59:49","gongDianJuId":"34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17","gongDianSuoId":"21bc1159-93a5-130f-1ba1-c247ea5fe840","id":"8414908a-76ee-46bd-84a0-0659460f56f4","jiChaoGongZuoLeiXing":2,"jiLiangDianBianHao":"1111111112894830","jiLiangDianChaoBiaoFangShi":5,"jiZhongQiZiChanHao":"","kanChaNanDu":2,"kanChaYongHuZhuangKuang":0,"kcGengHuanDianBiao":0,"kuiXianId":"6f9e786d-d82d-a7fa-bd6f-bb96134cfd53","liHuRiQi":"2016-02-02 12:00:00","lianXiDianHua":"13544940089","piCiHao":"201411252025","sheBeiLeiXing":1,"shiFouGengHuanBiaoXiang":0,"shiFouGengHuanDianBiao":2,"shiFouJiChaoFuGai":1,"shiFouZhengTiGengHuan":0,"shiFouZhengTiQianYi":0,"status":0,"taiQuBianHao":"0313040002464901","taiQuId":"23f69837-d2c8-4c12-385c-cbf0c7721a1b","taiQuLeiXing":1,"tongXunDiZhi":"","txtBiaoXiangCaiZhi":"","txtChaoBiaoZhouQi":"双月","txtCreatedAt":"2017-08-14 09:59:49","txtGongDianJu":"博罗供电局","txtGongDianSuo":"福田供电所","txtJiChaoGongZuoLeiXing":"集抄改造用户","txtJiLiangDianChaoBiaoFangShi":"远程低压遥抄","txtKanChaNanDu":"重点勘查","txtKanChaYongHuZhuangKuang":"","txtKuiXianMingCheng":"10kV山吓线F20","txtSheBeiLeiXing":"RS-485接口单相电子式电能表","txtSheBeiXingHao":"DDSF284","txtShengChanChangJia":"华立仪表集团股份有限公司","txtShiFouGengHuanBiaoXiang":"","txtShiFouGengHuanDianBiao":"否","txtShiFouJiChaoFuGai":"是","txtShiFouZhengTiGengHuan":"","txtShiFouZhengTiQianYi":"","txtTaiQuLeiXing":"小区","txtTaiQuMingCheng":"惠州市中港城实业有限公司(公变)#1","txtUpdatedAt":"","txtUserAddName":"余思敏","txtUserModifyName":"","txtYiChangQingKuang":"台区户号一对多","txtYingXiaoYongHuZhuangKuang":"运行","txtYongDianLeiBie":"居民生活","txtYongDianLiangQingKuang":"一年及以上无电量","txtYongHuLeiBie":"公变客户","txtYxGengHuanDianBiao":"","txtZuiJinChaoBiaoFangShi":"远程低压遥抄","userAddId":"62d7f995-fbe2-405a-936f-cb1d67215fdc","userModifyId":"0","version":0,"yiChangQingKuang":4,"yiNianHuDianLiang":"0","yingXiaoYongHuZhuangKuang":1,"yongDianDiZhi":"广东省惠州市博罗县福田镇中港城1栋201","yongDianLeiBie":4,"yongDianLiangQingKuang":3,"yongHuBianHao":"0313040011284127","yongHuHeTongRongLiang":"6","yongHuLeiBie":1,"yongHuMingCheng":"郭万燕","yxGengHuanDianBiao":2,"ziChanBianHao":"1310701035978","zongHeBeiLv":"1","zuiJinChaoBiaoFangShi":5,"zuiJinChaoBiaoShiJian":"2017-06-01 12:00:00"},{"anZhuangRiQi":"2016-01-30 12:00:00","banNianHuDianLiang":"0","bianYaQiBianHao":"1111111111427822","bianYaQiEDingRongLiang":"800","bianYaQiMingCheng":"惠州市中港城实业有限公司(公变)","biaoXiangCaiZhi":0,"chanPinId":"c6e47907-2c94-4f7d-b3ea-103b3f50a0f1","chaoBiaoQuDuanBianHao":"中港城1号公变","chaoBiaoQuDuanShuLiang":0,"chaoBiaoXuHao":"135","chaoBiaoYuan":"李春杨","chaoBiaoZhouQi":4,"chuChangRiQi":"2014-10-23 12:00:00","createdAt":"2017-08-14 09:59:49","gongDianJuId":"34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17","gongDianSuoId":"21bc1159-93a5-130f-1ba1-c247ea5fe840","id":"e95634d1-a25f-40b6-9fdb-ceefc389cf66","jiChaoGongZuoLeiXing":2,"jiLiangDianBianHao":"1111111112894836","jiLiangDianChaoBiaoFangShi":5,"jiZhongQiZiChanHao":"","kanChaNanDu":2,"kanChaYongHuZhuangKuang":0,"kcGengHuanDianBiao":0,"kuiXianId":"6f9e786d-d82d-a7fa-bd6f-bb96134cfd53","liHuRiQi":"2016-02-02 12:00:00","lianXiDianHua":"13710458238","piCiHao":"201411252024","sheBeiLeiXing":1,"shiFouGengHuanBiaoXiang":0,"shiFouGengHuanDianBiao":2,"shiFouJiChaoFuGai":1,"shiFouZhengTiGengHuan":0,"shiFouZhengTiQianYi":0,"status":0,"taiQuBianHao":"0313040002464901","taiQuId":"23f69837-d2c8-4c12-385c-cbf0c7721a1b","taiQuLeiXing":1,"tongXunDiZhi":"","txtBiaoXiangCaiZhi":"","txtChaoBiaoZhouQi":"双月","txtCreatedAt":"2017-08-14 09:59:49","txtGongDianJu":"博罗供电局","txtGongDianSuo":"福田供电所","txtJiChaoGongZuoLeiXing":"集抄改造用户","txtJiLiangDianChaoBiaoFangShi":"远程低压遥抄","txtKanChaNanDu":"重点勘查","txtKanChaYongHuZhuangKuang":"","txtKuiXianMingCheng":"10kV山吓线F20","txtSheBeiLeiXing":"RS-485接口单相电子式电能表","txtSheBeiXingHao":"DDSF284","txtShengChanChangJia":"华立仪表集团股份有限公司","txtShiFouGengHuanBiaoXiang":"","txtShiFouGengHuanDianBiao":"否","txtShiFouJiChaoFuGai":"是","txtShiFouZhengTiGengHuan":"","txtShiFouZhengTiQianYi":"","txtTaiQuLeiXing":"小区","txtTaiQuMingCheng":"惠州市中港城实业有限公司(公变)#1","txtUpdatedAt":"","txtUserAddName":"余思敏","txtUserModifyName":"","txtYiChangQingKuang":"台区户号一对多","txtYingXiaoYongHuZhuangKuang":"运行","txtYongDianLeiBie":"居民生活","txtYongDianLiangQingKuang":"一年及以上无电量","txtYongHuLeiBie":"公变客户","txtYxGengHuanDianBiao":"","txtZuiJinChaoBiaoFangShi":"远程低压遥抄","userAddId":"62d7f995-fbe2-405a-936f-cb1d67215fdc","userModifyId":"0","version":0,"yiChangQingKuang":4,"yiNianHuDianLiang":"0","yingXiaoYongHuZhuangKuang":1,"yongDianDiZhi":"广东省惠州市博罗县福田镇中港城1栋302","yongDianLeiBie":4,"yongDianLiangQingKuang":3,"yongHuBianHao":"0313040011284172","yongHuHeTongRongLiang":"6","yongHuLeiBie":1,"yongHuMingCheng":"熊育光","yxGengHuanDianBiao":2,"ziChanBianHao":"1310701020671","zongHeBeiLv":"1","zuiJinChaoBiaoFangShi":5,"zuiJinChaoBiaoShiJian":"2017-06-01 12:00:00"},{"anZhuangRiQi":"2017-03-27 12:00:00","banNianHuDianLiang":"0","bianYaQiBianHao":"1111111111427822","bianYaQiEDingRongLiang":"800","bianYaQiMingCheng":"惠州市中港城实业有限公司(公变)","biaoXiangCaiZhi":0,"chanPinId":"28edc063-74cc-4a7f-b375-35321ce609ca","chaoBiaoQuDuanBianHao":"中港城1号公变","chaoBiaoQuDuanShuLiang":0,"chaoBiaoXuHao":"131","chaoBiaoYuan":"李春杨","chaoBiaoZhouQi":4,"chuChangRiQi":"2016-10-01 12:00:00","createdAt":"2017-08-14 09:59:50","gongDianJuId":"34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17","gongDianSuoId":"21bc1159-93a5-130f-1ba1-c247ea5fe840","id":"0fd91259-fa8d-46a3-a2c0-bc7c9905ddda","jiChaoGongZuoLeiXing":3,"jiLiangDianBianHao":"1111111112894831","jiLiangDianChaoBiaoFangShi":5,"jiZhongQiZiChanHao":"","kanChaNanDu":2,"kanChaYongHuZhuangKuang":0,"kcGengHuanDianBiao":0,"kuiXianId":"6f9e786d-d82d-a7fa-bd6f-bb96134cfd53","liHuRiQi":"2016-02-02 12:00:00","lianXiDianHua":"15920351855","piCiHao":"03201610170061","sheBeiLeiXing":2,"shiFouGengHuanBiaoXiang":0,"shiFouGengHuanDianBiao":2,"shiFouJiChaoFuGai":1,"shiFouZhengTiGengHuan":0,"shiFouZhengTiQianYi":0,"status":0,"taiQuBianHao":"0313040002464901","taiQuId":"23f69837-d2c8-4c12-385c-cbf0c7721a1b","taiQuLeiXing":1,"tongXunDiZhi":"","txtBiaoXiangCaiZhi":"","txtChaoBiaoZhouQi":"双月","txtCreatedAt":"2017-08-14 09:59:50","txtGongDianJu":"博罗供电局","txtGongDianSuo":"福田供电所","txtJiChaoGongZuoLeiXing":"集抄维护用户","txtJiLiangDianChaoBiaoFangShi":"远程低压遥抄","txtKanChaNanDu":"重点勘查","txtKanChaYongHuZhuangKuang":"","txtKuiXianMingCheng":"10kV山吓线F20","txtSheBeiLeiXing":"单相电子式电能表(费控)","txtSheBeiXingHao":"DDSK217-J","txtShengChanChangJia":"浙江晨泰科技股份有限公司","txtShiFouGengHuanBiaoXiang":"","txtShiFouGengHuanDianBiao":"否","txtShiFouJiChaoFuGai":"是","txtShiFouZhengTiGengHuan":"","txtShiFouZhengTiQianYi":"","txtTaiQuLeiXing":"小区","txtTaiQuMingCheng":"惠州市中港城实业有限公司(公变)#1","txtUpdatedAt":"","txtUserAddName":"余思敏","txtUserModifyName":"","txtYiChangQingKuang":"台区户号一对多","txtYingXiaoYongHuZhuangKuang":"运行","txtYongDianLeiBie":"居民生活","txtYongDianLiangQingKuang":"一年及以上无电量","txtYongHuLeiBie":"公变客户","txtYxGengHuanDianBiao":"","txtZuiJinChaoBiaoFangShi":"远程低压遥抄","userAddId":"62d7f995-fbe2-405a-936f-cb1d67215fdc","userModifyId":"0","version":0,"yiChangQingKuang":4,"yiNianHuDianLiang":"0","yingXiaoYongHuZhuangKuang":1,"yongDianDiZhi":"广东省惠州市博罗县福田镇中港城1栋202","yongDianLeiBie":4,"yongDianLiangQingKuang":3,"yongHuBianHao":"0313040011284130","yongHuHeTongRongLiang":"6","yongHuLeiBie":1,"yongHuMingCheng":"梁碧容","yxGengHuanDianBiao":2,"ziChanBianHao":"03131DY00000001601249243","zongHeBeiLv":"1","zuiJinChaoBiaoFangShi":5,"zuiJinChaoBiaoShiJian":"2017-06-01 12:00:00"},{"anZhuangRiQi":"2016-01-30 12:00:00","banNianHuDianLiang":"0","bianYaQiBianHao":"1111111111427822","bianYaQiEDingRongLiang":"800","bianYaQiMingCheng":"惠州市中港城实业有限公司(公变)","biaoXiangCaiZhi":0,"chanPinId":"c6e47907-2c94-4f7d-b3ea-103b3f50a0f1","chaoBiaoQuDuanBianHao":"中港城1号公变","chaoBiaoQuDuanShuLiang":0,"chaoBiaoXuHao":"142","chaoBiaoYuan":"李春杨","chaoBiaoZhouQi":4,"chuChangRiQi":"2014-10-23 12:00:00","createdAt":"2017-08-14 09:59:50","gongDianJuId":"34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17","gongDianSuoId":"21bc1159-93a5-130f-1ba1-c247ea5fe840","id":"c51cb4a1-8572-4d05-8f38-bf4c7bdfe0fd","jiChaoGongZuoLeiXing":2,"jiLiangDianBianHao":"1111111112894843","jiLiangDianChaoBiaoFangShi":5,"jiZhongQiZiChanHao":"","kanChaNanDu":2,"kanChaYongHuZhuangKuang":0,"kcGengHuanDianBiao":0,"kuiXianId":"6f9e786d-d82d-a7fa-bd6f-bb96134cfd53","liHuRiQi":"2016-02-02 12:00:00","lianXiDianHua":"15218880658","piCiHao":"201411252024","sheBeiLeiXing":1,"shiFouGengHuanBiaoXiang":0,"shiFouGengHuanDianBiao":2,"shiFouJiChaoFuGai":1,"shiFouZhengTiGengHuan":0,"shiFouZhengTiQianYi":0,"status":0,"taiQuBianHao":"0313040002464901","taiQuId":"23f69837-d2c8-4c12-385c-cbf0c7721a1b","taiQuLeiXing":1,"tongXunDiZhi":"","txtBiaoXiangCaiZhi":"","txtChaoBiaoZhouQi":"双月","txtCreatedAt":"2017-08-14 09:59:50","txtGongDianJu":"博罗供电局","txtGongDianSuo":"福田供电所","txtJiChaoGongZuoLeiXing":"集抄改造用户","txtJiLiangDianChaoBiaoFangShi":"远程低压遥抄","txtKanChaNanDu":"重点勘查","txtKanChaYongHuZhuangKuang":"","txtKuiXianMingCheng":"10kV山吓线F20","txtSheBeiLeiXing":"RS-485接口单相电子式电能表","txtSheBeiXingHao":"DDSF284","txtShengChanChangJia":"华立仪表集团股份有限公司","txtShiFouGengHuanBiaoXiang":"","txtShiFouGengHuanDianBiao":"否","txtShiFouJiChaoFuGai":"是","txtShiFouZhengTiGengHuan":"","txtShiFouZhengTiQianYi":"","txtTaiQuLeiXing":"小区","txtTaiQuMingCheng":"惠州市中港城实业有限公司(公变)#1","txtUpdatedAt":"","txtUserAddName":"余思敏","txtUserModifyName":"","txtYiChangQingKuang":"台区户号一对多","txtYingXiaoYongHuZhuangKuang":"运行","txtYongDianLeiBie":"居民生活","txtYongDianLiangQingKuang":"一年及以上无电量","txtYongHuLeiBie":"公变客户","txtYxGengHuanDianBiao":"","txtZuiJinChaoBiaoFangShi":"远程低压遥抄","userAddId":"62d7f995-fbe2-405a-936f-cb1d67215fdc","userModifyId":"0","version":0,"yiChangQingKuang":4,"yiNianHuDianLiang":"0","yingXiaoYongHuZhuangKuang":1,"yongDianDiZhi":"广东省惠州市博罗县福田镇中港城1栋501","yongDianLeiBie":4,"yongDianLiangQingKuang":3,"yongHuBianHao":"0313040011284244","yongHuHeTongRongLiang":"6","yongHuLeiBie":1,"yongHuMingCheng":"吴志强","yxGengHuanDianBiao":2,"ziChanBianHao":"1310701020668","zongHeBeiLv":"1","zuiJinChaoBiaoFangShi":5,"zuiJinChaoBiaoShiJian":"2017-06-01 12:00:00"},{"anZhuangRiQi":"2016-01-30 12:00:00","banNianHuDianLiang":"0","bianYaQiBianHao":"1111111111427822","bianYaQiEDingRongLiang":"800","bianYaQiMingCheng":"惠州市中港城实业有限公司(公变)","biaoXiangCaiZhi":0,"chanPinId":"c6e47907-2c94-4f7d-b3ea-103b3f50a0f1","chaoBiaoQuDuanBianHao":"中港城1号公变","chaoBiaoQuDuanShuLiang":0,"chaoBiaoXuHao":"132","chaoBiaoYuan":"李春杨","chaoBiaoZhouQi":4,"chuChangRiQi":"2014-10-23 12:00:00","createdAt":"2017-08-14 09:59:51","gongDianJuId":"34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17","gongDianSuoId":"21bc1159-93a5-130f-1ba1-c247ea5fe840","id":"6a47255b-79ec-440a-a4ec-53cf470a5129","jiChaoGongZuoLeiXing":2,"jiLiangDianBianHao":"1111111112894832","jiLiangDianChaoBiaoFangShi":5,"jiZhongQiZiChanHao":"","kanChaNanDu":2,"kanChaYongHuZhuangKuang":0,"kcGengHuanDianBiao":0,"kuiXianId":"6f9e786d-d82d-a7fa-bd6f-bb96134cfd53","liHuRiQi":"2016-02-02 12:00:00","lianXiDianHua":"","piCiHao":"201411252024","sheBeiLeiXing":1,"shiFouGengHuanBiaoXiang":0,"shiFouGengHuanDianBiao":2,"shiFouJiChaoFuGai":1,"shiFouZhengTiGengHuan":0,"shiFouZhengTiQianYi":0,"status":0,"taiQuBianHao":"0313040002464901","taiQuId":"23f69837-d2c8-4c12-385c-cbf0c7721a1b","taiQuLeiXing":1,"tongXunDiZhi":"","txtBiaoXiangCaiZhi":"","txtChaoBiaoZhouQi":"双月","txtCreatedAt":"2017-08-14 09:59:51","txtGongDianJu":"博罗供电局","txtGongDianSuo":"福田供电所","txtJiChaoGongZuoLeiXing":"集抄改造用户","txtJiLiangDianChaoBiaoFangShi":"远程低压遥抄","txtKanChaNanDu":"重点勘查","txtKanChaYongHuZhuangKuang":"","txtKuiXianMingCheng":"10kV山吓线F20","txtSheBeiLeiXing":"RS-485接口单相电子式电能表","txtSheBeiXingHao":"DDSF284","txtShengChanChangJia":"华立仪表集团股份有限公司","txtShiFouGengHuanBiaoXiang":"","txtShiFouGengHuanDianBiao":"否","txtShiFouJiChaoFuGai":"是","txtShiFouZhengTiGengHuan":"","txtShiFouZhengTiQianYi":"","txtTaiQuLeiXing":"小区","txtTaiQuMingCheng":"惠州市中港城实业有限公司(公变)#1","txtUpdatedAt":"","txtUserAddName":"余思敏","txtUserModifyName":"","txtYiChangQingKuang":"台区户号一对多","txtYingXiaoYongHuZhuangKuang":"运行","txtYongDianLeiBie":"居民生活","txtYongDianLiangQingKuang":"一年及以上无电量","txtYongHuLeiBie":"公变客户","txtYxGengHuanDianBiao":"","txtZuiJinChaoBiaoFangShi":"远程低压遥抄","userAddId":"62d7f995-fbe2-405a-936f-cb1d67215fdc","userModifyId":"0","version":0,"yiChangQingKuang":4,"yiNianHuDianLiang":"0","yingXiaoYongHuZhuangKuang":1,"yongDianDiZhi":"广东省惠州市博罗县福田镇中港城1栋203","yongDianLeiBie":4,"yongDianLiangQingKuang":3,"yongHuBianHao":"0313040011284143","yongHuHeTongRongLiang":"6","yongHuLeiBie":1,"yongHuMingCheng":"惠州市中港城实业有限公司","yxGengHuanDianBiao":2,"ziChanBianHao":"1310701020667","zongHeBeiLv":"1","zuiJinChaoBiaoFangShi":5,"zuiJinChaoBiaoShiJian":"2017-06-01 12:00:00"},{"anZhuangRiQi":"2016-01-30 12:00:00","banNianHuDianLiang":"0","bianYaQiBianHao":"1111111111427822","bianYaQiEDingRongLiang":"800","bianYaQiMingCheng":"惠州市中港城实业有限公司(公变)","biaoXiangCaiZhi":0,"chanPinId":"c6e47907-2c94-4f7d-b3ea-103b3f50a0f1","chaoBiaoQuDuanBianHao":"中港城1号公变","chaoBiaoQuDuanShuLiang":0,"chaoBiaoXuHao":"140","chaoBiaoYuan":"李春杨","chaoBiaoZhouQi":4,"chuChangRiQi":"2014-10-16 12:00:00","createdAt":"2017-08-14 09:59:51","gongDianJuId":"34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17","gongDianSuoId":"21bc1159-93a5-130f-1ba1-c247ea5fe840","id":"c58843fc-3cee-425e-9ce9-2ff7fbd71639","jiChaoGongZuoLeiXing":2,"jiLiangDianBianHao":"1111111112894841","jiLiangDianChaoBiaoFangShi":5,"jiZhongQiZiChanHao":"","kanChaNanDu":2,"kanChaYongHuZhuangKuang":0,"kcGengHuanDianBiao":0,"kuiXianId":"6f9e786d-d82d-a7fa-bd6f-bb96134cfd53","liHuRiQi":"2016-02-02 12:00:00","lianXiDianHua":"13824457268","piCiHao":"201411252025","sheBeiLeiXing":1,"shiFouGengHuanBiaoXiang":0,"shiFouGengHuanDianBiao":2,"shiFouJiChaoFuGai":1,"shiFouZhengTiGengHuan":0,"shiFouZhengTiQianYi":0,"status":0,"taiQuBianHao":"0313040002464901","taiQuId":"23f69837-d2c8-4c12-385c-cbf0c7721a1b","taiQuLeiXing":1,"tongXunDiZhi":"","txtBiaoXiangCaiZhi":"","txtChaoBiaoZhouQi":"双月","txtCreatedAt":"2017-08-14 09:59:51","txtGongDianJu":"博罗供电局","txtGongDianSuo":"福田供电所","txtJiChaoGongZuoLeiXing":"集抄改造用户","txtJiLiangDianChaoBiaoFangShi":"远程低压遥抄","txtKanChaNanDu":"重点勘查","txtKanChaYongHuZhuangKuang":"","txtKuiXianMingCheng":"10kV山吓线F20","txtSheBeiLeiXing":"RS-485接口单相电子式电能表","txtSheBeiXingHao":"DDSF284","txtShengChanChangJia":"华立仪表集团股份有限公司","txtShiFouGengHuanBiaoXiang":"","txtShiFouGengHuanDianBiao":"否","txtShiFouJiChaoFuGai":"是","txtShiFouZhengTiGengHuan":"","txtShiFouZhengTiQianYi":"","txtTaiQuLeiXing":"小区","txtTaiQuMingCheng":"惠州市中港城实业有限公司(公变)#1","txtUpdatedAt":"","txtUserAddName":"余思敏","txtUserModifyName":"","txtYiChangQingKuang":"台区户号一对多","txtYingXiaoYongHuZhuangKuang":"运行","txtYongDianLeiBie":"居民生活","txtYongDianLiangQingKuang":"一年及以上无电量","txtYongHuLeiBie":"公变客户","txtYxGengHuanDianBiao":"","txtZuiJinChaoBiaoFangShi":"远程低压遥抄","userAddId":"62d7f995-fbe2-405a-936f-cb1d67215fdc","userModifyId":"0","version":0,"yiChangQingKuang":4,"yiNianHuDianLiang":"0","yingXiaoYongHuZhuangKuang":1,"yongDianDiZhi":"广东省惠州市博罗县福田镇中港城1栋403","yongDianLeiBie":4,"yongDianLiangQingKuang":3,"yongHuBianHao":"0313040011284228","yongHuHeTongRongLiang":"6","yongHuLeiBie":1,"yongHuMingCheng":"邓奎","yxGengHuanDianBiao":2,"ziChanBianHao":"1310701035986","zongHeBeiLv":"1","zuiJinChaoBiaoFangShi":5,"zuiJinChaoBiaoShiJian":"2017-06-01 12:00:00"}]
         * navigatePages : 8
         * navigatepageNums : [1,2,3,4,5,6,7,8]
         * next : 2
         * pageIndex : 1
         * pageSize : 10
         * pages : 20
         * preview : 0
         * size : 10
         * startRow : 1
         * total : 192
         */

        private int endRow;
        private int first;
        private boolean hasNext;
        private boolean hasPrevious;
        private boolean isFirst;
        private boolean isLast;
        private int last;
        private int navigatePages;
        private int next;
        private int pageIndex;
        private int pageSize;
        private int pages;
        private int preview;
        private int size;
        private int startRow;
        private int total;
        private List<ListBean> list;
        private List<Integer> navigatepageNums;

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getFirst() {
            return first;
        }

        public void setFirst(int first) {
            this.first = first;
        }

        public boolean isHasNext() {
            return hasNext;
        }

        public void setHasNext(boolean hasNext) {
            this.hasNext = hasNext;
        }

        public boolean isHasPrevious() {
            return hasPrevious;
        }

        public void setHasPrevious(boolean hasPrevious) {
            this.hasPrevious = hasPrevious;
        }

        public boolean isIsFirst() {
            return isFirst;
        }

        public void setIsFirst(boolean isFirst) {
            this.isFirst = isFirst;
        }

        public boolean isIsLast() {
            return isLast;
        }

        public void setIsLast(boolean isLast) {
            this.isLast = isLast;
        }

        public int getLast() {
            return last;
        }

        public void setLast(int last) {
            this.last = last;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getNext() {
            return next;
        }

        public void setNext(int next) {
            this.next = next;
        }

        public int getPageIndex() {
            return pageIndex;
        }

        public void setPageIndex(int pageIndex) {
            this.pageIndex = pageIndex;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPreview() {
            return preview;
        }

        public void setPreview(int preview) {
            this.preview = preview;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean implements Serializable{
            /**
             * anZhuangRiQi : 2016-01-30 12:00:00
             * banNianHuDianLiang : 1206
             * bianYaQiBianHao : 1111111111427822
             * bianYaQiEDingRongLiang : 800
             * bianYaQiMingCheng : 惠州市中港城实业有限公司(公变)
             * biaoXiangCaiZhi : 0
             * chanPinId : c6e47907-2c94-4f7d-b3ea-103b3f50a0f1
             * chaoBiaoQuDuanBianHao : 中港城1号公变
             * chaoBiaoQuDuanShuLiang : 0
             * chaoBiaoXuHao : 138
             * chaoBiaoYuan : 李春杨
             * chaoBiaoZhouQi : 4
             * chuChangRiQi : 2014-10-16 12:00:00
             * createdAt : 2017-08-14 09:59:46
             * gongDianJuId : 34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17
             * gongDianSuoId : 21bc1159-93a5-130f-1ba1-c247ea5fe840
             * id : e41218db-5c2d-4b29-af59-607b04e931d8
             * jiChaoGongZuoLeiXing : 2
             * jiLiangDianBianHao : 1111111112894839
             * jiLiangDianChaoBiaoFangShi : 5
             * jiZhongQiZiChanHao :
             * kanChaNanDu : 1
             * kanChaYongHuZhuangKuang : 0
             * kcGengHuanDianBiao : 0
             * kuiXianId : 6f9e786d-d82d-a7fa-bd6f-bb96134cfd53
             * liHuRiQi : 2016-02-02 12:00:00
             * lianXiDianHua : 13528024465
             * piCiHao : 201411252025
             * sheBeiLeiXing : 1
             * shiFouGengHuanBiaoXiang : 0
             * shiFouGengHuanDianBiao : 2
             * shiFouJiChaoFuGai : 1
             * shiFouZhengTiGengHuan : 0
             * shiFouZhengTiQianYi : 0
             * status : 0
             * taiQuBianHao : 0313040002464901
             * taiQuId : 23f69837-d2c8-4c12-385c-cbf0c7721a1b
             * taiQuLeiXing : 1
             * tongXunDiZhi :
             * txtBiaoXiangCaiZhi :
             * txtChaoBiaoZhouQi : 双月
             * txtCreatedAt : 2017-08-14 09:59:46
             * txtGongDianJu : 博罗供电局
             * txtGongDianSuo : 福田供电所
             * txtJiChaoGongZuoLeiXing : 集抄改造用户
             * txtJiLiangDianChaoBiaoFangShi : 远程低压遥抄
             * txtKanChaNanDu : 正常勘查
             * txtKanChaYongHuZhuangKuang :
             * txtKuiXianMingCheng : 10kV山吓线F20
             * txtSheBeiLeiXing : RS-485接口单相电子式电能表
             * txtSheBeiXingHao : DDSF284
             * txtShengChanChangJia : 华立仪表集团股份有限公司
             * txtShiFouGengHuanBiaoXiang :
             * txtShiFouGengHuanDianBiao : 否
             * txtShiFouJiChaoFuGai : 是
             * txtShiFouZhengTiGengHuan :
             * txtShiFouZhengTiQianYi :
             * txtTaiQuLeiXing : 小区
             * txtTaiQuMingCheng : 惠州市中港城实业有限公司(公变)#1
             * txtUpdatedAt :
             * txtUserAddName : 余思敏
             * txtUserModifyName :
             * txtYiChangQingKuang : 台区户号一对多
             * txtYingXiaoYongHuZhuangKuang : 运行
             * txtYongDianLeiBie : 居民生活
             * txtYongDianLiangQingKuang : 正常
             * txtYongHuLeiBie : 公变客户
             * txtYxGengHuanDianBiao :
             * txtZuiJinChaoBiaoFangShi : 远程低压遥抄
             * userAddId : 62d7f995-fbe2-405a-936f-cb1d67215fdc
             * userModifyId : 0
             * version : 0
             * yiChangQingKuang : 4
             * yiNianHuDianLiang : 1930
             * yingXiaoYongHuZhuangKuang : 1
             * yongDianDiZhi : 广东省惠州市博罗县福田镇中港城1栋401
             * yongDianLeiBie : 4
             * yongDianLiangQingKuang : 1
             * yongHuBianHao : 0313040011284202
             * yongHuHeTongRongLiang : 6
             * yongHuLeiBie : 1
             * yongHuMingCheng : 高殿龙
             * yxGengHuanDianBiao : 2
             * ziChanBianHao : 1310701035985
             * zongHeBeiLv : 1
             * zuiJinChaoBiaoFangShi : 5
             * zuiJinChaoBiaoShiJian : 2017-06-01 12:00:00
             */

            private String anZhuangRiQi;
            private String banNianHuDianLiang;
            private String bianYaQiBianHao;
            private String bianYaQiEDingRongLiang;
            private String bianYaQiMingCheng;
            private int biaoXiangCaiZhi;
            private String chanPinId;
            private String chaoBiaoQuDuanBianHao;
            private int chaoBiaoQuDuanShuLiang;
            private String chaoBiaoXuHao;
            private String chaoBiaoYuan;
            private int chaoBiaoZhouQi;
            private String chuChangRiQi;
            private String createdAt;
            private String gongDianJuId;
            private String gongDianSuoId;
            private String id;
            private int jiChaoGongZuoLeiXing;
            private String jiLiangDianBianHao;
            private int jiLiangDianChaoBiaoFangShi;
            private String jiZhongQiZiChanHao;
            private int kanChaNanDu;
            private int kanChaYongHuZhuangKuang;
            private int kcGengHuanDianBiao;
            private String kuiXianId;
            private String liHuRiQi;
            private String lianXiDianHua;
            private String piCiHao;
            private int sheBeiLeiXing;
            private int shiFouGengHuanBiaoXiang;
            private int shiFouGengHuanDianBiao;
            private int shiFouJiChaoFuGai;
            private int shiFouZhengTiGengHuan;
            private int shiFouZhengTiQianYi;
            private int status;
            private String taiQuBianHao;
            private String taiQuId;
            private int taiQuLeiXing;
            private String tongXunDiZhi;
            private String txtBiaoXiangCaiZhi;
            private String txtChaoBiaoZhouQi;
            private String txtCreatedAt;
            private String txtGongDianJu;
            private String txtGongDianSuo;
            private String txtJiChaoGongZuoLeiXing;
            private String txtJiLiangDianChaoBiaoFangShi;
            private String txtKanChaNanDu;
            private String txtKanChaYongHuZhuangKuang;
            private String txtKuiXianMingCheng;
            private String txtSheBeiLeiXing;
            private String txtSheBeiXingHao;
            private String txtShengChanChangJia;
            private String txtShiFouGengHuanBiaoXiang;
            private String txtShiFouGengHuanDianBiao;
            private String txtShiFouJiChaoFuGai;
            private String txtShiFouZhengTiGengHuan;
            private String txtShiFouZhengTiQianYi;
            private String txtTaiQuLeiXing;
            private String txtTaiQuMingCheng;
            private String txtUpdatedAt;
            private String txtUserAddName;
            private String txtUserModifyName;
            private String txtYiChangQingKuang;
            private String txtYingXiaoYongHuZhuangKuang;
            private String txtYongDianLeiBie;
            private String txtYongDianLiangQingKuang;
            private String txtYongHuLeiBie;
            private String txtYxGengHuanDianBiao;
            private String txtZuiJinChaoBiaoFangShi;
            private String userAddId;
            private String userModifyId;
            private int version;
            private int yiChangQingKuang;
            private String yiNianHuDianLiang;
            private int yingXiaoYongHuZhuangKuang;
            private String yongDianDiZhi;
            private int yongDianLeiBie;
            private int yongDianLiangQingKuang;
            private String yongHuBianHao;
            private String yongHuHeTongRongLiang;
            private int yongHuLeiBie;
            private String yongHuMingCheng;
            private int yxGengHuanDianBiao;
            private String ziChanBianHao;
            private String zongHeBeiLv;
            private int zuiJinChaoBiaoFangShi;
            private String zuiJinChaoBiaoShiJian;

            public String getAnZhuangRiQi() {
                return anZhuangRiQi;
            }

            public void setAnZhuangRiQi(String anZhuangRiQi) {
                this.anZhuangRiQi = anZhuangRiQi;
            }

            public String getBanNianHuDianLiang() {
                return banNianHuDianLiang;
            }

            public void setBanNianHuDianLiang(String banNianHuDianLiang) {
                this.banNianHuDianLiang = banNianHuDianLiang;
            }

            public String getBianYaQiBianHao() {
                return bianYaQiBianHao;
            }

            public void setBianYaQiBianHao(String bianYaQiBianHao) {
                this.bianYaQiBianHao = bianYaQiBianHao;
            }

            public String getBianYaQiEDingRongLiang() {
                return bianYaQiEDingRongLiang;
            }

            public void setBianYaQiEDingRongLiang(String bianYaQiEDingRongLiang) {
                this.bianYaQiEDingRongLiang = bianYaQiEDingRongLiang;
            }

            public String getBianYaQiMingCheng() {
                return bianYaQiMingCheng;
            }

            public void setBianYaQiMingCheng(String bianYaQiMingCheng) {
                this.bianYaQiMingCheng = bianYaQiMingCheng;
            }

            public int getBiaoXiangCaiZhi() {
                return biaoXiangCaiZhi;
            }

            public void setBiaoXiangCaiZhi(int biaoXiangCaiZhi) {
                this.biaoXiangCaiZhi = biaoXiangCaiZhi;
            }

            public String getChanPinId() {
                return chanPinId;
            }

            public void setChanPinId(String chanPinId) {
                this.chanPinId = chanPinId;
            }

            public String getChaoBiaoQuDuanBianHao() {
                return chaoBiaoQuDuanBianHao;
            }

            public void setChaoBiaoQuDuanBianHao(String chaoBiaoQuDuanBianHao) {
                this.chaoBiaoQuDuanBianHao = chaoBiaoQuDuanBianHao;
            }

            public int getChaoBiaoQuDuanShuLiang() {
                return chaoBiaoQuDuanShuLiang;
            }

            public void setChaoBiaoQuDuanShuLiang(int chaoBiaoQuDuanShuLiang) {
                this.chaoBiaoQuDuanShuLiang = chaoBiaoQuDuanShuLiang;
            }

            public String getChaoBiaoXuHao() {
                return chaoBiaoXuHao;
            }

            public void setChaoBiaoXuHao(String chaoBiaoXuHao) {
                this.chaoBiaoXuHao = chaoBiaoXuHao;
            }

            public String getChaoBiaoYuan() {
                return chaoBiaoYuan;
            }

            public void setChaoBiaoYuan(String chaoBiaoYuan) {
                this.chaoBiaoYuan = chaoBiaoYuan;
            }

            public int getChaoBiaoZhouQi() {
                return chaoBiaoZhouQi;
            }

            public void setChaoBiaoZhouQi(int chaoBiaoZhouQi) {
                this.chaoBiaoZhouQi = chaoBiaoZhouQi;
            }

            public String getChuChangRiQi() {
                return chuChangRiQi;
            }

            public void setChuChangRiQi(String chuChangRiQi) {
                this.chuChangRiQi = chuChangRiQi;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getGongDianJuId() {
                return gongDianJuId;
            }

            public void setGongDianJuId(String gongDianJuId) {
                this.gongDianJuId = gongDianJuId;
            }

            public String getGongDianSuoId() {
                return gongDianSuoId;
            }

            public void setGongDianSuoId(String gongDianSuoId) {
                this.gongDianSuoId = gongDianSuoId;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getJiChaoGongZuoLeiXing() {
                return jiChaoGongZuoLeiXing;
            }

            public void setJiChaoGongZuoLeiXing(int jiChaoGongZuoLeiXing) {
                this.jiChaoGongZuoLeiXing = jiChaoGongZuoLeiXing;
            }

            public String getJiLiangDianBianHao() {
                return jiLiangDianBianHao;
            }

            public void setJiLiangDianBianHao(String jiLiangDianBianHao) {
                this.jiLiangDianBianHao = jiLiangDianBianHao;
            }

            public int getJiLiangDianChaoBiaoFangShi() {
                return jiLiangDianChaoBiaoFangShi;
            }

            public void setJiLiangDianChaoBiaoFangShi(int jiLiangDianChaoBiaoFangShi) {
                this.jiLiangDianChaoBiaoFangShi = jiLiangDianChaoBiaoFangShi;
            }

            public String getJiZhongQiZiChanHao() {
                return jiZhongQiZiChanHao;
            }

            public void setJiZhongQiZiChanHao(String jiZhongQiZiChanHao) {
                this.jiZhongQiZiChanHao = jiZhongQiZiChanHao;
            }

            public int getKanChaNanDu() {
                return kanChaNanDu;
            }

            public void setKanChaNanDu(int kanChaNanDu) {
                this.kanChaNanDu = kanChaNanDu;
            }

            public int getKanChaYongHuZhuangKuang() {
                return kanChaYongHuZhuangKuang;
            }

            public void setKanChaYongHuZhuangKuang(int kanChaYongHuZhuangKuang) {
                this.kanChaYongHuZhuangKuang = kanChaYongHuZhuangKuang;
            }

            public int getKcGengHuanDianBiao() {
                return kcGengHuanDianBiao;
            }

            public void setKcGengHuanDianBiao(int kcGengHuanDianBiao) {
                this.kcGengHuanDianBiao = kcGengHuanDianBiao;
            }

            public String getKuiXianId() {
                return kuiXianId;
            }

            public void setKuiXianId(String kuiXianId) {
                this.kuiXianId = kuiXianId;
            }

            public String getLiHuRiQi() {
                return liHuRiQi;
            }

            public void setLiHuRiQi(String liHuRiQi) {
                this.liHuRiQi = liHuRiQi;
            }

            public String getLianXiDianHua() {
                return lianXiDianHua;
            }

            public void setLianXiDianHua(String lianXiDianHua) {
                this.lianXiDianHua = lianXiDianHua;
            }

            public String getPiCiHao() {
                return piCiHao;
            }

            public void setPiCiHao(String piCiHao) {
                this.piCiHao = piCiHao;
            }

            public int getSheBeiLeiXing() {
                return sheBeiLeiXing;
            }

            public void setSheBeiLeiXing(int sheBeiLeiXing) {
                this.sheBeiLeiXing = sheBeiLeiXing;
            }

            public int getShiFouGengHuanBiaoXiang() {
                return shiFouGengHuanBiaoXiang;
            }

            public void setShiFouGengHuanBiaoXiang(int shiFouGengHuanBiaoXiang) {
                this.shiFouGengHuanBiaoXiang = shiFouGengHuanBiaoXiang;
            }

            public int getShiFouGengHuanDianBiao() {
                return shiFouGengHuanDianBiao;
            }

            public void setShiFouGengHuanDianBiao(int shiFouGengHuanDianBiao) {
                this.shiFouGengHuanDianBiao = shiFouGengHuanDianBiao;
            }

            public int getShiFouJiChaoFuGai() {
                return shiFouJiChaoFuGai;
            }

            public void setShiFouJiChaoFuGai(int shiFouJiChaoFuGai) {
                this.shiFouJiChaoFuGai = shiFouJiChaoFuGai;
            }

            public int getShiFouZhengTiGengHuan() {
                return shiFouZhengTiGengHuan;
            }

            public void setShiFouZhengTiGengHuan(int shiFouZhengTiGengHuan) {
                this.shiFouZhengTiGengHuan = shiFouZhengTiGengHuan;
            }

            public int getShiFouZhengTiQianYi() {
                return shiFouZhengTiQianYi;
            }

            public void setShiFouZhengTiQianYi(int shiFouZhengTiQianYi) {
                this.shiFouZhengTiQianYi = shiFouZhengTiQianYi;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getTaiQuBianHao() {
                return taiQuBianHao;
            }

            public void setTaiQuBianHao(String taiQuBianHao) {
                this.taiQuBianHao = taiQuBianHao;
            }

            public String getTaiQuId() {
                return taiQuId;
            }

            public void setTaiQuId(String taiQuId) {
                this.taiQuId = taiQuId;
            }

            public int getTaiQuLeiXing() {
                return taiQuLeiXing;
            }

            public void setTaiQuLeiXing(int taiQuLeiXing) {
                this.taiQuLeiXing = taiQuLeiXing;
            }

            public String getTongXunDiZhi() {
                return tongXunDiZhi;
            }

            public void setTongXunDiZhi(String tongXunDiZhi) {
                this.tongXunDiZhi = tongXunDiZhi;
            }

            public String getTxtBiaoXiangCaiZhi() {
                return txtBiaoXiangCaiZhi;
            }

            public void setTxtBiaoXiangCaiZhi(String txtBiaoXiangCaiZhi) {
                this.txtBiaoXiangCaiZhi = txtBiaoXiangCaiZhi;
            }

            public String getTxtChaoBiaoZhouQi() {
                return txtChaoBiaoZhouQi;
            }

            public void setTxtChaoBiaoZhouQi(String txtChaoBiaoZhouQi) {
                this.txtChaoBiaoZhouQi = txtChaoBiaoZhouQi;
            }

            public String getTxtCreatedAt() {
                return txtCreatedAt;
            }

            public void setTxtCreatedAt(String txtCreatedAt) {
                this.txtCreatedAt = txtCreatedAt;
            }

            public String getTxtGongDianJu() {
                return txtGongDianJu;
            }

            public void setTxtGongDianJu(String txtGongDianJu) {
                this.txtGongDianJu = txtGongDianJu;
            }

            public String getTxtGongDianSuo() {
                return txtGongDianSuo;
            }

            public void setTxtGongDianSuo(String txtGongDianSuo) {
                this.txtGongDianSuo = txtGongDianSuo;
            }

            public String getTxtJiChaoGongZuoLeiXing() {
                return txtJiChaoGongZuoLeiXing;
            }

            public void setTxtJiChaoGongZuoLeiXing(String txtJiChaoGongZuoLeiXing) {
                this.txtJiChaoGongZuoLeiXing = txtJiChaoGongZuoLeiXing;
            }

            public String getTxtJiLiangDianChaoBiaoFangShi() {
                return txtJiLiangDianChaoBiaoFangShi;
            }

            public void setTxtJiLiangDianChaoBiaoFangShi(String txtJiLiangDianChaoBiaoFangShi) {
                this.txtJiLiangDianChaoBiaoFangShi = txtJiLiangDianChaoBiaoFangShi;
            }

            public String getTxtKanChaNanDu() {
                return txtKanChaNanDu;
            }

            public void setTxtKanChaNanDu(String txtKanChaNanDu) {
                this.txtKanChaNanDu = txtKanChaNanDu;
            }

            public String getTxtKanChaYongHuZhuangKuang() {
                return txtKanChaYongHuZhuangKuang;
            }

            public void setTxtKanChaYongHuZhuangKuang(String txtKanChaYongHuZhuangKuang) {
                this.txtKanChaYongHuZhuangKuang = txtKanChaYongHuZhuangKuang;
            }

            public String getTxtKuiXianMingCheng() {
                return txtKuiXianMingCheng;
            }

            public void setTxtKuiXianMingCheng(String txtKuiXianMingCheng) {
                this.txtKuiXianMingCheng = txtKuiXianMingCheng;
            }

            public String getTxtSheBeiLeiXing() {
                return txtSheBeiLeiXing;
            }

            public void setTxtSheBeiLeiXing(String txtSheBeiLeiXing) {
                this.txtSheBeiLeiXing = txtSheBeiLeiXing;
            }

            public String getTxtSheBeiXingHao() {
                return txtSheBeiXingHao;
            }

            public void setTxtSheBeiXingHao(String txtSheBeiXingHao) {
                this.txtSheBeiXingHao = txtSheBeiXingHao;
            }

            public String getTxtShengChanChangJia() {
                return txtShengChanChangJia;
            }

            public void setTxtShengChanChangJia(String txtShengChanChangJia) {
                this.txtShengChanChangJia = txtShengChanChangJia;
            }

            public String getTxtShiFouGengHuanBiaoXiang() {
                return txtShiFouGengHuanBiaoXiang;
            }

            public void setTxtShiFouGengHuanBiaoXiang(String txtShiFouGengHuanBiaoXiang) {
                this.txtShiFouGengHuanBiaoXiang = txtShiFouGengHuanBiaoXiang;
            }

            public String getTxtShiFouGengHuanDianBiao() {
                return txtShiFouGengHuanDianBiao;
            }

            public void setTxtShiFouGengHuanDianBiao(String txtShiFouGengHuanDianBiao) {
                this.txtShiFouGengHuanDianBiao = txtShiFouGengHuanDianBiao;
            }

            public String getTxtShiFouJiChaoFuGai() {
                return txtShiFouJiChaoFuGai;
            }

            public void setTxtShiFouJiChaoFuGai(String txtShiFouJiChaoFuGai) {
                this.txtShiFouJiChaoFuGai = txtShiFouJiChaoFuGai;
            }

            public String getTxtShiFouZhengTiGengHuan() {
                return txtShiFouZhengTiGengHuan;
            }

            public void setTxtShiFouZhengTiGengHuan(String txtShiFouZhengTiGengHuan) {
                this.txtShiFouZhengTiGengHuan = txtShiFouZhengTiGengHuan;
            }

            public String getTxtShiFouZhengTiQianYi() {
                return txtShiFouZhengTiQianYi;
            }

            public void setTxtShiFouZhengTiQianYi(String txtShiFouZhengTiQianYi) {
                this.txtShiFouZhengTiQianYi = txtShiFouZhengTiQianYi;
            }

            public String getTxtTaiQuLeiXing() {
                return txtTaiQuLeiXing;
            }

            public void setTxtTaiQuLeiXing(String txtTaiQuLeiXing) {
                this.txtTaiQuLeiXing = txtTaiQuLeiXing;
            }

            public String getTxtTaiQuMingCheng() {
                return txtTaiQuMingCheng;
            }

            public void setTxtTaiQuMingCheng(String txtTaiQuMingCheng) {
                this.txtTaiQuMingCheng = txtTaiQuMingCheng;
            }

            public String getTxtUpdatedAt() {
                return txtUpdatedAt;
            }

            public void setTxtUpdatedAt(String txtUpdatedAt) {
                this.txtUpdatedAt = txtUpdatedAt;
            }

            public String getTxtUserAddName() {
                return txtUserAddName;
            }

            public void setTxtUserAddName(String txtUserAddName) {
                this.txtUserAddName = txtUserAddName;
            }

            public String getTxtUserModifyName() {
                return txtUserModifyName;
            }

            public void setTxtUserModifyName(String txtUserModifyName) {
                this.txtUserModifyName = txtUserModifyName;
            }

            public String getTxtYiChangQingKuang() {
                return txtYiChangQingKuang;
            }

            public void setTxtYiChangQingKuang(String txtYiChangQingKuang) {
                this.txtYiChangQingKuang = txtYiChangQingKuang;
            }

            public String getTxtYingXiaoYongHuZhuangKuang() {
                return txtYingXiaoYongHuZhuangKuang;
            }

            public void setTxtYingXiaoYongHuZhuangKuang(String txtYingXiaoYongHuZhuangKuang) {
                this.txtYingXiaoYongHuZhuangKuang = txtYingXiaoYongHuZhuangKuang;
            }

            public String getTxtYongDianLeiBie() {
                return txtYongDianLeiBie;
            }

            public void setTxtYongDianLeiBie(String txtYongDianLeiBie) {
                this.txtYongDianLeiBie = txtYongDianLeiBie;
            }

            public String getTxtYongDianLiangQingKuang() {
                return txtYongDianLiangQingKuang;
            }

            public void setTxtYongDianLiangQingKuang(String txtYongDianLiangQingKuang) {
                this.txtYongDianLiangQingKuang = txtYongDianLiangQingKuang;
            }

            public String getTxtYongHuLeiBie() {
                return txtYongHuLeiBie;
            }

            public void setTxtYongHuLeiBie(String txtYongHuLeiBie) {
                this.txtYongHuLeiBie = txtYongHuLeiBie;
            }

            public String getTxtYxGengHuanDianBiao() {
                return txtYxGengHuanDianBiao;
            }

            public void setTxtYxGengHuanDianBiao(String txtYxGengHuanDianBiao) {
                this.txtYxGengHuanDianBiao = txtYxGengHuanDianBiao;
            }

            public String getTxtZuiJinChaoBiaoFangShi() {
                return txtZuiJinChaoBiaoFangShi;
            }

            public void setTxtZuiJinChaoBiaoFangShi(String txtZuiJinChaoBiaoFangShi) {
                this.txtZuiJinChaoBiaoFangShi = txtZuiJinChaoBiaoFangShi;
            }

            public String getUserAddId() {
                return userAddId;
            }

            public void setUserAddId(String userAddId) {
                this.userAddId = userAddId;
            }

            public String getUserModifyId() {
                return userModifyId;
            }

            public void setUserModifyId(String userModifyId) {
                this.userModifyId = userModifyId;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }

            public int getYiChangQingKuang() {
                return yiChangQingKuang;
            }

            public void setYiChangQingKuang(int yiChangQingKuang) {
                this.yiChangQingKuang = yiChangQingKuang;
            }

            public String getYiNianHuDianLiang() {
                return yiNianHuDianLiang;
            }

            public void setYiNianHuDianLiang(String yiNianHuDianLiang) {
                this.yiNianHuDianLiang = yiNianHuDianLiang;
            }

            public int getYingXiaoYongHuZhuangKuang() {
                return yingXiaoYongHuZhuangKuang;
            }

            public void setYingXiaoYongHuZhuangKuang(int yingXiaoYongHuZhuangKuang) {
                this.yingXiaoYongHuZhuangKuang = yingXiaoYongHuZhuangKuang;
            }

            public String getYongDianDiZhi() {
                return yongDianDiZhi;
            }

            public void setYongDianDiZhi(String yongDianDiZhi) {
                this.yongDianDiZhi = yongDianDiZhi;
            }

            public int getYongDianLeiBie() {
                return yongDianLeiBie;
            }

            public void setYongDianLeiBie(int yongDianLeiBie) {
                this.yongDianLeiBie = yongDianLeiBie;
            }

            public int getYongDianLiangQingKuang() {
                return yongDianLiangQingKuang;
            }

            public void setYongDianLiangQingKuang(int yongDianLiangQingKuang) {
                this.yongDianLiangQingKuang = yongDianLiangQingKuang;
            }

            public String getYongHuBianHao() {
                return yongHuBianHao;
            }

            public void setYongHuBianHao(String yongHuBianHao) {
                this.yongHuBianHao = yongHuBianHao;
            }

            public String getYongHuHeTongRongLiang() {
                return yongHuHeTongRongLiang;
            }

            public void setYongHuHeTongRongLiang(String yongHuHeTongRongLiang) {
                this.yongHuHeTongRongLiang = yongHuHeTongRongLiang;
            }

            public int getYongHuLeiBie() {
                return yongHuLeiBie;
            }

            public void setYongHuLeiBie(int yongHuLeiBie) {
                this.yongHuLeiBie = yongHuLeiBie;
            }

            public String getYongHuMingCheng() {
                return yongHuMingCheng;
            }

            public void setYongHuMingCheng(String yongHuMingCheng) {
                this.yongHuMingCheng = yongHuMingCheng;
            }

            public int getYxGengHuanDianBiao() {
                return yxGengHuanDianBiao;
            }

            public void setYxGengHuanDianBiao(int yxGengHuanDianBiao) {
                this.yxGengHuanDianBiao = yxGengHuanDianBiao;
            }

            public String getZiChanBianHao() {
                return ziChanBianHao;
            }

            public void setZiChanBianHao(String ziChanBianHao) {
                this.ziChanBianHao = ziChanBianHao;
            }

            public String getZongHeBeiLv() {
                return zongHeBeiLv;
            }

            public void setZongHeBeiLv(String zongHeBeiLv) {
                this.zongHeBeiLv = zongHeBeiLv;
            }

            public int getZuiJinChaoBiaoFangShi() {
                return zuiJinChaoBiaoFangShi;
            }

            public void setZuiJinChaoBiaoFangShi(int zuiJinChaoBiaoFangShi) {
                this.zuiJinChaoBiaoFangShi = zuiJinChaoBiaoFangShi;
            }

            public String getZuiJinChaoBiaoShiJian() {
                return zuiJinChaoBiaoShiJian;
            }

            public void setZuiJinChaoBiaoShiJian(String zuiJinChaoBiaoShiJian) {
                this.zuiJinChaoBiaoShiJian = zuiJinChaoBiaoShiJian;
            }
        }
    }
}
