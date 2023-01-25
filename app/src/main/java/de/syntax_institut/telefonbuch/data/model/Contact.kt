package de.syntax_institut.telefonbuch.data.model

import android.os.Parcelable
import android.widget.Button
import android.widget.ImageView
import de.syntax_institut.telefonbuch.R
import kotlinx.parcelize.Parcelize

/**
 * Diese Klasse stellt einen Listeneintrag dar
 */

@Parcelize
data class Contact(
    val name: String,
    val number: String,
    val image: Int = R.drawable.avatar
    ) : Parcelable

