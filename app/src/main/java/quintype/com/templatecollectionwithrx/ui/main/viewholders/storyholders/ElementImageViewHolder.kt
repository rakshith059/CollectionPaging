package quintype.com.templatecollectionwithrx.ui.main.viewholders.storyholders

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import quintype.com.templatecollectionwithrx.R
import com.example.androidcore.models.story.StoryElement
import quintype.com.templatecollectionwithrx.Constants
import quintype.com.templatecollectionwithrx.utils.FragmentCallbacks
import quintype.com.templatecollectionwithrx.utils.Utilities


class ElementImageViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun create(parent: ViewGroup, mFragmentCallback: FragmentCallbacks?): ElementImageViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.story_image_element_holder, parent, false)
            return ElementImageViewHolder(view)
        }
    }

    fun bind(element: StoryElement) {
        val tvImageCaption = itemView?.findViewById<TextView>(R.id.story_image_element_holder_tv_image_caption)
        val ivImage = itemView?.findViewById<SimpleDraweeView>(R.id.story_image_element_holder_iv_image)

        val title = element.title()
        if (!TextUtils.isEmpty(title)) {
            tvImageCaption?.text = Utilities.parseHtml(title)
            tvImageCaption?.setVisibility(View.VISIBLE)
        } else {
            tvImageCaption?.setVisibility(View.GONE)
        }

        ivImage?.viewTreeObserver?.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                ivImage.viewTreeObserver?.removeOnPreDrawListener(this)
                if (element.imageMeta() != null) {
                    val aspectRatio = element.imageMeta().height * ivImage.measuredWidth
                    val newHeight = (aspectRatio / element.imageMeta().width)
                    val params = ivImage.layoutParams as LinearLayout.LayoutParams
                    params.height = newHeight
                    ivImage.layoutParams = params
                } else {
                    val params = ivImage.layoutParams as LinearLayout.LayoutParams
                    params.height = ivImage.resources?.getDimension(R.dimen.size_200dp)?.toInt() as Int
                    ivImage.layoutParams = params
                }

                val cdnHostName = Utilities.getSharedPreferences(ivImage.context, Constants.SP_CDN_IMAGE_NAME)
                val heroImageURL = cdnHostName + element.imageS3Key()

                ivImage.setImageURI(heroImageURL)

//                Glide.with(ivImage.context as Context)
//                        .load(heroImageURL)
//                        .into(ivImage)

//                imageLoader
//                        .width(imageView.getMeasuredWidth())
//                        .enableIndicator(false)
//                        .useFocalPoints(true)
//                        .using(element)
//                        .glide(imageView)
//                        .placeholder(R.drawable.placeholder)
//                        .centerCrop()
//                        .into(imageView)

                return true
            }
        })

        //Determining the position of this image element. Used by ImagePreviewActivity to
        //determine where to point the ViewPager when the activity starts.
//        if (imageElements.contains(element))
//            position = imageElements.indexOf(element)
    }
}
