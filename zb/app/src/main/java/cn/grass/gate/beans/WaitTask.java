package cn.grass.gate.beans;

import java.util.List;

/**
 * Created by min on 2017/8/29.
 */

public class WaitTask {

    /**
     * data : {"endRow":6,"first":1,"hasNext":false,"hasPrevious":false,"isFirst":true,"isLast":true,"last":1,"list":[{"bianYaQiBianHao":"1111111111427822","bianYaQiMingCheng":"惠州市中港城实业有限公司(公变)","biaoXiangShuLiang":0,"caiJiQiXiangShuLiang":0,"createdAt":"2017-08-14 10:01:16","dianYuanXianYongLiang":0,"erXingJCaiJiQiShuLiang":0,"erXingJiZhongQiShuLiang":0,"gengHuanBiaoXiangShuLiang":0,"gengHuanDianBiaoShuLiang":0,"gengHuanZhengTiShuLiang":0,"gongDianJuId":"34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17","id":"4cd49b5a-b1f3-472e-b319-ee4c730dfe05","jiuJiZhongQiShuLiang":0,"kanChaHuShu":0,"status":0,"taiQuId":"23f69837-d2c8-4c12-385c-cbf0c7721a1b","taiQuLeiXing":1,"taiQuZongHuShu":192,"txtCreatedAt":"2017-08-14 10:01:16","txtGongDianJu":"博罗供电局","txtTaiQuLeiXing":"小区","txtTaiQuMingCheng":"惠州市中港城实业有限公司(公变)#1","txtUpdatedAt":"2017-08-14 16:08:56","txtUserAddName":"","txtUserModifyName":"","updatedAt":"2017-08-14 04:08:56","version":0,"xianGuanYongLiang":0,"yiXingJiZhongQiShuLiang":0,"yongXianLiang485":0},{"bianYaQiBianHao":"13608G000009","bianYaQiMingCheng":"罗浮山林场公变","biaoXiangShuLiang":0,"caiJiQiXiangShuLiang":0,"createdAt":"2017-08-14 04:06:24","dianYuanXianYongLiang":0,"erXingJCaiJiQiShuLiang":0,"erXingJiZhongQiShuLiang":0,"gengHuanBiaoXiangShuLiang":0,"gengHuanDianBiaoShuLiang":0,"gengHuanZhengTiShuLiang":0,"gongDianJuId":"34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17","id":"1ff13b66-9a08-48e1-8a09-a0daf2fa9c71","jiuJiZhongQiShuLiang":0,"kanChaHuShu":0,"status":0,"taiQuId":"b10774c2-bef9-0b2a-74eb-b119b08466a6","taiQuLeiXing":2,"taiQuZongHuShu":200,"txtCreatedAt":"2017-08-14 16:06:24","txtGongDianJu":"博罗供电局","txtTaiQuLeiXing":"街道","txtTaiQuMingCheng":"罗浮山林场公变","txtUpdatedAt":"2017-08-14 16:17:28","txtUserAddName":"","txtUserModifyName":"","updatedAt":"2017-08-14 04:17:28","version":0,"xianGuanYongLiang":0,"yiXingJiZhongQiShuLiang":0,"yongXianLiang485":0},{"bianYaQiBianHao":"13608G000001","bianYaQiMingCheng":"湖洋公变","biaoXiangShuLiang":0,"caiJiQiXiangShuLiang":0,"createdAt":"2017-08-14 08:21:17","dianYuanXianYongLiang":0,"erXingJCaiJiQiShuLiang":0,"erXingJiZhongQiShuLiang":0,"gengHuanBiaoXiangShuLiang":0,"gengHuanDianBiaoShuLiang":0,"gengHuanZhengTiShuLiang":0,"gongDianJuId":"34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17","id":"670efaeb-e9aa-43fe-b544-1e7cdeef4351","jiuJiZhongQiShuLiang":0,"kanChaHuShu":0,"status":0,"taiQuId":"43ac1324-d090-4b04-56bb-f68e12fd7657","taiQuLeiXing":3,"taiQuZongHuShu":48,"txtCreatedAt":"2017-08-14 20:21:17","txtGongDianJu":"博罗供电局","txtTaiQuLeiXing":"农村","txtTaiQuMingCheng":"湖洋公变","txtUpdatedAt":"","txtUserAddName":"","txtUserModifyName":"","version":0,"xianGuanYongLiang":0,"yiXingJiZhongQiShuLiang":0,"yongXianLiang485":0},{"bianYaQiBianHao":"1111111111990508","bianYaQiMingCheng":"栏水屋塘面公变","biaoXiangShuLiang":0,"caiJiQiXiangShuLiang":0,"createdAt":"2017-08-14 08:33:06","dianYuanXianYongLiang":0,"erXingJCaiJiQiShuLiang":0,"erXingJiZhongQiShuLiang":0,"gengHuanBiaoXiangShuLiang":0,"gengHuanDianBiaoShuLiang":0,"gengHuanZhengTiShuLiang":0,"gongDianJuId":"34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17","id":"8e9d4451-ca3d-47cd-bbe4-4130332e9799","jiuJiZhongQiShuLiang":0,"kanChaHuShu":0,"status":0,"taiQuId":"90568f48-f735-d56e-6004-8ef0273b5f7b","taiQuLeiXing":1,"taiQuZongHuShu":173,"txtCreatedAt":"2017-08-14 20:33:06","txtGongDianJu":"博罗供电局","txtTaiQuLeiXing":"小区","txtTaiQuMingCheng":"栏水屋塘面公变","txtUpdatedAt":"2017-08-14 21:32:22","txtUserAddName":"","txtUserModifyName":"","updatedAt":"2017-08-14 09:32:22","version":0,"xianGuanYongLiang":0,"yiXingJiZhongQiShuLiang":0,"yongXianLiang485":0},{"bianYaQiBianHao":"13ZD0000034211","bianYaQiMingCheng":"连塘岗2公变","biaoXiangShuLiang":0,"caiJiQiXiangShuLiang":0,"createdAt":"2017-08-14 08:35:43","dianYuanXianYongLiang":0,"erXingJCaiJiQiShuLiang":0,"erXingJiZhongQiShuLiang":0,"gengHuanBiaoXiangShuLiang":0,"gengHuanDianBiaoShuLiang":0,"gengHuanZhengTiShuLiang":0,"gongDianJuId":"34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17","id":"9ef1159b-0531-4dcc-8a46-d959991d434a","jiuJiZhongQiShuLiang":0,"kanChaHuShu":0,"status":0,"taiQuId":"128b1090-36c6-3774-a740-ad35e992e3cd","taiQuLeiXing":3,"taiQuZongHuShu":52,"txtCreatedAt":"2017-08-14 20:35:43","txtGongDianJu":"博罗供电局","txtTaiQuLeiXing":"农村","txtTaiQuMingCheng":"莲塘2公变","txtUpdatedAt":"","txtUserAddName":"","txtUserModifyName":"","version":0,"xianGuanYongLiang":0,"yiXingJiZhongQiShuLiang":0,"yongXianLiang485":0},{"bianYaQiBianHao":"13608G000103","bianYaQiMingCheng":"立新工区公变","biaoXiangShuLiang":0,"caiJiQiXiangShuLiang":0,"createdAt":"2017-08-15 08:49:28","dianYuanXianYongLiang":0,"erXingJCaiJiQiShuLiang":0,"erXingJiZhongQiShuLiang":0,"gengHuanBiaoXiangShuLiang":0,"gengHuanDianBiaoShuLiang":1,"gengHuanZhengTiShuLiang":0,"gongDianJuId":"34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17","id":"eb3092fe-862f-4232-b635-e78e8522baa3","jiuJiZhongQiShuLiang":0,"kanChaHuShu":0,"status":0,"taiQuId":"a887b737-1dd6-f8b0-dbd9-ff91a9392e34","taiQuLeiXing":3,"taiQuZongHuShu":5,"txtCreatedAt":"2017-08-15 08:49:28","txtGongDianJu":"博罗供电局","txtTaiQuLeiXing":"农村","txtTaiQuMingCheng":"立新工区公变","txtUpdatedAt":"","txtUserAddName":"","txtUserModifyName":"","version":0,"xianGuanYongLiang":0,"yiXingJiZhongQiShuLiang":0,"yongXianLiang485":0}],"navigatePages":8,"navigatepageNums":[1],"next":0,"pageIndex":1,"pageSize":10,"pages":1,"preview":0,"size":6,"startRow":1,"total":6}
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
         * endRow : 6
         * first : 1
         * hasNext : false
         * hasPrevious : false
         * isFirst : true
         * isLast : true
         * last : 1
         * list : [{"bianYaQiBianHao":"1111111111427822","bianYaQiMingCheng":"惠州市中港城实业有限公司(公变)","biaoXiangShuLiang":0,"caiJiQiXiangShuLiang":0,"createdAt":"2017-08-14 10:01:16","dianYuanXianYongLiang":0,"erXingJCaiJiQiShuLiang":0,"erXingJiZhongQiShuLiang":0,"gengHuanBiaoXiangShuLiang":0,"gengHuanDianBiaoShuLiang":0,"gengHuanZhengTiShuLiang":0,"gongDianJuId":"34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17","id":"4cd49b5a-b1f3-472e-b319-ee4c730dfe05","jiuJiZhongQiShuLiang":0,"kanChaHuShu":0,"status":0,"taiQuId":"23f69837-d2c8-4c12-385c-cbf0c7721a1b","taiQuLeiXing":1,"taiQuZongHuShu":192,"txtCreatedAt":"2017-08-14 10:01:16","txtGongDianJu":"博罗供电局","txtTaiQuLeiXing":"小区","txtTaiQuMingCheng":"惠州市中港城实业有限公司(公变)#1","txtUpdatedAt":"2017-08-14 16:08:56","txtUserAddName":"","txtUserModifyName":"","updatedAt":"2017-08-14 04:08:56","version":0,"xianGuanYongLiang":0,"yiXingJiZhongQiShuLiang":0,"yongXianLiang485":0},{"bianYaQiBianHao":"13608G000009","bianYaQiMingCheng":"罗浮山林场公变","biaoXiangShuLiang":0,"caiJiQiXiangShuLiang":0,"createdAt":"2017-08-14 04:06:24","dianYuanXianYongLiang":0,"erXingJCaiJiQiShuLiang":0,"erXingJiZhongQiShuLiang":0,"gengHuanBiaoXiangShuLiang":0,"gengHuanDianBiaoShuLiang":0,"gengHuanZhengTiShuLiang":0,"gongDianJuId":"34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17","id":"1ff13b66-9a08-48e1-8a09-a0daf2fa9c71","jiuJiZhongQiShuLiang":0,"kanChaHuShu":0,"status":0,"taiQuId":"b10774c2-bef9-0b2a-74eb-b119b08466a6","taiQuLeiXing":2,"taiQuZongHuShu":200,"txtCreatedAt":"2017-08-14 16:06:24","txtGongDianJu":"博罗供电局","txtTaiQuLeiXing":"街道","txtTaiQuMingCheng":"罗浮山林场公变","txtUpdatedAt":"2017-08-14 16:17:28","txtUserAddName":"","txtUserModifyName":"","updatedAt":"2017-08-14 04:17:28","version":0,"xianGuanYongLiang":0,"yiXingJiZhongQiShuLiang":0,"yongXianLiang485":0},{"bianYaQiBianHao":"13608G000001","bianYaQiMingCheng":"湖洋公变","biaoXiangShuLiang":0,"caiJiQiXiangShuLiang":0,"createdAt":"2017-08-14 08:21:17","dianYuanXianYongLiang":0,"erXingJCaiJiQiShuLiang":0,"erXingJiZhongQiShuLiang":0,"gengHuanBiaoXiangShuLiang":0,"gengHuanDianBiaoShuLiang":0,"gengHuanZhengTiShuLiang":0,"gongDianJuId":"34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17","id":"670efaeb-e9aa-43fe-b544-1e7cdeef4351","jiuJiZhongQiShuLiang":0,"kanChaHuShu":0,"status":0,"taiQuId":"43ac1324-d090-4b04-56bb-f68e12fd7657","taiQuLeiXing":3,"taiQuZongHuShu":48,"txtCreatedAt":"2017-08-14 20:21:17","txtGongDianJu":"博罗供电局","txtTaiQuLeiXing":"农村","txtTaiQuMingCheng":"湖洋公变","txtUpdatedAt":"","txtUserAddName":"","txtUserModifyName":"","version":0,"xianGuanYongLiang":0,"yiXingJiZhongQiShuLiang":0,"yongXianLiang485":0},{"bianYaQiBianHao":"1111111111990508","bianYaQiMingCheng":"栏水屋塘面公变","biaoXiangShuLiang":0,"caiJiQiXiangShuLiang":0,"createdAt":"2017-08-14 08:33:06","dianYuanXianYongLiang":0,"erXingJCaiJiQiShuLiang":0,"erXingJiZhongQiShuLiang":0,"gengHuanBiaoXiangShuLiang":0,"gengHuanDianBiaoShuLiang":0,"gengHuanZhengTiShuLiang":0,"gongDianJuId":"34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17","id":"8e9d4451-ca3d-47cd-bbe4-4130332e9799","jiuJiZhongQiShuLiang":0,"kanChaHuShu":0,"status":0,"taiQuId":"90568f48-f735-d56e-6004-8ef0273b5f7b","taiQuLeiXing":1,"taiQuZongHuShu":173,"txtCreatedAt":"2017-08-14 20:33:06","txtGongDianJu":"博罗供电局","txtTaiQuLeiXing":"小区","txtTaiQuMingCheng":"栏水屋塘面公变","txtUpdatedAt":"2017-08-14 21:32:22","txtUserAddName":"","txtUserModifyName":"","updatedAt":"2017-08-14 09:32:22","version":0,"xianGuanYongLiang":0,"yiXingJiZhongQiShuLiang":0,"yongXianLiang485":0},{"bianYaQiBianHao":"13ZD0000034211","bianYaQiMingCheng":"连塘岗2公变","biaoXiangShuLiang":0,"caiJiQiXiangShuLiang":0,"createdAt":"2017-08-14 08:35:43","dianYuanXianYongLiang":0,"erXingJCaiJiQiShuLiang":0,"erXingJiZhongQiShuLiang":0,"gengHuanBiaoXiangShuLiang":0,"gengHuanDianBiaoShuLiang":0,"gengHuanZhengTiShuLiang":0,"gongDianJuId":"34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17","id":"9ef1159b-0531-4dcc-8a46-d959991d434a","jiuJiZhongQiShuLiang":0,"kanChaHuShu":0,"status":0,"taiQuId":"128b1090-36c6-3774-a740-ad35e992e3cd","taiQuLeiXing":3,"taiQuZongHuShu":52,"txtCreatedAt":"2017-08-14 20:35:43","txtGongDianJu":"博罗供电局","txtTaiQuLeiXing":"农村","txtTaiQuMingCheng":"莲塘2公变","txtUpdatedAt":"","txtUserAddName":"","txtUserModifyName":"","version":0,"xianGuanYongLiang":0,"yiXingJiZhongQiShuLiang":0,"yongXianLiang485":0},{"bianYaQiBianHao":"13608G000103","bianYaQiMingCheng":"立新工区公变","biaoXiangShuLiang":0,"caiJiQiXiangShuLiang":0,"createdAt":"2017-08-15 08:49:28","dianYuanXianYongLiang":0,"erXingJCaiJiQiShuLiang":0,"erXingJiZhongQiShuLiang":0,"gengHuanBiaoXiangShuLiang":0,"gengHuanDianBiaoShuLiang":1,"gengHuanZhengTiShuLiang":0,"gongDianJuId":"34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17","id":"eb3092fe-862f-4232-b635-e78e8522baa3","jiuJiZhongQiShuLiang":0,"kanChaHuShu":0,"status":0,"taiQuId":"a887b737-1dd6-f8b0-dbd9-ff91a9392e34","taiQuLeiXing":3,"taiQuZongHuShu":5,"txtCreatedAt":"2017-08-15 08:49:28","txtGongDianJu":"博罗供电局","txtTaiQuLeiXing":"农村","txtTaiQuMingCheng":"立新工区公变","txtUpdatedAt":"","txtUserAddName":"","txtUserModifyName":"","version":0,"xianGuanYongLiang":0,"yiXingJiZhongQiShuLiang":0,"yongXianLiang485":0}]
         * navigatePages : 8
         * navigatepageNums : [1]
         * next : 0
         * pageIndex : 1
         * pageSize : 10
         * pages : 1
         * preview : 0
         * size : 6
         * startRow : 1
         * total : 6
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

        public static class ListBean {
            @Override
            public String toString() {
                return "ListBean{" +
                        "bianYaQiBianHao='" + bianYaQiBianHao + '\'' +
                        ", bianYaQiMingCheng='" + bianYaQiMingCheng + '\'' +
                        ", biaoXiangShuLiang=" + biaoXiangShuLiang +
                        ", caiJiQiXiangShuLiang=" + caiJiQiXiangShuLiang +
                        ", createdAt='" + createdAt + '\'' +
                        ", dianYuanXianYongLiang=" + dianYuanXianYongLiang +
                        ", erXingJCaiJiQiShuLiang=" + erXingJCaiJiQiShuLiang +
                        ", erXingJiZhongQiShuLiang=" + erXingJiZhongQiShuLiang +
                        ", gengHuanBiaoXiangShuLiang=" + gengHuanBiaoXiangShuLiang +
                        ", gengHuanDianBiaoShuLiang=" + gengHuanDianBiaoShuLiang +
                        ", gengHuanZhengTiShuLiang=" + gengHuanZhengTiShuLiang +
                        ", gongDianJuId='" + gongDianJuId + '\'' +
                        ", id='" + id + '\'' +
                        ", jiuJiZhongQiShuLiang=" + jiuJiZhongQiShuLiang +
                        ", kanChaHuShu=" + kanChaHuShu +
                        ", status=" + status +
                        ", taiQuId='" + taiQuId + '\'' +
                        ", taiQuLeiXing=" + taiQuLeiXing +
                        ", taiQuZongHuShu=" + taiQuZongHuShu +
                        ", txtCreatedAt='" + txtCreatedAt + '\'' +
                        ", txtGongDianJu='" + txtGongDianJu + '\'' +
                        ", txtTaiQuLeiXing='" + txtTaiQuLeiXing + '\'' +
                        ", txtTaiQuMingCheng='" + txtTaiQuMingCheng + '\'' +
                        ", txtUpdatedAt='" + txtUpdatedAt + '\'' +
                        ", txtUserAddName='" + txtUserAddName + '\'' +
                        ", txtUserModifyName='" + txtUserModifyName + '\'' +
                        ", updatedAt='" + updatedAt + '\'' +
                        ", version=" + version +
                        ", xianGuanYongLiang=" + xianGuanYongLiang +
                        ", yiXingJiZhongQiShuLiang=" + yiXingJiZhongQiShuLiang +
                        ", yongXianLiang485=" + yongXianLiang485 +
                        '}';
            }

            /**
             * bianYaQiBianHao : 1111111111427822
             * bianYaQiMingCheng : 惠州市中港城实业有限公司(公变)
             * biaoXiangShuLiang : 0
             * caiJiQiXiangShuLiang : 0
             * createdAt : 2017-08-14 10:01:16
             * dianYuanXianYongLiang : 0
             * erXingJCaiJiQiShuLiang : 0
             * erXingJiZhongQiShuLiang : 0
             * gengHuanBiaoXiangShuLiang : 0
             * gengHuanDianBiaoShuLiang : 0
             * gengHuanZhengTiShuLiang : 0
             * gongDianJuId : 34bb9fdb-4c7b-d3c2-37aa-24a7efd1cd17
             * id : 4cd49b5a-b1f3-472e-b319-ee4c730dfe05
             * jiuJiZhongQiShuLiang : 0
             * kanChaHuShu : 0
             * status : 0
             * taiQuId : 23f69837-d2c8-4c12-385c-cbf0c7721a1b
             * taiQuLeiXing : 1
             * taiQuZongHuShu : 192
             * txtCreatedAt : 2017-08-14 10:01:16
             * txtGongDianJu : 博罗供电局
             * txtTaiQuLeiXing : 小区
             * txtTaiQuMingCheng : 惠州市中港城实业有限公司(公变)#1
             * txtUpdatedAt : 2017-08-14 16:08:56
             * txtUserAddName :
             * txtUserModifyName :
             * updatedAt : 2017-08-14 04:08:56
             * version : 0
             * xianGuanYongLiang : 0
             * yiXingJiZhongQiShuLiang : 0
             * yongXianLiang485 : 0
             */

