package com.r2dbc.model.vo;

import java.lang.management.ManagementFactory;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/***
 * UUID生成工具
 * @author admin
 *
 */
public abstract class UuidUtils {
    private static final AtomicInteger seq = new AtomicInteger();
    private static final AtomicInteger curtime = new AtomicInteger();

    /***
     * 为保证ID生成分布式全局唯一，ID采用[服务器代码2字节][固定1字节（0）][进程代码2字节][时间4字节][序列2字节]共计11字节
     * 服务器代码通过读取环境变量HOST_ID得到
     * 进程代码需要获取当前进程pid
     * 序列为本进程内时间秒相同时的自增序号。当前序号只用保存在内存中
     * @return Base58-UUID
     */
    public static String base58Uuid() {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        String pid = name.split("@")[0];
        Properties properties = System.getProperties();
        String host = properties.getProperty("RISKEYS_HOST", "O");
        byte[] b = host.getBytes();

        /**
         * 1 byte = 1字节 = 8bit 可表达 2的八次方个数字
         * 1 short= 2字节 = 16bit 可表达2的十六次方个数字
         * 1 int = 4字节 = 32bit 可表达2的32次方
         */
        ByteBuffer buffer = ByteBuffer.allocate(11);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        if(b.length >= 2){
            buffer.put(0, b[0]);
            buffer.put(1, b[1]);
        } else if(b.length == 1){
            buffer.put(0, b[0]);
            buffer.put(1, (byte)0);
        }else{
            buffer.putShort(0, (short)0);
        }
        buffer.put(2, (byte)0);
        buffer.putShort(3, (short) Integer.parseInt(pid));
        int time = (int) new Date().getTime();
        if (curtime.get() != time) {
            seq.set(0);
            curtime.set(time);
        }
        buffer.putInt(5, time);
        buffer.putShort(9, (short)seq.getAndIncrement());
        return bytesToHex(buffer.array()).toUpperCase();
    }

    /**
     * 字节数组转16进制
     * @param bytes 需要转换的byte数组
     * @return  转换后的Hex字符串
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if(hex.length() < 2){
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}