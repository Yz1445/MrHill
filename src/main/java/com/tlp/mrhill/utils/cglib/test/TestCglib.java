package com.tlp.mrhill.utils.cglib.test;

/**
 * @ClassName TestCglib
 * @Description TODO
 * @Author yangzhao
 * @Date 2020/4/2 15:47
 * @Version 1.0
 **/
public class TestCglib {
    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        PlayGame playGame = (PlayGame) cglibProxy.newInstall(new PlayGame());
        playGame.play();
    }
}
