package com.tlp.mrhill.Test;

import com.tlp.mrhill.model.User;
import com.tlp.mrhill.utils.DeepCloneUtil;

import java.io.*;
import java.util.Objects;

/**
 * @ClassName TestCloneable
 * @Description TODO
 * @Author yangzhao
 * @Date 2020/4/2 16:05
 * @Version 1.0
 **/
public class TestCloneable {
    static class Address implements Serializable{
        private String Type;
        private String vlaue;

        @Override
        public String toString() {
            return "Address[Type="+this.Type+",vlaue="+this.vlaue+"]";
        }

        public String getType() {
            return Type;
        }

        public void setType(String type) {
            Type = type;
        }

        public String getVlaue() {
            return vlaue;
        }

        public void setVlaue(String vlaue) {
            this.vlaue = vlaue;
        }
    }
    static class objectA implements Cloneable{
        private Integer id;
        private String Name;
        private  Address address;

        @Override
        public String toString() {
            return "objectA[id="+id+",Name="+Name+"address="+address.toString()+"]";
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    static class objectB implements Serializable{
        private static final long serialVersionUID = -2273502212411632747L;
        private Integer id;
        private String Name;
        private  Address address;
        @Override
        public String toString() {
            return "objectA[id="+id+",Name="+Name+"address="+address.toString()+"]";
        }
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public objectB deepClone() throws IOException {
            objectB ob1 = null;
            objectB ob2 = this;
            PipedOutputStream ou = new PipedOutputStream();
            PipedInputStream in = new PipedInputStream();
            in.connect(ou);
            try (ObjectOutputStream bo = new ObjectOutputStream(ou);
            ObjectInputStream bi = new ObjectInputStream(in)){
                bo.writeObject(ob2);
                ob1 = (objectB) bi.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return ob1;
        }
    }
    /**
     * @author yangzhao
     * @Description 克隆方法是浅克隆被复制对象的所有值属性都含有与原来对象的相同，而所有的对象引用属性仍然指向原来的对象。
     * @Date 16:21 2020/4/2
     * @Param [args]
     * @return void
     **/
    public static void main(String[] args) throws Exception {
        TestDeepCloneUtil();

    }
    //浅克隆测试方法
    public static void TestClone(){
        objectA a = new objectA();
        a.setName("简单类型测试浅克隆！");
        Address address = new Address();
        address.setVlaue("复杂类型！");
        a.setAddress(address);
        objectA b = null;
        try {
            b = (objectA) a.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
//        objectA b = a;
        b.setName("修改简单类型测试浅克隆！");
        b.address.setVlaue("修改复杂类型！");
        System.out.println(a.toString());
        System.out.println(b.toString());
    }
    //深克隆测试方法
    public static void TestDeepClone() throws IOException {
        objectB b = new objectB();
        b.setName("深克隆测试");
        Address address = new Address();
        address.setVlaue("引用对象");
        b.setAddress(address);
        objectB b2 = b.deepClone();
        b2.setName("深克隆测试修改");
        b2.address.setVlaue("引用对象修改");
        System.out.println(b2.toString());
        System.out.println(b.toString());
    }
    //深克隆工具类测试方法
    public static void TestDeepCloneUtil() throws Exception {
        objectB b = new objectB();
        b.setName("深克隆测试");
        Address address = new Address();
        address.setVlaue("引用对象");
        b.setAddress(address);
        objectB b2 = DeepCloneUtil.clone(b);
        b2.setName("深克隆测试修改");
        b2.address.setVlaue("引用对象修改");
        System.out.println(b.toString());
        System.out.println(b2.toString());
    }
}
