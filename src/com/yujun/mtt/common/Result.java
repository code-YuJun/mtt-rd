package com.yujun.mtt.common;

/**
 * ClassName: Result
 * Package: com.yujun.mtt.common
 */
/**
 * 全局统一返回结果类
 */
public class Result<T> {
    // 返回码
    private Integer code;
    // 返回消息
    private String message;
    // 返回数据
    private T data;

    public Result(){}

    // 返回数据
    /**
    * 写了这个方法，就不用每次手动 new Result<T>(); 和 result.setData(data); 了
     * User user = new User();
     * Result<User> result = build(user);
    */
    protected static <T> Result<T> build(T data) {
        Result<T> result = new Result<T>();
        if (data != null) result.setData(data);
        return result;
    }

    /**
     * 构建返回结果：data、code、message
     *  Result.build(user,200,"成功");
     */
    public static <T> Result<T> build(T body, Integer code, String message) {
        Result<T> result = build(body);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    /**
     *  Result.build(user,ResultCodeEnum.SUCCESS);
     */
    public static <T> Result<T> build(T body, ResultCodeEnum resultCodeEnum) {
        Result<T> result = build(body);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }

    /**
     * 操作成功
     * @param data  baseCategory1List
     * @param <T>
     * @return
     */
    public static<T> Result<T> ok(T data){
        Result<T> result = build(data);
        return build(data, ResultCodeEnum.SUCCESS);
    }
    // 链式调用
    /**
     * Result.ok(user).message("成功").code(201);
     * 相当于：
     * Result<User> result = Result.ok(user);
     * result.setMessage("登录成功");
     * result.setCode(201);
     * return result;
     */
    public Result<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}