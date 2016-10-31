package com.panda.teller;

/**
 * Created by root on 16-10-30.
 * 作为sendHttpRequest方法的参数传入
 * 当正常返回数据（json）时会调用onResponse方法，将对返回的数据的处理逻辑写到这个方法里
 * 当发生错误时会调用onError方法，将错误处理逻辑写到这个方法里
 */

public interface HttpResponseListener {

    void onResponse(String response);

    void onError(Exception e);

}
