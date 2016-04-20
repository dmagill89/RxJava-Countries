import java.util.List;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by david.magill on 4/20/16.
 */
public class Demo {

    public static void main(String[] args) {
        Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl("https://restcountries.eu/rest/v1/")
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                        .build();


        CountryService service = retrofit.create(CountryService.class);

        Observable<List<Country>> call = service.getCountries();

    }
}
