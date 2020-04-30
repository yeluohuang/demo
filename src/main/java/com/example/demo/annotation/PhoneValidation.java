package com.example.demo.annotation;

import com.example.demo.annotation.declare.Phone;
import com.example.demo.util.ValidateUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 身份证号校验注解的实现类
 * @author zhushj3
 * @date 2020/04/29
 */
public class PhoneValidation implements ConstraintValidator<Phone, String> {
    private boolean required = false;
    // 定义的手机号验证正则表达式
    private Pattern pattern = Pattern.compile("1(([38]\\d)|(5[^4&&\\d])|(4[579])|(7[0135678]))\\d{8}");
   // 添加注解时required为 true
   @Override
    public void initialize(Phone constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    // 只需要重写此处即可:true表示通过
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(required) {
            return pattern.matcher(value).matches();
        }else {
            if(ValidateUtil.isEmpty(value)) {
                return false;
            }else{
                return pattern.matcher(value).matches();
            }
        }
    }
}
