package com.cr.java.exception;

public class UserImpl implements IUser {

    //接口中没有声明异常
    //如果实现类中声明异常会导致编译不通过
    @Override
    public void getUser() {

    }

    //接口中有声明异常
    //实现类中可以不用声明异常
    @Override
    public void getAllUser() {

    }

    public static void main(String[] args) {
        //下面使用多态的方式需要抛出异常
        IUser user1 = new UserImpl();
        //user1.getAllUser();

        //下面的调用不需要抛出异常
        UserImpl user2 = new UserImpl();
        user2.getAllUser();
    }

}
