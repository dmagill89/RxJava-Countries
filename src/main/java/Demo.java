import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by david.magill on 4/20/16.
 */
public class Demo {

    public static void main(String[] args) {
        Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl("http://restcountries.eu/rest/v1/")
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                        .build();


        CountryService service = retrofit.create(CountryService.class);

        System.out.println("Main thread: " + Thread.currentThread().getId());
        Observable<Country[]> call = service.getCountry("germany");
        call
//            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.newThread())
            .subscribe(new Subscriber<Country[]>() {
                public void onCompleted() {
                    System.out.println("Thread finished: " + Thread.currentThread().getId());
                }

                public void onError(Throwable throwable) {
                    System.out.println(throwable.getMessage());
                }

                public void onNext(Country[] countries) {
                    System.out.println("received: " + countries[0].name + " on thread " + Thread.currentThread().getId());
                    String borders[] = countries[0].borders;
                    for(int i = 0; i < borders.length; i++) {
                        System.out.println("Border country :" + i + " " + borders[i] + " still on thread: " + Thread.currentThread().getId());
                    }
                }
            });

        System.out.println("Finished main :" + Thread.currentThread().getId());

    }
}
