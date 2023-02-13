
import android.content.Context
import android.widget.Toast
import es.dmoral.toasty.Toasty

object MessageUtil{
 fun showToast(context: Context, option: Int, message : String, timeup: Int){
     val lenght: Int = if(timeup==0) Toast.LENGTH_SHORT else Toast.LENGTH_LONG
     when(option){
         0 -> {
             Toasty.normal(context, message).show()
         }
         1 -> {
             Toasty.success(context, message, lenght, true).show()
         }
         2 -> {
             Toasty.info(context, message, lenght, true).show()
         }
         3 -> {
             Toasty.error(context, message, lenght, true).show()
         }
         4 -> {
             Toasty.warning(context, message, lenght, true).show()
         }
     }
 }
}
