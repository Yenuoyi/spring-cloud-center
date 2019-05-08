package com.yb.common.center.wrap;

/**
 * @author yebing
 */
public class WrapMapper {
    private WrapMapper() {
    }

    public static <E> Wrapper<E> wrap(int code, String message, E o) {
        return new Wrapper(code, message, o);
    }

    public static <E> Wrapper<E> wrap(int code, String message) {
        return wrap(code, message, null);
    }

    public static <E> Wrapper<E> wrap(int code) {
        return wrap(code, (String)null);
    }

    public static <E> Wrapper<E> wrap(Exception e) {
        return new Wrapper(500, e.getMessage());
    }

    public static <E> E unWrap(Wrapper<E> wrapper) {
        return wrapper.getResult();
    }

    public static <E> Wrapper<E> illegalArgument() {
        return wrap(400, "参数非法");
    }

    public static <E> Wrapper<E> error() {
        return wrap(500, "内部异常");
    }

    public static <E> Wrapper<E> ok() {
        return new Wrapper();
    }
}