            private String bianYaQiBianHao;
            private String bianYaQiMingCheng;
            private int biaoXiangShuLiang;
            private int caiJiQiXiangShuLiang;
            private String createdAt;
            private int dianYuanXianYongLiang;
            private int erXingJCaiJiQiShuLiang;
            private int erXingJiZhongQiShuLiang;
            private int gengHuanBiaoXiangShuLiang;
            private int gengHuanDianBiaoShuLiang;
            private int gengHuanZhengTiShuLiang;
            private String gongDianJuId;
            private String id;
            private int jiuJiZhongQiShuLiang;
            private int kanChaHuShu;
            private int status;
            private String taiQuId;
            private int taiQuLeiXing;
            private int taiQuZongHuShu;
            private String txtCreatedAt;
            private String txtGongDianJu;
            private String txtTaiQuLeiXing;
            private String txtTaiQuMingCheng;
            private String txtUpdatedAt;
            private String txtUserAddName;
            private String txtUserModifyName;
            private String updatedAt;
            private int version;
            private int xianGuanYongLiang;
            private int yiXingJiZhongQiShuLiang;
            private int yongXianLiang485;

            public String getBianYaQiBianHao() {
                return bianYaQiBianHao;
            }

            public void setBianYaQiBianHao(String bianYaQiBianHao) {
                this.bianYaQiBianHao = bianYaQiBianHao;
            }

