import com.robosoft.paybaymax.data.model.CurrencyData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {


    @GET("live?")
    suspend fun getData(@Query("access_key") accessKey: String): CurrencyData

}