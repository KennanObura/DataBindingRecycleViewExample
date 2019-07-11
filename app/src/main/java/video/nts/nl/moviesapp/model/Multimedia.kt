package video.nts.nl.moviesapp.model

import java.io.Serializable

data class Multimedia (
    var type: String? = null,
    var src: String? = null,
    var width: Float? = null,
    var height: Float? = null
): Serializable