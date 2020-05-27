package com.example.demo.business.system.domain;

/**
 * @author zhushj3
 * @description Jvm的信息
 * @date 2020/05/22
 **/
public class JvmInfos {
    private Long heapInit;    // 初始堆大小
    private Long heapUsed;    // 已使用的堆大小
    private Long heapMax;     // 堆的最大值
    private Long noHeapInit;  // 初始堆大小
    private Long noHeapUsed;  // 已使用的堆大小
    private Long noHeapMax;   // 堆的最大值
    private String heapDes;     // 堆的详细信息

    public Long getHeapInit() {
        return heapInit;
    }

    public void setHeapInit(Long heapInit) {
        this.heapInit = heapInit;
    }

    public Long getHeapUsed() {
        return heapUsed;
    }

    public void setHeapUsed(Long heapUsed) {
        this.heapUsed = heapUsed;
    }

    public Long getHeapMax() {
        return heapMax;
    }

    public void setHeapMax(Long heapMax) {
        this.heapMax = heapMax;
    }

    public Long getNoHeapInit() {
        return noHeapInit;
    }

    public void setNoHeapInit(Long noHeapInit) {
        this.noHeapInit = noHeapInit;
    }

    public Long getNoHeapUsed() {
        return noHeapUsed;
    }

    public void setNoHeapUsed(Long noHeapUsed) {
        this.noHeapUsed = noHeapUsed;
    }

    public Long getNoHeapMax() {
        return noHeapMax;
    }

    public void setNoHeapMax(Long noHeapMax) {
        this.noHeapMax = noHeapMax;
    }

    public String getHeapDes() {
        return heapDes;
    }

    public void setHeapDes(String heapDes) {
        this.heapDes = heapDes;
    }

    @Override
    public String toString() {
        return "JvmInfo{" +
                "heapInit=" + heapInit +
                ", heapUsed=" + heapUsed +
                ", heapMax=" + heapMax +
                ", noHeapInit=" + noHeapInit +
                ", noHeapUsed=" + noHeapUsed +
                ", noHeapMax=" + noHeapMax +
                ", heapDes='" + heapDes + '\'' +
                '}';
    }
}
