package com.amar.lockapp.helper

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.widget.TextView

class Utils {

    companion object {

        fun setHighLightedText(tv: TextView, textToHighlight: String) {
            val tvt = tv.text.toString()
            var ofe = tvt.indexOf(textToHighlight, 0)
            val wordToSpan: Spannable = SpannableString(tv.text)
            var ofs = 0
            while (ofs < tvt.length && ofe != -1) {
                ofe = tvt.indexOf(textToHighlight, ofs)
                if (ofe == -1) break else {
                    // set color here
                    wordToSpan.setSpan(
                        BackgroundColorSpan(Color.YELLOW),
                        ofe,
                        ofe + textToHighlight.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    tv.setText(wordToSpan, TextView.BufferType.SPANNABLE)
                }
                ofs = ofe + 1
            }
        }

    }
}