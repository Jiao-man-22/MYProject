package com.jiao.testproject.testproject.interview.base.design_pattern;

//  子类 继承 父类可以 调用 父类的 非 抽象方法 ，还必须重写 弗雷的 抽象方法
public class AppleFactory extends FactoryPattern {

    @Override
    public void createProduct() {
        System.out.println("卖出apple");
        super.ok();
    }

    public static void main(String[] args) {
        new AppleFactory().createProduct();
    }
}
