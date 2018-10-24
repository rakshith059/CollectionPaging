package quintype.com.templatecollectionwithrx.ui.main.fragments

import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.speech_dialog_fragment.*
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.story.Story

class SpeechDialogFragment : DialogFragment() {
    companion object {
        fun newInstance(): SpeechDialogFragment {
            val speechDialogFragment = SpeechDialogFragment()
            val args = Bundle()
            speechDialogFragment.arguments = args
            return speechDialogFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater?.inflate(R.layout.speech_dialog_fragment, container, false) as View
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        speech_dialog_fragment_iv_ripple?.setImageResource(R.drawable.ic_ripple)
    }
}
