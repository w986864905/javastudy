package AopStudy;

/**
 * @author 王品峰
 * @DateTime 2020/8/4 10:55
 * @Description
 */
public interface StaticProxy {
    void save();
    void find();
}
//目标对象
class UserDao implements StaticProxy{
    @Override
    public void save() {
        System.out.println("模拟：保存用户！");
    }
    @Override
    public void find() {
        System.out.println("模拟：查询用户");
    }
}
/**
 静态代理
 特点：
 1. 目标对象必须要实现接口
 2. 代理对象，要实现与目标对象一样的接口
 */
class UserDaoProxy implements StaticProxy{
    // 代理对象，需要维护一个目标对象
    private StaticProxy target = new UserDao();
    @Override
    public void save() {
        System.out.println("代理操作： 开启事务...");
        target.save();   // 执行目标对象的方法
        System.out.println("代理操作：提交事务...");
    }
    @Override
    public void find() {
        target.find();
    }
}
