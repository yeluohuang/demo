package com.example.demo.business.system.service.serviceImpl;

import com.example.demo.business.system.domain.JvmInfos;
import com.example.demo.business.system.domain.SystemInfos;
import com.example.demo.business.system.service.SystemService;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;
import oshi.software.os.OperatingSystemVersion;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;

/**
 * @author zhushj3
 * @description 系统接口实现
 * @date 2020/05/22
 **/
@Service
public class SystemServiceImpl implements SystemService {
    /**
     * 罗列当前系统和虚拟机的相关信息
     * @return 系统和虚拟机的信息
     */
    @Override
    public SystemInfos listSystemInfos() {
        SystemInfos result = new SystemInfos();
        MemoryUsage heapMemoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
        MemoryUsage noHeapMemoryUsage = ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage();
        // 堆信息
        JvmInfos jinfo = new JvmInfos();
        jinfo.setHeapInit(heapMemoryUsage.getInit());
        jinfo.setHeapMax(heapMemoryUsage.getMax());
        jinfo.setHeapUsed(heapMemoryUsage.getUsed());
        // 非堆信息
        jinfo.setNoHeapInit(noHeapMemoryUsage.getInit());
        jinfo.setNoHeapMax(noHeapMemoryUsage.getMax());
        jinfo.setNoHeapUsed(noHeapMemoryUsage.getUsed());
        result.setJvmParam(jinfo);
        // 系统参数
        SystemInfo info = new SystemInfo();
        OperatingSystem temp = info.getOperatingSystem();
        HardwareAbstractionLayer layer = info.getHardware();
        OperatingSystemVersion version = temp.getVersion();
        result.setOperateSystem(info.getOperatingSystem().getFamily());
        result.setVersion(version.getVersion());
        result.setProcessCount(temp.getProcessCount());
        result.setThreadCount(temp.getThreadCount());
        result.setAvaiable(layer.getMemory().getAvailable()/1024/1024);
        result.setSwap(layer.getMemory().getSwapTotal()/1024/1024);
        result.setTotal(layer.getMemory().getTotal()/1024/1024);
        return result;
    }
}
