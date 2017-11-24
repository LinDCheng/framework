package lean.ldc.smart4jframework.proxy;

/**
 * 代理接口
 * Created by LinDunCheng on 2017/11/8.
 */
public interface Proxy {

    /**
     * 执行链式代理
     * @param proxyChain
     * @return
     * @throws Throwable
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
