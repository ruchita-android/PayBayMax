import com.robosoft.paybaymax.data.model.Currencies
import com.robosoft.paybaymax.data.model.CurrencyData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("list?")
    suspend fun getCurriencies(@Query("access_key") accessKey: String): Currencies

    @GET("live?")
    suspend fun getExchangeRates(@Query("access_key") accessKey: String,
                                 @Query("source") currency: String,
                                 @Query("format") format: String): CurrencyData

}

//@Query("source") source: String,
//@Query("format") format: String