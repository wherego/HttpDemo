package com.zhao.httpdemo;

import com.zhao.httpdemo.net.HttpUtil;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("password", "12345");
        params.put("phone_number", "3413999999999");
        params.put("type", "1");
        String json = HttpUtil.hashMapToJson(params);
        System.out.println(json);
        assertEquals(4, 2 + 2);
    }
}