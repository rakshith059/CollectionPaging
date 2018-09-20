package quintype.com.templatecollectionwithrx.ui.main.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_web.*
import quintype.com.templatecollectionwithrx.R

class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

//        activity_web.loadUrl("https://swarajyamag.com/")
        activity_web.loadUrl("https://www.samachara.com")
    }
}
