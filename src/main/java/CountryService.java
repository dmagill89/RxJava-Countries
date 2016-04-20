import java.util.List;

import retrofit2.http.Path;
import rx.*;
import retrofit2.http.GET;

/**
 * Created by david.magill on 4/20/16.
 */
public interface CountryService {

    @GET("name/{country}")
    Observable<Country[]> getCountries(@Path("country") String country);
}
