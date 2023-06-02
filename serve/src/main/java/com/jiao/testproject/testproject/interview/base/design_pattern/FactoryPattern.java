package com.jiao.testproject.testproject.interview.base.design_pattern;

import org.apache.poi.ss.formula.functions.T;


// 抽象类中 可以写 抽象方法 和 具体方法
public abstract class  FactoryPattern {

    public abstract void createProduct();

    public void ok(){
        System.out.println("OK");
    };

}
