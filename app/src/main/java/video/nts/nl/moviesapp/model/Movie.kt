package video.nts.nl.moviesapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var display_title: String? = null,
    var headline: String? = null,
    var summary_short: String? = null,
    var publication_date: String? = null,
    var opening_date: String? = null,
    var mpaa_rating: String? = null,
    var critics_pick: Int = 0,
    val multimedia: Multimedia

): Parcelable