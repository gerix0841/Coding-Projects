import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.*;

public class CPMain {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Complex> queue = new ArrayBlockingQueue<>(128);
        ExecutorService pool = Executors.newFixedThreadPool(6);
        ConcurrentHashMap realMap = new ConcurrentHashMap<Integer,Double>();
        ConcurrentHashMap imagMap = new ConcurrentHashMap<Integer,Double>();
        ArrayList<Future<?>> futures = new ArrayList<Future<?>>();
        Complex badTotal = new Complex(0,0);

        try{
            pool.awaitTermination(5, TimeUnit.MILLISECONDS);
            pool.shutdown();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < 2000000){
                        queue.add(new Complex(ThreadLocalRandom.current().nextDouble(),ThreadLocalRandom.current().nextDouble()));
                }
                queue.add(new Complex(Double.MAX_VALUE,Double.MAX_VALUE));
                queue.add(new Complex(Double.MAX_VALUE,Double.MAX_VALUE));
                queue.add(new Complex(Double.MAX_VALUE,Double.MAX_VALUE));
                queue.add(new Complex(Double.MAX_VALUE,Double.MAX_VALUE));
            }
        };

        long startTime = System.nanoTime();

        runnable.run();

        Thread thread1 = new Thread(() -> {
            while(true){
                try {
                    Complex complex = queue.take();
                    if(complex.getReal() == Double.MAX_VALUE || complex.getImag() == Double.MAX_VALUE){
                        break;
                    }
                    else{
                        Complex complex2 = queue.take();
                        complex.multiply(complex2);

                        badTotal.set(new Complex(complex.getReal(),complex.getImag()));

                        realMap.put(Math.getExponent(complex.getReal()),complex.getReal());
                        imagMap.put(Math.getExponent(complex.getImag()),complex.getImag());

                        double a = Math.getExponent(complex.getReal());
                        double b = Math.getExponent(complex.getImag());

                        synchronized(realMap){
                            while(realMap.containsKey(a)){
                                double old_a = a;
                                //a = a + realMap.get(a);
                                a = Math.getExponent(a);
                                if(old_a != a){
                                    realMap.remove(old_a);
                                }
                            }
                        }

                        synchronized(imagMap){
                            while(imagMap.containsKey(b)){
                                double old_b = b;
                                //b = b + imagMap.get(b);
                                b = Math.getExponent(b);
                                if(old_b != b){
                                    imagMap.remove(old_b);
                                }
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while(true){
                try {
                    Complex complex = queue.take();
                    if(complex.getReal() == Double.MAX_VALUE || complex.getImag() == Double.MAX_VALUE){
                        break;
                    }
                    else{
                        Complex complex2 = queue.take();
                        complex.multiply(complex2);

                        realMap.put(Math.getExponent(complex.getReal()),complex.getReal());
                        imagMap.put(Math.getExponent(complex.getImag()),complex.getImag());

                        double a = Math.getExponent(complex.getReal());
                        double b = Math.getExponent(complex.getImag());

                        synchronized(realMap){
                            while(realMap.containsKey(a)){
                                double old_a = a;
                                //a = a + realMap.get(a);
                                a = Math.getExponent(a);
                                if(old_a != a){
                                    realMap.remove(old_a);
                                }
                            }
                        }

                        synchronized(imagMap){
                            while(imagMap.containsKey(b)){
                                double old_b = b;
                                //b = b + imagMap.get(b);
                                b = Math.getExponent(b);
                                if(old_b != b){
                                    imagMap.remove(old_b);
                                }
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread thread3 = new Thread(() -> {
            while(true){
                try {
                    Complex complex = queue.take();
                    if(complex.getReal() == Double.MAX_VALUE || complex.getImag() == Double.MAX_VALUE){
                        break;
                    }
                    else{
                        Complex complex2 = queue.take();
                        complex.multiply(complex2);

                        realMap.put(Math.getExponent(complex.getReal()),complex.getReal());
                        imagMap.put(Math.getExponent(complex.getImag()),complex.getImag());

                        double a = Math.getExponent(complex.getReal());
                        double b = Math.getExponent(complex.getImag());

                        synchronized(realMap){
                            while(realMap.containsKey(a)){
                                double old_a = a;
                                //a = a + realMap.get(a);
                                a = Math.getExponent(a);
                                if(old_a != a){
                                    realMap.remove(old_a);
                                }
                            }
                        }

                        synchronized(imagMap){
                            while(imagMap.containsKey(b)){
                                double old_b = b;
                                //b = b + imagMap.get(b);
                                b = Math.getExponent(b);
                                if(old_b != b){
                                    imagMap.remove(old_b);
                                }
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread thread4 = new Thread(() -> {
            while(true){
                try {
                    Complex complex = queue.take();
                    if(complex.getReal() == Double.MAX_VALUE || complex.getImag() == Double.MAX_VALUE){
                        break;
                    }
                    else{
                        Complex complex2 = queue.take();
                        complex.multiply(complex2);

                        realMap.put(Math.getExponent(complex.getReal()),complex.getReal());
                        imagMap.put(Math.getExponent(complex.getImag()),complex.getImag());

                        double a = Math.getExponent(complex.getReal());
                        double b = Math.getExponent(complex.getImag());

                        synchronized(realMap){
                            while(realMap.containsKey(a)){
                                double old_a = a;
                                //a = a + realMap.get(a);
                                a = Math.getExponent(a);
                                if(old_a != a){
                                    realMap.remove(old_a);
                                }
                            }
                        }

                        synchronized(imagMap){
                            while(imagMap.containsKey(b)){
                                double old_b = b;
                                //b = b + imagMap.get(b);
                                b = Math.getExponent(b);
                                if(old_b != b){
                                    imagMap.remove(old_b);
                                }
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();

        futures.add(pool.submit(runnable));
        futures.add(pool.submit(runnable));
        futures.add(pool.submit(runnable));
        futures.add(pool.submit(runnable));
        futures.add(pool.submit(runnable));

        final boolean[] doneAll = {false};
        final int[] count = {0};

        Thread threadHelp = new Thread(() -> {
            while (!doneAll[0]) {
                for (int i = 0; i < futures.size(); i++) {
                    if (futures.get(i).isDone()) {
                        count[0]++;
                    }
                }
                if (count[0] == 5) {
                    doneAll[0] = true;
                }

                System.out.println("Queue Size: " + String.valueOf(queue.size()) + "Active Threads: " + String.valueOf(Thread.currentThread().getThreadGroup().activeCount()) + "Finished: " + String.valueOf(count[0]));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.println("Final Sum In Thread: (" +"i)");
        });

        long finishTime = System.nanoTime();
        System.out.println("Total Time: " + String.valueOf(finishTime-startTime) + "ns");
        System.out.println("Bad sum: (" + badTotal.getReal() + badTotal.getImag() + ")");
        //System.out.println("lative accuracy: " Math.abs(total-badTotal)/Math.abs(total));



    }
}
