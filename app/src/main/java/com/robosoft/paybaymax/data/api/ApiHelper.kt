import com.robosoft.paybaymax.utils.Constants

class ApiHelper(private val apiInterface: ApiInterface) {

     suspend fun getData() = apiInterface.getData(Constants.ACCESS_KEY)
}