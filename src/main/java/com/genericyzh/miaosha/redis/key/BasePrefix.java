package com.genericyzh.miaosha.redis.key;

/**
 * @author genericyzh
 * @date 2018/10/1 23:40
 */
public abstract class BasePrefix implements KeyPrefix {
    private int expireSeconds;

    private String prefix;

    public BasePrefix(String prefix) {//0代表永不过期
        this(0, prefix);
    }

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds() {//默认0代表永不过期
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }

    @Override
    public String appendPrefix(String... value) {
        StringBuilder append = new StringBuilder();
        for (String s : value) {
            append.append(SPLIT).append(s == null ? "" : s);
        }
        return this.getPrefix() + append.toString();
    }
}
