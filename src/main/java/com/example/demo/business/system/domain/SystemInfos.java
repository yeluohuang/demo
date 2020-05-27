package com.example.demo.business.system.domain;

/**
 * @author zhushj3
 * @description 系统的信息
 * @date 2020/05/22
 **/
public class SystemInfos {
    private JvmInfos jvmParam;
    private String operateSystem;
    private String version;
    private long avaiable;  // 单位MB
    private long swap;  // 单位MB
    private long total;  // 单位MB
    private int threadCount;
    private int processCount;

    public JvmInfos getJvmParam() {
        return jvmParam;
    }

    public void setJvmParam(JvmInfos jvmParam) {
        this.jvmParam = jvmParam;
    }

    public String getOperateSystem() {
        return operateSystem;
    }

    public void setOperateSystem(String operateSystem) {
        this.operateSystem = operateSystem;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public long getAvaiable() {
        return avaiable;
    }

    public void setAvaiable(long avaiable) {
        this.avaiable = avaiable;
    }

    public long getSwap() {
        return swap;
    }

    public void setSwap(long swap) {
        this.swap = swap;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    public int getProcessCount() {
        return processCount;
    }

    public void setProcessCount(int processCount) {
        this.processCount = processCount;
    }

    @Override
    public String toString() {
        return "SystemInfos{" +
                "jvmParam=" + jvmParam +
                ", operateSystem='" + operateSystem + '\'' +
                ", version='" + version + '\'' +
                ", avaiable=" + avaiable +
                ", swap=" + swap +
                ", total=" + total +
                ", threadCount=" + threadCount +
                ", processCount=" + processCount +
                '}';
    }
}
