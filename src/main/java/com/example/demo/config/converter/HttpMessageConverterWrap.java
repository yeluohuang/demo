package com.example.demo.config.converter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

import java.nio.charset.Charset;

/**
 * @description 定义一个新的messageConverter对requestBody和@responseBody的数据进行转换
 * 如果需要开启，则声明@Configuration
 * @author zhushj3
 * @date 2020/05/22
 */

public class HttpMessageConverterWrap {
    // 引入fastjson解析json，不使用默认的jackson
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        // 1、定义一个convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 2、添加fastjson的配置信息
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        SerializerFeature[] serializerFeatures = new SerializerFeature[]{
                //    输出key是包含双引号
                //    SerializerFeature.QuoteFieldNames,
                //    是否输出为null的字段,若为null 则显示该字段
                //    数值字段如果为null，则输出为0
                //    SerializerFeature.WriteNullNumberAsZero,
                //    List字段如果为null,输出为[],而非null
                //    SerializerFeature.WriteNullListAsEmpty,
                //    字符类型字段如果为null,输出为"",而非null
                //    SerializerFeature.WriteNullStringAsEmpty,
                //    Boolean字段如果为null,输出为false,而非null
                //    SerializerFeature.WriteNullBooleanAsFalse,
                //    Date的日期转换器
                SerializerFeature.WriteDateUseDateFormat, // JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
                //    循环引用
                SerializerFeature.DisableCircularReferenceDetect,
        };
        fastJsonConfig.setSerializerFeatures(serializerFeatures);
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));
        // 3、在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        // 4、将convert添加到converters中
        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);
    }
}