            public String getBianYaQiMingCheng() {
                return bianYaQiMingCheng;
            }

            public void setBianYaQiMingCheng(String bianYaQiMingCheng) {
                this.bianYaQiMingCheng = bianYaQiMingCheng;
            }

            public int getBiaoXiangShuLiang() {
                return biaoXiangShuLiang;
            }

            public void setBiaoXiangShuLiang(int biaoXiangShuLiang) {
                this.biaoXiangShuLiang = biaoXiangShuLiang;
            }

            public int getCaiJiQiXiangShuLiang() {
                return caiJiQiXiangShuLiang;
            }

            public void setCaiJiQiXiangShuLiang(int caiJiQiXiangShuLiang) {
                this.caiJiQiXiangShuLiang = caiJiQiXiangShuLiang;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public int getDianYuanXianYongLiang() {
                return dianYuanXianYongLiang;
            }

            public void setDianYuanXianYongLiang(int dianYuanXianYongLiang) {
                this.dianYuanXianYongLiang = dianYuanXianYongLiang;
            }

            public int getErXingJCaiJiQiShuLiang() {
                return erXingJCaiJiQiShuLiang;
            }

            public void setErXingJCaiJiQiShuLiang(int erXingJCaiJiQiShuLiang) {
                this.erXingJCaiJiQiShuLiang = erXingJCaiJiQiShuLiang;
            }

            public int getErXingJiZhongQiShuLiang() {
                return erXingJiZhongQiShuLiang;
            }

            public void setErXingJiZhongQiShuLiang(int erXingJiZhongQiShuLiang) {
                this.erXingJiZhongQiShuLiang = erXingJiZhongQiShuLiang;
            }

            public int getGengHuanBiaoXiangShuLiang() {
                return gengHuanBiaoXiangShuLiang;
            }

            public void setGengHuanBiaoXiangShuLiang(int gengHuanBiaoXiangShuLiang) {
                this.gengHuanBiaoXiangShuLiang = gengHuanBiaoXiangShuLiang;
            }

            public int getGengHuanDianBiaoShuLiang() {
                return gengHuanDianBiaoShuLiang;
            }

            public void setGengHuanDianBiaoShuLiang(int gengHuanDianBiaoShuLiang) {
                this.gengHuanDianBiaoShuLiang = gengHuanDianBiaoShuLiang;
            }

            public int getGengHuanZhengTiShuLiang() {
                return gengHuanZhengTiShuLiang;
            }

            public void setGengHuanZhengTiShuLiang(int gengHuanZhengTiShuLiang) {
                this.gengHuanZhengTiShuLiang = gengHuanZhengTiShuLiang;
            }

            public String getGongDianJuId() {
                return gongDianJuId;
            }

            public void setGongDianJuId(String gongDianJuId) {
                this.gongDianJuId = gongDianJuId;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getJiuJiZhongQiShuLiang() {
                return jiuJiZhongQiShuLiang;
            }

            public void setJiuJiZhongQiShuLiang(int jiuJiZhongQiShuLiang) {
                this.jiuJiZhongQiShuLiang = jiuJiZhongQiShuLiang;
            }

            public int getKanChaHuShu() {
                return kanChaHuShu;
            }

            public void setKanChaHuShu(int kanChaHuShu) {
                this.kanChaHuShu = kanChaHuShu;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
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

            public int getTaiQuZongHuShu() {
                return taiQuZongHuShu;
            }

            public void setTaiQuZongHuShu(int taiQuZongHuShu) {
                this.taiQuZongHuShu = taiQuZongHuShu;
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

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }

            public int getXianGuanYongLiang() {
                return xianGuanYongLiang;
            }

            public void setXianGuanYongLiang(int xianGuanYongLiang) {
                this.xianGuanYongLiang = xianGuanYongLiang;
            }

            public int getYiXingJiZhongQiShuLiang() {
                return yiXingJiZhongQiShuLiang;
            }

            public void setYiXingJiZhongQiShuLiang(int yiXingJiZhongQiShuLiang) {
                this.yiXingJiZhongQiShuLiang = yiXingJiZhongQiShuLiang;
            }

            public int getYongXianLiang485() {
                return yongXianLiang485;
            }

            public void setYongXianLiang485(int yongXianLiang485) {
                this.yongXianLiang485 = yongXianLiang485;
            }
        }
    }
}
