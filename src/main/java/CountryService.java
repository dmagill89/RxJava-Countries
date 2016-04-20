import java.util.List;
import rx.*;
import retrofit2.http.GET;

/**
 * Created by david.magill on 4/20/16.
 */
public interface CountryService {

    @GET("/all")
    Observable<List<Country>> getCountries();
}
