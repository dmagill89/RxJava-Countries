import java.util.List;
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

        Observable<Country[]> call = service.getCountries("germany");
        call.subscribe(new Subscriber<Country[]>() {
            public void onCompleted() {

            }

            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            public void onNext(Country[] countries) {
                System.out.println(countries[0].name);
            }
        });

    }
}
