package com.github.sparkzxl.core.support;

/**
 * description：断言异常全局处理
 *
 * @author zhouxinlei
 * @date 2020/6/4 10:10 下午
 */
public class SparkZxlExceptionAssert {


    public static void businessFail(String message) {
        throw new BusinessException(ResponseResultStatus.FAILURE, null, message);
    }

}
