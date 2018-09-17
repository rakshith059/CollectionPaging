package quintype.com.templatecollectionwithrx

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

//        activity_web.loadUrl("https://swarajyamag.com/")
        activity_web.loadUrl("https://www.samachara.com")
    }
}
