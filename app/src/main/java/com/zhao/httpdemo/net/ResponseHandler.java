package com.zhao.httpdemo.net;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zhao on 2016/9/23.
 */

public class ResponseHandler {
    /**
     * 对结果进行预处理
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<BaseResponseEntity<T>, T> handleResult() {

        return new Observable.Transformer<BaseResponseEntity<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseResponseEntity<T>> tObservable) {
                return tObservable.flatMap(new Func1<BaseResponseEntity<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(BaseResponseEntity<T> result) {
                        if (result.success()) {
                            return createData(result.results);
                        } else {
                            return Observable.error(new ApiException(result.msg));
                        }
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };

    }

    /**
     * 创建成功的数据
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });

    }
}
