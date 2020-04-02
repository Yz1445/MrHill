package com.tlp.mrhill.Test;

import java.io.*;

/**
 * @ClassName TestSerializable
 * @Description TODO
 * @Author yangzhao
 * @Date 2020/4/1 16:35
 * @Version 1.0
 **/
public class TestSerializable implements Serializable {

    private static final long serialVersionUID = 5883804285826228758L;
    private int id;
    private String name;
    public TestSerializable(){}
    public TestSerializable(int id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestSerializable [id="+id+",name="+name+"]";
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("TestSerializable.txt"));
        oos.writeObject("测试序列化");
        oos.writeObject(618);
        TestSerializable testSerializable = new TestSerializable(1,"yangzhao");
        oos.writeObject(testSerializable);

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("TestSerializable.txt"));
        System.out.println((String) ois.readObject());
        System.out.println((int) ois.readObject());
        System.out.println((TestSerializable)ois.readObject());
    }
}
