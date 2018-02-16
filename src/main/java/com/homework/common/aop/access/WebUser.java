package com.homework.common.aop.access;

/**
 * @author：ldy on 16/02/2018 19:35
 */
public class WebUser {
    private static ThreadLocal<WebUser> webUserThreadLocal = new ThreadLocal();

    private Long userId;

    public WebUser(Long userId) {
        this.userId = userId;
    }

    public static WebUser getWebUser() {
        return (WebUser) webUserThreadLocal.get();
    }

    public static void setWebUser(WebUser webUser) {
        webUserThreadLocal.set(webUser);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
