import com.robosoft.paybaymax.utils.Constants

class ApiHelper(private val apiInterface: ApiInterface) {


     suspend fun getCurriencies() = apiInterface.getCurriencies(Constants.ACCESS_KEY)
     suspend fun getExchangeRates(currency: String) = apiInterface.getExchangeRates(Constants.ACCESS_KEY, currency,Constants.FORMAT)

}