package io.chengguo.apidebugger.ui.iview;

import java.util.Map;

public interface HttpInterf {

        /**
         * 获取Method
         *
         * @return
         */
        String method();

        /**
         * 获取URL
         *
         * @return
         */
        String url();

        /**
         * 获取请求头
         *
         * @return
         */
        Map<String, String> headers();

        String bodyType();

        Map<String, String> bodyFormData();

        Map<String, String> bodyUrlencode();

        Map<String, String> bodyRaw();

        String bodyBinary();


    }