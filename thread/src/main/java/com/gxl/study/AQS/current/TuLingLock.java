package com.gxl.study.AQS.current;

import com.gxl.study.AQS.util.UnsafeInstance;
import sun.misc.Unsafe;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * 模范ReentrantLock
 * @author gouxi
 * @description 自定义公平锁AQS(AbstractQueuedSynchronizer )，核心是：cas, 自旋, LockSupport
 * @since 2020/5/11
 */
public class TuLingLock {

    //锁的状态
    private volatile int state = 0;
    //定义持有锁的线程
    private Thread lockHolder;
    //定义存放cas失败的线程队列
    private ConcurrentLinkedQueue<Thread> queue = new ConcurrentLinkedQueue<>();

   private static final Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();
    private static final long stateOffset;

    static{
        try {
            stateOffset = unsafe.objectFieldOffset(TuLingLock.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            throw new Error();
        }
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Thread getLockHolder() {
        return lockHolder;
    }

    public void setLockHolder(Thread lockHolder) {
        this.lockHolder = lockHolder;
    }

    /**
     * 加锁
     */
    public void lock(){
        Thread thread = Thread.currentThread();
        //队列是空的才直接抢，不然就先去排队
        if(aquire(thread)){
            return;
        }
        queue.add(thread);
        //参与加锁失败则自旋
        for(;;){
            //阻塞线程
            LockSupport.park();
            //再次尝试获取，已经是排队的线程了
            /*
            队列的获取：
            poll：将首个元素从队列中弹出，如果队列是空的，就返回null
            peek：查看首个元素，不会移除首个元素，如果队列是空的就返回null
            element：查看首个元素，不会移除首个元素，如果队列是空的就抛出异常NoSuchElementException
            */
            if(queue.peek()==thread && aquire(thread)){
                //移出队列，
                queue.poll();
                return;
            }
        }
    }

    /**
     * 解锁
     */
    public void unlock(){
        //反向cas
        Thread thread = Thread.currentThread();
        //判断是否持有者
        if(thread != getLockHolder()){
            //抛错
            throw new RuntimeException("lockHolder is not the current thread");
        }
        //还原
        if(compareAndSwapState(1,0)){
            setLockHolder(null);
            //唤醒队列中下一个,如果还有的话;
            Thread first = queue.peek();
            if(first != null){
                LockSupport.unpark(first);
            }
        }
    }

    /**
     * 原子操作
     * @param expect 原本的值
     * @param update 更新之后的值
     * @return
     */
    public final boolean compareAndSwapState(int expect, int update){
        return unsafe.compareAndSwapInt(this,stateOffset,expect,update);
    }

    /**
     * 线程加锁或者排队
     * @param thread
     * @return
     */
    private boolean aquire(Thread thread){
        //说明没线程占用
        if(getState() ==0){
            //尝试去占用
            if((queue.size()==0 || queue.peek()==thread)&& compareAndSwapState(0,1)){
                //成功则记录状态和持有者
                setLockHolder(thread);
                return true;
            }
            //如果放到外面 就可以保证这个线程稳定拿到再结束，而不是重新排队
//            else{
//                //失败的加入队列,加到最后
//                //问题？如果这个线程才排到第一，尝试获取的时候失败了，意思是直接又只有重新排队？仔细想想好像不会出现这种情况了哦
//                queue.add(thread);
//            }
        }
        return false;
    }

}
