package me.fingerart.idea.engine.net;

import org.apache.http.cookie.Cookie;

import java.util.Date;

/**
 * Created by Sixape on 2016/10/14 0014.
 */
public class ArtCookie implements Cookie {
    private String name;
    private String value;

    public ArtCookie(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getComment() {
        return null;
    }

    @Override
    public String getCommentURL() {
        return null;
    }

    @Override
    public Date getExpiryDate() {
        return null;
    }

    @Override
    public boolean isPersistent() {
        return false;
    }

    @Override
    public String getDomain() {
        return null;
    }

    @Override
    public String getPath() {
        return null;
    }

    @Override
    public int[] getPorts() {
        return new int[0];
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public boolean isExpired(Date date) {
        return false;
    }
}
