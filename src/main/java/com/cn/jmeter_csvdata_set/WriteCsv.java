package com.cn.jmeter_csvdata_set;

import java.io.*;

/**
 * @ClassName: 1
 * @Author: huangzhijuan
 * @Description: TODO
 * @Date: Create in 1 1
 * @Version 1.0
 */
public class WriteCsv {
    //多媒体中心压测数据
    public void mediaCenter(){
        //第一步：设置输出的文件路径
        //如果该目录下不存在该文件，则文件会被创建到指定目录下。如果该目录有同名文件，那么该文件将被覆盖。
        File writeFile = new File("/Users/huangzhijuan/Documents/测试工作/接口测试/5.压测/多媒体中心/1v1/多媒体中心uid和peer_id.csv");
//        File writeFile = new File("/Users/huangzhijuan/Documents/测试工作/接口测试/5.压测/增值付费中心/recipient.csv");

        try {
            //第二步：通过BufferedReader类创建一个使用默认大小输出缓冲区的缓冲字符输出流
            BufferedWriter writeText = new BufferedWriter(new FileWriter(writeFile));

            int j=120100;
            int num =40000;//线程数*循环次数
            //第三步：将文档的下一行数据赋值给lineData，并判断是否为空，若不为空则输出
            for (int i = j; i < j+num; i++) {
                writeText.newLine();    //换行
                //调用write的方法将字符串写到流中
                writeText.write(i + "," + (100000 + i));
                //recipient
//                writeText.write(i + "," + (100000 + i)+ "," + (200000 + i));
            }

            //使用缓冲区的刷新方法将数据刷到目的地中
            writeText.flush();
            //关闭缓冲区，缓冲区没有调用系统底层资源，真正调用底层资源的是FileWriter对象，缓冲区仅仅是一个提高效率的作用
            //因此，此处的close()方法关闭的是被缓存的流对象
            writeText.close();
        } catch (FileNotFoundException e) {
            System.out.println("没有找到指定文件");
        } catch (IOException e) {
            System.out.println("文件读写出错");
        }

    }

    //im中心压测数据
    public void imCenter(){
        //第一步：设置输出的文件路径
        //如果该目录下不存在该文件，则文件会被创建到指定目录下。如果该目录有同名文件，那么该文件将被覆盖。
//        File writeFile = new File("/Users/huangzhijuan/Documents/测试工作/接口测试/5.压测/IM/IM二期/im发送者uid.csv");
//        File writeFile = new File("/Users/huangzhijuan/Documents/测试工作/接口测试/5.压测/IM/IM二期/request_id.csv");
          File writeFile = new File("/Users/huangzhijuan/Documents/测试工作/接口测试/5.压测/金融中台/虚拟币服务/to_uid2.csv");
        try {
            //第二步：通过BufferedReader类创建一个使用默认大小输出缓冲区的缓冲字符输出流
            BufferedWriter writeText = new BufferedWriter(new FileWriter(writeFile));

            int j=80001;//1220000
            int num = 40000;//线程数*循环次数
            //第三步：将文档的下一行数据赋值给lineData，并判断是否为空，若不为空则输出
            for (int i = j; i < j+num; i++) {
                writeText.newLine();    //换行
                //调用write的方法将字符串写到流中（双列数据）
//                writeText.write(i + ","+(i+"00"));
                //单列数据
                writeText.write(i + ",");
            }

            //使用缓冲区的刷新方法将数据刷到目的地中
            writeText.flush();
            //关闭缓冲区，缓冲区没有调用系统底层资源，真正调用底层资源的是FileWriter对象，缓冲区仅仅是一个提高效率的作用
            //因此，此处的close()方法关闭的是被缓存的流对象
            writeText.close();
        } catch (FileNotFoundException e) {
            System.out.println("没有找到指定文件");
        } catch (IOException e) {
            System.out.println("文件读写出错");
        }

    }

    //用户在线状态压测数据
    public void userCenter(){
        //第一步：设置输出的文件路径
        //如果该目录下不存在该文件，则文件会被创建到指定目录下。如果该目录有同名文件，那么该文件将被覆盖。
        File writeFile = new File("/Users/huangzhijuan/Documents/测试工作/接口测试/5.压测/用户在线服务/smid.csv");


        try {
            //第二步：通过BufferedReader类创建一个使用默认大小输出缓冲区的缓冲字符输出流
            BufferedWriter writeText = new BufferedWriter(new FileWriter(writeFile));

            int j=10000;
            int num =300000;//线程数*循环次数
            //第三步：将文档的下一行数据赋值给lineData，并判断是否为空，若不为空则输出
            for (int i = j; i < j+num; i++) {
                writeText.newLine();    //换行
                //调用write的方法将字符串写到流中
                writeText.write( i+","+("simd"+900000 + i)+ "," );
            }

            //使用缓冲区的刷新方法将数据刷到目的地中
            writeText.flush();
            //关闭缓冲区，缓冲区没有调用系统底层资源，真正调用底层资源的是FileWriter对象，缓冲区仅仅是一个提高效率的作用
            //因此，此处的close()方法关闭的是被缓存的流对象
            writeText.close();
        } catch (FileNotFoundException e) {
            System.out.println("没有找到指定文件");
        } catch (IOException e) {
            System.out.println("文件读写出错");
        }

    }




    public static void main(String[] args) throws Exception {
        WriteCsv writeCsv = new WriteCsv();
//        writeCsv.mediaCenter();
        writeCsv.imCenter();
//        writeCsv.userCenter();





    }
}
