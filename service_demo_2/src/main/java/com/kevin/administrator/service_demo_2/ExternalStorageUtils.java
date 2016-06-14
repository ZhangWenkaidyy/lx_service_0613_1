package com.kevin.administrator.service_demo_2;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 操作外部存储文件的工具类
 */
public class ExternalStorageUtils {
    /**
     * 判断外部存储设备是否可用
     * @return 是否可用
     */
    public static boolean isExternalStorageUseful(){
        boolean bl=false;
        //获取外部存储设备的访问状态  Environment环境
        String state=Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)){
            bl=true;
        }
        return bl;
    }

    /**
     * 根据type类型构建file判断文件是否存在
     * @param type  类型
     * @return 是否存在
     */
    public static boolean hasExternalStorageType(String type){
        boolean bl=false;
        if(type!=null && !"".equals(type)){// null  " "
            File file=Environment.getExternalStoragePublicDirectory(type);
            if(file.exists()){//判断文件是否存在
                bl=true;
            }
        }
        return bl;
    }

    /**
     * 写入数据到外部存储的公共路径下
     * @param type  文件夹的类型
     * @param fileName 文件名称
     * @param content 文件内容
     * @return  写入是够成功
     */
    public static boolean writeExternalStoragePublic(String type,String fileName,byte[] content){
        boolean bl=false;
        if(isExternalStorageUseful()){//外部设备可用
           if(hasExternalStorageType(type)){// type类型的路径存在
               try {
                   //getExternalStoragePublicDirectory(String type)
                   // 根据参数指定的类型构建外部存储的公共路径的file对象
                   // 注意:Type不能为null   指定的type表示sdcard下的文件夹
                   File parenFile=Environment.getExternalStoragePublicDirectory(type);//storage/sdcard/download/
                   File file=new File(parenFile,fileName);//根据路径、文件名构建完整的路径
                   FileOutputStream outputStream=new FileOutputStream(file);
                   outputStream.write(content);
                   outputStream.close();
                   bl=true;
               }catch (Exception e){
                   e.printStackTrace();
               }
           }
        }
        return bl;
    }

    /**
     * 读取外部存储公共路径下的文件
     * @param type  文件夹类型
     * @param fileName 文件名称
     * @return  文件的字节数组
     */
    public static byte[] readExternalStoragePublic(String type,String fileName){
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        if(isExternalStorageUseful()){
            if(hasExternalStorageType(type)){
                try {
                    File parentFile=Environment.getExternalStoragePublicDirectory(type);
                    File file=new File(parentFile,fileName);
                    FileInputStream inputStream=new FileInputStream(file);
                    int temp=0;
                    byte[] buff=new byte[1024];
                    while((temp=inputStream.read(buff))!=-1){
                        outputStream.write(buff,0,temp);
                    }
                    inputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return outputStream.toByteArray();
    }

    /**
     * 向外部存储的私有路径写入数据
     * @param context  上下文
     * @param type 文件夹类型
     * @param fileName  文件的名称
     * @param content 文件的字节数组
     * @return  是否写入成功
     *
     * getExternalFilesDir(type) type为null和不为null的区别
     * type表示写入文件夹的名称
     *  如果指定type  会根据type的名称创建文件夹并且写入数据
     *
     *  例如:type Environment.DIRECTORY_DOWNLOADS
     *
     *  storage/sdcard/Android/data/应用程序包名/files/download/xxx
     *
     *  type 设置为null    storage/sdcard/Android/data/应用程序包名/files/xx
     */
    public static boolean writeExternalStoragePrivate(Context context,String type,
                                                      String fileName,byte[] content){
        boolean bl=false;
        if(isExternalStorageUseful()){
            try {
                //getExternalFilesDir() 获取外部存储私有路径的file对象  type 指定文件夹的类型  type可以为null
                File parentFile=context.getExternalFilesDir(type);
                File file=new File(parentFile,fileName);
                FileOutputStream outputStream=new FileOutputStream(file);
                outputStream.write(content);
                outputStream.close();
                bl=true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return bl;
    }

    /**
     * 读取外部存储私有路径下的文件
     * @param context  上下文对象
     * @param type  文件夹类型
     * @param fileName 文件名称
     * @return 文件的字节数组
     */
    public static byte[] readExternalStoragePrivate(Context context,String type,String fileName){
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        if(isExternalStorageUseful()){
           try {
               File parentFile=context.getExternalFilesDir(type);
               File file=new File(parentFile,fileName);
               FileInputStream inputStream=new FileInputStream(file);
               int temp=0;
               byte[] buff=new byte[1024];
               while((temp=inputStream.read(buff))!=-1){
                   outputStream.write(buff,0,temp);
               }
               outputStream.close();
           }catch (Exception e){
               e.printStackTrace();
           }
        }
        return outputStream.toByteArray();
    }

    /**
     * 向sdcard根目录写入数据
     * @param fileName 文件名臣
     * @param content 文件内容
     * @return 是否写入成功
     */
    public static boolean writeExternalStorageRoot(String fileName,byte[] content){
        boolean bl=false;
        if(isExternalStorageUseful()){
            try {
                //getExternalStorageDirectory()获取sdcard根目录file对象
                File parentFile=Environment.getExternalStorageDirectory();
                File file=new File(parentFile,fileName);
                FileOutputStream outputStream=new FileOutputStream(file);
                outputStream.write(content);
                outputStream.close();
                bl=true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return bl;
    }

    /**
     * 从sdcard中读取数据
     * @param fileName  文件名称
     * @return  读取的字节数组
     */
    public static byte[] readExternalStorageRoot(String fileName){
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        if(isExternalStorageUseful()){
            try {
                File parentFile=Environment.getExternalStorageDirectory();
                File file=new File(parentFile,fileName);
                FileInputStream inputStream=new FileInputStream(file);
                int temp=0;
                byte[] buff=new byte[1024];
                while((temp=inputStream.read(buff))!=-1){
                    outputStream.write(buff,0,temp);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return outputStream.toByteArray();
    }

    /**
     * 获取当前指定file的总空间
     * @param file
     * @return
     *
     * 字节 byte  b  1byte＝8bit
     *
     * 1kb＝1024b
     * 兆字节
     * 1Mb=1024KB
     * 吉字节
     * 1GB=1024MB
     */
    public static long getTotalSpace(File file){
        long size=0;
        if(Build.VERSION.SDK_INT>18){
            size=file.getTotalSpace();//获取file路径下的总空间
        }else{
            StatFs sf=new StatFs(file.getAbsolutePath());
            size=sf.getBlockCount()*sf.getBlockSize();
        }
        return size/1024/1024;
    }

    /**
     * 获取file对象的可用空间
     * @param file
     * @return
     */
    public static long getFreeSpace(File file){
        long size=0;
        if(Build.VERSION.SDK_INT>18){
            size=file.getFreeSpace();//获取file路径下的可用空间
        }else{
            StatFs sf=new StatFs(file.getAbsolutePath());
            size=sf.getBlockCount()*sf.getBlockSize();
        }
        return size/1024/1024;
    }

    /**
     *  根据指定file类对象删除文件
     * @param file
     * @return
     */
    public static boolean deleteFile(File file){
        boolean bl=false;
        if(file!=null && file.exists()){
            bl=file.delete();
        }
        return bl;
    }

}













