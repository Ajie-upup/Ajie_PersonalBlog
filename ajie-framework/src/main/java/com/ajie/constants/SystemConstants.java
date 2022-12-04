package com.ajie.constants;

public class SystemConstants {
    /**
     * 文章是草稿
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;
    /**
     * 文章是正常发布状态
     * 0 - 已发布 ，1 - 未发布
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;

    /**
     * 分类正常状态
     * “0” - 正常
     */
    public static final String CATEGORY_STATUS_NORMAL = "0";
    /**
     * 审核状态 (0代表审核通过，1代表审核未通过，2代表未审核)
     */
    public static final String LINK_AUDIT_PASS = "0";
    /**
     * 用户前台登录前缀
     */
    public static final String BLOG_LOGIN = "bloglogin:";
    /**
     * 用户请求携带的token
     */
    public static final String TOKEN = "token";

    /**
     * 文章评论为根评论
     */
    public static final long ARTICLE_ROOT_ID = -1;

    /**
     * 评论类型
     * 1 --- 友链表评论
     * 0 --- 文章评论
     */
    public static final String ARTICLE_COMMENT = "0";
    public static final String LINK_COMMENT = "1";

    public static final String UPDATE_ARTICLE_VIEW_COUNT = "article:viewCount";
    public static final Long IS_ADMIN = 1L;
    public static final String MENU = "C";
    public static final String BUTTON = "F";
    public static final String MENU_STATUS_NORMAL = "0";
    public static final String ADMIN_LOGIN = "adminLogin:";

    /**
     * 正常状态
     */
    public static final String NORMAL = "0";
    public static final String ADMIN = "1";
}