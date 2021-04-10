package io.github.yezhihao.protostar.util;

import android.content.Context;

import com.tonfun.codecsnetty.NettyApplication;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;
import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;

/**
 * @author yezhihao
 * home https://gitee.com/yezhihao/jt808-server
 */
public class ClassUtils {

    public static List<Class<?>> getClassList(String packageName, Class<? extends Annotation> annotationClass) {
        List<Class<?>> classList = getClassList(packageName);
        Iterator<Class<?>> iterator = classList.iterator();
        while (iterator.hasNext()) {
            Class<?> next = iterator.next();
            if (!next.isAnnotationPresent(annotationClass))
                iterator.remove();
        }
        return classList;
    }

    public static List<Class<?> > getClassName(String packageName){
        List<String >classNameList=new ArrayList<String >();
        List<Class<?>> classList = new LinkedList();
        try {

            DexFile df = new DexFile(NettyApplication.nettyContext.getPackageCodePath());//通过DexFile查找当前的APK中可执行文件
            Enumeration<String> enumeration = df.entries();//获取df中的元素  这里包含了所有可执行的类名 该类名包含了包名+类名的方式
            while (enumeration.hasMoreElements()) {//遍历
                String className = (String) enumeration.nextElement();

                if (className.contains(packageName)) {//在当前所有可执行的类里面查找包含有该包名的所有类
                    classNameList.add(className);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String name : classNameList){
            addClass(classList,name);
        }
        return  classList;
    }

    public static List<Class<?>> getClassList(String packageName) {
        List<Class<?>> classList = new LinkedList();
        String path = packageName.replace(".", "/");
        try {
            Enumeration<URL> urls = ClassUtils.getClassLoader().getResources(path);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();

                if (url != null) {
                    String protocol = url.getProtocol();

                    if (protocol.equals("file")) {
                        addClass(classList, url.toURI().getPath(), packageName);

                    } else if (protocol.equals("jar")) {
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        JarFile jarFile = jarURLConnection.getJarFile();

                        Enumeration<JarEntry> jarEntries = jarFile.entries();
                        while (jarEntries.hasMoreElements()) {

                            JarEntry jarEntry = jarEntries.nextElement();
                            String entryName = jarEntry.getName();

                            if (entryName.startsWith(path) && entryName.endsWith(".class")) {
                                String className = entryName.substring(0, entryName.lastIndexOf(".")).replaceAll("/", ".");
                                addClass(classList, className);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Initial class error!");
        }
        return classList;
    }

    private static void addClass(List<Class<?>> classList, String packagePath, String packageName) {
        try {
            File[] files = new File(packagePath).listFiles(file -> (file.isDirectory() || file.getName().endsWith(".class")));
            if (files != null)
                for (File file : files) {
                    String fileName = file.getName();
                    if (file.isFile()) {
                        String className = fileName.substring(0, fileName.lastIndexOf("."));
                        if (packageName != null) {
                            className = packageName + "." + className;
                        }
                        addClass(classList, className);
                    } else {
                        String subPackagePath = fileName;
                        if (packageName != null) {
                            subPackagePath = packagePath + "/" + subPackagePath;
                        }
                        String subPackageName = fileName;
                        if (packageName != null) {
                            subPackageName = packageName + "." + subPackageName;
                        }
                        addClass(classList, subPackagePath, subPackageName);
                    }
                }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void addClass(List<Class<?>> classList, String className) {
        classList.add(loadClass(className, false));
    }

    public static Class<?> loadClass(String className, boolean isInitialized) {
        try {
            return Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
}