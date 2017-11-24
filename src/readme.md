4.借鉴Spring AOP风格，开发基于切面注解的AOP框架
    1.定义切面注解--annotation包下的Aspect注解
    2.搭建代理框架--proxy包下的Proxy*类,使用链式代理,也就说可将多个代理通过一条链子串起来，一个一个执行
      执行顺序取决于添加到链上的先后顺序
    3.创建代理对象--ProxyManager，输入一个目标类和一组Proxy接口实现，输出一个代理对象
    4.创建切面代理--在切面类中，需要在目标方法被调用的前后增加相应的逻辑,写一个抽象类，提供一个模板方法，
      并在该抽象类的具体实现中扩展相应的抽象方法
    5.加载AOP框架--编写AopHelper，然后添加到HelperLoader类中.在AopHelper中需要获取所有的目标类及其被
      拦截的切面类实例，并通过ProxyManager#createProxy方法创建代理对象，最后将其放入Bean Map中