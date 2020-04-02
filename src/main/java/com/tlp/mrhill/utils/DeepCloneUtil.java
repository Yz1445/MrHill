package com.tlp.mrhill.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @ClassName DeepCloneUtil
 * @Description TODO
 * @Author yangzhao
 * @Date 2020/4/2 17:00
 * @Version 1.0
 **/
public class DeepCloneUtil {
    private DeepCloneUtil(){
        throw new AssertionError();
    }
    public static <T> T clone(T obj) throws Exception{
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bout);
        oos.writeObject(obj);
        ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bin);
        return (T) ois.readObject();
    }
}
