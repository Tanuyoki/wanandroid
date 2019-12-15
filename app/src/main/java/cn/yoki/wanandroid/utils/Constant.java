package cn.yoki.wanandroid.utils;

public class Constant {

    public static String API_CENTER = "https://www.wanandroid.com";

    public static class API {

        // 首页 BANNER
        public static String HOME_BANNER = API_CENTER + "/banner/json";
        // 文章列表
        public static String HOME_ARTICLE_LIST = API_CENTER + "/article/list/0/json";
        // 置顶文章
        public static String HOME_ARTICLE_TOP = API_CENTER + "/article/top/json";
        // 搜索热词
        public static String HOME_HOTKEY = API_CENTER + "/hotkey/json";
        // 常用网站
        public static String HOME_COMMON_WEBSITE = API_CENTER + "/friend/json";

        // 体系数据
        public static String TREE_DATA = API_CENTER + "/tree/json";
        // 体系文章
        public static String TREE_ARTICLE_LIST = API_CENTER + "/article/list/0/json";

        // 导航数据
        public static String NAVI_DATA = API_CENTER + "/navi/json";

        // 项目分类
        public static String PROJECT_CLASSIFICATION = API_CENTER + "/project/tree/json";
        // 项目列表数据
        public static String PROJECT_LIST = API_CENTER + "/project/list/1/json";

        // 公众号列表
        public static String WX_ARTICLE_LIST = API_CENTER + "/wxarticle/chapters/json";
        // 公众号数据
        public static String WX_ARTICLE_DATA = API_CENTER + "/wxarticle/list/408/1/json";
        // 公众号搜索
        public static String WX_ARTICLE_GET = API_CENTER + "/wxarticle/list/405/1/json";

        // 最新项目
        public static String NEW_PROJECT_LIST = API_CENTER + "/article/listproject/0/json";


    }

}
